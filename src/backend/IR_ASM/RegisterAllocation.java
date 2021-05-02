package backend.IR_ASM;

import RISCV.RISCVUtility.RegEdge;
import RISCV.RISCVoperand.RISCVregister.RISCVVirtualRegister;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVfunction.RISCVFunction;
import RISCV.RISCVmodule.RISCVModule;
import RISCV.RISCVinstruction.*;
import RISCV.RISCVoperand.RISCVregister.RISCVPhysicalRegister;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;
import RISCV.RISCVoperand.RISCVimmediate.RISCVStackOffset;

import java.util.*;

import static java.lang.Integer.min;
public class RegisterAllocation {
    private HashSet<RISCVRegister> preColored, initial, simplifyWorkList, freezeWorkList, spillWorkList, spilledNodes, coalescedNodes, coloredNodes, canNotSpillNodes;
    private HashSet<RegEdge> adjSet;
    private Stack<RISCVRegister> selectStack;
    private int offset;
    private HashSet<RISCVMove> workListMoves, activeMoves, coalescedMoves, constrainedMoves, frozenMoves;
    private int K;

    private HashMap<RISCVBasicBlock, HashSet<RISCVRegister>> RegUse;
    private HashMap<RISCVBasicBlock, HashSet<RISCVRegister>> RegDef;
    private HashSet<RISCVBasicBlock> visited;

    RISCVModule module;
    public RegisterAllocation (RISCVModule module) {
        this.module = module;
        preColored = new LinkedHashSet<>();
        initial = new LinkedHashSet<>();
        simplifyWorkList = new LinkedHashSet<>();
        freezeWorkList = new LinkedHashSet<>();
        spillWorkList = new LinkedHashSet<>();
        spilledNodes = new LinkedHashSet<>();
        coalescedNodes = new LinkedHashSet<>();
        coloredNodes = new LinkedHashSet<>();
        canNotSpillNodes = new LinkedHashSet<>();
        adjSet = new LinkedHashSet<>();
        workListMoves = new LinkedHashSet<>();
        activeMoves = new LinkedHashSet<>();
        coalescedMoves = new LinkedHashSet<>();
        constrainedMoves = new LinkedHashSet<>();
        frozenMoves = new LinkedHashSet<>();
        selectStack = new Stack<>();
        preColored.addAll(module.getPhysicalRegisters());
        K = module.getColors().size();
    }

    private HashSet<RISCVRegister> adjacent(RISCVRegister reg) {
        return new LinkedHashSet<>(reg.adjList) {{removeAll(selectStack); removeAll(coalescedNodes);}};
    }
    private HashSet<RISCVRegister> adjacent(RISCVRegister reg1, RISCVRegister reg2) {
        return new HashSet<>(adjacent(reg1)) {{addAll(adjacent(reg2));}};
    }
    private HashSet<RISCVMove> nodeMoves(RISCVRegister reg) {
        return new LinkedHashSet<>(workListMoves) {{ addAll(activeMoves); retainAll(reg.moveList);}};
    }
    private void enableMoves(HashSet<RISCVRegister> nodes) {
        nodes.forEach(node -> nodeMoves(node).forEach(inst -> {
            if (activeMoves.contains(inst)) {
                activeMoves.remove(inst);
                workListMoves.add(inst);
            }
        }));
    }

    private RISCVRegister getAlias(RISCVRegister reg) {
        if (coalescedNodes.contains(reg)) {
            reg.alias = getAlias(reg.alias);
            return reg.alias;
        }
        else return reg;
    }

    private boolean ok(RISCVRegister reg1, RISCVRegister reg2) {
        for (RISCVRegister adj : adjacent(reg2)) if (!(adj.degree < K || preColored.contains(adj) || adjSet.contains(new RegEdge(adj, reg1)))) return false;
        return true;
    }

    private boolean conservative(HashSet<RISCVRegister> regs) {
        int num = 0;
        for (RISCVRegister node : regs) if (node.degree >= K) ++num;
        return num < K;
    }

    void allocate(RISCVFunction function) {
        offset = 0;
        while(true) {
            initial.clear();
            simplifyWorkList.clear();
            freezeWorkList.clear();
            spillWorkList.clear();
            spilledNodes.clear();
            coalescedNodes.clear();
            coloredNodes.clear();
            selectStack.clear();
            coalescedMoves.clear();
            constrainedMoves.clear();
            frozenMoves.clear();
            activeMoves.clear();
            workListMoves.clear();
            adjSet.clear();
            function.getBlockContain().forEach(block -> {
                for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next){
                    initial.addAll(inst.Defs());
                    initial.addAll(inst.Uses());
                }
            });
            initial.removeAll(preColored);
            initial.forEach(RISCVRegister::clear);
            preColored.forEach(RISCVRegister::init);

            for (RISCVBasicBlock block : function.getBlockContain()) {
                double weight = Math.pow(10, min(block.getNext().size(), block.getPrev().size()));
                for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                    inst.Uses().forEach(reg -> reg.weight += weight);
                    if (inst.Defs().size() > 0) inst.Defs().iterator().next().weight += weight;
                }
            }
            RegUse = new LinkedHashMap<>();
            RegDef = new LinkedHashMap<>();
            visited = new LinkedHashSet<>();
            function.getBlockContain().forEach(block -> {
                HashSet<RISCVRegister> uses = new LinkedHashSet<>();
                HashSet<RISCVRegister> defs = new LinkedHashSet<>();
                for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                    HashSet<RISCVRegister> instUses = inst.Uses();
                    instUses.removeAll(defs);
                    uses.addAll(instUses);
                    defs.addAll(inst.Defs());
                }
                RegUse.put(block, uses);
                RegDef.put(block, defs);
                block.setLiveIn(new LinkedHashSet<>());
                block.setLiveOut(new LinkedHashSet<>());
            });
            Stack<RISCVBasicBlock> S = new Stack<>();
            S.push(function.getExit());
            while(!S.empty()){
                RISCVBasicBlock now = S.pop();
                if (visited.contains(now)) continue;
                visited.add(now);
                HashSet<RISCVRegister> liveOut = new HashSet<>();
                for (RISCVBasicBlock successor : now.getNext()) liveOut.addAll(successor.getLiveIn());
                HashSet<RISCVRegister> liveIn = new HashSet<>(liveOut);
                liveIn.removeAll(RegDef.get(now));
                liveIn.addAll(RegUse.get(now));
                now.addLiveOut(liveOut);
                liveIn.removeAll(now.getLiveIn());
                if (!liveIn.isEmpty()) {
                    now.addLiveIn(liveIn);
                    visited.removeAll(now.getPrev());
                }
                for (RISCVBasicBlock prev : now.getPrev()) S.push(prev);
            }
            for (RISCVBasicBlock block : function.getBlockContain()) {
                HashSet<RISCVRegister> LiveRegs = new LinkedHashSet<>(block.getLiveOut());
                for (RISCVInstruction inst = block.getTail(); inst != null; inst = inst.prev) {
                    if (inst instanceof RISCVMove) {
                        LiveRegs.removeAll(inst.Uses());
                        HashSet<RISCVRegister> mvAbout = inst.Uses();
                        mvAbout.addAll(inst.Defs());
                        for (RISCVRegister reg : mvAbout) reg.moveList.add((RISCVMove) inst);
                        workListMoves.add((RISCVMove) inst);
                    }
                    HashSet<RISCVRegister> defs = inst.Defs();
                    LiveRegs.add(module.getPhysicalRegister(0));
                    LiveRegs.addAll(defs);
                    for (RISCVRegister def : defs) {
                        for(RISCVRegister reg : LiveRegs) {
                            if (reg != def && !adjSet.contains(new RegEdge(reg, def))) {
                                adjSet.add(new RegEdge(reg, def));
                                adjSet.add(new RegEdge(def, reg));
                                if (!preColored.contains(reg)) reg.addAdj(def);
                                if (!preColored.contains(def)) def.addAdj(reg);
                            }
                        }
                    }
                    LiveRegs.removeAll(defs);
                    LiveRegs.addAll(inst.Uses());
                }
            }
            for (RISCVRegister node : initial) {
                if (node.degree >= K) spillWorkList.add(node);
                else if (!nodeMoves(node).isEmpty()) freezeWorkList.add(node);
                else simplifyWorkList.add(node);
            }
            do{
                if (!simplifyWorkList.isEmpty()) {
                    RISCVRegister reg = simplifyWorkList.iterator().next();
                    simplifyWorkList.remove(reg);
                    selectStack.push(reg);
                    for (RISCVRegister reg1 : adjacent(reg)) {
                        if (reg1.degree-- == K) {
                            HashSet<RISCVRegister> nodes = new LinkedHashSet<>(adjacent(reg1));
                            nodes.add(reg1);
                       //     enableMoves(nodes);
                            nodes.forEach(node -> nodeMoves(node).forEach(inst -> {
                                if (activeMoves.contains(inst)) {
                                    activeMoves.remove(inst);
                                    workListMoves.add(inst);
                                }
                            }));
                            spillWorkList.remove(reg1);
                            if (!nodeMoves(reg1).isEmpty()) freezeWorkList.add(reg1);
                            else simplifyWorkList.add(reg1);
                        }
                    }
                }
                else if (!workListMoves.isEmpty()) {
                    RISCVMove move = workListMoves.iterator().next();
                    RISCVRegister x = getAlias(move.rd);
                    RISCVRegister y = getAlias(move.rs);
                    RISCVRegister u = preColored.contains(y) ? y : x;
                    RISCVRegister v = preColored.contains(y) ? x : y;
                    workListMoves.remove(move);
                    if (u == v) {
                        coalescedMoves.add(move);
                        if (!preColored.contains(u) && nodeMoves(u).isEmpty() && u.degree < K) {
                            freezeWorkList.remove(u);
                            simplifyWorkList.add(u);
                        }
                    }
                    else if (preColored.contains(v) || adjSet.contains(new RegEdge(u, v))) {
                        constrainedMoves.add(move);
                        if (!preColored.contains(u) && nodeMoves(u).isEmpty() && u.degree < K) {
                            freezeWorkList.remove(u);
                            simplifyWorkList.add(u);
                        }
                        if (!preColored.contains(v) && nodeMoves(v).isEmpty() && v.degree < K) {
                            freezeWorkList.remove(v);
                            simplifyWorkList.add(v);
                        }
                    }
                    else {
                        if ((preColored.contains(u) && ok(u, v)) || (!preColored.contains(u) && conservative(adjacent(u, v)))) {
                            coalescedMoves.add(move);
                            if (freezeWorkList.contains(v)) freezeWorkList.remove(v);
                            else spillWorkList.remove(v);
                            coalescedNodes.add(v);
                            v.alias = u;
                            u.moveList.addAll(v.moveList);
                            HashSet<RISCVRegister> tmp = new LinkedHashSet<>() {{add(v);}};
                            // enableMoves(tmp);
                            tmp.forEach(node -> nodeMoves(node).forEach(inst -> {
                                if (activeMoves.contains(inst)) {
                                    activeMoves.remove(inst);
                                    workListMoves.add(inst);
                                }
                            }));
                            adjacent(v).forEach(t -> {
                                if (t != u && !adjSet.contains(new RegEdge(t, u))) {
                                    adjSet.add(new RegEdge(t, u));
                                    adjSet.add(new RegEdge(u, t));
                                    if (!preColored.contains(t)) t.addAdj(u);
                                    if (!preColored.contains(u)) u.addAdj(t);
                                }
                                if (t.degree-- == K) {
                                    HashSet<RISCVRegister> nodes = new LinkedHashSet<>(adjacent(t));
                                    nodes.add(t);
                                   // enableMoves(nodes);
                                    nodes.forEach(node -> nodeMoves(node).forEach(inst -> {
                                        if (activeMoves.contains(inst)) {
                                            activeMoves.remove(inst);
                                            workListMoves.add(inst);
                                        }
                                    }));
                                    spillWorkList.remove(t);
                                    if (!nodeMoves(t).isEmpty()) freezeWorkList.add(t);
                                    else simplifyWorkList.add(t);
                                }
                            });
                            if (u.degree >= K && freezeWorkList.contains(u)) {
                                freezeWorkList.remove(u);
                                spillWorkList.add(u);
                            }
                            if (!preColored.contains(u) && nodeMoves(u).isEmpty() && u.degree < K) {
                                freezeWorkList.remove(u);
                                simplifyWorkList.add(u);
                            }
                        }
                        else activeMoves.add(move);
                    }
                }
                else if (!freezeWorkList.isEmpty()) {
                    RISCVRegister u = freezeWorkList.iterator().next();
                    freezeWorkList.remove(u);
                    simplifyWorkList.add(u);
                    for (RISCVMove mv : nodeMoves(u)) {
                        RISCVRegister x = mv.rd, y = mv.rs;
                        RISCVRegister v;
                        if (getAlias(u) == getAlias(y)) v = getAlias(x);
                        else v = getAlias(y);
                        activeMoves.remove(mv);
                        frozenMoves.add(mv);
                        if (v.degree < K && nodeMoves(v).isEmpty()) {
                            freezeWorkList.remove(v);
                            simplifyWorkList.add(v);
                        }
                    }
                }
                else if (!spillWorkList.isEmpty()) {
                    RISCVRegister m = null;
                    double min = Double.POSITIVE_INFINITY;
                    boolean hasCandidate = false;
                    for (RISCVRegister reg : spillWorkList) {
                        double cost = reg.weight / reg.degree;
                        if (reg instanceof RISCVVirtualRegister && ((RISCVVirtualRegister) reg).isConst()) cost /= 64;
                        if (!canNotSpillNodes.contains(reg)) {
                            hasCandidate = true;
                            if (cost < min) {
                                min = cost;
                                m = reg;
                            }
                        }
                        else if (!hasCandidate) m = reg;
                    }
                    spillWorkList.remove(m);
                    simplifyWorkList.add(m);
                    for (RISCVMove mv : nodeMoves(m)) {
                        RISCVRegister x = mv.rd, y = mv.rs;
                        RISCVRegister v;
                        if (getAlias(m) == getAlias(y)) v = getAlias(x);
                        else v = getAlias(y);
                        activeMoves.remove(mv);
                        frozenMoves.add(mv);
                        if (v.degree < K && nodeMoves(v).isEmpty()) {
                            freezeWorkList.remove(v);
                            simplifyWorkList.add(v);
                        }
                    }
                }
            }
            while (!(freezeWorkList.isEmpty() && simplifyWorkList.isEmpty() && spillWorkList.isEmpty() && workListMoves.isEmpty()));
            while (!selectStack.isEmpty()) {
                RISCVRegister n = selectStack.pop();
                ArrayList<RISCVPhysicalRegister> freeColors = new ArrayList<>(module.getColors());
                HashSet<RISCVRegister> colored = new LinkedHashSet<>(coloredNodes);
                colored.addAll(preColored);
                for (RISCVRegister w : n.adjList) if (colored.contains(getAlias(w))) freeColors.remove(getAlias(w).color);
                if (freeColors.isEmpty()) spilledNodes.add(n);
                else{
                    coloredNodes.add(n);
                    n.color = freeColors.get(0);
                }
            }
            coalescedNodes.forEach(n -> n.color = getAlias(n).color);
            if(spilledNodes.isEmpty()) break;
            HashSet<RISCVRegister> regs = new LinkedHashSet<>();
            spilledNodes.forEach(node -> {
                node.offset = new RISCVStackOffset(-offset - 4, false);
                offset += 4;
            });
            for (RISCVBasicBlock block : function.getBlockContain()) {
                for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                    if (inst.Defs().size() == 1) {
                        RISCVRegister dest = inst.Defs().iterator().next();
                        if (dest instanceof RISCVVirtualRegister) getAlias(dest);
                    }
                }
            }
            RISCVPhysicalRegister sp = module.getPhysicalRegister("sp");
            for (RISCVBasicBlock block : function.getBlockContain()) {
                for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                    for (RISCVRegister reg : inst.Uses()) {
                        if (reg.offset != null) {
                            if (inst.Defs().contains(reg)) {
                                RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
                                inst.appendFront(new RISCVLoad(sp, reg.offset, tmp, tmp.getWidth(), block));
                                inst.appendBack(new RISCVStore(sp, reg.offset, tmp, tmp.getWidth(), block));
                                inst.UpdateUse(reg, tmp);
                                inst.UpdateDef(reg, tmp);
                                regs.add(tmp);
                            }
                            else {
                                if (inst instanceof RISCVMove && ((RISCVMove)inst).rs == reg && ((RISCVMove)inst).rd.offset == null) {
                                    RISCVInstruction newInst;
                                    if (reg instanceof RISCVVirtualRegister && ((RISCVVirtualRegister) reg).isConst()) newInst = new RISCVLi(((RISCVVirtualRegister) reg).getConstValue(), ((RISCVMove) inst).rd, block);
                                    else newInst = new RISCVLoad(sp, reg.offset, ((RISCVMove) inst).rd, ((RISCVVirtualRegister) reg).getWidth(), block);
                                    inst.update(newInst);
                                    inst = newInst;
                                }
                                else {
                                    RISCVVirtualRegister newReg = new RISCVVirtualRegister( 4);
                                    if (reg instanceof RISCVVirtualRegister && ((RISCVVirtualRegister) reg).isConst()) inst.appendFront(new RISCVLi(((RISCVVirtualRegister) reg).getConstValue(), newReg, block));
                                    else inst.appendFront(new RISCVLoad(sp, reg.offset, newReg, newReg.getWidth(), block));
                                    inst.UpdateUse(reg, newReg);
                                    regs.add(newReg);
                                }
                            }
                        }
                    }
                    for (RISCVRegister def : inst.Defs()) {
                        if (def.offset != null) {
                            if (!inst.Uses().contains(def)) {
                                if (def instanceof RISCVVirtualRegister && ((RISCVVirtualRegister) def).isConst()) {
                                    RISCVInstruction newInst = new RISCVMove(module.getPhysicalRegister(0), module.getPhysicalRegister(0), block);
                                    inst.update(newInst);
                                    inst = newInst;
                                }
                                else if (inst instanceof RISCVMove && ((RISCVMove) inst).rs.offset == null){
                                    RISCVInstruction newInst = new RISCVStore(sp, def.offset, ((RISCVMove) inst).rs,  ((RISCVVirtualRegister)def).getWidth(), block);
                                    inst.update(newInst);
                                    inst = newInst;
                                }
                                else {
                                    RISCVVirtualRegister newReg = new RISCVVirtualRegister(4);
                                    inst.UpdateDef(def, newReg);
                                    inst.appendBack(new RISCVStore(sp, def.offset, newReg, ((RISCVVirtualRegister)def).getWidth(), block));
                                    regs.add(newReg);
                                }
                            }
                        }
                    }
                }
            }

            canNotSpillNodes.addAll(regs);
            initial.addAll(coloredNodes);
            initial.addAll(coalescedNodes);
            initial.addAll(regs);
        }
        offset += function.getOffset();
        offset = (offset + 15) / 16 * 16;
        for(RISCVBasicBlock block : function.getBlockContain()) for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) inst.updateOffset(offset);
    }

    public void run() { module.getExternalFunctionSet().forEach(func -> allocate(func)); }
}
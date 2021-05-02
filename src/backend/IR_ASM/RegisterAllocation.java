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
    private HashSet<RISCVRegister> preColored = new LinkedHashSet<>();
    private HashSet<RISCVRegister> initial = new LinkedHashSet<>();
    private HashSet<RISCVRegister> simplifyWorkList = new LinkedHashSet<>();
    private HashSet<RISCVRegister> freezeWorkList = new LinkedHashSet<>();
    private HashSet<RISCVRegister> spillWorkList = new LinkedHashSet<>();
    private HashSet<RISCVRegister> spilledNodes = new LinkedHashSet<>();
    private HashSet<RISCVRegister> coalescedNodes = new LinkedHashSet<>();
    private HashSet<RISCVRegister> coloredNodes = new LinkedHashSet<>();
    private HashSet<RegEdge> adjSet = new LinkedHashSet<>();
    private Stack<RISCVRegister> selectStack = new Stack<>();
    private int offset, K;
    private RISCVFunction currentFunction;
    private HashSet<RISCVMove> coalescedMoves = new LinkedHashSet<>();
    private HashSet<RISCVMove> constrainedMoves = new LinkedHashSet<>();
    private HashSet<RISCVMove> frozenMoves = new LinkedHashSet<>();
    private HashSet<RISCVMove> workListMoves = new LinkedHashSet<>();
    private HashSet<RISCVMove> activeMoves = new LinkedHashSet<>();

    private HashSet<RISCVRegister> spillTemps = new LinkedHashSet<>();

    private HashMap<RISCVBasicBlock, HashSet<RISCVRegister>> blockUses;
    private HashMap<RISCVBasicBlock, HashSet<RISCVRegister>> blockDefs;
    private HashSet<RISCVBasicBlock> visited;

    RISCVModule root;
    public RegisterAllocation (RISCVModule root) {
        this.root = root;
        preColored.addAll(root.getPhysicalRegisters());
        K = root.getColors().size();
    }

    private void runForBlock(RISCVBasicBlock block) {
        HashSet<RISCVRegister> uses = new LinkedHashSet<>();
        HashSet<RISCVRegister> defs = new LinkedHashSet<>();
        for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
            HashSet<RISCVRegister> instUses = inst.Uses();
            instUses.removeAll(defs);
            uses.addAll(instUses);
            defs.addAll(inst.Defs());
        }
        blockUses.put(block, uses);
        blockDefs.put(block, defs);
        block.setLiveIn(new LinkedHashSet<>());
        block.setLiveOut(new LinkedHashSet<>());
    }

    private void runBackward(RISCVBasicBlock block){
        Stack<RISCVBasicBlock> S = new Stack<>();
        S.push(block);
        while(!S.empty()){
            RISCVBasicBlock now = S.pop();
            if (visited.contains(now)) continue;
            visited.add(now);
            HashSet<RISCVRegister> liveOut = new HashSet<>();
            for (RISCVBasicBlock successor : now.getNext()) {
                liveOut.addAll(successor.getLiveIn());
            }
            HashSet<RISCVRegister> liveIn = new HashSet<>(liveOut);
            liveIn.removeAll(blockDefs.get(now));
            liveIn.addAll(blockUses.get(now));
            now.addLiveOut(liveOut);
            liveIn.removeAll(now.getLiveIn());
            if (!liveIn.isEmpty()) {
                now.addLiveIn(liveIn);
                visited.removeAll(now.getPrev());
            }
            for (RISCVBasicBlock precursor : now.getPrev()) {
                S.push(precursor);
            }
        }
    }

    private void LiveAnalysis(RISCVFunction function) {
        blockUses = new LinkedHashMap<>();
        blockDefs = new LinkedHashMap<>();
        visited = new LinkedHashSet<>();
        function.getBlockContain().forEach(this::runForBlock);
        runBackward(function.getExit());
    }

    void init() {
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
        currentFunction.getBlockContain().forEach(block -> {
            for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next){
                initial.addAll(inst.Defs());
                initial.addAll(inst.Uses());
            }
        });
        initial.removeAll(preColored);
        initial.forEach(RISCVRegister::clear);
        preColored.forEach(RISCVRegister::init);

        for (RISCVBasicBlock block : currentFunction.getBlockContain()) {
            double weight = Math.pow(10, min(block.getNext().size(), block.getPrev().size()));
            for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                inst.Uses().forEach(reg -> reg.weight += weight);
                if (inst.Defs().size() > 0) inst.Defs().iterator().next().weight += weight;
            }
        }
    }

    HashSet<RISCVRegister> adjacent(RISCVRegister n) {
        return new LinkedHashSet<>(n.adjList) {{removeAll(selectStack); removeAll(coalescedNodes);}};
    }
    HashSet<RISCVRegister> adjacent(RISCVRegister u, RISCVRegister v) {
        return new HashSet<>(adjacent(u)) {{addAll(adjacent(v));}};
    }
    HashSet<RISCVMove> nodeMoves(RISCVRegister n) {
        return new LinkedHashSet<>(workListMoves) {{ addAll(activeMoves); retainAll(n.moveList);}};
    }
    void enableMoves(HashSet<RISCVRegister> nodes) {
        nodes.forEach(node -> nodeMoves(node).forEach(move -> {
            if (activeMoves.contains(move)) {
                activeMoves.remove(move);
                workListMoves.add(move);
            }
        }));
    }

    void build() {
        for (RISCVBasicBlock block : currentFunction.getBlockContain()) {
            HashSet<RISCVRegister> currentLive = new LinkedHashSet<>(block.getLiveOut());
            for (RISCVInstruction inst = block.getTail(); inst != null; inst = inst.prev) {
                if (inst instanceof RISCVMove) {
                    currentLive.removeAll(inst.Uses());
                    HashSet<RISCVRegister> mvAbout = inst.Uses();
                    mvAbout.addAll(inst.Defs());
                    for (RISCVRegister reg : mvAbout) {
                        reg.moveList.add((RISCVMove) inst);
                    }
                    workListMoves.add((RISCVMove) inst);
                }
                HashSet<RISCVRegister> defs = inst.Defs();
                currentLive.add(root.getPhysicalRegister(0));
                currentLive.addAll(defs);
                for (RISCVRegister def : defs) {
                    for(RISCVRegister reg : currentLive) {
                        if (reg != def && !adjSet.contains(new RegEdge(reg, def))) {
                            adjSet.add(new RegEdge(reg, def));
                            adjSet.add(new RegEdge(def, reg));
                            if (!preColored.contains(reg)) reg.addAdj(def);
                            if (!preColored.contains(def)) def.addAdj(reg);
                        }
                    }
                }
                currentLive.removeAll(defs);
                currentLive.addAll(inst.Uses());
            }
        }
    }

    RISCVRegister getAlias(RISCVRegister n) {
        if (coalescedNodes.contains(n)) {
            n.alias = getAlias(n.alias);
            return n.alias;
        } else {
            return n;
        }
    }

    void addWorkList(RISCVRegister u) {
        if (!preColored.contains(u) && nodeMoves(u).isEmpty() && u.degree < K) {
            freezeWorkList.remove(u);
            simplifyWorkList.add(u);
        }
    }

    boolean OK(RISCVRegister t, RISCVRegister r) {
        return t.degree < K || preColored.contains(t) || adjSet.contains(new RegEdge(t, r));
    }

    boolean forAllOK(RISCVRegister u, RISCVRegister v) {
        for (RISCVRegister w : adjacent(v)) {
            if (!OK(w, u)) {
                return false;
            }
        }
        return true;
    }

    boolean conservative(HashSet<RISCVRegister> nodes) {
        int count = 0;
        for (RISCVRegister node : nodes) {
            if (node.degree >= K) ++count;
        }
        return count < K;
    }

    void runForFunction(RISCVFunction function) {
        offset = 0;
        currentFunction = function;
        for ( ; ; ) {
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
            currentFunction.getBlockContain().forEach(block -> {
                for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next){
                    initial.addAll(inst.Defs());
                    initial.addAll(inst.Uses());
                }
            });
            initial.removeAll(preColored);
            initial.forEach(RISCVRegister::clear);
            preColored.forEach(RISCVRegister::init);

            for (RISCVBasicBlock block : currentFunction.getBlockContain()) {
                double weight = Math.pow(10, min(block.getNext().size(), block.getPrev().size()));
                for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                    inst.Uses().forEach(reg -> reg.weight += weight);
                    if (inst.Defs().size() > 0) inst.Defs().iterator().next().weight += weight;
                }
            }
            blockUses = new LinkedHashMap<>();
            blockDefs = new LinkedHashMap<>();
            visited = new LinkedHashSet<>();
    //        function.getBlockContain().forEach(this::runForBlock);
            function.getBlockContain().forEach(block -> {
                HashSet<RISCVRegister> uses = new LinkedHashSet<>();
                HashSet<RISCVRegister> defs = new LinkedHashSet<>();
                for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                    HashSet<RISCVRegister> instUses = inst.Uses();
                    instUses.removeAll(defs);
                    uses.addAll(instUses);
                    defs.addAll(inst.Defs());
                }
                blockUses.put(block, uses);
                blockDefs.put(block, defs);
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
                for (RISCVBasicBlock successor : now.getNext()) {
                    liveOut.addAll(successor.getLiveIn());
                }
                HashSet<RISCVRegister> liveIn = new HashSet<>(liveOut);
                liveIn.removeAll(blockDefs.get(now));
                liveIn.addAll(blockUses.get(now));
                now.addLiveOut(liveOut);
                liveIn.removeAll(now.getLiveIn());
                if (!liveIn.isEmpty()) {
                    now.addLiveIn(liveIn);
                    visited.removeAll(now.getPrev());
                }
                for (RISCVBasicBlock precursor : now.getPrev()) {
                    S.push(precursor);
                }
            }
            //runBackward(function.getExit());
            for (RISCVBasicBlock block : currentFunction.getBlockContain()) {
                HashSet<RISCVRegister> currentLive = new LinkedHashSet<>(block.getLiveOut());
                for (RISCVInstruction inst = block.getTail(); inst != null; inst = inst.prev) {
                    if (inst instanceof RISCVMove) {
                        currentLive.removeAll(inst.Uses());
                        HashSet<RISCVRegister> mvAbout = inst.Uses();
                        mvAbout.addAll(inst.Defs());
                        for (RISCVRegister reg : mvAbout) {
                            reg.moveList.add((RISCVMove) inst);
                        }
                        workListMoves.add((RISCVMove) inst);
                    }
                    HashSet<RISCVRegister> defs = inst.Defs();
                    currentLive.add(root.getPhysicalRegister(0));
                    currentLive.addAll(defs);
                    for (RISCVRegister def : defs) {
                        for(RISCVRegister reg : currentLive) {
                            if (reg != def && !adjSet.contains(new RegEdge(reg, def))) {
                                adjSet.add(new RegEdge(reg, def));
                                adjSet.add(new RegEdge(def, reg));
                                if (!preColored.contains(reg)) reg.addAdj(def);
                                if (!preColored.contains(def)) def.addAdj(reg);
                            }
                        }
                    }
                    currentLive.removeAll(defs);
                    currentLive.addAll(inst.Uses());
                }
            }
            for (RISCVRegister node : initial) {
                if (node.degree >= K) {
                    spillWorkList.add(node);
                }
                else if (!nodeMoves(node).isEmpty()) {
                    freezeWorkList.add(node);
                }
                else {
                    simplifyWorkList.add(node);
                }
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
                            enableMoves(nodes);
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
                    RISCVRegister u, v;
                    if (preColored.contains(y)) {
                        u = y;
                        v = x;
                    }else {
                        u = x;
                        v = y;
                    }
                    workListMoves.remove(move);
                    if (u == v) {
                        coalescedMoves.add(move);
                        addWorkList(u);
                    } else if (preColored.contains(v) || adjSet.contains(new RegEdge(u, v))) {
                        constrainedMoves.add(move);
                        addWorkList(u);
                        addWorkList(v);
                    } else {
                        if ((preColored.contains(u) && forAllOK(u, v)) || (!preColored.contains(u) && conservative(adjacent(u, v)))) {
                            coalescedMoves.add(move);
                            if (freezeWorkList.contains(v)) {
                                freezeWorkList.remove(v);
                            }
                            else {
                                spillWorkList.remove(v);
                            }
                            coalescedNodes.add(v);
                            v.alias = u;
                            u.moveList.addAll(v.moveList);
                            HashSet<RISCVRegister> tmp = new LinkedHashSet<>() {{add(v);}};
                            enableMoves(tmp);
                            adjacent(v).forEach(t -> {
                                //addEdge(t, u);
                                if (t != u && !adjSet.contains(new RegEdge(t, u))) {
                                    adjSet.add(new RegEdge(t, u));
                                    adjSet.add(new RegEdge(u, t));
                                    if (!preColored.contains(t)) t.addAdj(u);
                                    if (!preColored.contains(u)) u.addAdj(t);
                                }
                                if (t.degree-- == K) {
                                    HashSet<RISCVRegister> nodes = new LinkedHashSet<>(adjacent(t));
                                    nodes.add(t);
                                    enableMoves(nodes);
                                    spillWorkList.remove(t);
                                    if (!nodeMoves(t).isEmpty()) freezeWorkList.add(t);
                                    else simplifyWorkList.add(t);
                                }
                            });
                            if (u.degree >= K && freezeWorkList.contains(u)) {
                                freezeWorkList.remove(u);
                                spillWorkList.add(u);
                            }
                            addWorkList(u);
                        } else {
                            activeMoves.add(move);
                        }
                    }
                }
                else if (!freezeWorkList.isEmpty()) {
                    RISCVRegister u = freezeWorkList.iterator().next();
                    freezeWorkList.remove(u);
                    simplifyWorkList.add(u);
                    for (RISCVMove mv : nodeMoves(u)) {
                        RISCVRegister x = mv.rd, y = mv.rs;
                        RISCVRegister v;
                        if (getAlias(u) == getAlias(y)) {
                            v = getAlias(x);
                        }
                        else {
                            v = getAlias(y);
                        }
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
                    double minCost = 1e9;
                    boolean hasCandidate = false;
                    for (RISCVRegister reg : spillWorkList) {
                        double cost = reg.weight / reg.degree;
                        if (reg instanceof RISCVVirtualRegister && ((RISCVVirtualRegister) reg).isConst()) {
                            cost /= 64;
                        }
                        if (!spillTemps.contains(reg)) {
                            hasCandidate = true;
                            if (cost < minCost) {
                                minCost = cost;
                                m = reg;
                            }
                        } else if (!hasCandidate) {
                            m = reg;
                        }
                    }
                    spillWorkList.remove(m);
                    simplifyWorkList.add(m);
                    for (RISCVMove mv : nodeMoves(m)) {
                        RISCVRegister x = mv.rd, y = mv.rs;
                        RISCVRegister v;
                        if (getAlias(m) == getAlias(y)) {
                            v = getAlias(x);
                        }
                        else {
                            v = getAlias(y);
                        }
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
                ArrayList<RISCVPhysicalRegister> okColors = new ArrayList<>(root.getColors());
                HashSet<RISCVRegister> colored = new LinkedHashSet<>(coloredNodes);
                colored.addAll(preColored);
                for (RISCVRegister w : n.adjList) {
                    if (colored.contains(getAlias(w))) {
                        okColors.remove(getAlias(w).color);
                    }
                }
                if (okColors.isEmpty()) {
                    spilledNodes.add(n);
                }
                else {
                    coloredNodes.add(n);
                    n.color = okColors.get(0);
                }
            }
            coalescedNodes.forEach(n -> n.color = getAlias(n).color);
            if (spilledNodes.isEmpty()) {
                break;
            }
            rewriteProgram();
        }
        offset += function.getOffset();
        offset = (offset + 15) / 16 * 16;
        for (RISCVBasicBlock block : currentFunction.getBlockContain()) {
            for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                inst.updateOffset(offset);
            }
        }
    }

    void rewriteProgram() {
        HashSet<RISCVRegister> newTemps = new LinkedHashSet<>();
        spilledNodes.forEach(v -> {
            v.offset = new RISCVStackOffset(-offset - 4, false);
            offset += 4;
        });
        for (RISCVBasicBlock block : currentFunction.getBlockContain()) {
            for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                if (inst.Defs().size() == 1) {
                    RISCVRegister dest = inst.Defs().iterator().next();
                    if (dest instanceof RISCVVirtualRegister) {
                        getAlias(dest);
                    }
                }
            }
        }
        RISCVPhysicalRegister sp = root.getPhysicalRegister("sp");
        for (RISCVBasicBlock block : currentFunction.getBlockContain()) {
            for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                for (RISCVRegister reg : inst.Uses()) {
                    if (reg.offset != null) {
                        if (inst.Defs().contains(reg)) {
                            RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
                            inst.appendFront(new RISCVLoad(sp, reg.offset, tmp, tmp.getWidth(), block));
                            inst.appendBack(new RISCVStore(sp, reg.offset, tmp, tmp.getWidth(), block));
                            inst.UpdateUse(reg, tmp);
                            inst.UpdateDef(reg, tmp);
                            newTemps.add(tmp);
                        }
                        else {
                            if (inst instanceof RISCVMove && ((RISCVMove)inst).rs == reg && ((RISCVMove)inst).rd.offset == null) {
                                RISCVInstruction replace;
                                if (reg instanceof RISCVVirtualRegister && ((RISCVVirtualRegister) reg).isConst()) {
                                    replace = new RISCVLi(((RISCVVirtualRegister) reg).getConstValue(), ((RISCVMove) inst).rd, block);
                                } else {
                                    replace = new RISCVLoad(sp, reg.offset, ((RISCVMove) inst).rd, ((RISCVVirtualRegister) reg).getWidth(), block);
                                }
                                inst.update(replace);
                                inst = replace;
                            } else {
                                RISCVVirtualRegister tmp = new RISCVVirtualRegister( 4);
                                if (reg instanceof RISCVVirtualRegister && ((RISCVVirtualRegister) reg).isConst()) {
                                    inst.appendFront(new RISCVLi(((RISCVVirtualRegister) reg).getConstValue(), tmp, block));
                                } else {
                                    inst.appendFront(new RISCVLoad(sp, reg.offset, tmp, tmp.getWidth(), block));
                                }
                                inst.UpdateUse(reg, tmp);
                                newTemps.add(tmp);
                            }
                        }
                    }
                }
                for (RISCVRegister def : inst.Defs()) {
                    if (def.offset != null) {
                        if (!inst.Uses().contains(def)) {
                            if (def instanceof RISCVVirtualRegister && ((RISCVVirtualRegister) def).isConst()) {
                                RISCVInstruction replace = new RISCVMove(root.getPhysicalRegister(0), root.getPhysicalRegister(0), block);
                                inst.update(replace);
                                inst = replace;
                            } else if (inst instanceof RISCVMove && ((RISCVMove) inst).rs.offset == null){
                                RISCVInstruction replace = new RISCVStore(sp, def.offset, ((RISCVMove) inst).rs,  ((RISCVVirtualRegister)def).getWidth(), block);
                                inst.update(replace);
                                inst = replace;
                            }
                            else {
                                RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
                                inst.UpdateDef(def, tmp);
                                inst.appendBack(new RISCVStore(sp, def.offset, tmp, ((RISCVVirtualRegister)def).getWidth(), block));
                                newTemps.add(tmp);
                            }
                        }
                    }
                }
            }
        }

        spillTemps.addAll(newTemps);
        initial.addAll(coloredNodes);
        initial.addAll(coalescedNodes);
        initial.addAll(newTemps);
    }

    void updateOffset() {
        for (RISCVBasicBlock block : currentFunction.getBlockContain()) {
            for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                inst.updateOffset(offset);
            }
        }
    }

    public void run() { root.getExternalFunctionSet().forEach(func -> runForFunction(func)); }
}
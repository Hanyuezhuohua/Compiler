package backend.IR_ASM;

import IR.IRbasicblock.IRBasicBlock;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVfunction.RISCVFunction;
import RISCV.RISCVinstruction.RISCVInstruction;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Stack;

public class LiveAnalysis {

    static HashMap<RISCVBasicBlock, HashSet<RISCVRegister>> blockUses;
    static HashMap<RISCVBasicBlock, HashSet<RISCVRegister>> blockDefs;
    static HashSet<RISCVBasicBlock> visited;

    static void runForBlock(RISCVBasicBlock block) {
        HashSet<RISCVRegister> uses = new HashSet<>();
        HashSet<RISCVRegister> defs = new HashSet<>();
        for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
            HashSet<RISCVRegister> instUses = inst.Uses();
            instUses.removeAll(defs);
            uses.addAll(instUses);
            defs.addAll(inst.Defs());
        }
        blockUses.put(block, uses);
        blockDefs.put(block, defs);
        block.setLiveIn(new LinkedHashSet<>());
        block.setLiveOut(new HashSet<>());
    }

    static void runBackward(RISCVBasicBlock block){
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

    public static void runForFunction(RISCVFunction function) {
        blockUses = new HashMap<>();
        blockDefs = new HashMap<>();
        visited = new HashSet<>();
        function.getBlockContain().forEach(LiveAnalysis::runForBlock);
        runBackward(function.getExit());
    }
}


package backend.IR_ASM;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVfunction.RISCVFunction;
import RISCV.RISCVinstruction.RISCVInstruction;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

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


    static void runBackward(RISCVBasicBlock block) {
        if (visited.contains(block)) return;
        visited.add(block);
        HashSet<RISCVRegister> liveOut = new HashSet<>();
        for (RISCVBasicBlock successor : block.getNext()) {
            liveOut.addAll(successor.getLiveIn());
        }
        HashSet<RISCVRegister> liveIn = new HashSet<>(liveOut);
        liveIn.removeAll(blockDefs.get(block));
        liveIn.addAll(blockUses.get(block));
        block.addLiveOut(liveOut);
        liveIn.removeAll(block.getLiveIn());
        if (!liveIn.isEmpty()) {
            block.addLiveIn(liveIn);
            visited.removeAll(block.getPrev());
        }
        for (RISCVBasicBlock precursor : block.getPrev()) {
            runBackward(precursor);
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


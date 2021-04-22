package backend.IR_ASM;

import RISCV.RISCVinstruction.Branch.BinaryBranch;
import RISCV.RISCVinstruction.Branch.UnaryBranch;
import RISCV.RISCVinstruction.RISCVInstruction;
import RISCV.RISCVoperand.RISCVregister.RISCVGlobalRegister;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVfunction.RISCVFunction;
import RISCV.RISCVmodule.RISCVModule;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class ASMPrinter {
    RISCVModule root;
    PrintStream out;
    boolean flag;
    HashSet<RISCVBasicBlock> visited;
    int blockCount;
    RISCVFunction currentFunction;

    public ASMPrinter(RISCVModule root, PrintStream out, boolean flag) {
        this.root = root;
        this.out = out;
        this.flag = flag;
        visited = new LinkedHashSet<>();
        blockCount = 0;
        currentFunction = null;
    }

    void Dfs(RISCVBasicBlock block){
        visited.add(block);
        block.setIdentifier("." + currentFunction.getIdentifier() + "_b." + blockCount++);
        if (block.getTail() instanceof UnaryBranch) {
            if (!visited.contains(((UnaryBranch) block.getTail()).jumpTo) && flag) {
                Dfs(((UnaryBranch) block.getTail()).jumpTo);
                block.getTail().remove();
            }
        }
        for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) if (inst instanceof BinaryBranch && !visited.contains(((BinaryBranch) inst).offset)) Dfs(((BinaryBranch) inst).offset);
    }

    void PrintBlock(RISCVBasicBlock block) {
        out.println(block.getIdentifier() + ": ");
        for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) out.println("\t" + inst.toString());
    }

    void PrintFunction(RISCVFunction function) {
        out.println("\t.globl\t" + function.getIdentifier());
        out.println("\t.p2align\t1");
        out.println("\t.type\t" + function.getIdentifier() +",@function");
        out.println(function.getIdentifier() + ":");
        blockCount = 0;
        currentFunction = function;
        visited.clear();
        Dfs(function.getEntry());
        visited.forEach(this::PrintBlock);
        out.println();
    }

    void PrintGlobalRegister(RISCVGlobalRegister gReg) {
        out.println("\t.type\t" + gReg.getIdentifier() + ",@object");
        out.println("\t.section\t.data");
        out.println("\t.globl\t" + gReg.getIdentifier());
        out.println("\t.p2align\t2");
        out.println(gReg.getIdentifier() + ":");
        out.println("\t.zero\t" + gReg.getWidth());
        out.println("\t.size\t" + gReg.getIdentifier() + ", " + gReg.getWidth() + "\n");
    }

    void PrintConstString(RISCVGlobalRegister gReg, String s) {
        out.println("\t.type\t" + gReg.getIdentifier() + ",@object");
        out.println("\t.section\t.rodata");
        out.println(gReg.getIdentifier() + ":");
        out.println("\t.asciz\t\"" + s + "\"");
        out.println("\t.size\t" + gReg.getIdentifier() + ", " + (s.length() + 1) + "\n");
    }

    public void run() {
        out.println("\t.text");
        root.getExternalFunctionSet().forEach(this::PrintFunction);
        root.getGlobalVariableSet().forEach(this::PrintGlobalRegister);
        root.getConstStringMap().forEach(this::PrintConstString);
    }



}

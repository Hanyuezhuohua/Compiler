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
    public ASMPrinter(RISCVModule root, PrintStream out, boolean flag) {
        this.root = root;
        this.out = out;
        this.flag = flag;
    }

    HashSet<RISCVBasicBlock> visited;
    int blockCount;
    RISCVFunction currentFunction;

    boolean Dfs(RISCVBasicBlock block) {
        if (visited.contains(block)) return false;
        visited.add(block);
        block.setIdentifier("." + currentFunction.getIdentifier() + "_." + blockCount++);
        if (block.getTail() instanceof UnaryBranch) {
            if (Dfs(((UnaryBranch) block.getTail()).jumpTo) && flag) {
                block.getTail().remove();
            }
        }
        for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
            if (inst instanceof BinaryBranch) {
                Dfs(((BinaryBranch) inst).offset);
            }
        }
        return true;
    }

    void ChangeName(RISCVFunction function) {
        blockCount = 0;
        currentFunction = function;
        visited = new LinkedHashSet<>();
        Dfs(function.getEntry());
    }

    void PrintBlock(RISCVBasicBlock block) {
        out.println(block.getIdentifier() + ": " /*+ " # " + block.comment*/);
        for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) out.println("\t" + inst.toString() /*+ (inst.comment == null ? "" : " # " + inst.comment)*/);
    }

    void PrintFunction(RISCVFunction function) {
        out.println("\t.globl\t" + function.getIdentifier());
        out.println("\t.p2align\t1");
        out.println("\t.type\t" + function.getIdentifier() +",@function");
        out.println(function.getIdentifier() + ":");
        ChangeName(function);
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

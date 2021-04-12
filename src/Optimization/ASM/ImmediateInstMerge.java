package Optimization.ASM;

import RISCV.RISCVUtility.RISCVVisitor;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVfunction.RISCVFunction;
import RISCV.RISCVinstruction.*;
import RISCV.RISCVinstruction.Binary.ImmediateBinary;
import RISCV.RISCVinstruction.Binary.RegisterBinary;
import RISCV.RISCVinstruction.Branch.BinaryBranch;
import RISCV.RISCVinstruction.Branch.UnaryBranch;
import RISCV.RISCVmodule.RISCVModule;

public class ImmediateInstMerge implements RISCVVisitor {
    private boolean hasImmediateInstMerge;

    public ImmediateInstMerge(){
        hasImmediateInstMerge = false;
    }

    public boolean hasImmediateInstMerge() {
        return hasImmediateInstMerge;
    }

    @Override
    public void visit(RISCVModule module) {
        module.getExternalFunctionSet().forEach(func -> func.accept(this));
    }

    @Override
    public void visit(RISCVFunction function) {
        function.getBlockContain().forEach(block -> block.accept(this));
    }

    @Override
    public void visit(RISCVBasicBlock block) {
        RISCVInstruction inst = block.getHead();
        while (inst != null){
            if(inst instanceof ImmediateBinary){
                boolean flag = ((ImmediateBinary) inst).mergeInst();
                if(!flag) inst = inst.getNext();
                else{
                    hasImmediateInstMerge = true;
                }
            }
            else inst = inst.getNext();
        }
    }

    @Override
    public void visit(ImmediateBinary inst) {

    }

    @Override
    public void visit(RegisterBinary inst) {

    }

    @Override
    public void visit(BinaryBranch inst) {

    }

    @Override
    public void visit(UnaryBranch inst) {

    }

    @Override
    public void visit(RISCVCall inst) {

    }

    @Override
    public void visit(RISCVLa inst) {

    }

    @Override
    public void visit(RISCVLi inst) {

    }

    @Override
    public void visit(RISCVLoad inst) {

    }

    @Override
    public void visit(RISCVLui inst) {

    }

    @Override
    public void visit(RISCVMove inst) {

    }

    @Override
    public void visit(RISCVReturn inst) {

    }

    @Override
    public void visit(RISCVStore inst) {

    }

    @Override
    public void visit(RISCVZeroCmp inst) {

    }
}

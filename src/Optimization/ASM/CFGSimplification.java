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

public class CFGSimplification implements RISCVVisitor {
    private boolean newCFGSimplification;

    public CFGSimplification(){
        newCFGSimplification = false;
    }

    public boolean hasNewCFGSimplification() {
        return newCFGSimplification;
    }

    @Override
    public void visit(RISCVModule module) {
        module.getExternalFunctionSet().forEach(func -> func.accept(this));
    }

    @Override
    public void visit(RISCVFunction function) {
        boolean change;
        do{
            change = false;
            for(RISCVBasicBlock block: function.getBlockContain()){
                if(block.getTail() instanceof UnaryBranch && ((UnaryBranch) block.getTail()).getJumpTo().getPrev().size() == 1 && !((UnaryBranch) block.getTail()).getJumpTo().equals(block)){
                    RISCVBasicBlock tmp = ((UnaryBranch) block.getTail()).getJumpTo();
                    block.getTail().remove();
                    block.merge(tmp);
                    function.getBlockContain().remove(tmp);
                    if(function.getExit().equals(tmp)) function.setExit(block);
                    change = true;
                    newCFGSimplification = true;
                    break;
                }
            }
        } while (change);
    }

    @Override
    public void visit(RISCVBasicBlock block) {

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

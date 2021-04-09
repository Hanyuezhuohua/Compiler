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
import RISCV.RISCVoperand.RISCVregister.RISCVPhysicalRegister;

public class RedundantInstRemove implements RISCVVisitor {
    private boolean newRedundantInstRemove;
    private RISCVPhysicalRegister zero;

    public RedundantInstRemove(){
        newRedundantInstRemove = false;
        zero = null;
    }

    public boolean hasNewRedundantInstRemove() {
        return newRedundantInstRemove;
    }

    @Override
    public void visit(RISCVModule module) {
        zero = module.getPhysicalRegister("zero");
        module.getExternalFunctionSet().forEach(func -> func.accept(this));
    }

    @Override
    public void visit(RISCVFunction function) {
        function.getBlockContain().forEach(block -> block.accept(this));
    }

    @Override
    public void visit(RISCVBasicBlock block) {
        for(RISCVInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
            inst.accept(this);
        }
    }

    @Override
    public void visit(ImmediateBinary inst) {
        if(inst.getOp() == ImmediateBinary.ImmediateBinaryOp.addi){
            if(inst.getRd().getColor().equals(inst.getRs().getColor()) && inst.getImm().getValue() == 0){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
        else if(inst.getOp() == ImmediateBinary.ImmediateBinaryOp.ori){
            if(inst.getRd().getColor().equals(inst.getRs().getColor()) && inst.getImm().getValue() == 0){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
        else if(inst.getOp() == ImmediateBinary.ImmediateBinaryOp.xori){
            if(inst.getRd().getColor().equals(inst.getRs().getColor()) && inst.getImm().getValue() == 0){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
        else if(inst.getOp() == ImmediateBinary.ImmediateBinaryOp.slli){
            if(inst.getRd().getColor().equals(inst.getRs().getColor()) && inst.getImm().getValue() == 0){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
        else if(inst.getOp() == ImmediateBinary.ImmediateBinaryOp.srai){
            if(inst.getRd().getColor().equals(inst.getRs().getColor()) && inst.getImm().getValue() == 0){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
    }

    @Override
    public void visit(RegisterBinary inst) {
        if(inst.getOp() == RegisterBinary.RegisterBinaryOp.add){
            if(inst.getRd().getColor().equals(inst.getRs1().getColor()) && inst.getRs2().getColor().equals(zero)){
                inst.remove();
                newRedundantInstRemove = true;
            }
            else if(inst.getRd().getColor().equals(inst.getRs2().getColor()) && inst.getRs1().getColor().equals(zero)){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
        else if(inst.getOp() == RegisterBinary.RegisterBinaryOp.sub){
            if(inst.getRd().getColor().equals(inst.getRs1().getColor()) && inst.getRs2().getColor().equals(zero)){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
        else if(inst.getOp() == RegisterBinary.RegisterBinaryOp.sll){
            if(inst.getRd().getColor().equals(inst.getRs1().getColor()) && inst.getRs2().getColor().equals(zero)){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
        else if(inst.getOp() == RegisterBinary.RegisterBinaryOp.sra){
            if(inst.getRd().getColor().equals(inst.getRs1().getColor()) && inst.getRs2().getColor().equals(zero)){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
        else if(inst.getOp() == RegisterBinary.RegisterBinaryOp.or){
            if(inst.getRd().getColor().equals(inst.getRs1().getColor()) && inst.getRs2().getColor().equals(zero)){
                inst.remove();
                newRedundantInstRemove = true;
            }
            else if(inst.getRd().getColor().equals(inst.getRs2().getColor()) && inst.getRs1().getColor().equals(zero)){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
        else if(inst.getOp() == RegisterBinary.RegisterBinaryOp.xor){
            if(inst.getRd().getColor().equals(inst.getRs1().getColor()) && inst.getRs2().getColor().equals(zero)){
                inst.remove();
                newRedundantInstRemove = true;
            }
            else if(inst.getRd().getColor().equals(inst.getRs2().getColor()) && inst.getRs1().getColor().equals(zero)){
                inst.remove();
                newRedundantInstRemove = true;
            }
        }
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
        if(inst.getRs().getColor().equals(inst.getRd().getColor())){
            inst.remove();
            newRedundantInstRemove = true;
        }
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

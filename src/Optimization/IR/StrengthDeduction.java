package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRConstInt;
import IR.IRtype.IRIntType;
import IR.IRutility.IRVisitor;

public class StrengthDeduction implements IRVisitor {
    public StrengthDeduction(){}

    @Override
    public void visit(IRModule module) {
        module.getExternalFunctionMap().forEach((id, func) -> func.accept(this));
    }

    @Override
    public void visit(IRFunction func) {
        func.getBlockContain().forEach(block -> block.accept(this));
    }

    @Override
    public void visit(IRBasicBlock block) {
        for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
            inst.accept(this);
        }
    }

    @Override
    public void visit(Alloca inst) {

    }

    @Override
    public void visit(Binary inst) {
        if(inst.getOp() == Binary.IRBinaryOpType.sdiv && inst.getOp2() instanceof IRConstInt && ((IRConstInt) inst.getOp2()).getValue() == 2){
            IRInstruction newInst = new BitwiseBinary(inst.getInstIn(), BitwiseBinary.IRBitwiseBinaryOpType.ashr, inst.getOp1(), new IRConstInt(1, IRIntType.IntTypeBytes.Int32), inst.getResult());
            inst.Remove();
            newInst.setPrev(inst.getPrev());
            newInst.setNext(inst.getNext());
            if(inst.getPrev() != null) inst.getPrev().setNext(newInst);
            else newInst.getInstIn().setHead(newInst);
            inst.getNext().setPrev(newInst);
        }
        if(inst.getOp() == Binary.IRBinaryOpType.mul && inst.getOp2() instanceof IRConstInt && ((IRConstInt) inst.getOp2()).getValue() == 2){
            IRInstruction newInst = new BitwiseBinary(inst.getInstIn(), BitwiseBinary.IRBitwiseBinaryOpType.shl, inst.getOp1(), new IRConstInt(1, IRIntType.IntTypeBytes.Int32), inst.getResult());
            inst.Remove();
            newInst.setPrev(inst.getPrev());
            newInst.setNext(inst.getNext());
            if(inst.getPrev() != null) inst.getPrev().setNext(newInst);
            else newInst.getInstIn().setHead(newInst);
            inst.getNext().setPrev(newInst);
        }
    }

    @Override
    public void visit(BitCast inst) {

    }

    @Override
    public void visit(BitwiseBinary inst) {

    }

    @Override
    public void visit(Br inst) {

    }

    @Override
    public void visit(Call inst) {

    }

    @Override
    public void visit(GetElementPtr inst) {

    }

    @Override
    public void visit(Icmp inst) {

    }

    @Override
    public void visit(Load inst) {

    }

    @Override
    public void visit(Move inst) {

    }

    @Override
    public void visit(Phi inst) {

    }

    @Override
    public void visit(Ret inst) {

    }

    @Override
    public void visit(Store inst) {

    }

    @Override
    public void visit(Trunc inst) {

    }

    @Override
    public void visit(Zext inst) {

    }
}

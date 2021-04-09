package IR.IRutility;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;

public class UseClear implements IRVisitor{
    public UseClear(){}

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
        for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()) inst.accept(this);
    }

    @Override
    public void visit(Alloca inst) {
        inst.getResult().clearInst();
    }

    @Override
    public void visit(Binary inst) {
        inst.getResult().clearInst();
        inst.getOp1().clearInst();
        inst.getOp2().clearInst();
    }

    @Override
    public void visit(BitCast inst) {
        inst.getResult().clearInst();
        inst.getValue().clearInst();
    }

    @Override
    public void visit(BitwiseBinary inst) {
        inst.getResult().clearInst();
        inst.getOp1().clearInst();
        inst.getOp2().clearInst();
    }

    @Override
    public void visit(Br inst) {
        if(inst.getCond() != null) inst.getCond().clearInst();
    }

    @Override
    public void visit(Call inst) {
        inst.getFunctionArgs().forEach(parameter -> parameter.clearInst());
        inst.getResult().clearInst();
    }

    @Override
    public void visit(GetElementPtr inst) {
        inst.getPtrval().clearInst();
        inst.getIndexs().forEach(index -> index.clearInst());
        inst.getResult().clearInst();
    }

    @Override
    public void visit(Icmp inst) {
        inst.getResult().clearInst();
        inst.getOp1().clearInst();
        inst.getOp2().clearInst();
    }

    @Override
    public void visit(Load inst) {
        inst.getPointer().clearInst();
        inst.getResult().clearInst();
    }

    @Override
    public void visit(Move inst) {
        inst.getValue().clearInst();
        inst.getResult().clearInst();
    }

    @Override
    public void visit(Phi inst) {
        inst.getValues().forEach(value -> value.clearInst());
    }

    @Override
    public void visit(Ret inst) {
        inst.getValue().clearInst();
    }

    @Override
    public void visit(Store inst) {
        inst.getValue().clearInst();
        inst.getPointer().clearInst();
    }

    @Override
    public void visit(Trunc inst) {
        inst.getResult().clearInst();
        inst.getValue().clearInst();
    }

    @Override
    public void visit(Zext inst) {
        inst.getResult().clearInst();
        inst.getValue().clearInst();
    }
}

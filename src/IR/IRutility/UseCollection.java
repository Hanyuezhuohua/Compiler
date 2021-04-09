package IR.IRutility;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRtype.IRVoidType;

public class UseCollection implements IRVisitor{
    public UseCollection(){}

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

    }

    @Override
    public void visit(Binary inst) {
        inst.getOp1().appendInst(inst);
        inst.getOp2().appendInst(inst);
    }

    @Override
    public void visit(BitCast inst) {
        inst.getValue().appendInst(inst);
    }

    @Override
    public void visit(BitwiseBinary inst) {
        inst.getOp1().appendInst(inst);
        inst.getOp2().appendInst(inst);
    }

    @Override
    public void visit(Br inst) {
        if(inst.getCond() != null) inst.getCond().appendInst(inst);
    }

    @Override
    public void visit(Call inst) {
        inst.getFunctionArgs().forEach(parameter -> parameter.appendInst(inst));
    }

    @Override
    public void visit(GetElementPtr inst) {
        inst.getPtrval().appendInst(inst);
        inst.getIndexs().forEach(index -> index.appendInst(inst));
    }

    @Override
    public void visit(Icmp inst) {
        inst.getOp1().appendInst(inst);
        inst.getOp2().appendInst(inst);
    }

    @Override
    public void visit(Load inst) {
        inst.getPointer().appendInst(inst);
    }

    @Override
    public void visit(Move inst) {

    }

    @Override
    public void visit(Phi inst) {
        inst.getValues().forEach(value -> value.appendInst(inst));
    }

    @Override
    public void visit(Ret inst) {
        if(!(inst.getValue().getOperandType() instanceof IRVoidType)){
            inst.getValue().appendInst(inst);
        }
    }

    @Override
    public void visit(Store inst) {
        inst.getPointer().appendInst(inst);
        inst.getValue().appendInst(inst);
    }

    @Override
    public void visit(Trunc inst) {
        inst.getValue().appendInst(inst);
    }

    @Override
    public void visit(Zext inst) {
        inst.getValue().appendInst(inst);
    }
}

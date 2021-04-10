package IR.IRutility;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;

public class DefCollection implements IRVisitor{

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
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(Binary inst) {
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(BitCast inst) {
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(BitwiseBinary inst) {
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(Br inst) {

    }

    @Override
    public void visit(Call inst) {
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(GetElementPtr inst) {
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(Icmp inst) {
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(Load inst) {
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(Move inst) {
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(Phi inst) {
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(Ret inst) {

    }

    @Override
    public void visit(Store inst) {

    }

    @Override
    public void visit(Trunc inst) {
        inst.getResult().setDef(inst);
    }

    @Override
    public void visit(Zext inst) {
        inst.getResult().setDef(inst);
    }
}

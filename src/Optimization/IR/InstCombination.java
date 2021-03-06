package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRutility.IRVisitor;

public class InstCombination implements IRVisitor {
    boolean newInstCombination;

    public InstCombination(){
        newInstCombination = false;
    }

    public boolean NewInstCombination() {
        return newInstCombination;
    }

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
        IRInstruction inst = block.getHead();
        while (inst != null){
            if(inst.merge()){
                newInstCombination = true;
                continue;
            }
            else inst = inst.getNext();
        }
    }

    @Override
    public void visit(Alloca inst) {

    }

    @Override
    public void visit(Binary inst) {

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

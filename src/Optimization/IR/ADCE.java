package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRtype.IRVoidType;
import IR.IRutility.*;
import backend.AST_IR.DominatorTree;

public class ADCE implements IRVisitor {
    private boolean newADCE;

    public ADCE(){
        newADCE = false;
    }


    @Override
    public void visit(IRModule module) {
        new UseClear().visit(module);
        new UseCollection().visit(module);
        new SideEffectCollection(module).run();
        module.getExternalFunctionMap().forEach((id, func) -> func.accept(this));
        module.getExternalFunctionMap().forEach((id, func) -> {
            new FuncBlockCollection().BlockCollecting(func);
            new DominatorTree(func).Lengauer_Tarjan();
        });
    }

    @Override
    public void visit(IRFunction func) {
        do{
            newADCE = false;
            func.getBlockContain().forEach(block -> block.accept(this));
        }while (newADCE);
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
        if(inst.getResult().getInstructions().size() == 0){
            inst.Remove();
            newADCE = true;
        }
    }

    @Override
    public void visit(BitCast inst) {
        if(inst.getResult().getInstructions().size() == 0){
            inst.Remove();
            newADCE = true;
        }
    }

    @Override
    public void visit(BitwiseBinary inst) {
        if(inst.getResult().getInstructions().size() == 0){
            inst.Remove();
            newADCE = true;
        }
    }

    @Override
    public void visit(Br inst) {

    }

    @Override
    public void visit(Call inst) {
        if(!inst.getFnptrval().hasSideEffect() && (inst.getResult().getOperandType() instanceof IRVoidType || inst.getResult().getInstructions().size() == 0)){
            inst.Remove();
            newADCE = true;
        }
        else if(inst.getFnptrval().getIdentifier().equals("malloc") && inst.getResult().getInstructions().size() == 0){
            inst.Remove();
            newADCE = true;
        }
    }

    @Override
    public void visit(GetElementPtr inst) {
        if(inst.getResult().getInstructions().size() == 0){
            inst.Remove();
            newADCE = true;
        }
    }

    @Override
    public void visit(Icmp inst) {
        if(inst.getResult().getInstructions().size() == 0){
            inst.Remove();
            newADCE = true;
        }
    }

    @Override
    public void visit(Load inst) {
        if(inst.getResult().getInstructions().size() == 0){
            inst.Remove();
            newADCE = true;
        }
    }

    @Override
    public void visit(Move inst) {

    }

    @Override
    public void visit(Phi inst) {
        if(inst.getResult().getInstructions().size() == 0){
            inst.Remove();
            newADCE = true;
        }
    }

    @Override
    public void visit(Ret inst) {

    }

    @Override
    public void visit(Store inst) {

    }

    @Override
    public void visit(Trunc inst) {
        if(inst.getResult().getInstructions().size() == 0){
            inst.Remove();
            newADCE = true;
        }
    }

    @Override
    public void visit(Zext inst) {
        if(inst.getResult().getInstructions().size() == 0){
            inst.Remove();
            newADCE = true;
        }
    }
}

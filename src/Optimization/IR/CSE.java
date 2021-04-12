package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRLocalRegister;
import IR.IRutility.FuncBlockCollection;
import IR.IRutility.IRVisitor;
import backend.AST_IR.DominatorTree;

import java.util.ArrayList;

public class CSE implements IRVisitor {
    private boolean newCSE;
    private static int CSELimit = 30;

    public CSE(){
        newCSE = false;
    }

    @Override
    public void visit(IRModule module) {
        module.getExternalFunctionMap().forEach((id, func) -> func.accept(this));
    }

    @Override
    public void visit(IRFunction func) {
        new FuncBlockCollection().BlockCollecting(func);
        new DominatorTree(func).Lengauer_Tarjan();
     //   DominatorTree.Lengauer_Tarjan(func);
        func.getBlockContain().forEach(block -> block.accept(this));
    }

    @Override
    public void visit(IRBasicBlock block) {
        do{
            newCSE = false;
            ArrayList<IRInstruction> instructions = new ArrayList<>();
            for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                if(!inst.hasSideEffect()){
                    boolean same = false;
                    for(IRInstruction collected: instructions){
                        if(inst.CSEChecker(collected)){
                            same = true;
                            ((IRLocalRegister) inst.getResult()).update(collected.getResult());
                            inst.Remove();
                            break;
                        }
                    }
                    if(!same) instructions.add(inst);
                    else newCSE = true;
                }
            }
            block.getNext().forEach(next -> {
                if(next.DomBy(block)){
                    IDomCSE(next, 0, instructions);
                }
            });
        }while (newCSE);
    }

    public void IDomCSE(IRBasicBlock block, int num, ArrayList<IRInstruction> instructions){
        for(IRInstruction inst = block.getHead(); inst != null && num++ < CSELimit; inst = inst.getNext()){
            if(!inst.hasSideEffect()){
                for(IRInstruction collected: instructions){
                    if(inst.CSEChecker(collected)){
                        ((IRLocalRegister) inst.getResult()).update(collected.getResult());
                        inst.Remove();
                        newCSE = true;
                        break;
                    }
                }
            }
            else if(inst instanceof Call) num += 12;
        }
        if(num < CSELimit){
            for(IRBasicBlock next : block.getNext()){
                if(next.DomBy(block)){
                    IDomCSE(next, num, instructions);
                }
            }
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

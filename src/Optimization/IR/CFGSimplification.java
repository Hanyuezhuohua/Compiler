package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRConstBool;
import IR.IRoperand.IRLocalRegister;
import IR.IRutility.FuncBlockCollection;
import IR.IRutility.IRVisitor;
import backend.AST_IR.DominatorTree;

public class CFGSimplification implements IRVisitor {
    private boolean newCFGSimplification;
    private boolean flag = false;

    public CFGSimplification(){
        newCFGSimplification = false;
    }

    public boolean Flag() {
        return flag;
    }

    @Override
    public void visit(IRModule module) {
        module.getExternalFunctionMap().forEach((id, func) -> func.accept(this));
    }

    @Override
    public void visit(IRFunction func) {
        do{
            new FuncBlockCollection().BlockCollecting(func);
            new DominatorTree(func).Lengauer_Tarjan();
            newCFGSimplification = false;
            for(IRBasicBlock block : func.getBlockContain()){
                if(block.canMerge()){
                    newCFGSimplification = true;
                    flag = true;
                    IRBasicBlock next = block.getNext().get(0);
                    for(IRInstruction inst = next.getHead(); inst != null && inst instanceof Phi; inst = inst.getNext()){
                        ((IRLocalRegister) inst.getResult()).update(((Phi) inst).getValues().get(0));
                        inst.Remove();
                    }
                    block.getTail().Remove();
                    block.merge(next);
                    if(next.equals(func.getExit())) func.setExit(block);
                    break;
                }
                else if(block.getTail() instanceof Br && ((Br) block.getTail()).getCond() != null){
                    if(((Br) block.getTail()).getIfTrue().equals(((Br) block.getTail()).getIfFalse())){
                        newCFGSimplification = true;
                        flag = true;
                        Br tmp = (Br) block.getTail();
                        block.getTail().Remove();
                        block.addInst(new Br(block, null, tmp.getIfTrue(), null));
                        break;
                    }
                    else if(((Br) block.getTail()).getCond() instanceof IRConstBool){
                        newCFGSimplification = true;
                        flag = true;
                        Br tmp = (Br) block.getTail();
                        if(tmp.getCond().isZero()){
                            for(IRInstruction inst = tmp.getIfTrue().getHead(); inst != null && inst instanceof Phi; inst = inst.getNext()){
                                for(int i = 0; i < ((Phi) inst).getValues().size(); ++i){
                                    if(((Phi) inst).getLabels().get(i).equals(block)){
                                        ((Phi) inst).getLabels().remove(i);
                                        ((Phi) inst).getValues().remove(i);
                                        --i;
                                    }
                                }
                                if(((Phi) inst).getValues().size() == 0) inst.Remove();
                            }
                            block.getTail().Remove();
                            block.addInst(new Br(block, null, tmp.getIfFalse(), null));
                        }
                        else{
                            for(IRInstruction inst = tmp.getIfFalse().getHead(); inst != null && inst instanceof Phi; inst = inst.getNext()){
                                for(int i = 0; i < ((Phi) inst).getValues().size(); ++i){
                                    if(((Phi) inst).getLabels().get(i).equals(block)){
                                        ((Phi) inst).getLabels().remove(i);
                                        ((Phi) inst).getValues().remove(i);
                                        --i;
                                    }
                                }
                                if(((Phi) inst).getValues().size() == 0) inst.Remove();
                            }
                            block.getTail().Remove();
                            block.addInst(new Br(block, null, tmp.getIfTrue(), null));
                        }
                    }
                }
            }
        }while (newCFGSimplification);
    }

    @Override
    public void visit(IRBasicBlock block) {

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

package Optimization.IR;

import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.DefCollection;
import IR.IRutility.UseClear;
import IR.IRutility.UseCollection;

public class MemoryAccess {
    private IRModule module;

    public MemoryAccess(IRModule module){
        this.module = module;
    }

    public void run(){
        new UseClear().visit(module);
        new UseCollection().visit(module);
        new DefCollection().visit(module);
        module.getExternalFunctionMap().forEach((id, func) -> func.getBlockContain().forEach(block -> module.getGlobalVariableList().forEach(var -> {
            boolean flag = false;
            IROperand cur = null;
            for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                if(inst instanceof Load && ((Load) inst).getPointer() == var){
                    if(cur == null) cur = inst.getResult();
                    else{
                       for(IRInstruction instruction: inst.getResult().getInstructions()) instruction.update((IRLocalRegister) inst.getResult(), cur);
                       inst.Remove();
                    }
                }
                else if(inst instanceof Store &&  ((Store) inst).getPointer() == var){
                    cur = ((Store) inst).getValue();
                    flag = true;
                    inst.Remove();
                }
                else if((inst instanceof Call && module.getExternalFunctionMap().containsKey(((Call) inst).getFnptrval().getIdentifier())) || inst == block.getTail()){
                    if(flag){
                        flag = false;
                        IRInstruction newInst = new Store(block, cur, var);
                        newInst.setPrev(inst.getPrev());
                        newInst.setNext(inst);
                        if(inst.getPrev() != null) inst.getPrev().setNext(newInst);
                        inst.setPrev(newInst);
                        if(inst == block.getHead()) block.setHead(newInst);
                    }
                    cur = null;
                }
            }
        })));
    }
}

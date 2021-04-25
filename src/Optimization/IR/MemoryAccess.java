package Optimization.IR;

import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.IROperand;

public class MemoryAccess {
    private IRModule module;

    public MemoryAccess(IRModule module){
        this.module = module;
    }

    public void run(){
        module.getExternalFunctionMap().forEach((id, func) -> {
            func.getBlockContain().forEach(block -> {
                module.getGlobalVariableList().forEach(var -> {
                    boolean flag = false;
                    IROperand value = null;
                    for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                        if(inst instanceof Load && ((Load) inst).getPointer() == var){
                            if(value != null) value = inst.getResult();
                            else{
                               IRInstruction newInst = new Move(block, inst.getResult(), value);
                               inst.Remove();
                               newInst.setPrev(inst.getPrev());
                               newInst.setNext(inst.getNext());
                               if(inst.getPrev() != null) inst.getPrev().setNext(newInst);
                               inst.getNext().setPrev(newInst);
                            }
                        }
                        else if(inst instanceof Store &&  ((Store) inst).getPointer() == var){
                            value = ((Store) inst).getValue();
                            flag = true;
                            inst.Remove();
                        }
                        else if(inst instanceof Call || inst == block.getTail()){
                            if(flag){
                                flag = false;
                                IRInstruction newInst = new Store(block, value, var);
                                newInst.setPrev(inst.getPrev());
                                newInst.setNext(inst);
                                inst.getPrev().setNext(newInst);
                                inst.setPrev(newInst);
                            }
                            value = null;
                        }
                    }
                });
            });
        });
    }
}

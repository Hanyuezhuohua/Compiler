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
        module.getExternalFunctionMap().forEach((id, func) -> {
            func.getBlockContain().forEach(block -> {
                module.getGlobalVariableList().forEach(var -> {
                    boolean flag = false;
                    IROperand value = null;
                    for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                        if(inst instanceof Load && ((Load) inst).getPointer() == var){
                            if(value == null) value = inst.getResult();
                            else{
                         //      IRInstruction newInst = new Move(block, inst.getResult(), value);
                               for(IRInstruction instruction: inst.getResult().getInstructions()){
                                    instruction.update((IRLocalRegister) inst.getResult(), value);
                                }
                               inst.Remove();
                          //     newInst.setPrev(inst.getPrev());
                          //     newInst.setNext(inst.getNext());
                          //     if(inst.getPrev() != null) inst.getPrev().setNext(newInst);
                          //     else block.setHead(newInst);
                          //     inst.getNext().setPrev(newInst);
                            }
                        }
                        else if(inst instanceof Store &&  ((Store) inst).getPointer() == var){
                            value = ((Store) inst).getValue();
                            flag = true;
                            inst.Remove();
                        }
                        else if((inst instanceof Call && module.getExternalFunctionMap().containsKey(((Call) inst).getFnptrval().getIdentifier())) || inst == block.getTail()){
                            if(flag){
                                flag = false;
                                IRInstruction newInst = new Store(block, value, var);
                                newInst.setPrev(inst.getPrev());
                                newInst.setNext(inst);
                                if(inst.getPrev() != null) inst.getPrev().setNext(newInst);
                                inst.setPrev(newInst);
                                if(inst == block.getHead()){
                                    block.setHead(newInst);
                                }
                            }
                            value = null;
                        }
                    }
                });
            });
        });
    }
}

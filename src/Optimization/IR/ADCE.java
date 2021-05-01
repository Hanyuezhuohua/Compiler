package Optimization.IR;

import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.IROperand;
import IR.IRtype.IRPointerType;
import IR.IRtype.IRVoidType;
import IR.IRutility.DefCollection;
import IR.IRutility.FuncBlockCollection;
import IR.IRutility.SideEffectCollection;
import backend.AST_IR.DominatorTree;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Stack;

public class ADCE {
    private IRModule module;
    private HashSet<IRInstruction> Instructions;
    private HashSet<IROperand> Operands;
    private boolean flag;

    public ADCE(IRModule module){
        this.module = module;
        Instructions = new LinkedHashSet<>();
        Operands = new LinkedHashSet<>();
        flag = false;
    }

    public boolean Flag() {
        return flag;
    }

    public void collectOperands(IROperand operand){
        operand.getInstructions().forEach(inst -> {
            if(inst instanceof Store){
                if(((Store) inst).getValue().getOperandType() instanceof IRPointerType){
                    if(!Operands.contains(((Store) inst).getValue())){
                        Operands.add(((Store) inst).getValue());
                        collectOperands(((Store) inst).getValue());
                    }
                    if(!Operands.contains(((Store) inst).getPointer())){
                        Operands.add(((Store) inst).getPointer());
                        collectOperands(((Store) inst).getPointer());
                    }
                }
            }
            else if(inst instanceof BitCast){
                if(inst.getResult().getOperandType() instanceof IRPointerType){
                    if(!Operands.contains(inst.getResult())){
                        Operands.add(inst.getResult());
                        collectOperands(inst.getResult());
                    }
                }
            }
            else if(inst instanceof GetElementPtr){
                if(inst.getResult().getOperandType() instanceof IRPointerType){
                    if(!Operands.contains(inst.getResult())){
                        Operands.add(inst.getResult());
                        collectOperands(inst.getResult());
                    }
                }
            }
            else if(inst instanceof Call){
                if(inst.getResult().getOperandType() instanceof IRPointerType){
                    if(!Operands.contains(inst.getResult())){
                        Operands.add(inst.getResult());
                        collectOperands(inst.getResult());
                    }
                }
            }
            else if(inst instanceof Load){
                if(inst.getResult().getOperandType() instanceof IRPointerType){
                    if(!Operands.contains(inst.getResult())){
                        Operands.add(inst.getResult());
                        collectOperands(inst.getResult());
                    }
                }
            }
            else if(inst instanceof Phi){
                if(inst.getResult().getOperandType() instanceof IRPointerType){
                    if(!Operands.contains(inst.getResult())){
                        Operands.add(inst.getResult());
                        collectOperands(inst.getResult());
                    }
                }
            }
        });
    }

    public void collectInstructions(IRInstruction instruction){
        Stack<IRInstruction> S = new Stack<>();
        S.push(instruction);
        while(!S.empty()){
            IRInstruction inst = S.pop();
            inst.getOperands().forEach(operand -> {
                if(operand.getDef() != null && !Instructions.contains(operand.getDef())){
                    Instructions.add(operand.getDef());
                    S.push(operand.getDef());
                }
                if(operand.getOperandType() instanceof IRPointerType){
                    operand.getInstructions().forEach(use -> {
                        if(!Instructions.contains(use)){
                            if(use instanceof BitCast){
                                Instructions.add(use);
                                S.push(use);
                            }
                            else if(use instanceof GetElementPtr){
                                Instructions.add(use);
                                S.push(use);
                            }
                            else if(use instanceof Phi){
                                Instructions.add(use);
                                S.push(use);
                            }
                            else if(use instanceof Load && use.getResult().getOperandType() instanceof IRPointerType){
                                Instructions.add(use);
                                S.push(use);
                            }
                            else if(use instanceof Store){
                                Instructions.add(use);
                                S.push(use);
                            }
                        }
                    });
                }
            });
            if(inst.getResult() != null && inst.getResult().getOperandType() instanceof IRPointerType){
                inst.getResult().getInstructions().forEach(use -> {
                    if(!Instructions.contains(use)){
                        if(use instanceof BitCast){
                            Instructions.add(use);
                            S.push(use);
                        }
                        else if(use instanceof GetElementPtr){
                            Instructions.add(use);
                            S.push(use);
                        }
                        else if(use instanceof Phi){
                            Instructions.add(use);
                            S.push(use);
                        }
                        else if(use instanceof Load && use.getResult().getOperandType() instanceof IRPointerType){
                            Instructions.add(use);
                            S.push(use);
                        }
                        else if(use instanceof Store){
                            Instructions.add(use);
                            S.push(use);
                        }
                    }
                });
            }
            inst.getInstIn().getPrev().forEach(prev -> {
                if(!Instructions.contains(prev.getTail())){
                    Instructions.add(prev.getTail());
                    S.push(prev.getTail());
                }
            });
        }
    }

    public void collect(){
        Operands.addAll(module.getGlobalVariableList());
        module.getExternalFunctionMap().forEach((id, func) -> {
            if(func.getClassPtr() != null && func.getClassPtr().getOperandType() instanceof IRPointerType) Operands.add(func.getClassPtr());
            func.getParameters().forEach(parameter -> {if(parameter.getOperandType() instanceof IRPointerType) Operands.add(parameter);});
            if(!(((Ret) func.getExit().getTail()).getValue().getOperandType() instanceof IRVoidType)){Operands.add(((Ret) func.getExit().getTail()).getValue());}
     //       func.setSideEffect(false);
        });
     //   HashSet<IROperand> OperandCopy = new LinkedHashSet<>(Operands);
     //   OperandCopy.forEach(operand -> collectOperands(operand));
        Stack<IROperand> OperandStack = new Stack<>();
        Operands.forEach(operand -> OperandStack.push(operand));
        while(!OperandStack.empty()){
            IROperand operand = OperandStack.pop();
            operand.getInstructions().forEach(inst -> {
                if(inst instanceof BitCast){
                    if(inst.getResult().getOperandType() instanceof IRPointerType){
                        if(!Operands.contains(inst.getResult())){
                            Operands.add(inst.getResult());
                            OperandStack.push(inst.getResult());
                        }
                    }
                }
                else if(inst instanceof GetElementPtr){
                    if(inst.getResult().getOperandType() instanceof IRPointerType){
                        if(!Operands.contains(inst.getResult())){
                            Operands.add(inst.getResult());
                            OperandStack.push(inst.getResult());
                        }
                    }
                }
                else if(inst instanceof Call){
                    if(inst.getResult().getOperandType() instanceof IRPointerType){
                        if(!Operands.contains(inst.getResult())){
                            Operands.add(inst.getResult());
                            OperandStack.push(inst.getResult());
                        }
                    }
                }
                else if(inst instanceof Load){
                    if(inst.getResult().getOperandType() instanceof IRPointerType){
                        if(!Operands.contains(inst.getResult())){
                            Operands.add(inst.getResult());
                            collectOperands(inst.getResult());
                        }
                    }
                }
                else if(inst instanceof Phi){
                    if(inst.getResult().getOperandType() instanceof IRPointerType){
                        if(!Operands.contains(inst.getResult())){
                            Operands.add(inst.getResult());
                            OperandStack.push(inst.getResult());
                        }
                    }
                }
                else if(inst instanceof Store){
                    if(((Store) inst).getValue().getOperandType() instanceof IRPointerType){
                        if(!Operands.contains(((Store) inst).getValue())){
                            Operands.add(((Store) inst).getValue());
                            OperandStack.push(((Store) inst).getValue());
                        }
                        if(!Operands.contains(((Store) inst).getPointer())){
                            Operands.add(((Store) inst).getPointer());
                            OperandStack.push(((Store) inst).getPointer());
                        }
                    }
                }
            });
        }
        new SideEffectCollection(module).runForADCE(Operands);
        module.getExternalFunctionMap().forEach((id, func) -> func.getBlockContain().forEach(block -> {
            for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()) if((inst instanceof Call && ((Call) inst).getFnptrval().hasSideEffect()) || (inst instanceof Store && Operands.contains(((Store) inst).getPointer())) || inst instanceof Ret) Instructions.add(inst);
        }));
        new DefCollection().visit(module);
      //  HashSet<IRInstruction> InstructionCopy = new LinkedHashSet<>(Instructions);
      //  InstructionCopy.forEach(this::collectInstructions);
        Stack<IRInstruction> InstStack = new Stack<>();
        Instructions.forEach(inst -> InstStack.push(inst));
        while(!InstStack.empty()){
            IRInstruction inst = InstStack.pop();
            inst.getOperands().forEach(operand -> {
                if(operand.getDef() != null && !Instructions.contains(operand.getDef())){
                    Instructions.add(operand.getDef());
                    InstStack.push(operand.getDef());
                }
                if(operand.getOperandType() instanceof IRPointerType){
                    operand.getInstructions().forEach(use -> {
                        if(!Instructions.contains(use)){
                            if(use instanceof BitCast){
                                Instructions.add(use);
                                InstStack.push(use);
                            }
                            else if(use instanceof GetElementPtr){
                                Instructions.add(use);
                                InstStack.push(use);
                            }
                            else if(use instanceof Phi){
                                Instructions.add(use);
                                InstStack.push(use);
                            }
                            else if(use instanceof Load && use.getResult().getOperandType() instanceof IRPointerType){
                                Instructions.add(use);
                                InstStack.push(use);
                            }
                            else if(use instanceof Store){
                                Instructions.add(use);
                                InstStack.push(use);
                            }
                        }
                    });
                }
            });
            if(inst.getResult() != null && inst.getResult().getOperandType() instanceof IRPointerType){
                inst.getResult().getInstructions().forEach(use -> {
                    if(!Instructions.contains(use)){
                        if(use instanceof BitCast){
                            Instructions.add(use);
                            InstStack.push(use);
                        }
                        else if(use instanceof GetElementPtr){
                            Instructions.add(use);
                            InstStack.push(use);
                        }
                        else if(use instanceof Phi){
                            Instructions.add(use);
                            InstStack.push(use);
                        }
                        else if(use instanceof Load && use.getResult().getOperandType() instanceof IRPointerType){
                            Instructions.add(use);
                            InstStack.push(use);
                        }
                        else if(use instanceof Store){
                            Instructions.add(use);
                            InstStack.push(use);
                        }
                    }
                });
            }
            inst.getInstIn().getPrev().forEach(prev -> {
                if(!Instructions.contains(prev.getTail())){
                    Instructions.add(prev.getTail());
                    InstStack.push(prev.getTail());
                }
            });
        }
    }

    public void Remove(){
        module.getExternalFunctionMap().forEach((id, func) -> {
            func.getBlockContain().forEach(block -> {
                for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                    if(!Instructions.contains(inst)){
                        inst.Remove();
                        flag = true;
                    }
                }
            });
            new FuncBlockCollection().BlockCollecting(func);
            new DominatorTree(func).Lengauer_Tarjan();
        });
    }

    public void run(){
        Operands.clear();
        Instructions.clear();
        collect();
        Remove();
    }
}

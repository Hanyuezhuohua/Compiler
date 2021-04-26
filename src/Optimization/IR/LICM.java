package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRGlobalVariable;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRPointerType;
import IR.IRutility.DefCollection;
import IR.IRutility.FuncBlockCollection;
import IR.IRutility.UseClear;
import IR.IRutility.UseCollection;
import backend.AST_IR.DominatorTree;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LICM {
    private HashSet<IRBasicBlock> tails;
    private HashSet<IRBasicBlock> loopBlocks;
    private boolean newLICM = false;
    public LICM(){
        tails = new LinkedHashSet<>();
        loopBlocks = new LinkedHashSet<>();
    }

    public boolean NewLICM() {
        return newLICM;
    }

    public boolean check(IRInstruction inst){
        assert inst instanceof Load;
        if(!(((Load) inst).getPointer() instanceof IRLocalRegister || ((Load) inst).getPointer() instanceof IRGlobalVariable)) return true;
        else if(((Load) inst).getPointer() instanceof IRGlobalVariable){
            for(IRBasicBlock block: loopBlocks){
                for(IRInstruction instruction = block.getHead(); instruction != null; instruction = instruction.getNext()){
                    if(instruction instanceof Store && ((Store) instruction).getPointer() == ((Load) inst).getPointer()) return false;
                }
            }
            return true;
        }
        else if(((Load) inst).getPointer().getOperandType() instanceof IRPointerType && ((IRPointerType) ((Load) inst).getPointer().getOperandType()).getPointTo() instanceof IRPointerType){
            if(((Load) inst).getPointer().getDef() != null && loopBlocks.contains(((Load) inst).getPointer().getDef().getInstIn())) return false;
            for(IRBasicBlock block: loopBlocks){
                for(IRInstruction instruction = block.getHead(); instruction != null; instruction = instruction.getNext()){
                    if(instruction instanceof Store && ((Store) instruction).getPointer().getOperandType() instanceof IRPointerType && ((IRPointerType) ((Store) instruction).getPointer().getOperandType()).getPointTo() instanceof IRPointerType) return false;
                }
            }
            return true;
        }
        return false;
    }

    public void run(IRModule module, boolean flag1){
        new UseClear().visit(module);
        new UseCollection().visit(module);
        new DefCollection().visit(module);
        module.getExternalFunctionMap().forEach((id, func) -> {
            func.setBlockContain(new FuncBlockCollection().BlockCollecting(func));
            new DominatorTree(func).Lengauer_Tarjan();
            for(IRBasicBlock block : func.getBlockContain()){
                tails.clear();
                loopBlocks.clear();
                if((block.getTail() instanceof Br && ((Br) block.getTail()).getCond() != null) || block.getTail() instanceof Ret) continue;
                IRBasicBlock head = ((Br) block.getTail()).getIfTrue();
                for(IRBasicBlock potentialTails: func.getBlockContain()){
                    if(potentialTails != block && potentialTails.DomBy(head) && potentialTails.getTail() instanceof Br && ((Br) potentialTails.getTail()).getCond() == null && ((Br) potentialTails.getTail()).getIfTrue() == head){
                        tails.add(potentialTails);
                    }
                }
                if(tails.isEmpty()) continue;
                loopBlocks.add(head);
                loopBlocks.addAll(tails);
                Queue<IRBasicBlock> q = new LinkedList<>(tails);
                while (!q.isEmpty()){
                    IRBasicBlock top = q.poll();
                    top.getPrev().forEach(prev -> {
                        if(!loopBlocks.contains(prev)){
                            loopBlocks.add(prev);
                            q.add(prev);
                        }
                    });
                }
                boolean flag = false;
                for(IRBasicBlock loopBlock : loopBlocks){
                    for (IRInstruction inst = loopBlock.getHead(); inst != null; inst = inst.getNext()){
                        if(inst instanceof Call){
                            flag = true;
                            break;
                        }
                    }
                    for(IRBasicBlock next: loopBlock.getNext()){
                        if(loopBlock != head && !loopBlocks.contains(next)) flag = true;
                    }
                    if(flag) break;
                }
                if(flag) continue;
                for(IRBasicBlock loopBlock : loopBlocks){
                    for (IRInstruction inst = loopBlock.getHead(); inst != null; inst = inst.getNext()){
                        if(inst instanceof Binary && ((!flag1 && func.getIdentifier().equals("main")) || flag1)){
                            boolean canMove = true;
                            for(IROperand operand: inst.getOperands()){
                                if(operand instanceof IRLocalRegister && operand.getDef() != null && loopBlocks.contains(operand.getDef().getInstIn())){
                                    canMove = false;
                                    break;
                                }
                            }
                            if(canMove){
                                newLICM = true;
                                inst.Remove();
                                inst.setInstIn(block);
                                block.addInstBeforeTail(inst);
                                inst.setInstIn(block);
                            }
                        }
                        else if(inst instanceof BitwiseBinary && flag1){
                            boolean canMove = true;
                            for(IROperand operand: inst.getOperands()){
                                if(operand instanceof IRLocalRegister && operand.getDef() != null && loopBlocks.contains(operand.getDef().getInstIn())){
                                    canMove = false;
                                    break;
                                }
                            }
                            if(canMove){
                                inst.Remove();
                                inst.setInstIn(block);
                                block.addInstBeforeTail(inst);
                                inst.setInstIn(block);
                            }
                        }
                        else if(inst instanceof BitCast || inst instanceof Zext || inst instanceof Trunc){
                            boolean canMove = true;
                            for(IROperand operand: inst.getOperands()){
                                if(operand instanceof IRLocalRegister && operand.getDef() != null && loopBlocks.contains(operand.getDef().getInstIn())){
                                    canMove = false;
                                    break;
                                }
                            }
                            if(canMove){
                                newLICM = true;
                                inst.Remove();
                                inst.setInstIn(block);
                                block.addInstBeforeTail(inst);
                                inst.setInstIn(block);
                            }
                        }
                        else if(inst instanceof GetElementPtr && flag1){
                            boolean canMove = true;
                            for(IROperand operand: inst.getOperands()){
                                if(operand instanceof IRLocalRegister && operand.getDef() != null && loopBlocks.contains(operand.getDef().getInstIn())){
                                    canMove = false;
                                    break;
                                }
                            }
                            if(canMove){
                                newLICM = true;
                                inst.Remove();
                                inst.setInstIn(block);
                                block.addInstBeforeTail(inst);
                                inst.setInstIn(block);
                            }
                        }
                        if(inst instanceof Load && flag1){
                            if(check(inst)){
                                inst.Remove();
                                inst.setInstIn(block);
                                block.addInstBeforeTail(inst);
                                inst.setInstIn(block);
                            }
                        }
                    }
                }
            }
        });
    }
}

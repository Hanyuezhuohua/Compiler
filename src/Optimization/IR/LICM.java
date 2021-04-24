package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.DefCollection;
import IR.IRutility.FuncBlockCollection;
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

    public void run(IRModule module){
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
                    if(potentialTails.DomBy(head) && potentialTails.getTail() instanceof Br && ((Br) potentialTails.getTail()).getCond() == null && ((Br) potentialTails.getTail()).getIfTrue() == head){
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
                    if(flag) break;
                }
                if(flag) continue;
                for(IRBasicBlock loopBlock : loopBlocks){
                    for (IRInstruction inst = loopBlock.getHead(); inst != null; inst = inst.getNext()){
                        if(inst instanceof Binary){
                            boolean canMove = true;
                            for(IROperand operand: inst.getOperands()){
                                if(operand instanceof IRLocalRegister && loopBlocks.contains(operand.getDef().getInstIn())){
                                    canMove = false;
                                    break;
                                }
                            }
                            if(canMove){
                                newLICM = true;
                                inst.Remove();
                                inst.setInstIn(block);
                                block.addInstBeforeTail(inst);
                            }
                        }
                        else if(inst instanceof BitwiseBinary){
                            boolean canMove = true;
                            for(IROperand operand: inst.getOperands()){
                                if(operand instanceof IRLocalRegister && loopBlocks.contains(operand.getDef().getInstIn())){
                                    canMove = false;
                                    break;
                                }
                            }
                            if(canMove){
                                inst.Remove();
                                inst.setInstIn(block);
                                block.addInstBeforeTail(inst);
                            }
                        }
                        else if(inst instanceof BitCast){
                            boolean canMove = true;
                            for(IROperand operand: inst.getOperands()){
                                if(operand instanceof IRLocalRegister && loopBlocks.contains(operand.getDef().getInstIn())){
                                    canMove = false;
                                    break;
                                }
                            }
                            if(canMove){
                                newLICM = true;
                                inst.Remove();
                                inst.setInstIn(block);
                                block.addInstBeforeTail(inst);
                            }
                        }
                        else if(inst instanceof GetElementPtr){
                            boolean canMove = true;
                            for(IROperand operand: inst.getOperands()){
                                if(operand instanceof IRLocalRegister && loopBlocks.contains(operand.getDef().getInstIn())){
                                    canMove = false;
                                    break;
                                }
                            }
                            if(canMove){
                                newLICM = true;
                                inst.Remove();
                                inst.setInstIn(block);
                                block.addInstBeforeTail(inst);
                            }
                        }
                        else if(inst instanceof Load){

                        }
                    }
                }
            }
        });
    }
}

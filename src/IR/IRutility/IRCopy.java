package IR.IRutility;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.IRInstruction;
import IR.IRoperand.IROperand;

import java.util.ArrayList;
import java.util.HashMap;

public class IRCopy {
    private HashMap<IROperand, IROperand> operandMap;
    private HashMap<IRBasicBlock, IRBasicBlock> blockMap;

    public IRCopy(){
        operandMap = new HashMap<>();
        blockMap = new HashMap<>();
    }

    public void init(IRFunction func){
        func.getBlockContain().forEach(block -> {
            if(!blockMap.containsKey(block)) blockMap.put(block, new IRBasicBlock(block.getBlockIn(), block.getIdentifier() + "_copy"));
            for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                inst.instCopy(blockMap.get(block), this);
            }
        });
    }

    public void put(IROperand op1, IROperand op2){
        operandMap.put(op1, op2);
    }

    public IROperand get(IROperand operand){
        if(operand == null) return null;
        if(!operandMap.containsKey(operand)) operandMap.put(operand, operand.operandCopy());
        return operandMap.get(operand);
    }

    public IRBasicBlock get(IRBasicBlock block){
        if(block == null) return null;
        if(!blockMap.containsKey(block)) blockMap.put(block, new IRBasicBlock(block.getBlockIn(), block.getIdentifier() + "_copy"));
        return blockMap.get(block);
    }

    public void connect(){
        blockMap.forEach((block, copy) -> {
            copy.setNext(new ArrayList<>());
            copy.setPrev(new ArrayList<>());
            block.getNext().forEach(next -> copy.addNext(blockMap.get(next)));
            block.getPrev().forEach(prev -> copy.addPrev(blockMap.get(prev)));
        });
    }
}

package IR.IRutility;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.Br;
import IR.IRinstruction.Ret;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FuncBlockCollection {
    static LinkedHashSet<IRBasicBlock> visited;
    public FuncBlockCollection(){}

    public LinkedHashSet<IRBasicBlock> BlockCollecting(IRFunction func){
        Queue<IRBasicBlock> blockQueue = new LinkedList<>();
        LinkedHashSet<IRBasicBlock>blocks = new LinkedHashSet<>();
        blocks.add(func.getEntry());
        blockQueue.add(func.getEntry());
        while(!blockQueue.isEmpty()){
            IRBasicBlock block = blockQueue.poll();
            for(IRBasicBlock next: block.getNext()){
                if(!blocks.contains(next)){
                    next.setPrev(new ArrayList<>());
                    blocks.add(next);
                    blockQueue.add(next);
                }
            }
            block.setNext(new ArrayList<>());
            if(block.getTail() instanceof Br){
                block.link(((Br) block.getTail()).getIfTrue());
                block.link(((Br) block.getTail()).getIfFalse());
            }
        }
        return blocks;
    }
}

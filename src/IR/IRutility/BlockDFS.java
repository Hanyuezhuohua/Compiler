package IR.IRutility;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class BlockDFS {

    private IRFunction func;
    private HashSet<IRBasicBlock> visited;
    private ArrayList<BlockEdge> BlockList;

    public BlockDFS(IRFunction func){
        this.func = func;
        visited = new LinkedHashSet<>();
        BlockList = new ArrayList<>();
    }

    public ArrayList<BlockEdge> getBlockList() {
        return BlockList;
    }

    public void DFS(IRBasicBlock block){
        visited.add(block);
        block.setIdom(null);
        block.setDomFrontiers(new HashSet<>());
        block.getNext().forEach(next -> {
            if(!visited.contains(next)){
                BlockList.add(new BlockEdge(next, block));
                DFS(next);
            }
        });
    }

    public void run(){
        BlockList.add(new BlockEdge(func.getEntry(), null));
        DFS(func.getEntry());
    }
}

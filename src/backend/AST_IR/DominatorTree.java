package backend.AST_IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// learn from TA yzh

public class DominatorTree {
    private ArrayList<ArrayList<IRBasicBlock>> dom;
    private int num;
    private HashMap<IRBasicBlock, Integer> dfn;
    private ArrayList<IRBasicBlock> rak;
    private HashMap<IRBasicBlock, IRBasicBlock> sdom;
    private HashMap<IRBasicBlock, IRBasicBlock> union;
    private HashMap<IRBasicBlock, IRBasicBlock> var;
    private HashMap<IRBasicBlock, IRBasicBlock> fa;
    private IRFunction func;

    public DominatorTree(IRFunction func){
        dom = new ArrayList<>();
        num = 0;
        dfn = new HashMap<>();
        rak = new ArrayList<>();
        sdom = new HashMap<>();
        union = new HashMap<>();
        var = new HashMap<>();
        fa = new HashMap<>();
        this.func = func;
    }

    private void BlockDFS(IRBasicBlock block){
        block.setIdom(null);
        block.setDomFrontiers(new HashSet<>());
        dfn.put(block, num++);
        rak.add(block);
        sdom.put(block, block);
        union.put(block, block);
        var.put(block, block);
        block.getNext().forEach(next -> {
            if(!dfn.containsKey(next)){
                BlockDFS(next);
                fa.put(next, block);
            }
        });
    }

    public IRBasicBlock find(IRBasicBlock block){
        if(union.get(block) != union.get(union.get(block))){
            if(dfn.get(sdom.get(var.get(block))) > dfn.get(sdom.get(find(union.get(block))))) var.put(block, find(union.get(block)));
            union.put(block, union.get(union.get(block)));
        }
        return var.get(block);
    }

    public void init(){
        IRBasicBlock entry = func.getEntry();
        BlockDFS(entry);
        for(int i = 0; i < num; ++i) dom.add(new ArrayList<>());
    }

    public void setSDom(){
        for(int i = num - 1; i > 0; --i){
            IRBasicBlock block = rak.get(i);
            for(IRBasicBlock prev : block.getPrev()){
                IRBasicBlock found = find(prev);
                if(dfn.get(sdom.get(block)) > dfn.get(sdom.get(found))) sdom.put(block, sdom.get(found));
            }
            dom.get(dfn.get(sdom.get(block))).add(block);
            IRBasicBlock tmp = fa.get(block);
            union.put(block, tmp);
            for(IRBasicBlock cur : dom.get(dfn.get(tmp))){
                IRBasicBlock Tmp = find(cur);
                cur.setIdom(dfn.get(sdom.get(Tmp)) < dfn.get(sdom.get(tmp)) ? Tmp : tmp);
            }
            dom.get(dfn.get(tmp)).clear();
        }
    }

    public void setIDom(){
        for(int i = 1; i < num; ++i){
            IRBasicBlock block = rak.get(i);
            if(block.getIdom() != sdom.get(block)) block.setIdom(block.getIdom().getIdom());
        }
    }

    public void setDomFrontier(){
        for(int i = 1; i < num; ++i){
            IRBasicBlock block = rak.get(i);
            if(block.getPrev().size() > 0){
                for (IRBasicBlock prev : block.getPrev()){
                    while(prev != block.getIdom()){
                        prev.addDomFrontier(block);
                        prev = prev.getIdom();
                    }
                }
            }
        }
    }

    public void Lengauer_Tarjan(){
        init();
        setSDom();
        setIDom();
        setDomFrontier();
    }
}

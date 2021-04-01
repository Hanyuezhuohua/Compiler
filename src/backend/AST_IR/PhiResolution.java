package backend.AST_IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.Br;
import IR.IRinstruction.IRInstruction;
import IR.IRinstruction.Move;
import IR.IRinstruction.Phi;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRConstVoid;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.BlockEdge;
import IR.IRutility.FuncBlockCollection;
import IR.IRutility.ParallelCopy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class PhiResolution {
    private HashSet<BlockEdge> edges;
    private HashMap<IRBasicBlock, ParallelCopy> copies;
    private IRFunction currentFunc;

    public PhiResolution(){
        edges = new HashSet<>();
        copies = new HashMap<>();
        currentFunc = null;
    }

    private void Collect(IRBasicBlock block){
        if(block.getNext().size() > 1){
            block.getNext().forEach(next -> {
                if(next.getPrev().size() > 1){
                    edges.add(new BlockEdge(block, next));
                }
            });
        }
    }

    private void Connect(BlockEdge edge){
        IRBasicBlock transfer = new IRBasicBlock(currentFunc, "transfer_station");
        currentFunc.addBlockContain(transfer);
        transfer.addInst(new Br(transfer, null, edge.getNext(), null));
        for(IRInstruction inst = edge.getNext().getHead(); inst instanceof Phi; inst = inst.getNext()){
            for(int i = 0; i < ((Phi)inst).getLabels().size(); ++i) if(((Phi)inst).getLabels().get(i) == edge.getPrev()) ((Phi)inst).getLabels().set(i, transfer);
        }
        edge.getPrev().updateBlock(edge.getNext(), transfer);
    }

    private void init(){
        copies = new HashMap<>();
        currentFunc.getBlockContain().forEach(block -> copies.put(block, new ParallelCopy()));
    }

    private IRBasicBlock update(IRBasicBlock block){
        if(block.getHead() != block.getTail() || !(block.getTail() instanceof Br && ((Br) block.getTail()).getCond() == null)) {
            return block;
        }
        IRBasicBlock New = update(((Br) block.getTail()).getIfTrue());
        if(!((Br) block.getTail()).getIfTrue().equals(New)) ((Br) block.getTail()).updateBlock(((Br) block.getTail()).getIfTrue(), New);
        return New;
    }

    private boolean blockCond(ParallelCopy copy){
        for(Move inst : copy.getCopies()) if(!inst.getValue().equals(inst.getResult())) return true;
        return false;
    }

    private void RemovePhi(IRBasicBlock block){
        IRInstruction inst = block.getHead();
        while(inst instanceof Phi){
            IRInstruction tmp = inst.getNext();
            inst.Remove();
            inst = tmp;
        }
    }

    private void addMove(IRBasicBlock block){
        for(IRInstruction inst = block.getHead(); inst instanceof Phi; inst = inst.getNext()){
            for(int i = 0; i < ((Phi)inst).getLabels().size(); ++i){
                IRBasicBlock label = ((Phi) inst).getLabels().get(i);
                IROperand value = ((Phi) inst).getValues().get(i);
                if(!(value instanceof IRConstVoid)){
                    copies.get(label).add(new Move(label, inst.getResult(), value));
                }
            }
        }
    }


    private void updateBlock(IRBasicBlock block){
        if(block.getTail() instanceof Br){
            IRBasicBlock ifTrue = update(((Br) block.getTail()).getIfTrue());
            if(!((Br) block.getTail()).getIfTrue().equals(ifTrue)) block.updateBlock(((Br) block.getTail()).getIfTrue(), ifTrue);
            if(((Br) block.getTail()).getIfFalse() != null){
                IRBasicBlock ifFalse = update(((Br) block.getTail()).getIfFalse());
                if(!((Br) block.getTail()).getIfFalse().equals(ifFalse)) block.updateBlock(((Br) block.getTail()).getIfFalse(), ifFalse);
            }
        }
    }

    private void resolveBlock(IRBasicBlock block){
        ParallelCopy copy = copies.get(block);
        boolean flag = true;
        while (blockCond(copy)){
            for(Iterator<Move> i = copy.getCopies().iterator(); i.hasNext(); ){
                Move inst = i.next();
                if(copy.getUseCnt().getOrDefault(inst.getResult(), 0) == 0){
                    i.remove();
                    if(inst.getValue() instanceof IRLocalRegister) copy.getUseCnt().put(inst.getValue(), copy.getUseCnt().get(inst.getValue()) - 1);
                    inst.addUse();
                    block.getTail().Insert(inst);
                    flag = false;
                }
            }
        }
        if(flag){
            for(Move inst: copy.getCopies()){
                if(inst.getValue() != inst.getResult()){
                    IROperand tmp = new IRLocalRegister(inst.getValue().getOperandType(), "copy");
                    Move move = new Move(block, inst.getResult(), inst.getValue());
                    move.addUse();
                    block.getTail().Insert(move);
                    copy.getUseCnt().remove(inst.getValue());
                    copy.getCopies().forEach(moveInst -> moveInst.update((IRLocalRegister) inst.getValue(), tmp));
                    break;
                }
            }
        }
    }

    public void run(IRModule module){
        module.getExternalFunctionMap().forEach((id, func) -> {
            edges = new HashSet<>();
            currentFunc = func;
            currentFunc.getBlockContain().forEach(this::Collect);
            edges.forEach(this::Connect);
            init();
            currentFunc.getBlockContain().forEach(this::addMove);
            currentFunc.getBlockContain().forEach(this::resolveBlock);
            currentFunc.getBlockContain().forEach(this::updateBlock);
            currentFunc.getBlockContain().forEach(this::RemovePhi);
            FuncBlockCollection collector = new FuncBlockCollection();
            currentFunc.setBlockContain(collector.BlockCollecting(currentFunc));
            new DominatorTree(currentFunc).Lengauer_Tarjan();
 //           currentFunc.getBlockContain().forEach(this::updateBlock);
            currentFunc = null;
        });
    }
}

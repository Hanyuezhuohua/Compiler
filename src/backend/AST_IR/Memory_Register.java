package backend.AST_IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;

import java.util.*;

public class Memory_Register {
    private HashSet<IRLocalRegister> varAlloc;
    private HashMap<IROperand, IROperand> edge;
    private HashSet<IRBasicBlock> blocks;
    private HashMap<IRBasicBlock, HashSet<Load>> loadAlloc;
    private HashMap<IRBasicBlock, HashMap<IROperand, Phi>> phiAlloc;
    private HashMap<IRBasicBlock, HashMap<IROperand, IROperand>> storeAlloc;
    private IRFunction currentFunc;


    public Memory_Register(){
        varAlloc = new LinkedHashSet<>();
        edge = new HashMap<>();
        blocks = new LinkedHashSet<>();
        loadAlloc = new LinkedHashMap<>();
        phiAlloc = new LinkedHashMap<>();
        storeAlloc = new LinkedHashMap<>();
        currentFunc = null;
    }

    private IROperand find(IROperand operand){
        if (edge.containsKey(operand)){
            IROperand next_1 = edge.get(operand);
            IROperand next_2 = find(next_1);
            if(!next_1.equals(next_2)) edge.put(operand, next_2);
            return next_2;
        }
        return operand;
    }

    private void init(){
        varAlloc = currentFunc.getVarAlloc();
        edge = new HashMap<>();
        blocks = new LinkedHashSet<>();
        loadAlloc = new LinkedHashMap<>();
        phiAlloc = new LinkedHashMap<>();
        storeAlloc = new LinkedHashMap<>();
        for(IRBasicBlock block: currentFunc.getBlockContain()){
            loadAlloc.put(block, new LinkedHashSet<>());
            phiAlloc.put(block, new LinkedHashMap<>());
            storeAlloc.put(block, new LinkedHashMap<>());
        }
    }

    private void Allocate(IRBasicBlock block){
        for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
            if(inst instanceof Load){
                IROperand pointer = ((Load) inst).getPointer();
                if(pointer instanceof IRLocalRegister && varAlloc.contains(pointer)){
                    HashMap<IROperand, IROperand> Stores = storeAlloc.get(block);
                    if(Stores.containsKey(pointer)){
                        edge.put(inst.getResult(), Stores.get(pointer));
                        inst.Remove();
                    }
                    else loadAlloc.get(block).add((Load) inst);
                }
            }
            else if(inst instanceof Store){
                IROperand pointer = ((Store) inst).getPointer();
                if(pointer instanceof IRLocalRegister && varAlloc.contains(pointer)){
                    blocks.add(block);
                    storeAlloc.get(block).put(pointer, ((Store) inst).getValue());
                    inst.Remove();
                }
            }
        }
    }

    private void AllocatePhi(){
        while (blocks.size() > 0){
            HashSet<IRBasicBlock> copy = blocks;
            blocks = new HashSet<>();
            for(IRBasicBlock block: copy){
                HashMap<IROperand, IROperand> Stores = storeAlloc.get(block);
                block.getDomFrontiers().forEach(domFrontier -> Stores.forEach((k, v) -> {
                    if(!phiAlloc.get(domFrontier).containsKey(k)){
                        IRLocalRegister result = new IRLocalRegister(v.getOperandType(), k.getIdentifier() + "_phi");
                        Phi inst = new Phi(domFrontier, new ArrayList<>(), new ArrayList<>(), result);
                        domFrontier.addInst(inst);
                        if(!storeAlloc.get(domFrontier).containsKey(k)){
                            storeAlloc.get(domFrontier).put(k, result);
                            blocks.add(domFrontier);
                        }
                        phiAlloc.get(domFrontier).put(k, inst);
                    }
                }));
            }
        }
    }

    private void addEdge(IRBasicBlock block){
        if(!phiAlloc.get(block).isEmpty()){
            phiAlloc.get(block).forEach((pointer, inst) -> block.getPrev().forEach(prev -> {
                IRBasicBlock tmp = prev;
                while(!storeAlloc.get(tmp).containsKey(pointer)) tmp = tmp.getIdom();
                inst.addBranch(storeAlloc.get(tmp).get(pointer), prev);
            }));
        }
        if(!loadAlloc.get(block).isEmpty()){
            loadAlloc.get(block).forEach(inst -> {
                IROperand result = inst.getResult();
                IROperand pointer = inst.getPointer();
                IROperand end;
                if (phiAlloc.get(block).containsKey(pointer)) end = phiAlloc.get(block).get(pointer).getResult();
                else {
                    IRBasicBlock tmp = block.getIdom();
                    while(!storeAlloc.get(tmp).containsKey(pointer)) tmp = tmp.getIdom();
                    end = storeAlloc.get(tmp).get(pointer);
                }
                edge.put(result, find(end));
                inst.Remove();
            });
        }
    }


    public void run(IRModule module){
        module.getExternalFunctionMap().forEach((id, func) -> {
            currentFunc = func;
            init();
            func.getBlockContain().forEach(this::Allocate);
            AllocatePhi();
            func.getBlockContain().forEach(this::addEdge);
            edge.forEach((start, end) -> ((IRLocalRegister)start).update(find(end)));
            while (func.getEntry().getHead() instanceof Alloca)func.getEntry().getHead().Remove();
            currentFunc = null;
        });
    }
}

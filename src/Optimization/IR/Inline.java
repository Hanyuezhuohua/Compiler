package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.Call;
import IR.IRinstruction.IRInstruction;
import IR.IRinstruction.Ret;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRLocalRegister;
import IR.IRutility.FuncBlockCollection;
import IR.IRutility.FuncCallCollection;
import IR.IRutility.IRCopy;
import backend.AST_IR.DominatorTree;

import java.util.*;

public class Inline {
    private IRModule module;
    private FuncCallCollection collection;
    private HashSet<IRFunction> inlineFunc;
    private HashMap<IRFunction, Integer> instNum;
    private HashMap<Call, IRFunction> inlines;
    private HashSet<IRFunction> inlined;
    private HashMap<IRFunction, Integer> inlineTime;
    private boolean newInline;
    private static int inlineINF = 750;
    private boolean flag;
    private boolean inlineRecursion;

    public Inline(IRModule module, boolean inlineRecursion){
        this.inlineRecursion = inlineRecursion;
        this.module = module;
        this.collection = new FuncCallCollection(module);
        inlineFunc = new LinkedHashSet<>();
        instNum = new LinkedHashMap<>();
        inlines = new LinkedHashMap<>();
        inlined = new LinkedHashSet<>();
        inlineTime = new LinkedHashMap<>();
        module.getExternalFunctionMap().forEach((id, func) -> inlineTime.put(func, 0));
        newInline = false;
        flag = false;
    }

    public boolean Flag() {
        return flag;
    }

    private void init(){
        inlineFunc.clear();
        instNum.clear();
        inlines.clear();
        newInline = false;
        collection.run();
        module.getExternalFunctionMap().forEach((id, func) -> {
 //           if(func.getCallee().size() == 0 || (inlineRecursion && !inlineFunc.contains(func) && func.getCallee().size() == 1 && func.getCallee().contains(func))) inlineFunc.add(func);
            if(func.getCallee().size() == 0 || (inlineRecursion && !inlineFunc.contains(func) && func.getCallee().size() == 1 && func.getCallee().contains(func))) inlineFunc.add(func);
            int cnt = 0;
            for (IRBasicBlock block : func.getBlockContain()) for (IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()) cnt++;
            instNum.put(func, cnt);
        });
        module.getInternalFunctionMap().forEach((id, func) -> instNum.put(func, inlineINF));
        module.getExternalFunctionMap().forEach((id, func) -> func.getBlockContain().forEach(block -> {
            for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()) if(inst instanceof Call && inlineFunc.contains(((Call) inst).getFnptrval()) && instNum.get(((Call) inst).getFnptrval()) < inlineINF) inlines.put((Call) inst, func);
        }));
     //   module.getExternalFunctionMap().forEach((id, func) -> func.getBlockContain().forEach(block -> {
     //       for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()) if(inst instanceof Call && (((Call) inst).getFnptrval() != func || inlineRecursion) && instNum.get(((Call) inst).getFnptrval()) < inlineINF) inlines.put((Call) inst, func);
     //   }));
    }

    private void inline(){
        for(Map.Entry<Call, IRFunction> entry : inlines.entrySet()){
            Call inst = entry.getKey();
            IRFunction func = entry.getValue();
            if(instNum.get(func) + instNum.get(inst.getFnptrval()) >= inlineINF || (func == inst.getFnptrval() && inlineTime.get(func) > 3)) continue;
            int num = inlineTime.get(func);
            inlineTime.put(func, ++num);
            newInline = true;
            flag = true;
            inlined.add(func);
            IRCopy copy = new IRCopy();
            if(inst.getFnptrval().getClassPtr() != null){
                copy.put(inst.getFnptrval().getClassPtr(), inst.getFunctionArgs().get(0));
                for(int i = 1; i < inst.getFunctionArgs().size(); ++i) copy.put(inst.getFnptrval().getParameters().get(i - 1), inst.getFunctionArgs().get(i));
            }
            else for (int i = 0; i < inst.getFunctionArgs().size(); ++i) copy.put(inst.getFnptrval().getParameters().get(i), inst.getFunctionArgs().get(i));
            copy.init(inst.getFnptrval(), func);
            copy.connect();
            IRBasicBlock newSplit = new IRBasicBlock(func, inst.getInstIn().getIdentifier() + "_split");
            inst.getInstIn().split(newSplit, inst);
            ((IRLocalRegister) inst.getResult()).update(((Ret) copy.get(inst.getFnptrval().getExit()).getTail()).getValue());
            copy.get(inst.getFnptrval().getExit()).getTail().Remove();
            copy.get(inst.getFnptrval().getExit()).merge(newSplit);
            inst.getInstIn().merge(copy.get(inst.getFnptrval().getEntry()));

            if(inst.getInstIn().equals(func.getExit()) && copy.get(inst.getFnptrval().getEntry()) != copy.get(inst.getFnptrval().getExit())) func.setExit(copy.get(inst.getFnptrval().getExit()));

            func.setBlockContain(new FuncBlockCollection().BlockCollecting(func));
            int cnt = 0;
            for (IRBasicBlock block : func.getBlockContain()) for (IRInstruction instruction = block.getHead(); instruction != null; instruction = instruction.getNext()) cnt++;
            instNum.put(func, cnt);
        }
    }

    private int bound = 6;

    public void run(){
        int cnt = 0;
        do{
            init();
            inline();
            cnt++;
            if(cnt > bound && inlineRecursion) break;
        }while (newInline);
        collection.run();
        inlined.forEach(func -> new DominatorTree(func).Lengauer_Tarjan());
    }
}

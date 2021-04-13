package Optimization.IR;

import IR.IRfunction.IRFunction;
import IR.IRinstruction.Call;
import IR.IRinstruction.IRInstruction;
import IR.IRinstruction.Load;
import IR.IRinstruction.Store;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRGlobalVariable;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRPointerType;
import IR.IRutility.FuncCallCollection;
import IR.IRutility.SideEffectCollection;
import IR.IRutility.UseClear;
import IR.IRutility.UseCollection;

import java.util.*;

public class RemoveLoadStore {
    private IRModule module;
    private HashSet<IRGlobalVariable> globals;
    private HashMap<IRGlobalVariable, IROperand> storeMap;
    private HashMap<IRGlobalVariable, IROperand> loadMap;
    private HashMap<IRFunction, HashSet<IRGlobalVariable>> uses;
    private HashMap<IRFunction, HashSet<IRGlobalVariable>> defs;


    public RemoveLoadStore(IRModule module){
        this.module = module;
        globals = new HashSet<>();
        storeMap = new HashMap<>();
        loadMap = new HashMap<>();
        uses = new LinkedHashMap<>();
        defs = new LinkedHashMap<>();
    }

    private void collectGlobal(){
        module.getExternalFunctionMap().forEach((id, func) -> {
            HashSet<IRGlobalVariable> Uses = new LinkedHashSet<>();
            HashSet<IRGlobalVariable> Defs = new LinkedHashSet<>();
            func.getBlockContain().forEach(block -> {
                for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                    for(IROperand operand : inst.getOperands()){
                        if(operand instanceof IRGlobalVariable){
                            Uses.add((IRGlobalVariable) operand);
                            if(inst instanceof Store) Defs.add((IRGlobalVariable) operand);
                        }
                    }
                }
            });
            uses.put(func, Uses);
            defs.put(func, Defs);
        });

        boolean modified;
        do{
            modified = false;
            for(IRFunction func : module.getExternalFunctionMap().values()){
                for (IRFunction caller : func.getCaller()){
                    if(!uses.get(caller).containsAll(uses.get(func))){
                        uses.get(caller).addAll(uses.get(func));
                        modified = true;
                    }
                }
            }
        }while (modified);
    }


    private void Change(IRFunction func){
        HashSet<IRGlobalVariable> Localize = new LinkedHashSet<>(uses.get(func));
        func.getCallee().forEach(callee -> Localize.removeAll(uses.get(callee)));
        func.getBlockContain().forEach(block -> {
            globals = new LinkedHashSet<>();
            loadMap.clear();
            storeMap.clear();
            IRInstruction call = null;
            for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                if(inst instanceof Call){
                    call = inst;
                    break;
                }
                for(IROperand operand : inst.getOperands()){
                    if(operand instanceof IRGlobalVariable){
                        globals.add((IRGlobalVariable) operand);
                    }
                }
            }
           // globals.retainAll(Localize);
            for(IRInstruction inst = block.getHead(); inst != null && !(inst instanceof Call); inst = inst.getNext()){
                if(inst instanceof Load && ((Load) inst).getPointer() instanceof IRGlobalVariable){
                    IRGlobalVariable var = (IRGlobalVariable) ((Load) inst).getPointer();
                    if(loadMap.containsKey(var)) continue;
                    else loadMap.put(var, inst.getResult());
                }
            }
            for(IRInstruction inst = call == null ? block.getTail() : call.getPrev(); inst != null; inst = inst.getPrev()){
                if(inst instanceof Store && ((Store) inst).getPointer() instanceof IRGlobalVariable){
                    IRGlobalVariable var = (IRGlobalVariable) ((Store) inst).getPointer();
                    if(storeMap.containsKey(var)) continue;
                    else storeMap.put(var, ((Store) inst).getValue());
                }
            }
            globals.forEach(global -> {
                if(loadMap.containsKey(global) && storeMap.containsKey(global)){
                    IROperand loadValue = null;
                    IROperand storeValue = null;
                    for(IRInstruction inst = block.getTail(); inst != null; inst = inst.getPrev()){
                        if(inst instanceof Load && ((Load) inst).getPointer() == global){
                            loadValue = inst.getResult();
                            if(loadValue != loadMap.get(global) && storeValue != null){
                                inst.Remove();
                                for (IRInstruction uses : loadValue.getInstructions()){
                                    uses.update((IRLocalRegister) loadValue, storeValue);
                                }
                            }
                        }
                        if(inst instanceof Store && ((Store) inst).getPointer() == global){
                            storeValue = ((Store) inst).getValue();
                            if(storeValue != storeMap.get(global)){
                                inst.Remove();
                            }
                        }
                    }
                }
            });
        });
    }

    public void run(){
        new UseClear().visit(module);
        new UseCollection().visit(module);
        new SideEffectCollection(module).run();
        collectGlobal();
        module.getExternalFunctionMap().forEach((id, func) -> Change(func));
    }
}

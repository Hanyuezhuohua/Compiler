package Optimization.IR;

import IR.IRfunction.IRFunction;
import IR.IRinstruction.Alloca;
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

public class GlobalToLocal {
    private IRModule module;
    private HashMap<IRFunction, HashSet<IRGlobalVariable>> uses;
    private HashMap<IRFunction, HashSet<IRGlobalVariable>> defs;

    public GlobalToLocal(IRModule module){
        this.module = module;
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
        HashMap<IRGlobalVariable, IRLocalRegister> ChangeMap = new LinkedHashMap<>();
        Localize.forEach(var -> {
            ChangeMap.put(var, new IRLocalRegister(var.getOperandType(), "local_" + var.getIdentifier()));
            func.addVar(ChangeMap.get(var));
        });
        func.getBlockContain().forEach(block -> {
            for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                HashSet<IROperand> uses = new LinkedHashSet<>(inst.getOperands());
                uses.retainAll(Localize);
                if(inst instanceof Load && uses.contains(((Load) inst).getPointer()))((Load) inst).setPointer(ChangeMap.get(((Load) inst).getPointer()));
                else if(inst instanceof Store && uses.contains(((Store) inst).getPointer()))((Store) inst).setPointer(ChangeMap.get(((Store) inst).getPointer()));
            }
        });
        for(Map.Entry<IRGlobalVariable, IRLocalRegister> data : ChangeMap.entrySet()){
            IRGlobalVariable global = data.getKey();
            IRLocalRegister local = data.getValue();
            IRLocalRegister tmp = new IRLocalRegister(((IRPointerType)global.getOperandType()).getPointTo(), "tmp");
            func.getEntry().addInstBefore(new Store(func.getEntry(), tmp, local));
            func.getEntry().addInstBefore(new Load(func.getEntry(), global, tmp));
            func.getEntry().addInstBefore(new Alloca(func.getEntry(), local));
            func.getEntry().addAlloc(local);
            if(defs.get(func).contains(global)){
                IRLocalRegister Tmp = new IRLocalRegister(((IRPointerType)global.getOperandType()).getPointTo(), "tmp");
                func.getExit().addInstBeforeTail(new Load(func.getExit(), local, Tmp));
                func.getExit().addInstBeforeTail(new Store(func.getExit(), Tmp, global));
            }
        }
    }

    public void run(){
        new UseClear().visit(module);
        new UseCollection().visit(module);
        new SideEffectCollection(module).run();
        new FuncCallCollection(module).run();
        collectGlobal();
        module.getExternalFunctionMap().forEach((id, func) -> {
            if(func.getCallee().size() == 0) Change(func);
        });
    }
}

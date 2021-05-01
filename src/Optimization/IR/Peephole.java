package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRinstruction.Call;
import IR.IRinstruction.IRInstruction;
import IR.IRinstruction.Load;
import IR.IRinstruction.Store;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRGlobalVariable;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.FuncBlockCollection;

import java.util.*;

public class Peephole {

    private IRModule module;
    private HashMap<IRGlobalVariable, IRInstruction> GlobalVarMap;
    private HashMap <IRInstruction, Integer> InstMap;
    private ArrayList<Store> StoreList;
    private boolean newLoadStoreRemove;
    private boolean flag;
    private int cnt;
    private static int Limit = 10000;

    public Peephole(IRModule module) {
        this.module = module;
        GlobalVarMap = new LinkedHashMap<>();
        InstMap = new LinkedHashMap<>();
        StoreList = new ArrayList<>();
    }

    private void init(){
        newLoadStoreRemove = false;
        GlobalVarMap.clear();
        InstMap.clear();
        StoreList.clear();
        cnt = 0;
    }

    private boolean CSEChecker(IRInstruction inst){
        for (Store store : StoreList) if (store.getPointer().getOperandType().CSEChecker(inst instanceof Store ? ((Store) inst).getPointer().getOperandType() : ((Load) inst).getPointer().getOperandType())) return false;
        return true;
    }

    private boolean OutRange(int a){
        return a - cnt > Limit;
    }

    private void PrevCollect(IRBasicBlock block){
        for (IRInstruction inst = block.getTail().getPrev(); inst != null && !(inst instanceof Call); inst = inst.getPrev(), cnt++) {
            if (inst instanceof Store && CSEChecker(inst)) {
                InstMap.put(inst, cnt);
                StoreList.add((Store) inst);
            }
            else if (inst instanceof Load && CSEChecker(inst)) InstMap.put(inst, cnt);
        }
    }

    private boolean LoadRemove(IRInstruction inst){
        for(Iterator<Map.Entry<IRInstruction, Integer>> iter = InstMap.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<IRInstruction, Integer> entry = iter.next();
            if(OutRange(entry.getValue())) iter.remove();
            else{
                IRInstruction i = entry.getKey();
                if (i instanceof Load && ((Load) i).getPointer().CSEChecker(((Load) inst).getPointer())) {
                    ((IRLocalRegister) inst.getResult()).update(i.getResult());
                    inst.Remove();
                    return true;
                }
                else if (i instanceof Store && ((Store) i).getPointer().CSEChecker(((Load) inst).getPointer())) {
                    ((IRLocalRegister) inst.getResult()).update(((Store) i).getValue());
                    inst.Remove();
                    return true;
                }
            }
        }
        return false;
    }

    private void uselessRemove(IRInstruction inst){
        for (Iterator<Map.Entry<IRInstruction, Integer>> iter = InstMap.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<IRInstruction, Integer> entry = iter.next();
            IRInstruction cur = entry.getKey();
            if(cur instanceof Store && ((Store) cur).getPointer().getOperandType().CSEChecker(((Store) inst).getPointer().getOperandType())) iter.remove();
            if(cur instanceof Load && ((Load) cur).getPointer().getOperandType().CSEChecker(((Store) inst).getPointer().getOperandType())) iter.remove();
        }
    }

    private boolean StoreRemove(IRInstruction inst){
        for (Iterator<Map.Entry<IRInstruction, Integer>> iter = InstMap.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<IRInstruction, Integer> entry = iter.next();
            if (OutRange(entry.getValue())) iter.remove();
            else{
                IRInstruction i = entry.getKey();
                if (i instanceof Store && !StoreList.contains(i) && ((Store) i).getPointer().CSEChecker(((Store) inst).getPointer())){
                    i.Remove();
                    iter.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean run() {
        flag = false;
        module.getExternalFunctionMap().forEach((s, function) -> {
            function.setBlockContain(new FuncBlockCollection().BlockCollecting(function));
            function.getBlockContain().forEach(block -> {
                do{
                    init();
                    if(block.DomByPrev()) PrevCollect(block.getIdom());
                    for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext(), cnt++) {
                        if(inst instanceof Load){
                            if(((Load) inst).getPointer() instanceof IRGlobalVariable && GlobalVarMap.containsKey(((Load) inst).getPointer())) {
                                IROperand value = GlobalVarMap.get(((Load) inst).getPointer()) instanceof Store ? ((Store) GlobalVarMap.get(((Load) inst).getPointer())).getValue() : GlobalVarMap.get(((Load) inst).getPointer()).getResult();
                                ((IRLocalRegister) inst.getResult()).update(value);
                                newLoadStoreRemove = true;
                                inst.Remove();
                            }
                            else if(((Load) inst).getPointer() instanceof IRGlobalVariable) GlobalVarMap.put((IRGlobalVariable) ((Load) inst).getPointer(), inst);
                            else{
                                if(LoadRemove(inst)) newLoadStoreRemove = true;
                                else InstMap.put(inst, cnt);
                            }
                        }
                        else if (inst instanceof Store) {
                            if (((Store) inst).getPointer() instanceof IRGlobalVariable && GlobalVarMap.containsKey(((Store) inst).getPointer()) ) {
                                if (GlobalVarMap.get(((Store) inst).getPointer()) instanceof Store && !StoreList.contains(GlobalVarMap.get(((Store) inst).getPointer()))) {
                                    GlobalVarMap.get(((Store) inst).getPointer()).Remove();
                                    newLoadStoreRemove = true;
                                }
                            }
                            if (((Store) inst).getPointer() instanceof IRGlobalVariable) GlobalVarMap.put((IRGlobalVariable) ((Store) inst).getPointer(), inst);
                            else{
                                if(StoreRemove(inst)) newLoadStoreRemove = true;
                                uselessRemove(inst);
                                InstMap.put(inst, cnt);
                            }
                        }
                        else if(inst instanceof Call){ GlobalVarMap.clear(); InstMap.clear(); }
                    }
                    if(newLoadStoreRemove) flag = true;
                } while(newLoadStoreRemove);
            });
        });
        return flag;
    }
}
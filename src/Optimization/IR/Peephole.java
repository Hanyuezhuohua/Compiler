package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.Call;
import IR.IRinstruction.IRInstruction;
import IR.IRinstruction.Load;
import IR.IRinstruction.Store;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRGlobalVariable;
import IR.IRoperand.IRLocalRegister;
import IR.IRutility.FuncBlockCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Peephole {

    private IRModule module;
//    private HashMap<IRGlobalVariable, IRInstruction> globalLoadStore = new HashMap<>();
//    private HashMap <IRInstruction, Integer> available = new HashMap<>();
//    private ArrayList<Store> protectedStore = new ArrayList<>();
//    private boolean newLoadStoreRemove;
    private boolean modified;
//    private int cnt;

    public Peephole(IRModule module) {
        this.module = module;
    }

    private void init(){
//        newLoadStoreRemove = false;
//        globalLoadStore.clear();
//        available.clear();
//        protectedStore.clear();
//        cnt = 0;
    }

    public void runForBlock(IRBasicBlock block) {
        boolean newLoadStoreRemove;
        do {
            newLoadStoreRemove = false;
            HashMap<IRGlobalVariable, IRInstruction> globalLoadStore = new HashMap<>();
            HashMap <IRInstruction, Integer> available = new HashMap<>();
            ArrayList<Store> protectedStore = new ArrayList<>();
//            init();
            int cnt = 0;
            if (block.getPrev().contains(block.getIdom()) && block.getPrev().size() == 1) {
                for (IRInstruction inst = block.getIdom().getTail().getPrev(); inst != null; inst = inst.getPrev()) {
                    cnt++;
                    if (inst instanceof Store) {
                        boolean collision = false;
                        for (Store store : protectedStore) {
                            if (store.getPointer().getOperandType().CSEChecker(((Store) inst).getPointer().getOperandType())) {
                                collision = true;
                                break;
                            }
                        }
                        if (collision) continue;
                        available.put(inst, cnt);
                        protectedStore.add((Store) inst);
                    } else if (inst instanceof Load) {
                        boolean collision = false;
                        for (Store store : protectedStore) {
                            if (store.getPointer().getOperandType().CSEChecker(((Load) inst).getPointer().getOperandType())) {
                                collision = true;
                                break;
                            }
                        }
                        if (collision) continue;
                        available.put(inst, cnt);
                    } else if (inst instanceof Call) {
                        break;
                    }
                }
            }
            for (IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()) {
                cnt++;
                if (inst instanceof Load ) {
                    if (((Load) inst).getPointer() instanceof IRGlobalVariable && globalLoadStore.containsKey(((Load) inst).getPointer())) {
                        IRGlobalVariable global = (IRGlobalVariable) ((Load) inst).getPointer();
                        IRInstruction last = globalLoadStore.get(global);
                        if (last instanceof Load) {
                            ((IRLocalRegister) inst.getResult()).update(last.getResult());
                        } else {
                            assert last instanceof Store;
                            ((IRLocalRegister) inst.getResult()).update(((Store)last).getValue());
                        }
                        newLoadStoreRemove = true;
                        inst.Remove();
                    } else {
                        if (((Load) inst).getPointer() instanceof IRGlobalVariable) {
                            globalLoadStore.put((IRGlobalVariable) ((Load) inst).getPointer(), inst);
                        }
                        else {
                            boolean replaced = false;
                            for (Iterator<Map.Entry<IRInstruction, Integer>> iter = available.entrySet().iterator(); iter.hasNext(); ) {
                                Map.Entry<IRInstruction, Integer> entry = iter.next();
                                if (entry.getValue() - cnt > 9999) {
                                    iter.remove();
                                } else {
                                    IRInstruction i = entry.getKey();
                                    if (i instanceof Load) {
                                        if (((Load) i).getPointer().CSEChecker(((Load) inst).getPointer())) {
                                            ((IRLocalRegister) inst.getResult()).update(i.getResult());
                                            inst.Remove();
                                            replaced = true;
                                            break;
                                        }
                                    } else if (i instanceof Store) {
                                        if (((Store) i).getPointer().CSEChecker(((Load) inst).getPointer())) {
                                            ((IRLocalRegister) inst.getResult()).update(((Store) i).getValue());
                                            inst.Remove();
                                            replaced = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!replaced) {
                                available.put(inst, cnt);
                            } else {
                                newLoadStoreRemove = true;
                            }
                        }
                    }
                } else if (inst instanceof Store) {
                    if (((Store) inst).getPointer() instanceof IRGlobalVariable && globalLoadStore.containsKey(((Store) inst).getPointer()) ) {
                        IRGlobalVariable global = (IRGlobalVariable) ((Store) inst).getPointer();
                        IRInstruction last = globalLoadStore.get(global);
                        if (last instanceof Store && !protectedStore.contains(last)) {
                            last.Remove();
                            newLoadStoreRemove = true;
                        }
                    }
                    if (((Store) inst).getPointer() instanceof IRGlobalVariable) {
                        globalLoadStore.put((IRGlobalVariable) ((Store) inst).getPointer(), inst);
                    } else {
                        boolean replaced = false;
                        for (Iterator<Map.Entry<IRInstruction, Integer>> iter = available.entrySet().iterator(); iter.hasNext(); ) {
                            Map.Entry<IRInstruction, Integer> entry = iter.next();
                            if (entry.getValue() - cnt > 9999) {
                                iter.remove();
                            } else {
                                IRInstruction i = entry.getKey();
                                if (i instanceof Store && !protectedStore.contains(i)) {
                                    if (((Store) i).getPointer().CSEChecker(((Store) inst).getPointer())) {
                                        i.Remove();
                                        iter.remove();
                                        replaced = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (replaced) newLoadStoreRemove = true;
                        for (Iterator<Map.Entry<IRInstruction, Integer>> iter = available.entrySet().iterator(); iter.hasNext(); ) {
                            Map.Entry<IRInstruction, Integer> entry = iter.next();
                            IRInstruction cur = entry.getKey();
                            if (cur instanceof Store) {
                                if (((Store) cur).getPointer().getOperandType().CSEChecker(((Store) inst).getPointer().getOperandType())) {
                                    iter.remove();
                                }
                            } else if (cur instanceof Load) {
                                if (((Load) cur).getPointer().getOperandType().CSEChecker(((Store) inst).getPointer().getOperandType())) {
                                    iter.remove();
                                }
                            }
                        }
                        available.put(inst, cnt);
                    }
                } else if (inst instanceof Call) {
                    globalLoadStore.clear();
                    available.clear();
                }
            }
            modified |= newLoadStoreRemove;
        } while (newLoadStoreRemove);
    }

    public boolean run() {
        modified = false;
        module.getExternalFunctionMap().forEach((s, function) -> {
            function.setBlockContain(new FuncBlockCollection().BlockCollecting(function));
            function.getBlockContain().forEach(this::runForBlock);
        });
        return modified;
    }
}

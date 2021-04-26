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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Peephole {

    IRModule module;
    public Peephole(IRModule root) {
        this.module = root;
    }

    boolean modified;

    void runForBlock(IRBasicBlock block) {
        boolean changed;
        do {
            changed = false;
            HashMap<IRGlobalVariable, IRInstruction> globalLoadStore = new HashMap<>(); // globals never have aliases
            HashMap <IRInstruction, Integer> available = new HashMap<>(); // used as pair, store the time stamp
            ArrayList<Store> protectedStore = new ArrayList<>(); // should not delete cross-block store
            int timeStamp = 0;
            if (block.getPrev().contains(block.getIdom()) && block.getPrev().size() == 1) {
                for (IRInstruction inst = block.getIdom().getTail().getPrev(); inst != null; inst = inst.getPrev()) {
                    timeStamp++;
                    if (inst instanceof Store) {
                        boolean collision = false;
                        for (Store store : protectedStore) {
                            if (store.getPointer().getOperandType().CSEChecker(((Store) inst).getPointer().getOperandType())) {
                                collision = true;
                                break;
                            }
                        }
                        if (collision) continue;
                        available.put(inst, timeStamp);
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
                        available.put(inst, timeStamp);
                    } else if (inst instanceof Call) {
                        break;
                    }
                }
            }
            for (IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()) {
                timeStamp++;
                if (inst instanceof Load ) {
                    if (((Load) inst).getPointer() instanceof IRGlobalVariable && globalLoadStore.containsKey((IRGlobalVariable) ((Load) inst).getPointer())) {
                        IRGlobalVariable global = (IRGlobalVariable) ((Load) inst).getPointer();
                        IRInstruction last = globalLoadStore.get(global);
                        if (last instanceof Load) {
                            ((IRLocalRegister) ((Load) inst).getResult()).update(((Load)last).getResult());
                        } else {
                            assert last instanceof Store;
                            ((IRLocalRegister) ((Load) inst).getResult()).update(((Store)last).getValue());
                        }
                        changed = true;
                        inst.Remove();
                    } else {
                        if (((Load) inst).getPointer() instanceof IRGlobalVariable) {
                            globalLoadStore.put((IRGlobalVariable) ((Load) inst).getPointer(), inst);
                        }
                        else {
                            boolean replaced = false;
                            for (Iterator<Map.Entry<IRInstruction, Integer>> iter = available.entrySet().iterator(); iter.hasNext(); ) {
                                Map.Entry<IRInstruction, Integer> entry = iter.next();
                                if (entry.getValue() - timeStamp > 9999) {
                                    iter.remove();
                                } else {
                                    IRInstruction i = entry.getKey();
                                    if (i instanceof Load) {
                                        if (((Load) i).getPointer().CSEChecker(((Load) inst).getPointer())) {
                                            ((IRLocalRegister) ((Load) inst).getResult()).update(((Load) i).getResult());
                                            inst.Remove();
                                            replaced = true;
                                            break;
                                        }
                                    } else if (i instanceof Store) {
                                        if (((Store) i).getPointer().CSEChecker(((Load) inst).getPointer())) {
                                            ((IRLocalRegister) ((Load) inst).getResult()).update(((Store) i).getValue());
                                            inst.Remove();
                                            replaced = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!replaced) {
                                available.put(inst, timeStamp);
                            } else {
                                changed = true;
                            }
                        }
                    }
                } else if (inst instanceof Store) {
                    if (((Store) inst).getPointer() instanceof IRGlobalVariable && globalLoadStore.containsKey((IRGlobalVariable) ((Store) inst).getPointer()) ) {
                        IRGlobalVariable global = (IRGlobalVariable) ((Store) inst).getPointer();
                        IRInstruction last = globalLoadStore.get(global);
                        if (last instanceof Store && !protectedStore.contains(last)) {
                            last.Remove();
                            changed = true;
                        }
                    }
                    if (((Store) inst).getPointer() instanceof IRGlobalVariable) {
                        globalLoadStore.put((IRGlobalVariable) ((Store) inst).getPointer(), inst);
                    } else {
                        boolean replaced = false;
                        for (Iterator<Map.Entry<IRInstruction, Integer>> iter = available.entrySet().iterator(); iter.hasNext(); ) {
                            Map.Entry<IRInstruction, Integer> entry = iter.next();
                            if (entry.getValue() - timeStamp > 9999) {
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
                        if (replaced) changed = true;
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
                        available.put(inst, timeStamp);
                    }
                } else if (inst instanceof Call) {
                    globalLoadStore.clear();
                    available.clear();
                }
            }
            modified |= changed;
        } while (changed);
    }

    void runForFunction(IRFunction function) {
        function.getBlockContain().forEach(this::runForBlock);
    }

    public boolean run() {
        modified = false;
        module.getExternalFunctionMap().forEach((s, function) -> runForFunction(function));
        return modified;
    }
}

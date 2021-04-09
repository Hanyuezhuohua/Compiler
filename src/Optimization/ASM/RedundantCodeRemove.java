package Optimization.ASM;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVfunction.RISCVFunction;
import RISCV.RISCVinstruction.*;
import RISCV.RISCVmodule.RISCVModule;
import RISCV.RISCVoperand.RISCVregister.RISCVPhysicalRegister;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class RedundantCodeRemove {
    private boolean modified;
    private boolean newRedundantCodeRemove;

    public RedundantCodeRemove(){
        modified = false;
        newRedundantCodeRemove = false;
    }

    public boolean hasNewRedundantCodeRemove() {
        return newRedundantCodeRemove;
    }

    public boolean sameAssign(RISCVInstruction inst){
        if(inst.getPrev() != null && inst.getPrev().Defs().size() == 1 && inst.Defs().size() == 1 && inst.getPrev().Defs().iterator().next().getColor() == inst.Defs().iterator().next().getColor()) return true;
        return false;
    }

    public void removeAssign(RISCVInstruction inst){
        while(!modified && sameAssign(inst)){
            modified = true;
            for(RISCVRegister register: inst.Uses()){
                if(register.getColor() == inst.Defs().iterator().next().getColor()){
                    modified = false;
                    break;
                }
            }
            if(modified){
                newRedundantCodeRemove = true;
                inst.getPrev().remove();
                modified = false;
            }
            else break;
        }
    }

    public void Eliminate(RISCVFunction func){
        for(RISCVBasicBlock block : func.getBlockContain()){
            HashMap<RISCVPhysicalRegister, RISCVInstruction> RegisterAllocation = new LinkedHashMap<>();
            for(RISCVInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                modified = false;
                if(inst instanceof RISCVLa){
                    if(RegisterAllocation.containsKey(((RISCVLa) inst).getRd().getColor())){
                        RISCVInstruction allocated = RegisterAllocation.get(((RISCVLa) inst).getRd().getColor());
                        if(allocated instanceof RISCVLa && ((RISCVLa) allocated).getRs().equals(((RISCVLa) inst).getRs())){
                            inst.remove();
                            modified = true;
                            newRedundantCodeRemove = true;
                        }
                    }
                }
                else if(inst instanceof RISCVLui){
                    if(RegisterAllocation.containsKey(((RISCVLui) inst).getRd().getColor())){
                        RISCVInstruction allocated = RegisterAllocation.get(((RISCVLui) inst).getRd().getColor());
                        if(allocated instanceof RISCVLui && ((RISCVLui) allocated).getImm().equals(((RISCVLui) inst).getImm())){
                            inst.remove();
                            modified = true;
                            newRedundantCodeRemove = true;
                        }
                    }
                }
                else if(inst instanceof RISCVLi){
                    if(RegisterAllocation.containsKey(((RISCVLi) inst).getRd().getColor())){
                        RISCVInstruction allocated = RegisterAllocation.get(((RISCVLi) inst).getRd().getColor());
                        if(allocated instanceof RISCVLi && ((RISCVLi) allocated).getRs() == ((RISCVLi) inst).getRs()){
                            inst.remove();
                            modified = true;
                            newRedundantCodeRemove = true;
                        }
                    }
                }
                else if(inst instanceof RISCVLoad){
                    if(RegisterAllocation.containsKey(((RISCVLoad) inst).getRd().getColor())){
                        RISCVInstruction allocated = RegisterAllocation.get(((RISCVLoad) inst).getRd().getColor());
                        if(allocated instanceof RISCVLoad && ((RISCVLoad) allocated).getRs() == ((RISCVLoad) inst).getRs() && ((RISCVLoad) allocated).getOffset().getValue() == ((RISCVLoad) inst).getOffset().getValue() && ((RISCVLoad) allocated).getWidth() == ((RISCVLoad) inst).getWidth()){
                            inst.remove();
                            modified = true;
                            newRedundantCodeRemove = true;
                        }
                    }
                }
    /*            else if(inst instanceof RISCVStore){
                    for(RISCVInstruction load = inst.getNext(); load != null; load = load.getNext()){
                        if(load instanceof RISCVLoad && ((RISCVStore) inst).getImm().getValue() == ((RISCVLoad) load).getOffset().getValue() && ((RISCVStore) inst).getWidth() == ((RISCVLoad) load).getWidth() && ((RISCVStore) inst).getRs1() == ((RISCVLoad) load).getRs() && ((RISCVStore) inst).getRs2().getColor() == ((RISCVLoad) load).getRd().getColor()){
                            load.remove();
                            modified = true;
                            newRedundantCodeRemove = true;
                        }
                    }
                }*/
                removeAssign(inst);
                if(!modified) for (RISCVRegister def : inst.Defs()) RegisterAllocation.put(def.getColor(), inst);
            }
        }
    }

    public void run(RISCVModule module){
        module.getExternalFunctionSet().forEach(func -> Eliminate(func));
    }
}

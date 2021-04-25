package Optimization.IR;

import IR.IRinstruction.Br;
import IR.IRinstruction.Call;
import IR.IRinstruction.Phi;
import IR.IRinstruction.Ret;
import IR.IRmodule.IRModule;

public class TailCall {
    private IRModule module;
    public TailCall(IRModule module){
        this.module = module;
    }

    public void run(){
        module.getExternalFunctionMap().forEach((id, func) -> {
            func.getBlockContain().forEach(block -> {
                if(block == func.getExit()
                        || (block.getTail() instanceof Br && ((Br) block.getTail()).getCond() == null && ((Br) block.getTail()).getIfTrue() == func.getExit()
                            && (func.getExit().getHead() instanceof Ret || (func.getExit().getHead() instanceof Phi && func.getExit().getHead().getNext() instanceof Ret)))){
                    if(block.getTail().getPrev() != null && block.getTail().getPrev() instanceof Call && ((Call) block.getTail().getPrev()).getFnptrval() == func){
                        ((Call) block.getTail().getPrev()).tailCall = true;
                    }
                }
            });
        });
    }
}

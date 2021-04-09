package IR.IRutility;

import IR.IRinstruction.Call;
import IR.IRinstruction.IRInstruction;
import IR.IRinstruction.Store;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRGlobalVariable;
import Util.error.ErrorMessage;

public class SideEffectCollection {
    private IRModule module;
    public SideEffectCollection(IRModule module){
        this.module = module;
    }

    public void run(){
        module.getInternalFunctionMap().forEach((id, func) -> {
            if(id.equals("_str_length")) func.setSideEffect(false);
            else if(id.equals("_str_substring")) func.setSideEffect(false);
            else if(id.equals("_str_parseInt")) func.setSideEffect(false);
            else if(id.equals("_str_ord")) func.setSideEffect(false);
            else if(id.equals("_str_concat")) func.setSideEffect(false);
            else if(id.equals("_str_eq")) func.setSideEffect(false);
            else if(id.equals("_str_ne")) func.setSideEffect(false);
            else if(id.equals("_str_lt")) func.setSideEffect(false);
            else if(id.equals("_str_gt")) func.setSideEffect(false);
            else if(id.equals("_str_le")) func.setSideEffect(false);
            else if(id.equals("_str_ge")) func.setSideEffect(false);
            else if(id.equals("_gbl_print")) func.setSideEffect(true);
            else if(id.equals("_gbl_println")) func.setSideEffect(true);
            else if(id.equals("_gbl_printInt")) func.setSideEffect(true);
            else if(id.equals("_gbl_printlnInt")) func.setSideEffect(true);
            else if(id.equals("_gbl_getString")) func.setSideEffect(true);
            else if(id.equals("_gbl_getInt")) func.setSideEffect(true);
            else if(id.equals("_gbl_toString")) func.setSideEffect(false);
            else if(id.equals("malloc")) func.setSideEffect(true);
            else throw new ErrorMessage();
        });

        new FuncCallCollection(module).run();

        module.getExternalFunctionMap().forEach((id, func) -> {
            func.getBlockContain().forEach(block -> {
                for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                    if(inst instanceof Call && ((Call) inst).getFnptrval().hasSideEffect()){
                        func.setSideEffect(true);
                        func.getCaller().forEach(caller -> caller.setSideEffect(true));
                        break;
                    }
                    else if(inst instanceof Store && ((Store) inst).getPointer() instanceof IRGlobalVariable){
                        func.setSideEffect(true);
                        func.getCaller().forEach(caller -> caller.setSideEffect(true));
                        break;
                    }
                }
            });
        });
    }
}

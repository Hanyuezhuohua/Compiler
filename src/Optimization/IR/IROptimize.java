package Optimization.IR;

import IR.IRmodule.IRModule;

public class IROptimize {
    private IRModule module;
    private boolean modified;

    public IROptimize(IRModule module){
        this.module = module;
        modified = false;
    }

    public void run(){
        do {
            modified = false;
        }while (modified);
    }
}

package Optimization.ASM;

import RISCV.RISCVmodule.RISCVModule;

public class ASMOptimize {
    private RISCVModule module;
    private Boolean modified;

    public ASMOptimize(RISCVModule module){
        this.module = module;
        modified = false;
    }

    public void run(){
        do{
            modified = false;
            RedundantInstRemove Opt1 = new RedundantInstRemove();
            Opt1.visit(module);
            modified |= Opt1.hasNewRedundantInstRemove();
            CFGSimplification Opt2 = new CFGSimplification();
            Opt2.visit(module);
            modified |= Opt2.hasNewCFGSimplification();
            RedundantCodeRemove Opt3 = new RedundantCodeRemove();
            Opt3.run(module);
            modified |= Opt3.hasNewRedundantCodeRemove();
        }while (modified);
    }
}

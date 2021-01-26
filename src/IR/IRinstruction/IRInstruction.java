package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;

public abstract class IRInstruction {
    private IRBasicBlock instIn;
    private IRInstruction prev;
    private IRInstruction next;

    public IRInstruction(IRBasicBlock instIn){
        this.instIn = instIn;
    }
}

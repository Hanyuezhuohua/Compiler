package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IROperand;

public class Br extends IRInstruction{
    private IROperand cond;
    private IRBasicBlock ifTrue;
    private IRBasicBlock ifFalse;

    public Br(IRBasicBlock instIn, IROperand cond, IRBasicBlock ifTrue, IRBasicBlock ifFalse){
        super(instIn);
        this.cond = cond;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }
}

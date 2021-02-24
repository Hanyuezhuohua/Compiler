package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IROperand;

public class Ret extends IRInstruction{
    private IROperand value;

    public Ret(IRBasicBlock instIn, IROperand value){
        super(instIn);
        this.value = value;
    }

    @Override
    public boolean Terminal() {
        return true;
    }
}

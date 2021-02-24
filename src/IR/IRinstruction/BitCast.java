package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IROperand;

public class BitCast extends IRInstruction{
    private IROperand value;
    private IROperand result;

    public BitCast(IRBasicBlock instIn, IROperand value, IROperand result){
        super(instIn);
        this.value = value;
        this.result = result;
    }

    @Override
    public boolean Terminal() {
        return false;
    }
}

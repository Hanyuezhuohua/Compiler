package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IROperand;

public class Store extends IRInstruction{
    private IROperand value;
    private IROperand pointer;

    public Store(IRBasicBlock instIn, IROperand value, IROperand pointer){
        super(instIn);
        this.value = value;
        this.pointer = pointer;
    }

    @Override
    public boolean Terminal() {
        return false;
    }
}

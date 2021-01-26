package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IROperand;

public class Load extends IRInstruction{
    private IROperand pointer;
    private IROperand result;

    public Load(IRBasicBlock instIn, IROperand pointer, IROperand result){
        super(instIn);
        this.pointer = pointer;
        this.result = result;
    }
}

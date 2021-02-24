package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IROperand;

import java.util.ArrayList;

public class GetElementPtr extends IRInstruction{
    private IROperand ptrval;
    private ArrayList<IROperand> index;
    private IROperand result;

    public GetElementPtr(IRBasicBlock instIn, IROperand ptrval, ArrayList<IROperand> index, IROperand result){
        super(instIn);
        this.ptrval = ptrval;
        this.index = index;
        this.result = result;
    }

    @Override
    public boolean Terminal() {
        return false;
    }
}

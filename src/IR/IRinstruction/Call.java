package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRoperand.IROperand;

import java.util.ArrayList;

public class Call extends IRInstruction{
    private IRFunction fnptrval;
    private ArrayList<IROperand> functionArgs;
    private IROperand result;

    public Call(IRBasicBlock instIn, IRFunction fnptrval, ArrayList<IROperand> functionArgs, IROperand result){
        super(instIn);
        this.fnptrval = fnptrval;
        this.functionArgs = functionArgs;
        this.result = result;
    }

    @Override
    public boolean Terminal() {
        return false;
    }
}

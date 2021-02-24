package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IROperand;

import java.util.ArrayList;

public class Phi extends IRInstruction{
    private ArrayList<IROperand> values;
    private ArrayList<IRBasicBlock> labels;
    private IROperand result;

    public Phi(IRBasicBlock instIn, ArrayList<IROperand> values, ArrayList<IRBasicBlock> labels, IROperand result){
        super(instIn);
        this.values = values;
        this.labels = labels;
        this.result = result;
    }

    @Override
    public boolean Terminal() {
        return false;
    }
}

package IR.IRoperand;

import IR.IRinstruction.IRInstruction;
import IR.IRtype.IRBoolType;

import java.util.HashSet;

public class IRConstBool extends IROperand{
    private boolean value;

    public IRConstBool(boolean value){
        super(new IRBoolType(), value? "1" : "0");
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public HashSet<IRInstruction> getInstructions() {
        return new HashSet<>();
    }

    @Override
    public void appendInst(IRInstruction inst) {}

    @Override
    public void removeInst(IRInstruction inst) {}
}

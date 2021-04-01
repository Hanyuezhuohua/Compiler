package IR.IRoperand;

import IR.IRinstruction.IRInstruction;
import IR.IRtype.IRIntType;

import java.util.HashSet;

public class IRConstInt extends IROperand{
    private int value;

    public IRConstInt(int value, IRIntType.IntTypeBytes intTypeBytes){
        super(new IRIntType(intTypeBytes), Integer.toString(value));
        this.value = value;
    }

    @Override
    public HashSet<IRInstruction> getInstructions() {
        return new HashSet<>();
    }

    @Override
    public void appendInst(IRInstruction inst) {}

    @Override
    public void removeInst(IRInstruction inst) {}

    public int getValue() {
        return value;
    }

    @Override
    public boolean isZero() {
        return value == 0;
    }

    @Override
    public boolean isImm() {
        return -(1 << 11) <= value && value <= (1 << 11) - 1;
    }

}

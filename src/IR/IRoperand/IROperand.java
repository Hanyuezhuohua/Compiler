package IR.IRoperand;

import IR.IRtype.IRType;

public abstract class IROperand {
    private IRType OperandType;

    public IROperand(IRType operandType){
        this.OperandType = operandType;
    }

    public IRType getOperandType() {
        return OperandType;
    }
}

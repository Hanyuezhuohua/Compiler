package IR.IRoperand;

import IR.IRtype.IRType;

public class IRGlobalVariable extends IROperand{
    private String identifier;
    private IROperand value;

    public IRGlobalVariable(IRType OperandType, String identifier, IROperand init){
        super(OperandType);
        this.identifier = identifier;
        this.value = init;
    }
}

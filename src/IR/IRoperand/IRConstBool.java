package IR.IRoperand;

import IR.IRtype.IRBoolType;

public class IRConstBool extends IROperand{
    private boolean value;

    public IRConstBool(boolean value){
        super(new IRBoolType());
        this.value = value;
    }
}

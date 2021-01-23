package IR.IRoperand;

import IR.IRtype.IRIntType;

public class IRConstInt extends IROperand{
    private int value;

    public IRConstInt(int value){
        super(new IRIntType(IRIntType.IntTypeBytes.Int32));
        this.value = value;
    }
}

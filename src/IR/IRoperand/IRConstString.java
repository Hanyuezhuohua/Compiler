package IR.IRoperand;

import IR.IRtype.IRArrayType;
import IR.IRtype.IRIntType;
import IR.IRtype.IRPointerType;

public class IRConstString extends IROperand{
    private String value;

    public IRConstString(String value){
        super(new IRPointerType(new IRArrayType(value.length(), new IRIntType(IRIntType.IntTypeBytes.Int8))));
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

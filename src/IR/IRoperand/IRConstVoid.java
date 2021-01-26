package IR.IRoperand;

import IR.IRtype.IRVoidType;

public class IRConstVoid extends IROperand{
    public IRConstVoid(){
        super(new IRVoidType());
    }
}

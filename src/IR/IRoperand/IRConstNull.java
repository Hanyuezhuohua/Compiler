package IR.IRoperand;

import IR.IRtype.IRPointerType;
import IR.IRtype.IRVoidType;

public class IRConstNull extends IROperand{
    public IRConstNull(){
        super(new IRPointerType(new IRVoidType()));
    }
}

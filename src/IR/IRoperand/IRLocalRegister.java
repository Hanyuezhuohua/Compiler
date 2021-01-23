package IR.IRoperand;

import IR.IRtype.IRType;

public class IRLocalRegister extends IROperand{
    String identifier;

    IRLocalRegister(IRType OperandType, String identifier){
        super(OperandType);
        this.identifier = identifier;
    }
}

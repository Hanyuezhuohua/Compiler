package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;

public class Alloca extends IRInstruction{
    private IRLocalRegister result;
    public Alloca(IRBasicBlock instIn, IRLocalRegister result){
        super(instIn);
        this.result = result;
    }

    @Override
    public boolean Terminal() {
        return false;
    }
}

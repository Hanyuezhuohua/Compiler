package IR.IRoperand;

import IR.IRinstruction.IRInstruction;
import IR.IRtype.IRPointerType;
import IR.IRtype.IRVoidType;

import java.util.HashSet;

public class IRConstNull extends IROperand{
    public IRConstNull(){
        super(new IRPointerType(new IRVoidType(), false), "null");
    }
    @Override
    public HashSet<IRInstruction> getInstructions() {
        return new HashSet<>();
    }

    @Override
    public void appendInst(IRInstruction inst) {}

    @Override
    public void removeInst(IRInstruction inst) {}

    @Override
    public boolean isZero() {
        return true;
    }
}

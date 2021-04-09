package IR.IRoperand;

import IR.IRinstruction.IRInstruction;
import IR.IRtype.IRVoidType;

import java.util.HashSet;

public class IRConstVoid extends IROperand{
    private HashSet<IRInstruction> instructions = new HashSet<>();
    public IRConstVoid(){
        super(new IRVoidType(), "");
    }
    @Override
    public HashSet<IRInstruction> getInstructions() {
        return instructions;
    }

    @Override
    public void appendInst(IRInstruction inst) {
        instructions.add(inst);
    }

    @Override
    public void removeInst(IRInstruction inst) {
        instructions.remove(inst);
    }

    @Override
    public void clearInst() {
        instructions.clear();
    }

    @Override
    public boolean isZero() { return true; }

    @Override
    public IROperand operandCopy() {
        return this;
    }

    @Override
    public boolean CSEChecker(IROperand other) {
        return other instanceof IRConstVoid;
    }
}

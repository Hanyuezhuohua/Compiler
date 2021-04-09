package IR.IRoperand;

import IR.IRinstruction.IRInstruction;
import IR.IRtype.IRType;

import java.util.HashSet;

public class IRLocalRegister extends IROperand{
    private HashSet<IRInstruction> instructions;

    public IRLocalRegister(IRType OperandType, String identifier){
        super(OperandType, identifier);
        instructions = new HashSet<>();
    }

    @Override
    public String PrintOperand() {
        return "%" + super.PrintOperand();
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

    public void update(IROperand New){
        instructions.forEach(inst -> {
            inst.update(this, New);
            New.appendInst(inst);
        });
        instructions.clear();
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Override
    public IROperand operandCopy() {
        return new IRLocalRegister(getOperandType(), getIdentifier());
    }

    @Override
    public boolean CSEChecker(IROperand other) {
        return equals(other);
    }
}

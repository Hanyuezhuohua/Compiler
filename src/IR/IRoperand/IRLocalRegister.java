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

    public void update(IROperand New){
        instructions.forEach(inst -> {
            inst.update(this, New);
            New.appendInst(inst);
        });
        instructions.clear();
    }
}

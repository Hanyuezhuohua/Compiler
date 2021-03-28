package IR.IRoperand;

import IR.IRinstruction.IRInstruction;
import IR.IRtype.IRType;

import java.util.ArrayList;
import java.util.HashSet;

public class IRGlobalVariable extends IROperand{
    private IROperand value;
    private boolean initialization;
    private HashSet<IRInstruction> instructions;

    public IRGlobalVariable(IRType OperandType, String identifier, IROperand init, boolean initialization){
        super(OperandType, identifier);
        this.value = init;
        this.initialization = initialization;
        instructions = new HashSet<>();
    }

    @Override
    public String PrintOperand() {
        return "@" + super.PrintOperand();
    }

    public boolean isInitialization() {
        return initialization;
    }

    public IROperand getValue() {
        return value;
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
}

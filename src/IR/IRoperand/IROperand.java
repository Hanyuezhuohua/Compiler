package IR.IRoperand;

import IR.IRinstruction.IRInstruction;
import IR.IRtype.IRType;

import java.util.HashSet;

public abstract class IROperand {
    private IRType OperandType;
    private String identifier;

    public IROperand(IRType operandType, String identifier){
        this.OperandType = operandType;
        this.identifier = identifier;
    }

    public IRType getOperandType() {
        return OperandType;
    }

    public String getIdentifier(){
        return identifier;
    }

    public String PrintOperand(){
        return identifier;
    }

    public void ChangeIdentifier(String identifier){
        this.identifier = identifier;
    }

    public abstract HashSet<IRInstruction> getInstructions();

    public abstract void appendInst(IRInstruction inst);

    public abstract void removeInst(IRInstruction inst);

    @Override
    public String toString() {
        return PrintOperand();
    }
}

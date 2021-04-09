package IR.IRoperand;

import IR.IRinstruction.IRInstruction;
import IR.IRtype.IRType;

import javax.swing.table.TableCellEditor;
import java.util.HashSet;

public abstract class IROperand {
    protected IRType OperandType;
    protected String identifier;

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

    public abstract void clearInst();

    public abstract boolean isZero();

    public boolean isImm(){
        return false;
    }

    public abstract IROperand operandCopy();

    public abstract boolean CSEChecker(IROperand other);

    @Override
    public String toString() {
        return PrintOperand();
    }
}

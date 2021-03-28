package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRVisitor;

import java.util.HashSet;

public class Zext extends IRInstruction{
    private IROperand value;
    private IROperand result;

    public Zext(IRBasicBlock instIn, IROperand value, IROperand result){
        super(instIn);
        this.value = value;
        this.result = result;
        this.result.appendInst(this);
    }

    @Override
    public boolean Terminal() {
        return false;
    }

    @Override
    public String PrintInst() {
        return result.PrintOperand()
                + " = zext "
                + value.getOperandType().getType()
                + " "
                + value.PrintOperand()
                + " to "
                + result.getOperandType().getType();
    }

    @Override
    public IROperand getResult() {
        return result;
    }

    @Override
    public HashSet<IROperand> getOperands() {
        HashSet<IROperand> operands = new HashSet<>();
        operands.add(value);
        return operands;
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        if(value.equals(Old)){
            value = New;
        }
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    public IROperand getValue() {
        return value;
    }
}

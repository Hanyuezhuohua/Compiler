package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRVisitor;

import java.util.HashSet;

public class Move extends IRInstruction{
    public IROperand result;
    public IROperand value;
    public Move(IRBasicBlock instIn, IROperand result, IROperand value){
        super(instIn);
        this.result = result;
        this.value = value;
 //       this.value.appendInst(this);
    }

    @Override
    public boolean Terminal() {
        return false;
    }

    @Override
    public IROperand getResult() {
        return result;
    }

    @Override
    public String PrintInst() {
        return  result.PrintOperand()
                + " = mv "
                + value.getOperandType().getType()
                + " "
                + value.PrintOperand();
     }

    @Override
    public HashSet<IROperand> getOperands() {
        HashSet<IROperand> operands = new HashSet<>();
        operands.add(value);
        return operands;
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        if (value.equals(Old)){
            value = New;
        }
    }

    public IROperand getValue() {
        return value;
    }

    public void addUse(){
        this.value.appendInst(this);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

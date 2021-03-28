package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRVisitor;

import java.util.HashSet;

public class Store extends IRInstruction{
    private IROperand value;
    private IROperand pointer;

    public Store(IRBasicBlock instIn, IROperand value, IROperand pointer){
        super(instIn);
        this.value = value;
        this.pointer = pointer;
        this.value.appendInst(this);
        this.pointer.appendInst(this);
    }

    @Override
    public boolean Terminal() {
        return false;
    }

    @Override
    public String PrintInst() {
        return "store "
                + value.getOperandType().getType()
                + " "
                + value.PrintOperand()
                + ", "
                + pointer.getOperandType().getType()
                + " "
                + pointer.PrintOperand()
                + ", align "
                + value.getOperandType().getSize() / 8;
    }

    public IROperand getPointer() {
        return pointer;
    }

    public IROperand getValue() {
        return value;
    }

    @Override
    public HashSet<IROperand> getOperands() {
        HashSet<IROperand> operands = new HashSet<>();
        operands.add(pointer);
        operands.add(value);
        return operands;
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        if (value.equals(Old)){
            value = New;
        }
        if (pointer.equals(Old)){
            pointer = New;
        }
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

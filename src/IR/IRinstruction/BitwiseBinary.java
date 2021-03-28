package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRVisitor;

import java.util.HashSet;

public class BitwiseBinary extends IRInstruction{
    public enum IRBitwiseBinaryOpType{
        shl, ashr, and, or, xor
    }

    private IRBitwiseBinaryOpType op;
    private IROperand op1;
    private IROperand op2;
    private IROperand result;

    public BitwiseBinary(IRBasicBlock instIn, IRBitwiseBinaryOpType op, IROperand op1, IROperand op2, IROperand result){
        super(instIn);
        this.op = op;
        this.op1 = op1;
        this.op2 = op2;
        this.result = result;
        this.op1.appendInst(this);
        this.op2.appendInst(this);
    }

    @Override
    public boolean Terminal() {
        return false;
    }

    @Override
    public String PrintInst() {
        return result.PrintOperand()
                + " = "
                + op.toString()
                + " "
                + op1.getOperandType().getType()
                + " "
                + op1.PrintOperand()
                + ", "
                + op2.PrintOperand();
    }

    @Override
    public IROperand getResult() {
        return result;
    }

    @Override
    public HashSet<IROperand> getOperands() {
        HashSet<IROperand> operands = new HashSet<>();
        operands.add(op1);
        operands.add(op2);
        return operands;
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        if(op1.equals(Old)){
            op1 = New;
        }
        if(op2.equals(Old)){
            op2 = New;
        }
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    public IRBitwiseBinaryOpType getOp() {
        return op;
    }

    public IROperand getOp1() {
        return op1;
    }

    public IROperand getOp2() {
        return op2;
    }
}

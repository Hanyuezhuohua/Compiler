package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRConstInt;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRIntType;
import IR.IRutility.IRCopy;
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
    public void instCopy(IRBasicBlock instIn, IRCopy Map) {
        instIn.addInst(new BitwiseBinary(instIn, op, Map.get(op1), Map.get(op2), Map.get(result)));
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

    @Override
    public boolean hasSideEffect() {
        return false;
    }

    @Override
    public boolean CSEChecker(IRInstruction other) {
        if(other instanceof BitwiseBinary && ((BitwiseBinary) other).op == op){
            if(op == IRBitwiseBinaryOpType.shl){
                if(op1.CSEChecker(((BitwiseBinary) other).op1) && op2.CSEChecker(((BitwiseBinary) other).op2)) return true;
                else return false;
            }
            else if(op == IRBitwiseBinaryOpType.ashr){
                if(op1.CSEChecker(((BitwiseBinary) other).op1) && op2.CSEChecker(((BitwiseBinary) other).op2)) return true;
                else return false;
            }
            else if(op == IRBitwiseBinaryOpType.and){
                if((op1.CSEChecker(((BitwiseBinary) other).op1) && op2.CSEChecker(((BitwiseBinary) other).op2)) || (op1.CSEChecker(((BitwiseBinary) other).op2) && op2.CSEChecker(((BitwiseBinary) other).op1))) return true;
                else return false;
            }
            else if(op == IRBitwiseBinaryOpType.or){
                if((op1.CSEChecker(((BitwiseBinary) other).op1) && op2.CSEChecker(((BitwiseBinary) other).op2)) || (op1.CSEChecker(((BitwiseBinary) other).op2) && op2.CSEChecker(((BitwiseBinary) other).op1))) return true;
                else return false;
            }
            else if(op == IRBitwiseBinaryOpType.xor){
                if((op1.CSEChecker(((BitwiseBinary) other).op1) && op2.CSEChecker(((BitwiseBinary) other).op2)) || (op1.CSEChecker(((BitwiseBinary) other).op2) && op2.CSEChecker(((BitwiseBinary) other).op1))) return true;
                else return false;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public boolean merge() {
        if(next instanceof BitwiseBinary && op == IRBitwiseBinaryOpType.shl && ((BitwiseBinary) next).op == IRBitwiseBinaryOpType.shl && op2 instanceof IRConstInt && ((BitwiseBinary) next).op2 instanceof IRConstInt && result == ((BitwiseBinary) next).op1){
            result = next.getResult();
            op2 = new IRConstInt(((IRConstInt) op2).getValue() + ((IRConstInt) ((BitwiseBinary) next).op2).getValue(), IRIntType.IntTypeBytes.Int32);
            next.Remove();
            return true;
        }
        else if(next instanceof BitwiseBinary && op == IRBitwiseBinaryOpType.ashr && ((BitwiseBinary) next).op == IRBitwiseBinaryOpType.ashr && op2 instanceof IRConstInt && ((BitwiseBinary) next).op2 instanceof IRConstInt && result == ((BitwiseBinary) next).op1){
            result = next.getResult();
            op2 = new IRConstInt(((IRConstInt) op2).getValue() + ((IRConstInt) ((BitwiseBinary) next).op2).getValue(), IRIntType.IntTypeBytes.Int32);
            next.Remove();
            return true;
        }
        return false;
    }
}

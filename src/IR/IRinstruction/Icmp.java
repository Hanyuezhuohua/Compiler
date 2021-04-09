package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRConstNull;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRCopy;
import IR.IRutility.IRVisitor;

import java.util.HashSet;

public class Icmp extends IRInstruction{
    public enum IRIcmpOpType{
        eq, ne, sgt, sge, slt, sle
    }

    private IRIcmpOpType op;
    private IROperand op1;
    private IROperand op2;
    private IROperand result;

    public Icmp(IRBasicBlock instIn, IRIcmpOpType op, IROperand op1, IROperand op2, IROperand result){
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
        instIn.addInst(new Icmp(instIn, op, Map.get(op1), Map.get(op2), Map.get(result)));
    }

    @Override
    public boolean Terminal() {
        return false;
    }

    @Override
    public String PrintInst() {
        return result.PrintOperand()
                + " = icmp "
                + op.toString()
                + " "
                + (op1 instanceof IRConstNull ? (op2 instanceof IRConstNull ? "int*" : op2.getOperandType().getType()) : op1.getOperandType().getType())
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

    public IRIcmpOpType getOp() {
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
        if(other instanceof Icmp && ((Icmp) other).op == op){
            if(op == IRIcmpOpType.eq){
                if((op1.CSEChecker(((Icmp) other).op1) && op2.CSEChecker(((Icmp) other).op2)) || (op1.CSEChecker(((Icmp) other).op2) && op2.CSEChecker(((Icmp) other).op1))) return true;
                else return false;
            }
            else if(op == IRIcmpOpType.ne){
                if((op1.CSEChecker(((Icmp) other).op1) && op2.CSEChecker(((Icmp) other).op2)) || (op1.CSEChecker(((Icmp) other).op2) && op2.CSEChecker(((Icmp) other).op1))) return true;
                else return false;
            }
            else if(op == IRIcmpOpType.sgt){
                if(op1.CSEChecker(((Icmp) other).op1) && op2.CSEChecker(((Icmp) other).op2)) return true;
                else return false;
            }
            else if(op == IRIcmpOpType.sge){
                if(op1.CSEChecker(((Icmp) other).op1) && op2.CSEChecker(((Icmp) other).op2)) return true;
                else return false;
            }
            else if(op == IRIcmpOpType.sle){
                if(op1.CSEChecker(((Icmp) other).op1) && op2.CSEChecker(((Icmp) other).op2)) return true;
                else return false;
            }
            else if(op == IRIcmpOpType.slt){
                if(op1.CSEChecker(((Icmp) other).op1) && op2.CSEChecker(((Icmp) other).op2)) return true;
                else return false;
            }
            else return false;
        }
        else if(other instanceof Icmp){
            if(op == IRIcmpOpType.sgt && ((Icmp) other).op == IRIcmpOpType.sle){
                if(op1.CSEChecker(((Icmp) other).op2) && op2.CSEChecker(((Icmp) other).op1)) return true;
                else return false;
            }
            else if(op == IRIcmpOpType.sge && ((Icmp) other).op == IRIcmpOpType.slt){
                if(op1.CSEChecker(((Icmp) other).op2) && op2.CSEChecker(((Icmp) other).op1)) return true;
                else return false;
            }
            else if(op == IRIcmpOpType.sle && ((Icmp) other).op == IRIcmpOpType.sgt){
                if(op1.CSEChecker(((Icmp) other).op2) && op2.CSEChecker(((Icmp) other).op1)) return true;
                else return false;
            }
            else if(op == IRIcmpOpType.slt && ((Icmp) other).op == IRIcmpOpType.sge){
                if(op1.CSEChecker(((Icmp) other).op2) && op2.CSEChecker(((Icmp) other).op1)) return true;
                else return false;
            }
            else return false;
        }
        else return false;
    }
}

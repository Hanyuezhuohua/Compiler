package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRVisitor;

import java.util.HashSet;

public class Br extends IRInstruction{
    private IROperand cond;
    private IRBasicBlock ifTrue;
    private IRBasicBlock ifFalse;

    public Br(IRBasicBlock instIn, IROperand cond, IRBasicBlock ifTrue, IRBasicBlock ifFalse){
        super(instIn);
        this.cond = cond;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
        if(cond != null){
            this.cond.appendInst(this);
        }
    }

    @Override
    public boolean Terminal() {
        return true;
    }

    public IRBasicBlock getIfTrue() {
        return ifTrue;
    }

    public IRBasicBlock getIfFalse() {
        return ifFalse;
    }

    public IROperand getCond() {
        return cond;
    }

    @Override
    public String PrintInst() {
        if(cond == null){
            return "br label " + ifTrue.PrintBasicBlock();
        }
        else{
            return "br i1 "
                    + cond.PrintOperand()
                    + ", label "
                    + ifTrue.PrintBasicBlock()
                    + ", label "
                    + ifFalse.PrintBasicBlock();
        }
    }


    @Override
    public HashSet<IROperand> getOperands() {
        HashSet<IROperand> operands = new HashSet<>();
        if(cond != null){
            operands.add(cond);
        }
        return operands;
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        if(cond.equals(Old)){
            cond = New;
        }
    }

    public void updateBlock(IRBasicBlock Old, IRBasicBlock New){
        if(ifTrue.equals(Old)){
            ifTrue = New;
        }
        if(ifFalse != null){
            if(ifFalse.equals(Old)){
                ifFalse = New;
            }
        }
    }

    public boolean BinaryBr(){
        if(instIn.getHead() != instIn.getTail()){
            if(instIn.getTail().getPrev() instanceof Icmp){
                if(instIn.getTail().getPrev().getResult() == cond){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

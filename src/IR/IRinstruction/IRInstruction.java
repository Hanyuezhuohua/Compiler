package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRVisitor;

import java.util.HashSet;

public abstract class IRInstruction {
    protected IRBasicBlock instIn;
    protected IRInstruction prev;
    protected IRInstruction next;

    public IRInstruction(IRBasicBlock instIn){
        this.instIn = instIn;
    }

    public abstract boolean Terminal();

    public void setNext(IRInstruction next) {
        this.next = next;
    }

    public void setPrev(IRInstruction prev) {
        this.prev = prev;
    }

    public IRInstruction getNext() {
        return next;
    }

    public IRInstruction getPrev() {
        return prev;
    }

    public String PrintInst(){
        return "";
    }

    public IROperand getResult(){
        return null;
    }

    public void Remove(){
        if(prev != null){
            prev.next = next;
        }
        else{
            instIn.setHead(next);
        }
        if(next != null){
            next.prev = prev;
        }
        else{
            instIn.setTail(prev);
        }
        getOperands().forEach(operand -> {
            operand.removeInst(this);
        });
    }

    public void Insert(IRInstruction inst){
        if(prev != null){
            prev.setNext(inst);
            inst.setPrev(prev);
        }
        else{
            instIn.setHead(inst);
        }
        inst.setNext(this);
        this.setPrev(inst);
    }

    public abstract void update(IRLocalRegister Old, IROperand New);

    public abstract HashSet<IROperand> getOperands();

    public abstract void accept(IRVisitor visitor);

    @Override
    public String toString() {
        return PrintInst();
    }
}

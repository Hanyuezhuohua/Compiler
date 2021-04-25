package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRCopy;
import IR.IRutility.IRVisitor;

import java.util.HashMap;
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
            if(next != null) next.setPrev(null);
        }
        if(next != null){
            next.prev = prev;
        }
        else{
            instIn.setTail(prev);
            if(prev != null) prev.setNext(null);
        }
        getOperands().forEach(operand -> {
            operand.removeInst(this);
        });
        if(this instanceof Br){
            if(((Br) this).getCond() != null){
                instIn.getNext().remove(((Br) this).getIfTrue());
                instIn.getNext().remove(((Br) this).getIfFalse());
                ((Br) this).getIfTrue().getPrev().remove(this);
                ((Br) this).getIfFalse().getPrev().remove(this);
            }
            else {
                instIn.getNext().remove(((Br) this).getIfTrue());
                ((Br) this).getIfTrue().getPrev().remove(this);
            }
        }
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

    public void setInstIn(IRBasicBlock instIn) {
        this.instIn = instIn;
    }

    public IRBasicBlock getInstIn() {
        return instIn;
    }

    public abstract void update(IRLocalRegister Old, IROperand New);

    public abstract HashSet<IROperand> getOperands();

    public abstract void instCopy(IRBasicBlock instIn, IRCopy Map);

    public abstract void accept(IRVisitor visitor);

    public abstract boolean hasSideEffect();

    public abstract boolean CSEChecker(IRInstruction other);

    public boolean merge(){
        return false;
    }

    @Override
    public String toString() {
        return PrintInst();
    }
}

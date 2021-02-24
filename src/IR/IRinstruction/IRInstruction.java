package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;

public abstract class IRInstruction {
    private IRBasicBlock instIn;
    private IRInstruction prev;
    private IRInstruction next;

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
}

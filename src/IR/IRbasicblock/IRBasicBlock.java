package IR.IRbasicblock;

import IR.IRfunction.IRFunction;
import IR.IRinstruction.IRInstruction;

public class IRBasicBlock {
    private IRFunction blockIn;
    private String identifier;

    private IRInstruction head, tail;

    private IRBasicBlock prev, next;

    public IRBasicBlock(IRFunction blockIn, String identifier){
        this.blockIn = blockIn;
        this.identifier = identifier;
        head = tail = null;
        prev = next = null;
    }

    public void addInst(IRInstruction inst){
        if(head == null){
            head = tail = inst;
        }
        else if(!inst.Terminal()){
            tail.setNext(inst);
            inst.setPrev(tail);
            tail = inst;
        }
    }
}

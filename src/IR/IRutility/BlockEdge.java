package IR.IRutility;

import IR.IRbasicblock.IRBasicBlock;

public class BlockEdge {
    private IRBasicBlock prev;
    private IRBasicBlock next;
    public BlockEdge(IRBasicBlock prev, IRBasicBlock next){
        this.prev = prev;
        this.next = next;
    }

    public IRBasicBlock getNext() {
        return next;
    }

    public IRBasicBlock getPrev() {
        return prev;
    }
}

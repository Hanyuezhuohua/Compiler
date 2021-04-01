package RISCV.RISCVbasicblock;

import RISCV.RISCVinstruction.Branch.UnaryBranch;
import RISCV.RISCVinstruction.RISCVInstruction;
import RISCV.RISCVinstruction.RISCVReturn;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class RISCVBasicBlock {
    private String identifier;
    private RISCVInstruction head;
    private RISCVInstruction tail;
    private ArrayList<RISCVBasicBlock> prev;
    private ArrayList<RISCVBasicBlock> next;
    private HashSet<RISCVRegister> liveIn;
    private HashSet<RISCVRegister> liveOut;

    public RISCVBasicBlock(String identifier) {
        this.identifier = identifier;
        this.head = null;
        this.tail = null;
        this.prev = new ArrayList<>();
        this.next = new ArrayList<>();
        this.liveIn = new LinkedHashSet<>();
        this.liveOut = new LinkedHashSet<>();
    }
    public void addInst(RISCVInstruction inst) {
        if (tail == null) {
            head = tail = inst;
            inst.next = inst.prev = null;
        }
        else {
            tail.appendBack(inst);
        }
    }

    public String getIdentifier() { return identifier; }

    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public RISCVInstruction getHead() {
        return head;
    }

    public void setHead(RISCVInstruction head) {
        this.head = head;
    }

    public RISCVInstruction getTail() { return tail; }

    public void setTail(RISCVInstruction tail) { this.tail = tail; }

    public ArrayList<RISCVBasicBlock> getPrev() { return prev; }

    public void addPrev(RISCVBasicBlock prev) { this.prev.add(prev); }

    public void removePrev(RISCVBasicBlock prev) { this.prev.remove(prev); }

    public void clearPrev() { prev.clear();}

    public ArrayList<RISCVBasicBlock> getNext() { return next; }

    public void addNext(RISCVBasicBlock next) { this.next.add(next); }

    public void removeNext(RISCVBasicBlock next) { this.next.remove(next); }

    public void addNext(ArrayList<RISCVBasicBlock> next) { this.next.addAll(next); }

    public void clearNext() { next.clear();}

    public void setLiveIn(HashSet<RISCVRegister> liveIn) { this.liveIn = liveIn; }

    public HashSet<RISCVRegister> getLiveIn() { return liveIn; }

    public void addLiveIn(HashSet<RISCVRegister> liveIn) {this.liveIn.addAll(liveIn); }

    public HashSet<RISCVRegister> getLiveOut() { return liveOut; }

    public void setLiveOut(HashSet<RISCVRegister> liveOut) { this.liveOut = liveOut; }

    public void addLiveOut(HashSet<RISCVRegister> liveOut) {this.liveOut.addAll(liveOut); }

    public void disconnect(RISCVBasicBlock block){
        this.removeNext(block);
        block.removePrev(this);
    }

    public boolean Terminate(){
        return tail instanceof UnaryBranch || tail instanceof RISCVReturn;
    }

    @Override
    public String toString() {
        return identifier;
    }
}

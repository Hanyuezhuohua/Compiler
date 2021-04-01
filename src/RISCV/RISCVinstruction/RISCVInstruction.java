package RISCV.RISCVinstruction;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public abstract class RISCVInstruction {
    public enum SCategory {
        add, sub, slt, xor, or, and, sll, sra, mul, div, rem
    }

    public RISCVInstruction prev;
    public RISCVInstruction next;
    public RISCVBasicBlock block;

    public RISCVInstruction() {}

    public abstract String toString();
    public HashSet<RISCVRegister> Uses() {
        return new HashSet<>();
    }
    public HashSet<RISCVRegister> Defs() {
        return new HashSet<>();
    }
    public void UpdateUse(RISCVRegister Old, RISCVRegister New) {}
    public void UpdateDef(RISCVRegister Old, RISCVRegister New) {}
    public void updateOffset(int offset) {}
    public abstract RISCVInstruction copy();
    public void appendFront(RISCVInstruction inst) {
        if (prev != null) prev.next = inst;
        else block.setHead(inst);
        inst.prev = prev;
        inst.next = this;
        this.prev = inst;
    }
    public void appendBack(RISCVInstruction inst) {
        if (next != null) next.prev = inst;
        else block.setTail(inst);
        inst.next = next;
        inst.prev = this;
        this.next = inst;
    }
    public void update(RISCVInstruction inst) {
        if (prev != null) {
            prev.next = inst;
            inst.prev = prev;
        }
        else block.setHead(inst);
        if (next != null) {
            next.prev = inst;
            inst.next = next;
        }
        else block.setTail(inst);
    }
    public void remove() {
        if (prev != null) prev.next = next;
        else block.setHead(next);
        if (next != null) next.prev = prev;
        else block.setTail(prev);
    }
}


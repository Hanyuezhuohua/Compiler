package RISCV.RISCVinstruction;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public abstract class RISCVInstruction {

    public RISCVInstruction prev;
    public RISCVInstruction next;
    public RISCVBasicBlock instIn;

    public RISCVInstruction(RISCVBasicBlock instIn) {
        this.instIn = instIn;
        prev = null;
        next = null;
    }

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
        else instIn.setHead(inst);
        inst.prev = prev;
        inst.next = this;
        this.prev = inst;
    }
    public void appendBack(RISCVInstruction inst) {
        if (next != null) next.prev = inst;
        else instIn.setTail(inst);
        inst.next = next;
        inst.prev = this;
        this.next = inst;
    }
    public void update(RISCVInstruction inst) {
        if (prev != null) {
            prev.next = inst;
            inst.prev = prev;
        }
        else instIn.setHead(inst);
        if (next != null) {
            next.prev = inst;
            inst.next = next;
        }
        else instIn.setTail(inst);
    }
    public void remove() {
        if (prev != null) prev.next = next;
        else instIn.setHead(next);
        if (next != null) next.prev = prev;
        else instIn.setTail(prev);
    }
}


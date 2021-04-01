package RISCV.RISCVinstruction;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVimmediate.RISCVImmediate;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVLui extends RISCVInstruction {
    public RISCVImmediate value;
    public RISCVRegister rd;
    public RISCVLui(RISCVImmediate value, RISCVRegister rd, RISCVBasicBlock block) {
        this.value = value;
        this.rd = rd;
        this.block = block;
    }
    @Override
    public String toString() {
        return "lui " + rd + ", " + value;
    }
    @Override
    public HashSet<RISCVRegister> Defs() {
        return new HashSet<>() {{ add(rd); }};
    }
    @Override
    public void UpdateDef(RISCVRegister old, RISCVRegister newReg) {
        if (rd == old) {
            rd = newReg;
        }
    }
    @Override
    public void updateOffset(int stackOffset) {
        value.updateOffset(stackOffset);
    }

    @Override
    public RISCVInstruction copy() {
        return new RISCVLui(value, rd, block);
    }
}

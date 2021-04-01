package RISCV.RISCVinstruction;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVLi extends RISCVInstruction {
    public int value;
    public RISCVRegister rd;
    public RISCVLi(int value, RISCVRegister rd, RISCVBasicBlock block) {
        this.value = value;
        this.rd = rd;
        this.instIn = block;
    }
    @Override
    public String toString() {
        return "li " + rd + ", " + value;
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
    public RISCVInstruction copy() {
        return new RISCVLi(value, rd, instIn);
    }
}

package RISCV.RISCVinstruction;

import RISCV.RISCVoperand.RISCVregister.RISCVGlobalRegister;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVLa extends RISCVInstruction {
    public RISCVGlobalRegister src;
    public RISCVRegister rd;
    public RISCVLa(RISCVGlobalRegister src, RISCVRegister rd, RISCVBasicBlock block) {
        super(block);
        this.rd = rd;
        this.src = src;
    }
    @Override
    public String toString() {
        return "la " + rd + ", " + src;
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
        return new RISCVLa(src, rd, instIn);
    }
}

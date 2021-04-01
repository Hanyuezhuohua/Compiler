package RISCV.RISCVinstruction;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVZeroCmp extends RISCVInstruction {
    public RISCVRegister rs, rd;
    public SzCategory op;
    public enum SzCategory {
        seqz, snez,
    }
    public RISCVZeroCmp(RISCVRegister rs, SzCategory op, RISCVRegister rd, RISCVBasicBlock RISCVBasicBlock) {
        this.rs = rs;
        this.op = op;
        this.rd = rd;
        this.instIn = RISCVBasicBlock;
    }
    @Override
    public String toString() {
        return op + " " + rd + ", " + rs;
    }
    @Override
    public HashSet<RISCVRegister> Uses() {
        return new HashSet<>(){{add(rs);}};
    }
    @Override
    public void UpdateUse(RISCVRegister old, RISCVRegister newReg) {
        if (rs == old) {
            rs = newReg;
        }
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
        return new RISCVZeroCmp(rs, op, rd, instIn);
    }
}

package RISCV.RISCVinstruction;

import RISCV.RISCVoperand.RISCVregister.RISCVGlobalRegister;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVimmediate.RISCVImmediate;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;
import Util.error.ErrorMessage;

import java.util.HashSet;

public class RISCVLoad extends RISCVInstruction {
    public RISCVRegister rs, rd;
    public RISCVImmediate offset;
    public int width;
    public RISCVLoad(RISCVRegister rs, RISCVImmediate offset, RISCVRegister rd, int width, RISCVBasicBlock block) {
        this.instIn = block;
        this.rs = rs;
        this.offset = offset;
        this.rd = rd;
        this.width = width;
    }
    @Override
    public String toString() {
        switch (width) {
            case 1:
                return "lb " + rd + ", " + (rs instanceof RISCVGlobalRegister ? rs : (offset + "(" + rs + ")"));
            case 4:
                return "lw " + rd + ", " + (rs instanceof RISCVGlobalRegister ? rs : (offset + "(" + rs + ")"));
        }
        throw new ErrorMessage();
    }
    @Override
    public HashSet<RISCVRegister> Uses() {
        return new HashSet<>(){{ if (!(rs instanceof RISCVGlobalRegister)) add(rs);}};
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
    public void updateOffset(int stackOffset) {
        offset.updateOffset(stackOffset);
    }

    @Override
    public RISCVInstruction copy() {
        return new RISCVLoad(rs, offset, rd, width, instIn);
    }
}

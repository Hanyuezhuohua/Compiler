package RISCV.RISCVinstruction;

import RISCV.RISCVUtility.RISCVVisitor;
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
        super(block);
        this.rs = rs;
        this.offset = offset;
        this.rd = rd;
        this.width = width;
    }

    public RISCVRegister getRd() {
        return rd;
    }

    public RISCVRegister getRs() {
        return rs;
    }

    public RISCVImmediate getOffset() {
        return offset;
    }

    public int getWidth() {
        return width;
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
    public void updateOffset(int offset) {
        this.offset.updateOffset(offset);
    }

    @Override
    public RISCVInstruction copy() {
        return new RISCVLoad(rs, offset, rd, width, instIn);
    }

    @Override
    public void accept(RISCVVisitor visitor) {
        visitor.visit(this);
    }
}

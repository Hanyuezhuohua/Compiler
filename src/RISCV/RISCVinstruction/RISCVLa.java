package RISCV.RISCVinstruction;

import RISCV.RISCVUtility.RISCVVisitor;
import RISCV.RISCVoperand.RISCVregister.RISCVGlobalRegister;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVLa extends RISCVInstruction {
    public RISCVGlobalRegister rs;
    public RISCVRegister rd;
    public RISCVLa(RISCVGlobalRegister rs, RISCVRegister rd, RISCVBasicBlock block) {
        super(block);
        this.rd = rd;
        this.rs = rs;
    }

    public RISCVRegister getRd() {
        return rd;
    }

    public RISCVGlobalRegister getRs() {
        return rs;
    }

    @Override
    public String toString() {
        return "la " + rd + ", " + rs;
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
        return new RISCVLa(rs, rd, instIn);
    }

    @Override
    public void accept(RISCVVisitor visitor) {
        visitor.visit(this);
    }
}

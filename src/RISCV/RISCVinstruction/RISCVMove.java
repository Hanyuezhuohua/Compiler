package RISCV.RISCVinstruction;

import RISCV.RISCVUtility.RISCVVisitor;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVMove extends RISCVInstruction {
    public RISCVRegister rs, rd;
    public RISCVMove(RISCVRegister rs, RISCVRegister rd, RISCVBasicBlock RISCVBasicBlock) {
        super(RISCVBasicBlock);
        this.rs = rs;
        this.rd = rd;
    }

    public RISCVRegister getRd() {
        return rd;
    }

    public RISCVRegister getRs() {
        return rs;
    }

    @Override
    public String toString() {
        return "mv " + rd + ", " + rs;
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
        return new RISCVMove(rs, rd, instIn);
    }

    @Override
    public void accept(RISCVVisitor visitor) {
        visitor.visit(this);
    }
}

package RISCV.RISCVinstruction;

import RISCV.RISCVUtility.RISCVVisitor;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVLi extends RISCVInstruction {
    public int rs;
    public RISCVRegister rd;
    public RISCVLi(int rs, RISCVRegister rd, RISCVBasicBlock block) {
        super(block);
        this.rs = rs;
        this.rd = rd;
    }

    public RISCVRegister getRd() {
        return rd;
    }

    public int getRs() {
        return rs;
    }

    @Override
    public String toString() {
        return "li " + rd + ", " + rs;
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
        return new RISCVLi(rs, rd, instIn);
    }

    @Override
    public void accept(RISCVVisitor visitor) {
        visitor.visit(this);
    }
}

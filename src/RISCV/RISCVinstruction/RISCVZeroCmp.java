package RISCV.RISCVinstruction;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVZeroCmp extends RISCVInstruction {
    public enum RISCVZeroCmpOp {seqz, snez}
    private RISCVRegister rs;
    private RISCVRegister rd;
    private RISCVZeroCmpOp op;
    public RISCVZeroCmp(RISCVRegister rs, RISCVZeroCmpOp op, RISCVRegister rd, RISCVBasicBlock instIn) {
        super(instIn);
        this.rs = rs;
        this.op = op;
        this.rd = rd;
    }
    @Override
    public String toString() {
        return op.toString() + " " + rd.toString() + ", " + rs.toString();
    }
    @Override
    public HashSet<RISCVRegister> Uses() {
        HashSet<RISCVRegister> res = new HashSet<>();
        res.add(rs);
        return res;
    }
    @Override
    public void UpdateUse(RISCVRegister Old, RISCVRegister New) {
        if (rs == Old) rs = New;
    }
    @Override
    public HashSet<RISCVRegister> Defs() {
        HashSet<RISCVRegister> res = new HashSet<>();
        res.add(rd);
        return res;
    }
    @Override
    public void UpdateDef(RISCVRegister Old, RISCVRegister New) {
        if (rd == Old) rd = New;
    }

    @Override
    public RISCVInstruction copy() {
        return new RISCVZeroCmp(rs, op, rd, instIn);
    }
}

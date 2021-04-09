package RISCV.RISCVinstruction;

import RISCV.RISCVUtility.RISCVVisitor;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVimmediate.RISCVImmediate;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVLui extends RISCVInstruction {
    public RISCVImmediate imm;
    public RISCVRegister rd;
    public RISCVLui(RISCVImmediate imm, RISCVRegister rd, RISCVBasicBlock instIn) {
        super(instIn);
        this.imm = imm;
        this.rd = rd;
    }

    public RISCVRegister getRd() {
        return rd;
    }

    public RISCVImmediate getImm() {
        return imm;
    }

    @Override
    public String toString() {
        return "lui " + rd.toString() + ", " + imm.toString();
    }
    @Override
    public HashSet<RISCVRegister> Defs() {
        HashSet<RISCVRegister> res = new HashSet<>();
        res.add(rd);
        return res;
    }

    @Override
    public HashSet<RISCVRegister> Uses() {
        return new HashSet<>();
    }

    @Override
    public void UpdateDef(RISCVRegister Old, RISCVRegister New) {
        if (rd == Old) rd = New;
    }
    @Override
    public void updateOffset(int offset) {
        imm.updateOffset(offset);
    }

    @Override
    public RISCVInstruction copy() {
        return new RISCVLui(imm, rd, instIn);
    }

    @Override
    public void accept(RISCVVisitor visitor) {
        visitor.visit(this);
    }
}

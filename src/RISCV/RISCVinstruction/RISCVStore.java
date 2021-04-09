package RISCV.RISCVinstruction;

import RISCV.RISCVUtility.RISCVVisitor;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVimmediate.RISCVImmediate;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVStore extends RISCVInstruction {
    private RISCVRegister rs1;
    private RISCVRegister rs2;
    private RISCVImmediate imm;
    private int width;
    public RISCVStore(RISCVRegister rs1, RISCVImmediate imm, RISCVRegister rs2, int width, RISCVBasicBlock block) {
        super(block);
        this.rs1 = rs1;
        this.imm = imm;
        this.rs2 = rs2;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public RISCVImmediate getImm() {
        return imm;
    }

    public RISCVRegister getRs1() {
        return rs1;
    }

    public RISCVRegister getRs2() {
        return rs2;
    }

    @Override
    public String toString() {
        if(width == 1) return "sb " + rs2 + ", " + (imm + "(" + rs1 + ")");
        else return "sw " + rs2 + ", " + (imm + "(" + rs1 + ")");
    }
    @Override
    public HashSet<RISCVRegister> Uses() {
        HashSet<RISCVRegister> res = new HashSet<>();
        res.add(rs1);
        res.add(rs2);
        return res;
    }
    @Override
    public void UpdateUse(RISCVRegister Old, RISCVRegister New) {
        if (rs1 == Old) rs1 = New;
        if (rs2 == Old) rs2 = New;
    }
    @Override
    public void updateOffset(int offset) {
        imm.updateOffset(offset);
    }

    @Override
    public RISCVInstruction copy() {
        return new RISCVStore(rs1, imm, rs2, width, instIn);
    }

    @Override
    public void accept(RISCVVisitor visitor) {
        visitor.visit(this);
    }
}

package RISCV.RISCVinstruction.Binary;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVinstruction.RISCVInstruction;
import RISCV.RISCVoperand.RISCVimmediate.RISCVImmediate;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class ImmediateBinary extends RISCVInstruction {
    public enum ImmediateBinaryOp{
        addi, slli, srai, andi, ori, xori, slti
    }
    public RISCVRegister rs, rd;
    public RISCVImmediate imm;
    public ImmediateBinaryOp op;
    public ImmediateBinary(RISCVRegister rs, RISCVImmediate imm, ImmediateBinaryOp op, RISCVRegister rd, RISCVBasicBlock RISCVBasicBlock) {
        this.rs = rs;
        this.imm = imm;
        this.op = op;
        this.rd = rd;
        this.instIn = RISCVBasicBlock;
    }
    @Override
    public HashSet<RISCVRegister> Uses() {
        HashSet<RISCVRegister> res = new HashSet<>();
        res.add(rs);
        return res;
    }
    @Override
    public HashSet<RISCVRegister> Defs() {
        HashSet<RISCVRegister> res = new HashSet<>();
        res.add(rd);
        return res;
    }
    @Override
    public void UpdateUse(RISCVRegister Old, RISCVRegister New) {
        if(rs == Old) rs = New;
    }

    @Override
    public void UpdateDef(RISCVRegister Old, RISCVRegister New) {
        if(rd == Old) rd = New;
    }
    @Override
    public void updateOffset(int offset) { imm.updateOffset(offset); }

    @Override
    public RISCVInstruction copy() {
        return new ImmediateBinary(rs, imm, op, rd, instIn);
    }

    @Override
    public String toString() {
        return op.toString() + " " + rd.toString() + ", " + rs.toString() + ", " + imm.getValue();
    }
}

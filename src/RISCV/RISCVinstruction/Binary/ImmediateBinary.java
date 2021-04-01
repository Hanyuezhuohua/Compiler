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
        this.block = RISCVBasicBlock;
    }
    @Override
    public String toString() {
        return op + "" + rd + ", " + rs + ", " + imm.getValue();
    }
    @Override
    public HashSet<RISCVRegister> Uses() {
        return new HashSet<>(){{ add(rs); }};
    }
    @Override
    public HashSet<RISCVRegister> Defs() {
        return new HashSet<>() {{ add(rd); }};
    }
    @Override
    public void UpdateUse(RISCVRegister old, RISCVRegister newReg) {
        if (rs == old) { rs = newReg; }
    }

    @Override
    public void UpdateDef(RISCVRegister old, RISCVRegister newReg) {
        if (rd == old) {
            rd = newReg;
        }
    }
    @Override
    public void updateOffset(int stackOffset) {
        imm.updateOffset(stackOffset);
    }

    @Override
    public RISCVInstruction copy() {
        return new ImmediateBinary(rs, imm, op, rd, block);
    }
}

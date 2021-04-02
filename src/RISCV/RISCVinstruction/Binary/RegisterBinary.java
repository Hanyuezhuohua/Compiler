package RISCV.RISCVinstruction.Binary;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVinstruction.RISCVInstruction;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RegisterBinary extends RISCVInstruction {
    public enum RegisterBinaryOp{
        add, sub, mul, div, rem, sll, sra, and, or, xor, slt
    }
    public RISCVRegister rs1, rs2, rd;
    public RegisterBinaryOp op;
    public RegisterBinary(RISCVRegister rs1, RISCVRegister rs2, RegisterBinaryOp op, RISCVRegister rd, RISCVBasicBlock RISCVBasicBlock) {
        super(RISCVBasicBlock);
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.op = op;
        this.rd = rd;
    }
    @Override
    public String toString() {
        return op + " " + rd + ", " + rs1 + ", " + rs2;
    }
    @Override
    public HashSet<RISCVRegister> Uses() {
        return new HashSet<>(){{add(rs1); add(rs2);}};
    }
    @Override
    public void UpdateUse(RISCVRegister old, RISCVRegister newReg) {
        boolean success = false;
        if (rs1 == old) {
            rs1 = newReg;
            success = true;
        }
        if (rs2 == old) {
            rs2 = newReg;
            success = true;
        }
        assert success;
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
        return new RegisterBinary(rs1, rs2, op, rd, instIn);
    }
}

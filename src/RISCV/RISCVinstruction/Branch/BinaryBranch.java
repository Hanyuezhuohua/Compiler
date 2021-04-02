package RISCV.RISCVinstruction.Branch;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVinstruction.RISCVInstruction;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class BinaryBranch extends RISCVInstruction {
    public enum BinaryBranchOp {
        eq, ne, le, ge, lt, gt
    }
    public RISCVRegister rs1, rs2;
    public BinaryBranchOp op;
    public RISCVBasicBlock offset;
    public BinaryBranch(RISCVRegister rs1, RISCVRegister rs2, BinaryBranchOp op, RISCVBasicBlock offset, RISCVBasicBlock RISCVBasicBlock) {
        super(RISCVBasicBlock);
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.op = op;
        this.offset = offset;
    }
    @Override
    public String toString() {
        return "b" + op + " " + rs1 + ", " + rs2 + ", " + offset;
    }
    @Override
    public HashSet<RISCVRegister> Uses() {
        return new HashSet<>(){{add(rs1); add(rs2);}};
    }
    @Override
    public void UpdateUse(RISCVRegister old, RISCVRegister newReg) {
        if (rs1 == old) {
            rs1 = newReg;
        }
        if (rs2 == old) {
            rs2 = newReg;
        }
    }

    @Override
    public RISCVInstruction copy() {
        return new BinaryBranch(rs1, rs2, op, offset, instIn);
    }
}

package RISCV.RISCVinstruction;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVoperand.RISCVimmediate.RISCVImmediate;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;
import Util.error.ErrorMessage;

import java.util.HashSet;

public class RISCVStore extends RISCVInstruction {
    public RISCVRegister rs1, rs2;
    public RISCVImmediate offset;
    public int width;
    public RISCVStore(RISCVRegister rs1, RISCVImmediate offset, RISCVRegister rs2, int width, RISCVBasicBlock block) {
        this.block = block;
        this.rs1 = rs1;
        this.offset = offset;
        this.rs2 = rs2;
        this.width = width;
    }
    @Override
    public String toString() {
        switch (width) {
            case 1:
                return "sb " + rs2 + ", " + (offset + "(" + rs1 + ")");
            case 4:
                return "sw " + rs2 + ", " + (offset + "(" + rs1 + ")");
        }
        throw new ErrorMessage();
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
    public void updateOffset(int stackOffset) {
        offset.updateOffset(stackOffset);
    }

    @Override
    public RISCVInstruction copy() {
        return new RISCVStore(rs1, offset, rs2, width, block);
    }
}

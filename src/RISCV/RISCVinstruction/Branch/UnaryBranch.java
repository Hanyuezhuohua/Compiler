package RISCV.RISCVinstruction.Branch;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVinstruction.RISCVInstruction;

public class UnaryBranch extends RISCVInstruction {
    public RISCVBasicBlock offset;
    public UnaryBranch(RISCVBasicBlock offset, RISCVBasicBlock RISCVBasicBlock) {
        super(RISCVBasicBlock);
        this.offset = offset;
    }
    @Override
    public String toString() {
        return "j " + offset;
    }

    @Override
    public RISCVInstruction copy() {
        return new UnaryBranch(offset, instIn);
    }
}

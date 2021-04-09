package RISCV.RISCVinstruction.Branch;

import RISCV.RISCVUtility.RISCVVisitor;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVinstruction.RISCVInstruction;

public class UnaryBranch extends RISCVInstruction {
    public RISCVBasicBlock jumpTo;
    public UnaryBranch(RISCVBasicBlock jumpTo, RISCVBasicBlock RISCVBasicBlock) {
        super(RISCVBasicBlock);
        this.jumpTo = jumpTo;
    }

    public RISCVBasicBlock getJumpTo() {
        return jumpTo;
    }

    @Override
    public String toString() {
        return "j " + jumpTo;
    }

    @Override
    public RISCVInstruction copy() {
        return new UnaryBranch(jumpTo, instIn);
    }

    @Override
    public void accept(RISCVVisitor visitor) {
        visitor.visit(this);
    }
}

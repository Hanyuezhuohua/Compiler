package RISCV.RISCVinstruction;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVmodule.RISCVModule;
import RISCV.RISCVoperand.RISCVregister.RISCVPhysicalRegister;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class RISCVReturn extends RISCVInstruction {
    static RISCVPhysicalRegister ra;
    public RISCVReturn(RISCVModule root, RISCVBasicBlock RISCVBasicBlock) {
        if (root != null) ra = root.getPhysicalRegister("ra");
        this.instIn = RISCVBasicBlock;
    }
    @Override
    public String toString() {
        return "ret";
    }
    @Override
    public HashSet<RISCVRegister> Uses() {
        return new HashSet<>() {{ add(ra); }};
    }

    @Override
    public RISCVInstruction copy() {
        return new RISCVReturn(null, instIn);
    }
}

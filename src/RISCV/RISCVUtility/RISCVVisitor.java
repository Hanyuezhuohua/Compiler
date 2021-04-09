package RISCV.RISCVUtility;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVfunction.RISCVFunction;
import RISCV.RISCVinstruction.*;
import RISCV.RISCVinstruction.Binary.ImmediateBinary;
import RISCV.RISCVinstruction.Binary.RegisterBinary;
import RISCV.RISCVinstruction.Branch.BinaryBranch;
import RISCV.RISCVinstruction.Branch.UnaryBranch;
import RISCV.RISCVmodule.RISCVModule;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

public interface RISCVVisitor {
    void visit(RISCVModule module);
    void visit(RISCVFunction function);
    void visit(RISCVBasicBlock block);
    void visit(ImmediateBinary inst);
    void visit(RegisterBinary inst);
    void visit(BinaryBranch inst);
    void visit(UnaryBranch inst);
    void visit(RISCVCall inst);
    void visit(RISCVLa inst);
    void visit(RISCVLi inst);
    void visit(RISCVLoad inst);
    void visit(RISCVLui inst);
    void visit(RISCVMove inst);
    void visit(RISCVReturn inst);
    void visit(RISCVStore inst);
    void visit(RISCVZeroCmp inst);
}

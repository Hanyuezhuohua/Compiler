package RISCV.RISCVinstruction;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVfunction.RISCVFunction;
import RISCV.RISCVmodule.RISCVModule;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

import static java.lang.Integer.min;

public class RISCVCall extends RISCVInstruction {
    public RISCVFunction callee;
    public RISCVModule rootInfo;

    public RISCVCall(RISCVModule rootInfo, RISCVFunction callee, RISCVBasicBlock block) {
        super(block);
        this.callee = callee;
        this.rootInfo = rootInfo;
    }

    @Override
    public String toString() {
        return "call " + callee;
    }

    @Override
    public HashSet<RISCVRegister> Uses() {
        return new HashSet<>() {{
            for (int i = 0; i < min(callee.getParameters().size(), 8); i++) {
                add(rootInfo.getFuncArgs().get(i));
            }
        }};
    }

    @Override
    public void UpdateUse(RISCVRegister old, RISCVRegister newReg) {
        assert false;
    }

    @Override
    public HashSet<RISCVRegister> Defs() {
        return new HashSet<>(rootInfo.getCallerSavedRegs());
    }

    @Override
    public RISCVInstruction copy() {
        return new RISCVCall(rootInfo, callee, instIn);
    }
}

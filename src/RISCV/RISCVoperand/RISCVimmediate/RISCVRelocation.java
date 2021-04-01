package RISCV.RISCVoperand.RISCVimmediate;

import RISCV.RISCVoperand.RISCVregister.RISCVGlobalRegister;

public class RISCVRelocation extends RISCVImmediate {
    public enum RelocationType {
        hi, lo
    }
    private RISCVGlobalRegister var;
    private RelocationType location;

    public RISCVRelocation(RISCVGlobalRegister var, RelocationType location) {
        super(0);
        this.var = var;
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RISCVRelocation && ((RISCVRelocation) obj).var.equals(var) && ((RISCVRelocation) obj).location.equals(location);
    }

    @Override
    public int hashCode() {
        return var.hashCode() ^ location.hashCode();
    }

    @Override
    public String toString() {
        return "%" + location.toString() + "(" + var + ")";
    }
}

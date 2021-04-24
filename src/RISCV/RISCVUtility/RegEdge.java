package RISCV.RISCVUtility;

import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

public class RegEdge {
    private RISCVRegister from;
    private RISCVRegister to;

    public RegEdge(RISCVRegister from, RISCVRegister to) {
        this.from = from;
        this.to = to;
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof RegEdge && ((RegEdge) obj).from.equals(from) && ((RegEdge) obj).to.equals(to);
    }
    @Override
    public int hashCode() {
        return from.hashCode() ^ to.hashCode();
    }
}

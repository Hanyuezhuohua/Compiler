package RISCV.RISCVoperand.RISCVimmediate;

public class RISCVStackOffset extends RISCVImmediate {
    private boolean direction;
    private boolean assigned;
    public RISCVStackOffset(int value, boolean direction) {
        super(value);
        this.direction = direction;
        assigned = false;
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof RISCVStackOffset && ((RISCVStackOffset) obj).value == value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public void updateOffset(int offset) {
        if (!assigned) value += offset * (direction ? -1 : 1);
        assigned = true;
    }
}

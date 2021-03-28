package RISCV.RISCVoperand.RISCVimmediate;

public class RISCVImmediate {
    protected int value;
    public RISCVImmediate(int value) { this.value = value; }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RISCVImmediate && ((RISCVImmediate) obj).value == value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public void updateOffset(int offset) {}

    public int getValue() {
        return value;
    }
}

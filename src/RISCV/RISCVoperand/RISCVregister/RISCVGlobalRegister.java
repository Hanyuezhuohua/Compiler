package RISCV.RISCVoperand.RISCVregister;

public class RISCVGlobalRegister extends RISCVRegister {
    private int width;
    private String identifier;

    public RISCVGlobalRegister(String identifier, int width) {
        this.width = width;
        this.identifier = identifier;
    }

    public int getWidth() { return width; }

    public String getIdentifier() { return identifier; }

    @Override
    public String toString() {
        return identifier;
    }
}

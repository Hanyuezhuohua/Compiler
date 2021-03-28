package RISCV.RISCVoperand.RISCVregister;

public class RISCVGlobalRegister extends RISCVRegister {
    public int width;
    public String identifier;

    public RISCVGlobalRegister(String identifier, int width) {
        this.width = width;
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }
}

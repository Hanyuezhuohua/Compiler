package RISCV.RISCVoperand.RISCVregister;

public class RISCVPhysicalRegister extends RISCVRegister {
    private String identifier;
    public RISCVPhysicalRegister(String identifier) {
        this.identifier = identifier;
    }
    @Override
    public String toString() {
        return identifier;
    }
}

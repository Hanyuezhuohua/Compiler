package RISCV.RISCVfunction;

import RISCV.RISCVUtility.RISCVVisitor;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVmodule.RISCVModule;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class RISCVFunction {
    private RISCVModule functionIn;
    private String identifier;
    public HashSet<RISCVBasicBlock> blockContain;
    private ArrayList<RISCVRegister> parameters;
    private RISCVBasicBlock entry;
    private RISCVBasicBlock exit;
    private int Offset;
    public RISCVFunction(RISCVModule functionIn, String identifier) {
        this.functionIn = functionIn;
        this.identifier = identifier;
        blockContain = new LinkedHashSet<>();
        parameters = new ArrayList<>();
        entry = null;
        exit = null;
        Offset = 0;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void addBlock(RISCVBasicBlock block){
        blockContain.add(block);
    }

    public void removeBlock(RISCVBasicBlock block){
        blockContain.remove(block);
    }

    public HashSet<RISCVBasicBlock> getBlockContain() {
        return blockContain;
    }

    public ArrayList<RISCVRegister> getParameters() {
        return parameters;
    }

    public void addParameter(RISCVRegister register) { parameters.add(register); }

    public RISCVBasicBlock getEntry() {
        return entry;
    }

    public void setEntry(RISCVBasicBlock entry) {
        this.entry = entry;
    }

    public RISCVBasicBlock getExit() {
        return exit;
    }

    public void setExit(RISCVBasicBlock exit) {
        this.exit = exit;
    }

    public void setOffset(int offset) {
        Offset = offset;
    }

    public int getOffset() {
        return Offset;
    }

    @Override
    public String toString() {
        return this.identifier;
    }

    public void accept(RISCVVisitor visitor){
        visitor.visit(this);
    }
}

package RISCV.RISCVoperand.RISCVregister;

import RISCV.RISCVinstruction.RISCVMove;
import RISCV.RISCVoperand.RISCVimmediate.RISCVImmediate;
import RISCV.RISCVoperand.RISCVOperand;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class RISCVRegister implements RISCVOperand {
    public int degree;
    public float weight;
    public RISCVPhysicalRegister color;
    public RISCVRegister alias;
    public RISCVImmediate offset;
    public HashSet<RISCVRegister> adjList;
    public HashSet<RISCVMove> moveList;
    public RISCVRegister() {
        degree = 0;
        weight = 0;
        if (this instanceof RISCVPhysicalRegister) color = (RISCVPhysicalRegister) this;
        else color = null;
        alias = null;
        offset = null;
        adjList = new LinkedHashSet<>();
        moveList = new LinkedHashSet<>();
    }
    public void clear() {
        weight = 0;
        degree = 0;
        color = null;
        alias = null;
        adjList = new LinkedHashSet<>();
        moveList = new LinkedHashSet<>();
    }
    public void init() {
        weight = 0;
        degree = 0x3f3f3f3f;
        color = (RISCVPhysicalRegister) this;
        alias = null;
        adjList = new LinkedHashSet<>();
        moveList = new LinkedHashSet<>();
    }
    public void addAdj(RISCVRegister reg) {
        adjList.add(reg);
        degree++;
    }

    public HashSet<RISCVRegister> getAdjList() {
        return adjList;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public RISCVPhysicalRegister getColor() {
        return color;
    }

    public void setColor(RISCVPhysicalRegister color) {
        this.color = color;
    }

    public void setAlias(RISCVRegister alias) {
        this.alias = alias;
    }

    public RISCVRegister getAlias() {
        return alias;
    }

    public void setOffset(RISCVImmediate offset) {
        this.offset = offset;
    }

    public RISCVImmediate getOffset() {
        return offset;
    }

    public void addMove(RISCVMove inst){
        moveList.add(inst);
    }

    public HashSet<RISCVMove> getMoveList() {
        return moveList;
    }
}

package IR.IRtype;

import IR.IRoperand.IRConstNull;
import IR.IRoperand.IROperand;

public class IRPointerType implements IRType{
    private IRType pointTo;
    private boolean resolvable;

    public IRPointerType(IRType pointTo, boolean resolvable){
        this.pointTo = pointTo;
        this.resolvable = resolvable;
    }

    @Override
    public int getSize() {
        return 32;
    }

    @Override
    public String getType() {
        return  pointTo.getType() + "*";
    }

    public IRType getPointTo() {
        return pointTo;
    }

    @Override
    public IROperand initValue() { return new IRConstNull(); }

    @Override
    public String toString() {
        return getType();
    }

    @Override
    public Boolean resolvable() { return resolvable; }

    @Override
    public Boolean CSEChecker(IRType other) {
        return other instanceof IRPointerType && ((IRPointerType) other).pointTo.CSEChecker(pointTo);
    }
}

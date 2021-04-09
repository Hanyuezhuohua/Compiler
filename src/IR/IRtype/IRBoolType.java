package IR.IRtype;

import IR.IRoperand.IRConstBool;
import IR.IRoperand.IROperand;

public class IRBoolType implements IRType{ // same to i1
    @Override
    public int getSize() {
        return 8;
    }

    @Override
    public String getType() {
        return "i1";
    }

    @Override
    public IROperand initValue() { return new IRConstBool(false); }

    @Override
    public Boolean resolvable() { return false; }

    @Override
    public String toString() {
        return getType();
    }

    @Override
    public Boolean CSEChecker(IRType other) {
        return other instanceof IRBoolType;
    }
}

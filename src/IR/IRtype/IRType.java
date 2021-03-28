package IR.IRtype;

import IR.IRoperand.IROperand;

public interface IRType {
    public abstract int getSize();
    public abstract String getType();
    public abstract IROperand initValue();
    public abstract Boolean resolvable();
}

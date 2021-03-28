package IR.IRtype;

import IR.IRoperand.IROperand;
import Util.error.ErrorMessage;

public class IRVoidType implements IRType{
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getType() {
        return "void";
    }

    @Override
    public IROperand initValue() { throw new ErrorMessage("IRVoidType initValue ERROR"); }

    @Override
    public String toString() {
        return getType();
    }

    @Override
    public Boolean resolvable() { return false; }
}

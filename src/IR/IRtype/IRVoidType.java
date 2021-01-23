package IR.IRtype;

import Util.error.ErrorMessage;

public class IRVoidType implements IRType{
    @Override
    public int getSize() {
        throw new ErrorMessage("IRVoidType getSize ERROR");
    }

    @Override
    public String getType() {
        return "void";
    }
}

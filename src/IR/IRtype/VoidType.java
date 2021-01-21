package IR.IRtype;

import Util.error.ErrorMessage;

public class VoidType implements Type{
    @Override
    public int getSize() {
        throw new ErrorMessage("VoidType getSize ERROR");
    }

    @Override
    public String getType() {
        return "void";
    }
}

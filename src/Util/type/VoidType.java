package Util.type;

import Util.error.ErrorMessage;

import javax.swing.text.Position;

public class VoidType implements Type{
    @Override
    public String getType() {
        return "void";
    }

    @Override
    public int getDim() {
        return 0;
    }

    @Override
    public void checkAssignment(Type t, Position pos) {
        throw new ErrorMessage("VoidType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, Position pos) {
        throw new ErrorMessage("VoidType Equality Error", pos);
    }
}

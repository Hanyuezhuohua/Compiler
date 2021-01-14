package Util.type;

import Util.error.ErrorMessage;

import javax.swing.text.Position;

public class BaseType implements Type{
    @Override
    public String getType() {
        return null;
    }

    @Override
    public int getDim() {
        return 0;
    }

    @Override
    public void checkAssignment(Type t, Position pos) {
        throw new ErrorMessage("BaseType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, Position pos) {
        throw new ErrorMessage("BaseType Equality Error", pos);
    }
}

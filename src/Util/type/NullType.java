package Util.type;

import Util.error.ErrorMessage;

import javax.swing.text.Position;

public class NullType implements Type{
    @Override
    public String getType() {
        return "null";
    }

    @Override
    public int getDim() {
        return 0;
    }

    @Override
    public void checkAssignment(Type t, Position pos) {
        throw new ErrorMessage("NullType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, Position pos) {
        if(t.getType().equals("int") || t.getType().equals("bool")) return;
        else throw new ErrorMessage("NullType Equality Error", pos);
    }
}

package Util.type;

import Util.error.ErrorMessage;

import javax.swing.text.Position;

public class BoolType implements Type{
    @Override
    public String getType() {
        return "bool";
    }

    @Override
    public int getDim() {
        return 0;
    }

    @Override
    public void checkAssignment(Type t, Position pos) {
        if(t.getType().equals("bool")) return;
        else throw new ErrorMessage("BoolType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, Position pos) {
        if(t.getType().equals("bool")) return;
        else throw new ErrorMessage("BoolType Equality Error", pos);
    }
}

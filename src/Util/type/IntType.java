package Util.type;

import Util.error.ErrorMessage;

import javax.swing.text.Position;

public class IntType implements Type{
    @Override
    public String getType() {
        return "int";
    }

    @Override
    public int getDim() {
        return 0;
    }

    @Override
    public void checkAssignment(Type t, Position pos) {
        if(t.getType().equals("int")) return;
        else throw new ErrorMessage("IntType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, Position pos) {
        if(t.getType().equals("int")) return;
        else throw new ErrorMessage("IntTYpe Equality Error", pos);
    }
}

package Util.type;

import Util.error.ErrorMessage;

import Util.position;

public class ClassType implements Type{
    private String identifier;

    public ClassType(String identifier){
        this.identifier = identifier;
    }

    @Override
    public String getType() {
        return identifier;
    }

    @Override
    public int getDim() {
        return 0;
    }

    @Override
    public void checkAssignment(Type t, position pos) {
        if(t.getType().equals(identifier) || t.getType().equals("null")) return;
        else throw new ErrorMessage("ClassType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, position pos) {
        if (t.getType().equals(identifier) || t.getType().equals("null")) return;
        else throw new ErrorMessage("ClassType Equality Error", pos);
    }
}

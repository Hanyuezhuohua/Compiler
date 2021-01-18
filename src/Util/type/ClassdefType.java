package Util.type;

import Util.error.ErrorMessage;
import Util.position;

public class ClassdefType implements Type{
    @Override
    public String getType() {
        return "class";
    }

    @Override
    public int getDim() {
        return 0;
    }

    @Override
    public void checkAssignment(Type t, position pos) {
        throw new ErrorMessage("ClassdefType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, position pos) {
        throw new ErrorMessage("ClassdefType Equality Error", pos);
    }
}
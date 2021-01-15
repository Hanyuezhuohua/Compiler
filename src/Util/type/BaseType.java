package Util.type;

import Util.error.ErrorMessage;
import Util.position;

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
    public void checkAssignment(Type t, position pos) {
        throw new ErrorMessage("BaseType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, position pos) {
        throw new ErrorMessage("BaseType Equality Error", pos);
    }
}

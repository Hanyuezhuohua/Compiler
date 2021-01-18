package Util.type;

import Util.error.ErrorMessage;
import Util.position;

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
    public void checkAssignment(Type t, position pos) {
        throw new ErrorMessage("NullType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, position pos) {
        if(t.getType().equals("int") || t.getType().equals("bool") || t.getType().equals("void")){
            throw new ErrorMessage("NullType Equality Error", pos);
        }
        else return;
    }
}

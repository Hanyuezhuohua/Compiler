package Util.type;

import Util.error.ErrorMessage;
import Util.position;

public class StringType implements Type{
    @Override
    public String getType() {
        return "string";
    }

    @Override
    public int getDim() {
        return 0;
    }

    @Override
    public void checkAssignment(Type t, position pos) {
        if(t.getType().equals("string") || t.getType().equals("null")) return;
        else throw new ErrorMessage("StringType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, position pos) {
        if(t.getType().equals("string") || t.getType().equals("null")) return;
        else throw new ErrorMessage("StringType Equality Error", pos);
    }
}

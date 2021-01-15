package Util.type;

import Util.error.ErrorMessage;
import Util.position;

public class ArrayType implements Type{
    private Type elementType;
    private int dim;
    public ArrayType(Type t, int d){
        elementType = t;
        dim = d;
    }
    public ArrayType(Type t){
        elementType = t;
        dim = 0;
    }
    public ArrayType(){
        elementType = new BaseType();
        dim = 0;
    }
    public void setDim(int d){
        dim = d;
    }
    public void setElementType(Type t){
        elementType = t;
    }

    @Override
    public String getType() {
        return elementType.getType() + "[" + dim + "]";
    }

    @Override
    public int getDim() {
        return dim;
    }

    public String getElementType() {
        return elementType.getType();
    }

    public Type getBaseType(){
        return elementType;
    }

    @Override
    public void checkAssignment(Type t, position pos) {
        if(t.getType().equals("null") || (t.getType().equals(getType()))) return;
        else throw new ErrorMessage("ArrayType Assignment Error", pos);
    }

    @Override
    public void checkEquality(Type t, position pos) {
        if(t.getType().equals("null")) return;
        else throw new ErrorMessage("ArrayType Equality Error", pos);
    }
}

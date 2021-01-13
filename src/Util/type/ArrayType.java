package Util.type;

public class ArrayType extends Type{
    public int dim;
    public Type elementtype;
    public ArrayType(Type t, int dim){ //t should be some basic type here
        super(t.type + "_array");
        this.elementtype = t;
        this.dim = dim;
    }

    @Override
    public int getDim(){
        return dim;
    }

    @Override
    public boolean isequal(Type t) {
        return this.getDim() == t.getDim() && this.type.equals(t.type);
    }

    @Override
    public String tostring() {
        return null;
    }
}

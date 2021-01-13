package Util.type;

public class BoolType extends Type{
    public BoolType(){super("bool");}

    @Override
    public boolean isequal(Type t){
        return this.type.equals(t.type);
    }

    @Override
    public String tostring(){
        return this.type;
    }
}

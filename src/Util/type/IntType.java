package Util.type;

public class IntType extends Type{
    public IntType(){super("int");}

    @Override
    public boolean isequal(Type t){
        return this.type.equals(t.type);
    }

    @Override
    public String tostring(){
        return this.type;
    }
}

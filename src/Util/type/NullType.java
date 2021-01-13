package Util.type;

public class NullType extends Type{
    public NullType(){super("null");}
    @Override
    public boolean isequal(Type t){
        return this.type.equals(t.type);
    }
    public String tostring(){
        return this.type;
    }
}

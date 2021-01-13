package Util.type;

public class VoidType extends Type{
    public VoidType(){super("void");}
    @Override
    public boolean isequal(Type t){
        return this.type.equals(t.type);
    }
    public String tostring(){
        return this.type;
    }
}

package Util.type;

public class StringType extends Type{
    public StringType(){super("string");}
    @Override
    public boolean isequal(Type t){
        return this.type.equals(t.type);
    }
    public String tostring(){
        return this.type;
    }
}

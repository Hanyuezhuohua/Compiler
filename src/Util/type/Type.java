package Util.type;

public abstract class Type {
    public String type;
    public Type(String type){ this.type = type; }
    public int getDim(){ return 0; }
    public abstract boolean isequal(Type t);
    public abstract String tostring();
}

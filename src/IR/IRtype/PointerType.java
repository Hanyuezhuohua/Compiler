package IR.IRtype;

public class PointerType implements Type{
    private Type pointTo;

    public PointerType(Type pointTo){
        this.pointTo = pointTo;
    }

    @Override
    public int getSize() {
        return 32;
    }

    @Override
    public String getType() {
        return  pointTo.toString() + "*";
    }
}

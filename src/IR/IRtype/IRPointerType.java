package IR.IRtype;

public class IRPointerType implements IRType{
    private IRType pointTo;

    public IRPointerType(IRType pointTo){
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

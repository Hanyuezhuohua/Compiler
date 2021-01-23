package IR.IRtype;

public class IRArrayType implements IRType{
    private int arraySize;
    private IRType baseType;

    public IRArrayType(int arraySize, IRType baseType){
        this.arraySize = arraySize;
        this.baseType = baseType;
    }

    @Override
    public int getSize() {
        return arraySize * baseType.getSize(); //check later
    }

    @Override
    public String getType() {
        return "[" + arraySize + "x" + baseType.toString() + "]";
    }
}

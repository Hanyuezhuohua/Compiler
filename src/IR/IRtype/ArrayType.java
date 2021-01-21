package IR.IRtype;

public class ArrayType implements Type{
    private int arraySize;
    private Type baseType;

    public ArrayType(int arraySize, Type baseType){
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

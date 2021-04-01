package IR.IRtype;

import IR.IRoperand.IRConstNull;
import IR.IRoperand.IROperand;

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
        return "[" + arraySize + " x " + baseType.getType() + "]";
    }

    @Override
    public IROperand initValue() { return new IRConstNull(); }

    @Override
    public Boolean resolvable() { return false; }

    @Override
    public String toString() {
        return getType();
    }
}

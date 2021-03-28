package IR.IRtype;

import IR.IRoperand.IRConstInt;
import IR.IRoperand.IROperand;
import Util.error.ErrorMessage;

public class IRIntType implements IRType{
    public enum IntTypeBytes{
        Int8, Int32
    }
    private IntTypeBytes intTypeBytes;

    public IRIntType(IntTypeBytes intTypeBytes){
        this.intTypeBytes = intTypeBytes;
    }

    @Override
    public int getSize() {
        if(intTypeBytes.equals(IntTypeBytes.Int8)) return 8;
        else if(intTypeBytes.equals(IntTypeBytes.Int32)) return 32;
        else throw new ErrorMessage("IR IntType getSize ERROR");
    }

    @Override
    public String getType() {
        if(intTypeBytes.equals(IntTypeBytes.Int8)) return "i8";
        else if(intTypeBytes.equals(IntTypeBytes.Int32)) return "i32";
        else throw new ErrorMessage("IR IntType toString ERROR");
    }

    @Override
    public IROperand initValue() {
        return new IRConstInt(0, intTypeBytes);
    }

    @Override
    public Boolean resolvable() { return false; }

    @Override
    public String toString() {
        return getType();
    }
}

package IR.IRtype;

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
        if(intTypeBytes.equals(IntTypeBytes.Int8)) return "int8";
        else if(intTypeBytes.equals(IntTypeBytes.Int32)) return "int32";
        else throw new ErrorMessage("IR IntType toString ERROR");
    }
}

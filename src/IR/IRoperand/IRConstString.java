package IR.IRoperand;

import IR.IRinstruction.IRInstruction;
import IR.IRtype.IRArrayType;
import IR.IRtype.IRIntType;
import IR.IRtype.IRPointerType;

import java.util.HashSet;

public class IRConstString extends IROperand{
    private String value;

    public IRConstString(String value, int num){
        super(new IRPointerType(new IRArrayType(value.length(), new IRIntType(IRIntType.IntTypeBytes.Int8)), true), "str" + Integer.toString(num));
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String PrintOperand() {
        return "@" + super.PrintOperand();
    }

    public String IRStringChange(){
        String res = new String(value);
        res = res.replace("\\\\", "\\5C");
        res = res.replace("\\n", "\\0A");
        res = res.replace("\\\"", "\\22");
        res = res.replace("\\r", "\\0D");
        res = res.replace("\\t", "\\09");
        return "c\"" + res + "\"";
    }

    @Override
    public HashSet<IRInstruction> getInstructions() {
        return new HashSet<>();
    }

    @Override
    public void appendInst(IRInstruction inst) {}

    @Override
    public void removeInst(IRInstruction inst) {}

    @Override
    public boolean isZero() { return false; }

}

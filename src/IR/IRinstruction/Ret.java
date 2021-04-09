package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRVoidType;
import IR.IRutility.IRCopy;
import IR.IRutility.IRVisitor;

import java.util.HashSet;

public class Ret extends IRInstruction{
    private IROperand value;

    public Ret(IRBasicBlock instIn, IROperand value){
        super(instIn);
        this.value = value;
        if(!(value.getOperandType() instanceof IRVoidType)){
            this.value.appendInst(this);
        }
    }

    @Override
    public void instCopy(IRBasicBlock instIn, IRCopy Map) {
        instIn.addInst(new Ret(instIn, Map.get(value)));
    }

    @Override
    public boolean Terminal() {
        return true;
    }

    @Override
    public String PrintInst() {
        return "ret "
                + value.getOperandType().getType()
                + " "
                + value.PrintOperand();
    }

    @Override
    public HashSet<IROperand> getOperands() {
        HashSet<IROperand> operands = new HashSet<>();
        if(!(value.getOperandType() instanceof IRVoidType)){
            operands.add(value);
        }
        return operands;
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        if (value.equals(Old)){
            value = New;
        }
    }

    public IROperand getValue() {
        return value;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean hasSideEffect() {
        return true;
    }

    @Override
    public boolean CSEChecker(IRInstruction other) {
        return false;
    }
}

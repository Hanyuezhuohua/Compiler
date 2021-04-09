package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRPointerType;
import IR.IRutility.IRCopy;
import IR.IRutility.IRVisitor;

import java.util.HashMap;
import java.util.HashSet;

public class Alloca extends IRInstruction{
    private IRLocalRegister result;
    public Alloca(IRBasicBlock instIn, IRLocalRegister result){
        super(instIn);
        this.result = result;
    }

    @Override
    public void instCopy(IRBasicBlock instIn, IRCopy Map) {
        instIn.addInst(new Alloca(instIn, (IRLocalRegister) Map.get(result)));
    }

    @Override
    public boolean Terminal() {
        return false;
    }

    @Override
    public IRLocalRegister getResult() {
        return result;
    }

    @Override
    public String PrintInst() {
        return result.PrintOperand()
                + " = alloca "
                + ((IRPointerType) result.getOperandType()).getPointTo().getType()
                + ", align "
                + ((IRPointerType) result.getOperandType()).getPointTo().getSize() / 8;
    }

    @Override
    public HashSet<IROperand> getOperands() {
        return new HashSet<>();
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {}

    @Override
    public boolean hasSideEffect() {
        return true;
    }

    @Override
    public boolean CSEChecker(IRInstruction other) {
        return false;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

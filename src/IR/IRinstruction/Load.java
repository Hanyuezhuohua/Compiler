package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRCopy;
import IR.IRutility.IRVisitor;

import java.util.HashSet;

public class Load extends IRInstruction{
    private IROperand pointer;
    private IROperand result;

    public Load(IRBasicBlock instIn, IROperand pointer, IROperand result){
        super(instIn);
        this.pointer = pointer;
        this.result = result;
        this.pointer.appendInst(this);
    }

    @Override
    public void instCopy(IRBasicBlock instIn, IRCopy Map) {
        instIn.addInst(new Load(instIn, Map.get(pointer), Map.get(result)));
    }

    @Override
    public boolean Terminal() {
        return false;
    }

    @Override
    public String PrintInst() {
        return result.PrintOperand()
                + " = load "
                + result.getOperandType().getType()
                + ", "
                + pointer.getOperandType().getType()
                + " "
                + pointer.PrintOperand()
                + ", align "
                + result.getOperandType().getSize()/ 8;
    }

    @Override
    public IROperand getResult() {
        return result;
    }

    public IROperand getPointer() {
        return pointer;
    }

    @Override
    public HashSet<IROperand> getOperands() {
        HashSet<IROperand> operands = new HashSet<>();
        operands.add(pointer);
        return operands;
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        if (pointer.equals(Old)){
            pointer = New;
        }
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

package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRPointerType;
import IR.IRutility.IRVisitor;

import java.util.ArrayList;
import java.util.HashSet;

public class GetElementPtr extends IRInstruction{
    private IROperand ptrval;
    private ArrayList<IROperand> index;
    private IROperand result;

    public GetElementPtr(IRBasicBlock instIn, IROperand ptrval, ArrayList<IROperand> index, IROperand result){
        super(instIn);
        this.ptrval = ptrval;
        this.index = index;
        this.result = result;
        this.ptrval.appendInst(this);
        this.index.forEach(operand -> {
            operand.appendInst(this);
        });
    }

    @Override
    public boolean Terminal() {
        return false;
    }

    @Override
    public String PrintInst() {
        StringBuilder res = new StringBuilder();
        res.append(result.PrintOperand());
        res.append(" = getelementptr inbounds ");
        res.append(((IRPointerType) ptrval.getOperandType()).getPointTo().getType());
        res.append(", ");
        res.append(ptrval.getOperandType().getType());
        res.append(" ");
        res.append(ptrval.PrintOperand());
        for(int i = 0; i < index.size(); ++i){
            res.append(", ");
            res.append(index.get(i).getOperandType().getType());
            res.append(" ");
            res.append(index.get(i).PrintOperand());
        }
        return res.toString();
    }

    @Override
    public IROperand getResult() {
        return result;
    }

    public ArrayList<IROperand> getIndex() {
        return index;
    }

    public IROperand getPtrval() {
        return ptrval;
    }

    @Override
    public HashSet<IROperand> getOperands() {
        HashSet<IROperand> operands = new HashSet<>(index);
        operands.add(ptrval);
        return operands;
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        for (int i = 0; i < index.size(); ++i){
            if(index.get(i).equals(Old)){
                index.set(i, New);
            }
        }
        if (ptrval.equals(Old)){
            ptrval = New;
        }
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

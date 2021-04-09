package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRPointerType;
import IR.IRutility.IRCopy;
import IR.IRutility.IRVisitor;

import java.util.ArrayList;
import java.util.HashSet;

public class GetElementPtr extends IRInstruction{
    private IROperand ptrval;
    private ArrayList<IROperand> indexs;
    private IROperand result;

    public GetElementPtr(IRBasicBlock instIn, IROperand ptrval, ArrayList<IROperand> indexs, IROperand result){
        super(instIn);
        this.ptrval = ptrval;
        this.indexs = indexs;
        this.result = result;
        this.ptrval.appendInst(this);
        this.indexs.forEach(operand -> {
            operand.appendInst(this);
        });
    }

    @Override
    public void instCopy(IRBasicBlock instIn, IRCopy Map) {
        ArrayList<IROperand> indexs = new ArrayList<>();
        this.indexs.forEach(index -> indexs.add(Map.get(index)));
        instIn.addInst(new GetElementPtr(instIn, Map.get(ptrval), indexs, Map.get(result)));
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
        for(int i = 0; i < indexs.size(); ++i){
            res.append(", ");
            res.append(indexs.get(i).getOperandType().getType());
            res.append(" ");
            res.append(indexs.get(i).PrintOperand());
        }
        return res.toString();
    }

    @Override
    public IROperand getResult() {
        return result;
    }

    public ArrayList<IROperand> getIndexs() {
        return indexs;
    }

    public IROperand getPtrval() {
        return ptrval;
    }

    @Override
    public HashSet<IROperand> getOperands() {
        HashSet<IROperand> operands = new HashSet<>(indexs);
        operands.add(ptrval);
        return operands;
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        for (int i = 0; i < indexs.size(); ++i){
            if(indexs.get(i).equals(Old)){
                indexs.set(i, New);
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

    @Override
    public boolean hasSideEffect() {
        return false;
    }

    @Override
    public boolean CSEChecker(IRInstruction other) {
        if(!(other instanceof GetElementPtr)) return false;
        if(!((GetElementPtr) other).ptrval.CSEChecker(ptrval)) return false;
        for(int i = 0; i < indexs.size(); ++i){
            if(!((GetElementPtr) other).indexs.get(i).CSEChecker(indexs.get(i))) return false;
        }
        return true;
    }
}

package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRVisitor;

import java.util.ArrayList;
import java.util.HashSet;

public class Phi extends IRInstruction{
    private ArrayList<IROperand> values;
    private ArrayList<IRBasicBlock> labels;
    private IROperand result;

    public Phi(IRBasicBlock instIn, ArrayList<IROperand> values, ArrayList<IRBasicBlock> labels, IROperand result){
        super(instIn);
        this.values = values;
        this.labels = labels;
        this.result = result;
        this.values.forEach(value -> {
            value.appendInst(this);
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
        res.append(" = phi ");
        res.append(result.getOperandType().getType());
        for (int i = 0; i < values.size(); i++){
            if(i > 0){
                res.append(",");
            }
            res.append(" [ ").append(values.get(i).PrintOperand());
            res.append(", ").append(labels.get(i).PrintBasicBlock()).append(" ]");
        }
        return res.toString();
    }

    @Override
    public IROperand getResult() {
        return result;
    }

    public void addBranch(IROperand value, IRBasicBlock label){
        values.add(value);
        labels.add(label);
        value.appendInst(this);
    }

    public ArrayList<IROperand> getValues() {
        return values;
    }

    public ArrayList<IRBasicBlock> getLabels() {
        return labels;
    }

    @Override
    public HashSet<IROperand> getOperands() {
        return new HashSet<>(values);
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        for (int i = 0; i < values.size(); ++i){
            if (values.get(i).equals(Old)){
                values.set(i, New);
            }
        }
    }

    public void addValue(IROperand value){
        values.add(value);
        value.appendInst(this);
    }

    public void addBlock(IRBasicBlock block){
        labels.add(block);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRutility.IRCopy;
import IR.IRutility.IRVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

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
    public void instCopy(IRBasicBlock instIn, IRCopy Map) {
        ArrayList<IROperand> values = new ArrayList<>();
        ArrayList<IRBasicBlock> labels = new ArrayList<>();
        this.values.forEach(value -> values.add(Map.get(value)));
        this.labels.forEach(label -> labels.add(Map.get(label)));
        instIn.addInst(new Phi(instIn, values, labels, Map.get(result)));
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

    public void updateLabel(IRBasicBlock Old, IRBasicBlock New){
        for(int i = 0; i < labels.size(); ++i){
            if(labels.get(i).equals(Old)) labels.set(i, New);
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

    @Override
    public boolean hasSideEffect() {
        return true;
    }

    @Override
    public boolean CSEChecker(IRInstruction other) {
        if(!(other instanceof Phi)) return false;
        if(((Phi) other).values.size() != values.size()) return false;
        HashMap<IRBasicBlock, IROperand> phiMap = new LinkedHashMap<>();
        for(int i = 0; i < values.size(); ++i) phiMap.put(labels.get(i), values.get(i));
        for(int i = 0; i < values.size(); ++i){
            if(!phiMap.containsKey(((Phi) other).labels.get(i))) return false;
            if(!phiMap.get(((Phi) other).labels.get(i)).CSEChecker(((Phi) other).values.get(i))) return false;
        }
        return true;
    }
}


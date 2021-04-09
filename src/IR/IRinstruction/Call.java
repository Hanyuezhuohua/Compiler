package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRoperand.IRConstVoid;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRVoidType;
import IR.IRutility.IRCopy;
import IR.IRutility.IRVisitor;

import java.util.ArrayList;
import java.util.HashSet;

public class Call extends IRInstruction{
    private IRFunction fnptrval;
    private ArrayList<IROperand> functionArgs;
    private IROperand result;

    public Call(IRBasicBlock instIn, IRFunction fnptrval, ArrayList<IROperand> functionArgs, IROperand result){
        super(instIn);
        this.fnptrval = fnptrval;
        this.functionArgs = functionArgs;
        this.result = result;
        this.functionArgs.forEach(functionArg -> {
            functionArg.appendInst(this);
        });
    }

    @Override
    public void instCopy(IRBasicBlock instIn, IRCopy Map) {
        ArrayList<IROperand> functionArgs = new ArrayList<>();
        this.functionArgs.forEach(parameter -> functionArgs.add(Map.get(parameter)));
        instIn.addInst(new Call(instIn, fnptrval, functionArgs, Map.get(result)));
    }

    @Override
    public boolean Terminal() {
        return false;
    }

    @Override
    public String PrintInst() {
        StringBuilder res = new StringBuilder();
        if(!(result.getOperandType() instanceof IRVoidType)){
            res.append(result.PrintOperand()).append(" = ");
        }
        res.append("call ").append(fnptrval.getReturnType().getType()).append(" ");
        res.append(fnptrval.PrintFunction());
        if(functionArgs.size() == 0){
            res.append("()");
        }
        else{
            res.append("(");
            for(int i = 0; i < functionArgs.size(); i++){
                res.append(functionArgs.get(i).getOperandType().getType());
                res.append(" ");
                res.append(functionArgs.get(i).PrintOperand());
                if(i == functionArgs.size() - 1){
                    res.append(")");
                }
                else{
                    res.append(", ");
                }
            }
        }
        return res.toString();
    }

    @Override
    public IROperand getResult() {
        if(!(result instanceof IRConstVoid)){
            return result;
        }
        else return null;
    }

    @Override
    public HashSet<IROperand> getOperands() {
        return new HashSet<IROperand>(functionArgs);
    }

    @Override
    public void update(IRLocalRegister Old, IROperand New) {
        for (int i = 0; i < functionArgs.size(); ++i){
            if (functionArgs.get(i).equals(Old)){
                functionArgs.set(i, New);
            }
        }
    }

    public ArrayList<IROperand> getFunctionArgs() {
        return functionArgs;
    }

    public IRFunction getFnptrval() {
        return fnptrval;
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

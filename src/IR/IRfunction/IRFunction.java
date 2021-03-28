package IR.IRfunction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRinstruction.Store;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRPointerType;
import IR.IRtype.IRType;
import IR.IRtype.IRVoidType;
import IR.IRutility.IRVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class IRFunction {
    private IRModule functionIn;

    private String identifier;
    private IRType returnType;
    private ArrayList<IRLocalRegister> parameters;

    private IROperand classPtr;

    private IRBasicBlock entry;
    private IRBasicBlock exit;

    private HashSet<IRFunction> funcCall;
    private HashSet<IRLocalRegister> varAlloc;
    private LinkedHashSet<IRBasicBlock> blockContain;

    public IRFunction(IRModule functionIn, String identifier, IRType returnType, ArrayList<IRLocalRegister> parameters, IROperand classPtr){
        this.functionIn = functionIn;
        this.identifier = identifier;
        this.returnType = returnType;
        this.parameters = parameters;
        this.classPtr = classPtr;
        entry = new IRBasicBlock(this, "entry");
        exit = null;
        funcCall = new HashSet<>();
        varAlloc = new LinkedHashSet<>();
        blockContain = new LinkedHashSet<>();
        blockContain.add(entry);
    }

    public String getIdentifier() {
        return identifier;
    }

    public IROperand getClassPtr() {
        return classPtr;
    }

    public void setExit(IRBasicBlock exit) {
        this.exit = exit;
    }

    public IRBasicBlock getEntry() {
        return entry;
    }

    public IRBasicBlock getExit() {
        return exit;
    }

    public IRType getReturnType() {
        return returnType;
    }

    public ArrayList<IRLocalRegister> getParameters() {
        return parameters;
    }

    public void addAlloc(){
        for(IRLocalRegister var: varAlloc){
            entry.addAlloc(var);
 //           entry.addInst(new Store(entry, ((IRPointerType) var.getOperandType()).getPointTo().initValue(), var));
        }
    }

    public void setBlockContain(LinkedHashSet<IRBasicBlock> blockContain) {
        this.blockContain = blockContain;
    }

    public void addBlockContain(IRBasicBlock block){
        this.blockContain.add(block);
    }

    public LinkedHashSet<IRBasicBlock> getBlockContain() {
        return blockContain;
    }

    public void addParameter(IRLocalRegister parameter) {
        parameters.add(parameter);
    }

    public void addVar(IRLocalRegister var) {
        varAlloc.add(var);
    }

    public String PrintFunction(){
        return "@" + identifier;
    }

    public HashSet<IRLocalRegister> getVarAlloc() {
        return varAlloc;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}

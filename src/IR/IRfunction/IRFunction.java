package IR.IRfunction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRType;
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

    private HashSet<IRFunction> callee;
    private HashSet<IRFunction> caller;
    private HashSet<IRLocalRegister> varAlloc;
    private LinkedHashSet<IRBasicBlock> blockContain;

    private boolean sideEffect;

    public IRFunction(IRModule functionIn, String identifier, IRType returnType, ArrayList<IRLocalRegister> parameters, IROperand classPtr){
        this.functionIn = functionIn;
        this.identifier = identifier;
        this.returnType = returnType;
        this.parameters = parameters;
        this.classPtr = classPtr;
        entry = new IRBasicBlock(this, "entry");
        exit = null;
        callee = new HashSet<>();
        caller = new HashSet<>();
        varAlloc = new LinkedHashSet<>();
        blockContain = new LinkedHashSet<>();
        blockContain.add(entry);
    }

    public boolean hasSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(boolean sideEffect) {
        this.sideEffect = sideEffect;
    }

    public HashSet<IRFunction> getCallee() {
        return callee;
    }

    public HashSet<IRFunction> getCaller() {
        return caller;
    }

    public void ClearCallee(){
        this.callee.clear();
    }

    public void ClearCaller(){
        this.caller.clear();
    }

    public void addCallee(IRFunction callee){
        this.callee.add(callee);
    }

    public void addCaller(IRFunction caller){
        this.caller.add(caller);
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

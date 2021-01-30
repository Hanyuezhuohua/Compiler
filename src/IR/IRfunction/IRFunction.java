package IR.IRfunction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRType;

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
        varAlloc = new HashSet<>();
        blockContain = new LinkedHashSet<>();
        blockContain.add(entry);
    }

    public String getIdentifier() {
        return identifier;
    }
}

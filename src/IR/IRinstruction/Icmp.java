package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IROperand;

public class Icmp extends IRInstruction{
    public enum IRIcmpOpType{
        eg, ne, sgt, sge, slt, sle
    }

    private IRIcmpOpType op;
    private IROperand op1;
    private IROperand op2;
    private IROperand result;

    public Icmp(IRBasicBlock instIn, IRIcmpOpType op, IROperand op1, IROperand op2, IROperand result){
        super(instIn);
        this.op = op;
        this.op1 = op1;
        this.op2 = op2;
        this.result = result;
    }
}

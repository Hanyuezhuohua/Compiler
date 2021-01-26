package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IROperand;

public class BitwiseBinary extends IRInstruction{
    public enum IRBitwiseBinaryOpType{
        shl, ashr, and, or, xor
    }

    private IRBitwiseBinaryOpType op;
    private IROperand op1;
    private IROperand op2;
    private IROperand result;

    public BitwiseBinary(IRBasicBlock instIn, IRBitwiseBinaryOpType op, IROperand op1, IROperand op2, IROperand result){
        super(instIn);
        this.op = op;
        this.op1 = op1;
        this.op2 = op2;
        this.result = result;
    }
}

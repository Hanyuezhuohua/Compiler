package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRoperand.IROperand;

public class Binary extends IRInstruction{
    public enum IRBinaryOpType{
        add, sub, mul, sdiv, srem
    }

    private IRBinaryOpType op;
    private IROperand op1;
    private IROperand op2;
    private IROperand result;

    public Binary(IRBasicBlock instIn, IRBinaryOpType op, IROperand op1, IROperand op2, IROperand result){
        super(instIn);
        this.op = op;
        this.op1 = op1;
        this.op2 = op2;
        this.result = result;
    }

    @Override
    public boolean Terminal() {
        return false;
    }
}

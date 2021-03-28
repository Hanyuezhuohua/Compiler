package AST;

import IR.IRoperand.IROperand;
import Util.position;

public class IntegerliteralNode extends ExprNode{
    private int val;
    public IntegerliteralNode(position pos, int val){
        super(pos);
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isConst() {
        return true;
    }
}

package AST;

import IR.IRoperand.IROperand;
import Util.position;

public class NullliteralNode extends ExprNode{
    public NullliteralNode(position pos){
        super(pos);
        this.constant = this;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isConst() {
        return false;
    }
}

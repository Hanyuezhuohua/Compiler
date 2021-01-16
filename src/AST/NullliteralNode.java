package AST;

import Util.position;

public class NullliteralNode extends ExprNode{
    public NullliteralNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

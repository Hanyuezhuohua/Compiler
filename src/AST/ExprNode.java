package AST;

import Util.position;

public abstract class ExprNode extends ASTNode{
    public ExprNode(position pos){
        super(pos);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

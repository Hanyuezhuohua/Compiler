package AST;

import Util.position;

public class ExprNode extends ASTNode{
    public ExprNode(position pos){
        super(pos);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

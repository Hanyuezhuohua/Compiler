package AST;

import Util.position;

public class ExprstatementNode extends StatementNode{
    ExprNode expression;
    public ExprstatementNode(position pos, ExprNode expression){
        super(pos);
        this.expression = expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

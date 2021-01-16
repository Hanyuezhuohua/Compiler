package AST;

import Util.position;

public class WhilestatementNode extends StatementNode{
    private ExprNode condition;
    private StatementNode block;

    public WhilestatementNode(position pos, ExprNode condition, StatementNode block){
        super(pos);
        this.condition = condition;
        this.block = block;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

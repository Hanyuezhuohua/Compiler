package AST;

import Util.position;

public class WhilestatementNode extends StatementNode{
    private ExprNode condition;
    private SuiteNode block;

    public WhilestatementNode(position pos, ExprNode condition, SuiteNode block){
        super(pos);
        this.condition = condition;
        this.block = block;
    }

    public ExprNode getCondition() {
        return condition;
    }

    public SuiteNode getBlock() {
        return block;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

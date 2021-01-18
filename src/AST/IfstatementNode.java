package AST;

import Util.position;

public class IfstatementNode extends StatementNode{
    private ExprNode condition;
    private SuiteNode trueStat;
    private SuiteNode falseStat;
    public IfstatementNode(position pos, ExprNode condition, SuiteNode trueStat, SuiteNode falseStat){
        super(pos);
        this.condition = condition;
        this.trueStat = trueStat;
        this.falseStat = falseStat;
    }

    public ExprNode getCondition() {
        return condition;
    }

    public StatementNode getTrueStat() {
        return trueStat;
    }

    public StatementNode getFalseStat() {
        return falseStat;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

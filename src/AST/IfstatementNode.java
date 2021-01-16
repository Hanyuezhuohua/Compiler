package AST;

import Util.position;

public class IfstatementNode extends StatementNode{
    private ExprNode condition;
    private StatementNode trueStat;
    private StatementNode falseStat;
    public IfstatementNode(position pos, ExprNode condition, StatementNode trueStat, StatementNode falseStat){
        super(pos);
        this.condition = condition;
        this.trueStat = trueStat;
        this.falseStat = falseStat;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

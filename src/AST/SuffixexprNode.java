package AST;

import Util.position;

public class SuffixexprNode extends ExprNode{
    public enum SuffixOpType{
        AddTwice, MinusTwice;
    }
    private SuffixOpType op;
    private ExprNode expression;

    public SuffixexprNode(position pos, SuffixOpType op, ExprNode expression){
        super(pos);
        this.op = op;
        this.expression =expression;
    }

    public ExprNode getExpression() {
        return expression;
    }

    public SuffixOpType getOp() {
        return op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

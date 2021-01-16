package AST;

import Util.position;

public class PrefixexprNode extends ExprNode{
    public enum PrefixOpType{
        Add, Minus, AddAdd, MinusMinus, NotLogic, NotAri;
    }
    private PrefixOpType op;
    private ExprNode expression;

    public PrefixexprNode(position pos, PrefixOpType op, ExprNode expression){
        super(pos);
        this.op = op;
        this.expression = expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

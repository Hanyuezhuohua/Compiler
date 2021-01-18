package AST;

import Util.position;

public class BinaryexprNode extends ExprNode{
    public enum BinaryOpType{
        AddBinary, MinusBinary, Mul, Div, Mod, LeftShift, RightShift,
        Less, LessEqual, Greater, GreaterEqual, Equal, NotEqual,
        AndAri, OrAri, AndLogic, OrLogic, XorAri,
        Assign
    }
    private ExprNode lhs, rhs;
    private BinaryOpType op;

    public BinaryexprNode(position pos, ExprNode lhs, ExprNode rhs, BinaryOpType op){
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }

    public ExprNode getLhs() {
        return lhs;
    }

    public ExprNode getRhs() {
        return rhs;
    }

    public BinaryOpType getOp() {
        return op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

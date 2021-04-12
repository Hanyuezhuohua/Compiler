package AST;

import Util.position;
import Util.scope.Scope;
import Util.symbol.Symbol;

import java.util.HashSet;

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

    public void setLhs(ExprNode lhs) {
        this.lhs = lhs;
    }

    public void setRhs(ExprNode rhs) {
        this.rhs = rhs;
    }

    public void setOp(BinaryOpType op) {
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

    @Override
    public boolean modified(HashSet<Symbol> symbols) {
        return lhs.modified(symbols) || rhs.modified(symbols);
    }

    @Override
    public boolean isSame(ExprNode node) {
        return node instanceof BinaryexprNode && lhs.isSame(((BinaryexprNode) node).lhs) && rhs.isSame(((BinaryexprNode) node).rhs);
    }

    @Override
    public ExprNode copy(Scope scope) {
        ExprNode copy =  new BinaryexprNode(pos, lhs.copy(scope), rhs.copy(scope), op);
        copy.setScope(scope);
        copy.setType(getType());
        copy.setExprType(getExprType());
        copy.setConstant(getConstant());
        return copy;
    }
}

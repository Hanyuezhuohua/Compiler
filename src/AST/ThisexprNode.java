package AST;

import Util.position;
import Util.symbol.Symbol;

public class ThisexprNode extends ExprNode{
    private Symbol symbol;

    public ThisexprNode(position pos){
        super(pos);
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

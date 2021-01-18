package AST;

import Util.position;
import Util.symbol.Symbol;

public class IdentifierNode extends ExprNode{
    private String identifier;
    private Symbol symbol;

    public IdentifierNode(position pos, String identifier){
        super(pos);
        this.identifier = identifier;
        symbol = null;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

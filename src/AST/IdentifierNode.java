package AST;

import Util.position;
import Util.symbol.Symbol;

public class IdentifierNode extends ExprNode{
    private String identifier;
    private Symbol symbol;
    private boolean member;

    public IdentifierNode(position pos, String identifier){
        super(pos);
        this.identifier = identifier;
        symbol = null;
        member = false;
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

    public void setMember(boolean member) { this.member = member; }

    public boolean isMember() { return member; }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

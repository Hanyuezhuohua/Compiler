package AST;

import Util.position;
import Util.scope.Scope;
import Util.symbol.Symbol;

import java.util.HashSet;

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

    @Override
    public boolean modified(HashSet<Symbol> symbols) {
        return symbols.contains(symbols);
    }

    @Override
    public boolean isSame(ExprNode node) {
        return node instanceof IdentifierNode && ((IdentifierNode) node).getSymbol() == symbol;
    }

    @Override
    public ExprNode copy(Scope scope) {
        IdentifierNode copy = new IdentifierNode(pos, identifier);
        copy.setSymbol(symbol);
        copy.setScope(scope);
        copy.setType(getType());
        copy.setExprType(getExprType());
        copy.setConstant(getConstant());
        return copy;
    }
}

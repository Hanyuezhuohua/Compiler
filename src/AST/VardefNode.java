package AST;

import Util.position;
import Util.symbol.Symbol;

public class VardefNode extends ASTNode{
    private TypeNode type;
    private Symbol symbol;
    private String identifier;
    private ExprNode expression;
    public VardefNode(String identifier,position pos){
        super(pos);
        type = null;
        symbol = null;
        this.identifier = identifier;
        expression = null;
    }

    public VardefNode(String identifier, TypeNode type, position pos){
        super(pos);
        this.type = type;
        symbol = null;
        this.identifier = identifier;
        expression = null;
    }

    public TypeNode getType() {
        return type;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ExprNode getExpression() {
        return expression;
    }

    public void setType(TypeNode type) {
        this.type = type;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setExpression(ExprNode expression) {
        this.expression = expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

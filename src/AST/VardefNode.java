package AST;

import Util.position;
import Util.symbol.Symbol;

public class VardefNode extends ASTNode{
    private TypeNode type;
    private Symbol symbol;
    private String identifier;
    private ExprNode expression;
    private boolean isParameter;
    public VardefNode(String identifier,position pos, boolean isParameter){
        super(pos);
        type = null;
        symbol = null;
        this.identifier = identifier;
        expression = null;
        this.isParameter = isParameter;
    }

    public VardefNode(String identifier, TypeNode type, position pos, boolean isParameter){
        super(pos);
        this.type = type;
        symbol = null;
        this.identifier = identifier;
        expression = null;
        this.isParameter = isParameter;
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

    public boolean isParameter() {
        return isParameter;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

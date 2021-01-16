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

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

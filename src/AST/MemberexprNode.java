package AST;

import Util.position;
import Util.symbol.Symbol;

public class MemberexprNode extends ExprNode{
    private ExprNode expression;
    private String identifier;
    private Symbol symbol;

    public MemberexprNode(position pos, ExprNode expression, String identifier){
        super(pos);
        this.expression = expression;
        this.identifier = identifier;
        this.symbol = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

package AST;

import Util.position;
import Util.symbol.Symbol;

public class ReturnstatementNode extends StatementNode{
    private ExprNode returnVal;
    private Symbol symbol;

    public ReturnstatementNode(position pos, ExprNode returnVal){
        super(pos);
        this.returnVal = returnVal;
        symbol = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

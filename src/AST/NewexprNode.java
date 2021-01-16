package AST;

import Util.position;
import Util.symbol.FuncSymbol;

public class NewexprNode extends ExprNode{
    private NewtypeNode newtype;
    private FuncSymbol funcSymbol;
    public NewexprNode(position pos, NewtypeNode newtype){
        super(pos);
        this.newtype = newtype;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

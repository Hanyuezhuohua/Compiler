package AST;

import Util.scope.Scope;
import Util.position;

public class ThisexprNode extends ExprNode{
    private Scope scope;

    public ThisexprNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

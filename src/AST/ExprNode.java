package AST;

import Util.position;
import Util.type.Type;

public abstract class ExprNode extends ASTNode{
    public enum ExprType {
        LVALUE, RVALUE,CLASS, FUNCTION
    }
    private Type type;
    private ExprType exprType;

    public ExprNode(position pos){
        super(pos);
        type = null;
        exprType = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

package AST;

import Util.position;

public class BoolliteralNode extends ExprNode{
    private boolean val;
    public BoolliteralNode(position pos, boolean val){
        super(pos);
        this.val = val;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

package AST;

import Util.position;

public class IntegerliteralNode extends ExprNode{
    private int val;
    public IntegerliteralNode(position pos, int val){
        super(pos);
        this.val = val;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

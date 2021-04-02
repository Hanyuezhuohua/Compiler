package AST;

import Util.position;

public class BoolliteralNode extends ExprNode{
    private boolean val;
    public BoolliteralNode(position pos, boolean val){
        super(pos);
        this.val = val;
        this.constant = this;
    }

    public void setVal(boolean val) {
        this.val = val;
    }

    public boolean getVal() {
        return val;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isConst() {
        return true;
    }
}

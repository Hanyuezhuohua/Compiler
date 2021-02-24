package AST;

import Util.position;

public class StringliteralNode extends ExprNode{
    private String val;
    public StringliteralNode(position pos, String val){
        super(pos);
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

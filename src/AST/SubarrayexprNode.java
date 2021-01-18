package AST;

import Util.position;

public class SubarrayexprNode extends ExprNode{
    private ExprNode identifier;
    private ExprNode index;

    public SubarrayexprNode(position pos, ExprNode identifier, ExprNode index){
        super(pos);
        this.identifier = identifier;
        this.index = index;
    }

    public ExprNode getIdentifier() {
        return identifier;
    }

    public ExprNode getIndex() {
        return index;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

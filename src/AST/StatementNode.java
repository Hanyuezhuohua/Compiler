package AST;

import Util.position;

public abstract class StatementNode extends ASTNode{
    public StatementNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
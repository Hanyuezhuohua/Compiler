package AST;

import Util.position;

public class EmptystatementNode extends StatementNode{
    public EmptystatementNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

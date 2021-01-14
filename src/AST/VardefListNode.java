package AST;

import Util.position;

public class VardefListNode extends ASTNode{
    public VardefListNode(position pos){
        super(pos);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

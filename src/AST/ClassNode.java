package AST;

import Util.position;

public class ClassNode extends ASTNode{
    public ClassNode(position pos){
        super(pos);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}

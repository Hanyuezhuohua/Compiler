package AST;

import Util.position;

public abstract class ASTNode {
    public position pos;
    public ASTNode(position pos){
        this.pos = pos;
    }
    abstract public void accept(ASTVisitor visitor);
}

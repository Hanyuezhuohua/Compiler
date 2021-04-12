package AST;

import Util.position;
import Util.scope.Scope;

public abstract class ASTNode {
    protected position pos;
    protected Scope scope;
    public ASTNode(position pos){
        this.pos = pos;
    }

    public position getPos() {
        return pos;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    abstract public void accept(ASTVisitor visitor);
}

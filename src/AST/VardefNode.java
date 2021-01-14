package AST;

import Util.position;

public class VardefNode extends ASTNode{
    public VardefNode(position pos){
        super(pos);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

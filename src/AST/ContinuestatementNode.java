package AST;

import Util.position;

public class ContinuestatementNode extends StatementNode{
    StatementNode jumpto;

    public ContinuestatementNode(position pos){
        super(pos);
    }

    public void setJumpto(StatementNode jumpto) {
        this.jumpto = jumpto;
    }

    public StatementNode getJumpto() {
        return jumpto;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

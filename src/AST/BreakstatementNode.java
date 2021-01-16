package AST;

import Util.position;

public class BreakstatementNode extends StatementNode{
    private StatementNode jumpto;
    public BreakstatementNode(position pos){
        super(pos);
        jumpto = null;
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

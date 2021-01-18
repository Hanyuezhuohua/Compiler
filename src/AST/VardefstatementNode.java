package AST;

import Util.position;

public class VardefstatementNode extends StatementNode{
    private VardefListNode varList;

    public VardefstatementNode(position pos, VardefListNode varList){
        super(pos);
        this.varList = varList;
    }

    public VardefListNode getVarList() {
        return varList;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

package AST;

import Util.position;

import java.util.List;

public class SuiteNode extends ASTNode{
    private List<StatementNode> statementList;
    public SuiteNode(position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

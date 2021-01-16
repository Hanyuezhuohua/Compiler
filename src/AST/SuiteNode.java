package AST;

import Util.position;

import java.util.ArrayList;
import java.util.List;

public class SuiteNode extends StatementNode{
    private List<StatementNode> statementList;
    public SuiteNode(position pos){
        super(pos);
        statementList = new ArrayList<>();
    }

    public List<StatementNode> getStatementList() {
        return statementList;
    }

    public void addStat(StatementNode stat){
        statementList.add(stat);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

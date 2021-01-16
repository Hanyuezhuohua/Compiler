package AST;

import Util.position;

public class ForstatementNode extends StatementNode{
    private ExprNode initExpr;
    private ExprNode condition;
    private ExprNode incr;
    private VardefListNode initDef;
    private StatementNode block;

    public ForstatementNode(position pos, ExprNode init, ExprNode condition, ExprNode incr, StatementNode block){
        super(pos);
        initExpr = init;
        this.condition = condition;
        this.incr = incr;
        initDef = null;
        this.block = block;
    }

    public ForstatementNode(position pos, VardefListNode init, ExprNode condition, ExprNode incr, StatementNode block){
        super(pos);
        initExpr = null;
        this.condition = condition;
        this.incr = incr;
        initDef = init;
        this.block = block;
    }

    public ForstatementNode(position pos, ExprNode condition, ExprNode incr, StatementNode block){
        super(pos);
        initExpr = null;
        this.condition = condition;
        this.incr = incr;
        initDef = null;
        this.block = block;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

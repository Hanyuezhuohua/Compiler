package AST;

import Util.position;
import Util.symbol.FuncSymbol;

import java.util.List;

public class FuncexprNode extends ExprNode{
    private ExprNode expression;
    private List<ExprNode> parameterList;
//    private FuncSymbol funcSymbol; //maybe useful

    public  FuncexprNode(position pos, ExprNode expression, List<ExprNode> parameterList){
        super(pos);
        this.expression = expression;
        this.parameterList = parameterList;
//        this.funcSymbol = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

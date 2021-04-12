package AST;

import Util.position;
import Util.symbol.FuncSymbol;
import Util.symbol.Symbol;

import java.util.HashSet;
import java.util.List;

public class FuncexprNode extends ExprNode{
    private ExprNode expression;
    private List<ExprNode> parameterList;
    private FuncSymbol funcSymbol;

    public  FuncexprNode(position pos, ExprNode expression, List<ExprNode> parameterList){
        super(pos);
        this.expression = expression;
        this.parameterList = parameterList;
        this.funcSymbol = null;
    }

    public ExprNode getExpression() {
        return expression;
    }

    public List<ExprNode> getParameterList() {
        return parameterList;
    }

    public void setFuncSymbol(FuncSymbol funcSymbol) {
        this.funcSymbol = funcSymbol;
    }

    public FuncSymbol getFuncSymbol() {
        return funcSymbol;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean modified(HashSet<Symbol> symbols) {
        return false;
    }
}

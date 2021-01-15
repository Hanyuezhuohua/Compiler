package AST;

import Util.position;
import Util.symbol.Symbol;

import java.util.ArrayList;
import java.util.List;

public class FundefNode extends ASTNode{
    private TypeNode returnType;
    private String identifier;
    private List<VardefNode> parameterList;
    private SuiteNode suite;
    private Symbol symbol;

    public FundefNode(TypeNode returnType, String identifier, List<VardefNode> parameterList, SuiteNode suite, position pos){
        super(pos);
        this.returnType = returnType;
        this.identifier = identifier;
        if(parameterList == null) this.parameterList = new ArrayList<>();
        else this.parameterList = parameterList;
        this.suite = suite;
    }

    public FundefNode(String identifier, SuiteNode suite, position pos){
        super(pos);
        this.returnType = null;
        this.identifier = identifier;
        this.parameterList = new ArrayList<>();
        this.suite = suite;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void setParameterList(List<VardefNode> parameterList) {
        this.parameterList = parameterList;
    }

    public void setReturnType(TypeNode returnType) {
        this.returnType = returnType;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

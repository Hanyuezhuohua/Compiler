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
    private int returnExistence;
    private boolean isConstructor;

    public FundefNode(TypeNode returnType, String identifier, List<VardefNode> parameterList, SuiteNode suite, position pos){
        super(pos);
        this.returnType = returnType;
        this.identifier = identifier;
        if(parameterList == null) this.parameterList = new ArrayList<>();
        else this.parameterList = parameterList;
        this.suite = suite;
        this.returnExistence = 0;
        this.isConstructor = false;
    }

    public FundefNode(String identifier, SuiteNode suite, position pos){
        super(pos);
        this.returnType = null;
        this.identifier = identifier;
        this.parameterList = new ArrayList<>();
        this.suite = suite;
        this.returnExistence = 0;
        this.isConstructor = false;
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

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setReturnExistence(int returnExistence) {
        this.returnExistence = returnExistence;
    }

    public void setConstructor(boolean constructor) {
        isConstructor = constructor;
    }

    public String getIdentifier() {
        return identifier;
    }

    public TypeNode getReturnType() {
        return returnType;
    }

    public List<VardefNode> getParameterList() {
        return parameterList;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public SuiteNode getSuite() {
        return suite;
    }

    public boolean isConstructor() {
        return isConstructor;
    }

    public int ReturnExistence() {
        return returnExistence;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

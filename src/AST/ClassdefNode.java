package AST;

import Util.symbol.Symbol;
import Util.position;

import java.util.ArrayList;
import java.util.List;

public class ClassdefNode extends ASTNode{
    String identifier;
    List<VardefNode> varList;
    List<FundefNode> funcList;
    FundefNode constructor;
    Symbol symbol;

    public ClassdefNode(position pos, String identifier){
        super(pos);
        this.identifier = identifier;
        varList = new ArrayList<>();
        funcList = new ArrayList<>();
        constructor = null;
        symbol = null;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<VardefNode> getVarList() {
        return varList;
    }

    public List<FundefNode> getFuncList() {
        return funcList;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setVarList(List<VardefNode> varList) {
        this.varList = varList;
    }

    public void setFuncList(List<FundefNode> funcList) {
        this.funcList = funcList;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void addVar(VardefNode v){
        varList.add(v);
    }

    public void addFunc(FundefNode f){
        funcList.add(f);
    }

    public void setConstructor(FundefNode constructor) {
        this.constructor = constructor;
    }

    public FundefNode getConstructor() {
        return constructor;
    }

    public void addVarList(List<VardefNode> varList) {
        this.varList.addAll(varList);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

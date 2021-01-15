package Util.symbol;

import Util.type.*;
import Util.scope.Scope;
import AST.ASTNode;

public class Symbol{
    private String identifier;
    private Type type;
    private Scope scope;
    private ASTNode defination;

    public Symbol(String identifier, Type type, ASTNode defination){
        this.identifier = identifier;
        this.type = type;
        this.defination = defination;
    }

    public void setScope(Scope scope){
        this.scope = scope;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    public Scope getScope() {
        return scope;
    }

    public ASTNode getDefination() {
        return defination;
    }
}

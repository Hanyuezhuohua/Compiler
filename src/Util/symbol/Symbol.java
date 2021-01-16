package Util.symbol;

import Util.type.*;
import Util.scope.Scope;
import AST.ASTNode;

public class Symbol{
    private String identifier;
    private Type type;
    private Scope scope;
    private ASTNode definition;

    public Symbol(String identifier, Type type, ASTNode definition){
        this.identifier = identifier;
        this.type = type;
        this.definition = definition;
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

    public ASTNode getDefinition() {
        return definition;
    }
}

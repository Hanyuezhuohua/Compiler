package Util.symbol;

import Util.type.*;
import Util.scope.Scope;
import AST.ASTNode;
import Util.position;

public class Symbol{
    private String identifier;
    private Type type;
    private Scope scope;
    private ASTNode definition;
    private position pos;

    public Symbol(position pos, String identifier, Type type, ASTNode definition){
        this.pos = pos;
        this.identifier = identifier;
        this.type = type;
        this.definition = definition;
        this.scope = null;
    }

    public Symbol(position pos, String identifier, Type type){
        this.pos = pos;
        this.identifier = identifier;
        this.type = type;
        this.definition = null;
        this.scope = null;
    }
    public Symbol(position pos, String identifier, Type type, Scope scope){
        this.pos = pos;
        this.identifier = identifier;
        this.type = type;
        this.definition = null;
        this.scope = scope;
    }

    public void setScope(Scope scope){
        this.scope = scope;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    public position getPos() {
        return pos;
    }

    public Scope getScope() {
        return scope;
    }

    public ASTNode getDefinition() {
        return definition;
    }
}

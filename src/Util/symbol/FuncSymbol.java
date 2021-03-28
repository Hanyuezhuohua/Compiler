package Util.symbol;

import AST.FundefNode;
import IR.IRfunction.IRFunction;
import Util.scope.Scope;
import Util.type.Type;
import Util.position;

public class FuncSymbol extends Symbol{
    private IRFunction irFunction;
    private boolean member = false;
    public FuncSymbol(position pos, String identifier, Type type, FundefNode definition){
        super(pos, identifier, type, definition);
    }
    public FuncSymbol(position pos, String identifier, Type type){
        super(pos, identifier, type);
    }
    public FuncSymbol(position pos, String identifier, Type type, Scope scope){
        super(pos, identifier, type, scope);
    }

    public void setIrFunction(IRFunction irFunction) {
        this.irFunction = irFunction;
    }

    public void setMember(boolean member) {
        this.member = true;
    }

    public boolean isMember() {
        return member;
    }

    public IRFunction getIrFunction() {
        return irFunction;
    }
}

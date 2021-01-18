package Util.symbol;

import AST.ClassdefNode;
import Util.scope.Scope;
import Util.type.Type;
import Util.position;

public class ClassSymbol extends Symbol{
    private FuncSymbol constructor;
    public ClassSymbol(position pos, String identifier, Type type, ClassdefNode definition){
        super(pos, identifier, type, definition);
        constructor = null;
    }

    public ClassSymbol(position pos, String identifier, Type type){
        super(pos, identifier, type);
        constructor = null;
    }

    public ClassSymbol(position pos, String identifier, Type type, Scope scope){
        super(pos, identifier, type, scope);
        constructor = null;
    }

    public void setConstructor(FuncSymbol constructor) {
        this.constructor = constructor;
    }

    public FuncSymbol getConstructor() {
        return constructor;
    }
}

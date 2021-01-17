package Util.symbol;

import AST.FundefNode;
import Util.scope.Scope;
import Util.type.Type;
import Util.position;

public class FuncSymbol extends Symbol{
    public FuncSymbol(position pos, String identifier, Type type, FundefNode definition){
        super(pos, identifier, type, definition);
    }
    public FuncSymbol(position pos, String identifier, Type type){
        super(pos, identifier, type);
    }
    public FuncSymbol(position pos, String identifier, Type type, Scope scope){
        super(pos, identifier, type, scope);
    }
}

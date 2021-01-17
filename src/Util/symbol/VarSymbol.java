package Util.symbol;

import AST.VardefNode;
import Util.scope.Scope;
import Util.type.Type;
import Util.position;

public class VarSymbol extends Symbol{
    public VarSymbol(position pos, String identifier, Type type, VardefNode definition){
        super(pos, identifier, type, definition);
    }
    public VarSymbol(position pos, String identifier, Type type){
        super(pos, identifier, type);
    }
    public VarSymbol(position pos, String identifier, Type type, Scope scope){
        super(pos, identifier, type, scope);
    }
}

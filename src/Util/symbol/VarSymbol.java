package Util.symbol;

import AST.VardefNode;
import Util.type.Type;

public class VarSymbol extends Symbol{
    public VarSymbol(String identifier, Type type, VardefNode definition){
        super(identifier, type, definition);
    }
}

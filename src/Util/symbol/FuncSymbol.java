package Util.symbol;

import AST.FundefNode;
import Util.type.Type;

public class FuncSymbol extends Symbol{
    FuncSymbol(String identifier, Type type, FundefNode definition){
        super(identifier, type, definition);
    }
}

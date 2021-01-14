package Util.symbol;

import AST.ASTNode;
import Util.type.Type;

public class FuncSymbol extends Symbol{ //need to modify
    FuncSymbol(String identifier, Type type, ASTNode defination){
        super(identifier, type, defination);
    }
}

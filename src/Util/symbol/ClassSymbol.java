package Util.symbol;

import AST.ASTNode;
import Util.type.Type;

public class ClassSymbol extends Symbol{ //need to modify
    ClassSymbol(String identifier, Type type, ASTNode defination){
        super(identifier, type, defination);
    }
}

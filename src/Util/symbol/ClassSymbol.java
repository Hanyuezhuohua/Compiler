package Util.symbol;

import AST.ClassdefNode;
import Util.type.Type;

public class ClassSymbol extends Symbol{
    ClassSymbol(String identifier, Type type, ClassdefNode defination){
        super(identifier, type, defination);
    }
}

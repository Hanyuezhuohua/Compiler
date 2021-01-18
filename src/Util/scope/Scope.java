package Util.scope;

import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.symbol.Symbol;
import Util.symbol.VarSymbol;
import Util.position;

public interface Scope {
    Scope getParent();
    void registerVar(VarSymbol v);
    void registerFunc(FuncSymbol f);
    void registerClass(ClassSymbol c);
    void check(String identifier);
    Symbol findSymbol(String identifier, position pos);
    ClassSymbol findClassSymbol(String identifier, position pos);
}

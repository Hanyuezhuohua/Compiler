package Util.scope;

import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.symbol.Symbol;
import Util.symbol.VarSymbol;
import Util.position;

public interface Scope {
    Scope getParent();
    void checkVarLocal(VarSymbol v);
    void checkFuncLocal(FuncSymbol f);
    void checkClassLocal(ClassSymbol c);
    void registerVar(VarSymbol v);
    void registerFunc(FuncSymbol f);
    void registerClass(ClassSymbol c);
    Symbol findSymbol(String identifier, position pos);
}

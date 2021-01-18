package Util.scope;

import Util.error.ErrorMessage;
import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.symbol.Symbol;
import Util.symbol.VarSymbol;
import Util.position;

import java.util.LinkedHashMap;
import java.util.Map;

public class LocalScope implements Scope{
    private Map<String, VarSymbol> VarSymbolTable;
    private Map<String, FuncSymbol> FuncSymbolTable;
    private Scope upScope;

    public LocalScope(Scope upScope){
        VarSymbolTable = new LinkedHashMap<>();
        FuncSymbolTable = new LinkedHashMap<>();
        this.upScope = upScope;
    }

    @Override
    public Scope getParent() {
        return upScope;
    }

    @Override
    public void check(String identifier) {
        Scope globalScope = getParent();
        while (globalScope != null){
            globalScope = globalScope.getParent();
        }
        if(VarSymbolTable.containsKey(identifier) || FuncSymbolTable.containsKey(identifier) || (((GlobalScope) globalScope).getClassSymbolTable()).containsKey(identifier)){
            throw new ErrorMessage("LocalScope check Error");
        }
    }

    @Override
    public void registerVar(VarSymbol v) {
        check(v.getIdentifier());
        VarSymbolTable.put(v.getIdentifier(), v);
    }

    @Override
    public void registerFunc(FuncSymbol f) {
        check(f.getIdentifier());
        FuncSymbolTable.put(f.getIdentifier(), f);
    }

    @Override
    public void registerClass(ClassSymbol c) {
        throw new ErrorMessage("LocalScope registerClass Error", c.getPos());
    }

    @Override
    public Symbol findSymbol(String identifier, position pos) {
        Symbol tmp = VarSymbolTable.get(identifier);
        if(tmp != null) return tmp;
        else{
            tmp = FuncSymbolTable.get(identifier);
            if(tmp != null) return tmp;
            else return upScope.findSymbol(identifier, pos);
        }
    }

    @Override
    public ClassSymbol findClassSymbol(String identifier, position pos) {
        return upScope.findClassSymbol(identifier, pos);
    }
}

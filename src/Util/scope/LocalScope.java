package Util.scope;

import Util.error.ErrorMessage;
import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.symbol.Symbol;
import Util.symbol.VarSymbol;
import Util.position;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LocalScope implements Scope{
    private Map<String, VarSymbol> VarSymbolTable;
    private Map<String, FuncSymbol> FuncSymbolTable;
    private List<VarSymbol> VarSymbolList;
    private Scope upScope;

    public LocalScope(Scope upScope){
        VarSymbolTable = new LinkedHashMap<>();
        FuncSymbolTable = new LinkedHashMap<>();
        VarSymbolList = new ArrayList<>();
        this.upScope = upScope;
    }

    public Map<String, VarSymbol> getVarSymbolTable() {
        return VarSymbolTable;
    }

    public List<VarSymbol> getVarSymbolList() {
        return VarSymbolList;
    }

    @Override
    public Scope getParent() {
        return upScope;
    }

    @Override
    public void check(String identifier) {
        Scope globalScope = getParent();
        while (globalScope instanceof LocalScope){
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

    public void registerPara(VarSymbol v){
        registerVar(v);
        VarSymbolList.add(v);
    }

    public void registerClassConstructor(FuncSymbol f){
        if(VarSymbolTable.containsKey(f.getIdentifier()) || FuncSymbolTable.containsKey(f.getIdentifier())){
            throw new ErrorMessage("LocalScope registerClassConstructor Error");
        }
        FuncSymbolTable.put(f.getIdentifier(), f);
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

    public Symbol findSymbolLocal(String identifier, position pos){
        Symbol tmp = VarSymbolTable.get(identifier);
        if(tmp != null) return tmp;
        else{
            tmp = FuncSymbolTable.get(identifier);
            if(tmp != null) return tmp;
            else throw new ErrorMessage("LocalSymbol finSymbolLocal ERROR", pos);
        }
    }

    @Override
    public ClassSymbol findClassSymbol(String identifier, position pos) {
        return upScope.findClassSymbol(identifier, pos);
    }
}

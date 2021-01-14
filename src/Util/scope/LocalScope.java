package Util.scope;

import Util.error.ErrorMessage;
import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.symbol.Symbol;
import Util.symbol.VarSymbol;

import javax.swing.text.Position;
import java.util.LinkedHashMap;
import java.util.Map;

public class LocalScope implements Scope{
    private Map<String, Symbol> symbolTable;
    private Scope upScope;

    public LocalScope(Scope upScope){
        symbolTable = new LinkedHashMap<>();
        this.upScope = upScope;
    }

    @Override
    public Scope getParent() {
        return upScope;
    }

    @Override
    public void checkVarLocal(VarSymbol v) {
        if (symbolTable.containsKey(v.getIdentifier())){
            throw new ErrorMessage("LocalScope checkVarLocal Error"); //need pos
        }
        else return;
    }

    @Override
    public void checkFuncLocal(FuncSymbol f) {
        if(symbolTable.containsKey(f.getIdentifier())){
            throw new ErrorMessage("LocalScope checkFuncLocal Error"); //need pos
        }
        else return;
    }

    @Override
    public void checkClassLocal(ClassSymbol c) {
        throw new ErrorMessage("LocalScope checkClassLocal Error"); //need pos
    }

    @Override
    public void registerVar(VarSymbol v) {
        checkVarLocal(v);
        symbolTable.put(v.getIdentifier(), v);
        v.setScope(this);
    }

    @Override
    public void registerFunc(FuncSymbol f) {
        checkFuncLocal(f);
        symbolTable.put(f.getIdentifier(), f);
        f.setScope(this);
    }

    @Override
    public void registerClass(ClassSymbol c) {
        throw new ErrorMessage("LocalScope registerClass Error"); //need pos
    }

    @Override
    public Symbol findSymbol(String identifier, Position pos) {
        Symbol res = symbolTable.get(identifier);
        if(res != null) return res;
        else return upScope.findSymbol(identifier, pos);
    }
}

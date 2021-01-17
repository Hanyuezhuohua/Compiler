package Util.scope;

import Util.error.ErrorMessage;
import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.symbol.Symbol;
import Util.symbol.VarSymbol;
import Util.position;
import Util.type.NullType;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalScope implements Scope{
    private Map<String, Symbol> symbolTable;
    private Scope upScope;
    private NullType nullType; //maybe need to be modified

    public GlobalScope(Scope upScope){
        symbolTable = new LinkedHashMap<>();
        this.upScope = upScope;
        nullType = new NullType();
    }

    public GlobalScope(){
        symbolTable = new LinkedHashMap<>();
        upScope = null;
        nullType = new NullType();
    }

    @Override
    public Scope getParent() {
        return upScope;
    }

    @Override
    public void checkVarLocal(VarSymbol v) {
        if (symbolTable.containsKey(v.getIdentifier())){
            throw new ErrorMessage("GlobalScope checkVarLocal Error", v.getPos());
        }
        else return;
    }

    @Override
    public void checkFuncLocal(FuncSymbol f) {
        if(symbolTable.containsKey(f.getIdentifier())){
            throw new ErrorMessage("GlobalScope checkFuncLocal Error", f.getPos());
        }
        else return;
    }

    @Override
    public void checkClassLocal(ClassSymbol c) {
        if(symbolTable.containsKey(c.getIdentifier())){
            throw new ErrorMessage("GlobalScope checkClassLocal Error", c.getPos());
        }
    }

    @Override
    public void registerVar(VarSymbol v) {
        checkVarLocal(v);
        symbolTable.put(v.getIdentifier(), v);
    }

    @Override
    public void registerFunc(FuncSymbol f) {
        checkFuncLocal(f);
        symbolTable.put(f.getIdentifier(), f);
    }

    @Override
    public void registerClass(ClassSymbol c) {
        checkClassLocal(c);
        symbolTable.put(c.getIdentifier(), c);
    }

    @Override
    public Symbol findSymbol(String identifier, position pos) {
        Symbol res = symbolTable.get(identifier);
        if(res != null) return res;
        else throw new ErrorMessage("GlobalScope findSymbol Error", pos);
    }
}

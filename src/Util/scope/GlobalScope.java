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
    private Map<String, VarSymbol> VarSymbolTable;
    private Map<String, FuncSymbol> FuncSymbolTable;
    private Map<String, ClassSymbol> ClassSymbolTable;
    private NullType nullType; //maybe need to be modified

    public GlobalScope(){
        VarSymbolTable = new LinkedHashMap<>();
        FuncSymbolTable = new LinkedHashMap<>();
        ClassSymbolTable = new LinkedHashMap<>();
        nullType = new NullType();
    }

    public Map<String, ClassSymbol> getClassSymbolTable() {
        return ClassSymbolTable;
    }

    @Override
    public Scope getParent() {
        return null;
    }

    @Override
    public void check(String identifier) {
        if(VarSymbolTable.containsKey(identifier) || FuncSymbolTable.containsKey(identifier) || ClassSymbolTable.containsKey(identifier)){
            throw new ErrorMessage("GlobalScope check Error");
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
        check(c.getIdentifier());
        ClassSymbolTable.put(c.getIdentifier(), c);
    }

    @Override
    public Symbol findSymbol(String identifier, position pos) {
        Symbol tmp = VarSymbolTable.get(identifier);
        if(tmp != null) return tmp;
        else{
            tmp = FuncSymbolTable.get(identifier);
            if(tmp != null) return tmp;
            else{
                tmp = ClassSymbolTable.get(identifier);
                if(tmp != null) return tmp;
                else throw new ErrorMessage("GlobalScope findSymbol Error", pos);
            }
        }
    }

    @Override
    public ClassSymbol findClassSymbol(String identifier, position pos) {
        ClassSymbol tmp = ClassSymbolTable.get(identifier);
        if(tmp != null) return tmp;
        else throw new ErrorMessage("GlobalScope findClassSymbol Error", pos);
    }
}

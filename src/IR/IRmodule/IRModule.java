package IR.IRmodule;

import IR.IRfunction.IRFunction;
import IR.IRoperand.IRConstString;
import IR.IRoperand.IRGlobalVariable;
import IR.IRtype.*;
import IR.IRutility.IRVisitor;
import Util.error.ErrorMessage;
import Util.type.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class IRModule {
    private HashMap<String, IRFunction> internalFunctionMap;
    private HashMap<String, IRFunction> externalFunctionMap;
    private HashMap<String, IRConstString> constStringMap;
    private HashMap<String, IRClassType> classTypeMap;
    private HashMap<String, IRType> baseTypeMap;
    private ArrayList<IRGlobalVariable> globalVariableList;

    public IRModule(){
        internalFunctionMap = new LinkedHashMap<>();
        externalFunctionMap = new LinkedHashMap<>();
        constStringMap = new LinkedHashMap<>();
        classTypeMap = new LinkedHashMap<>();
        baseTypeMap = new LinkedHashMap<>();
        globalVariableList = new ArrayList<>();
    }

    public void addBuiltinFunction(IRFunction BuiltinFunction){
        if(internalFunctionMap.containsKey(BuiltinFunction.getIdentifier())){
            throw new ErrorMessage("addBuiltinFunction Conflict");
        }
        internalFunctionMap.put(BuiltinFunction.getIdentifier(), BuiltinFunction);
    }

    public void addExternalFunction(IRFunction ExternalFunction){
        if(externalFunctionMap.containsKey(ExternalFunction.getIdentifier())){
            throw new ErrorMessage("addExternalFunction Conflict");
        }
        externalFunctionMap.put(ExternalFunction.getIdentifier(), ExternalFunction);
    }

    public void removeExternalFunction(String identifier){
        externalFunctionMap.remove(identifier);
    }

    public void addBuiltinType(IRType builtinType, String identifier){
        if(baseTypeMap.containsKey(identifier)){
            throw new ErrorMessage("addBuiltinType Conflict");
        }
        baseTypeMap.put(identifier, builtinType);
    }

    public void addClassType(IRClassType ClassType){
        if(classTypeMap.containsKey(ClassType.getIdentifier())){
            throw new ErrorMessage("addClassType Conflict");
        }
        classTypeMap.put(ClassType.getIdentifier(), ClassType);
    }

    public void addConstString(String string){
        if(!constStringMap.containsKey(string)){
            constStringMap.put(string, new IRConstString(string, constStringMap.size()));
        }
    }

    public void addGlobalVariableList(IRGlobalVariable globalVariable){
        globalVariableList.add(globalVariable);
    }

    public IRType getClassType(String identifier){
        if(baseTypeMap.containsKey(identifier)){
            return baseTypeMap.get(identifier);
        }
        return classTypeMap.get(identifier);
    }

    public IRFunction getFunction(String identifier){
        if(internalFunctionMap.containsKey(identifier)){
            return internalFunctionMap.get(identifier);
        }
        else{
            return externalFunctionMap.get(identifier);
        }
    }

    public IRConstString getConstString(String string){
        return constStringMap.get(string);
    }

    public HashMap<String, IRFunction> getInternalFunctionMap() {
        return internalFunctionMap;
    }

    public HashMap<String, IRClassType> getClassTypeMap() {
        return classTypeMap;
    }


    public ArrayList<IRGlobalVariable> getGlobalVariableList() {
        return globalVariableList;
    }

    public HashMap<String, IRConstString> getConstStringMap() {
        return constStringMap;
    }

    public HashMap<String, IRFunction> getExternalFunctionMap() {
        return externalFunctionMap;
    }

    public boolean internal(IRFunction func){
        return internalFunctionMap.containsKey(func);
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}

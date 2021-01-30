package IR.IRmodule;

import IR.IRfunction.IRFunction;
import IR.IRoperand.IRConstString;
import IR.IRoperand.IRGlobalVariable;
import IR.IRtype.*;
import Util.error.ErrorMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class IRModule {
    private HashMap<String, IRFunction> internalFunctionMap;
    private HashMap<String, IRFunction> externalFunctionMap;
    private HashMap<String, IRConstString> constStringMap;
    private HashMap<String, IRType> classTypeMap;
    private ArrayList<IRGlobalVariable> globalVariableList;
    public static IRType IRString = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
    public static IRType IRVoid = new IRVoidType();
    public static IRType IRInt = new IRIntType(IRIntType.IntTypeBytes.Int32);
    public static IRType IRChar = new IRIntType(IRIntType.IntTypeBytes.Int8);
    public static IRType IRBool = new IRBoolType();

    public IRModule(){
        internalFunctionMap = new HashMap<>();
        externalFunctionMap = new HashMap<>();
        constStringMap = new HashMap<>();
        classTypeMap = new HashMap<>();
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

    public void addBuiltinType(IRType builtinType, String identifier){
        if(classTypeMap.containsKey(identifier)){
            throw new ErrorMessage("addBuiltinType Conflict");
        }
        classTypeMap.put(identifier, builtinType);
    }

    public void addClassType(IRClassType ClassType){
        if(classTypeMap.containsKey(ClassType.getIdentifier())){
            throw new ErrorMessage("addClassType Conflict");
        }
        classTypeMap.put(ClassType.getIdentifier(), ClassType);
    }

    public IRType getClassType(String identifier){
        return classTypeMap.get(identifier);
    }
}

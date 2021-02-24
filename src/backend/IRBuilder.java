package backend;

import AST.*;
import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.*;
import IR.IRtype.*;
import Util.error.ErrorMessage;
import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.type.*;

import java.util.ArrayList;
import java.util.Stack;

public class IRBuilder implements ASTVisitor {
    private IRModule module;
    private IRFunction currentFunction;
    private IRBasicBlock currentBasicBlock;
    private Stack<IRBasicBlock> Breaks;
    private Stack<IRBasicBlock> Continues;
    public IRBuilder(){
        module = new IRModule();
        currentFunction = null;
        currentBasicBlock = null;
    }
    @Override
    public void visit(RootNode node) {
        FuncSymbol Length = (FuncSymbol) node.getScope().findClassSymbol("string", node.getPos()).getScope().findSymbol("length", node.getPos());
        ArrayList<IRLocalRegister> IRLengthPara = new ArrayList<>();
        IRLengthPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IRFunction IRLength = new IRFunction(module, "s_length", new IRIntType(IRIntType.IntTypeBytes.Int32), IRLengthPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IRLength);
        Length.setIrFunction(IRLength);

        FuncSymbol Substring = (FuncSymbol) node.getScope().findClassSymbol("string", node.getPos()).getScope().findSymbol("substring", node.getPos());
        ArrayList<IRLocalRegister> IRSubstringPara = new ArrayList<>();
        IRSubstringPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IRSubstringPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "left"));
        IRSubstringPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "right"));
        IRFunction IRSubstring = new IRFunction(module, "s_substring", new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), IRSubstringPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IRSubstring);
        Substring.setIrFunction(IRSubstring);

        FuncSymbol ParseInt = (FuncSymbol) node.getScope().findClassSymbol("string", node.getPos()).getScope().findSymbol("parseInt", node.getPos());
        ArrayList<IRLocalRegister> IRParseIntPara = new ArrayList<>();
        IRParseIntPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IRFunction IRParseInt = new IRFunction(module, "s_parseInt", new IRIntType(IRIntType.IntTypeBytes.Int32), IRParseIntPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IRParseInt);
        ParseInt.setIrFunction(IRParseInt);

        FuncSymbol Ord = (FuncSymbol) node.getScope().findClassSymbol("string", node.getPos()).getScope().findSymbol("ord", node.getPos());
        ArrayList<IRLocalRegister> IROrdPara = new ArrayList<>();
        IROrdPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IROrdPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "i"));
        IRFunction IROrd = new IRFunction(module, "s_ord", new IRIntType(IRIntType.IntTypeBytes.Int32), IROrdPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IROrd);
        Ord.setIrFunction(IROrd);

        ArrayList<IRLocalRegister> IRStrAddPara = new ArrayList<>();
        IRStrAddPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IRStrAddPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "lhs"));
        IRStrAddPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "rhs"));
        IRFunction IRStrAdd = new IRFunction(module, "s_add", new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), IRStrAddPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IRStrAdd);

        ArrayList<IRLocalRegister> IRStrEQPara = new ArrayList<>();
        IRStrEQPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IRStrEQPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "lhs"));
        IRStrEQPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "rhs"));
        IRFunction IRStrEQ = new IRFunction(module, "s_eq", new IRBoolType(), IRStrEQPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IRStrEQ);

        ArrayList<IRLocalRegister> IRStrNEPara = new ArrayList<>();
        IRStrNEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IRStrNEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "lhs"));
        IRStrNEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "rhs"));
        IRFunction IRStrNE = new IRFunction(module, "s_ne", new IRBoolType(), IRStrNEPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IRStrNE);

        ArrayList<IRLocalRegister> IRStrLTPara = new ArrayList<>();
        IRStrLTPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IRStrLTPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "lhs"));
        IRStrLTPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "rhs"));
        IRFunction IRStrLT = new IRFunction(module, "s_lt", new IRBoolType(), IRStrLTPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IRStrLT);

        ArrayList<IRLocalRegister> IRStrGTPara = new ArrayList<>();
        IRStrGTPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IRStrGTPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "lhs"));
        IRStrGTPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "rhs"));
        IRFunction IRStrGT = new IRFunction(module, "s_gt", new IRBoolType(), IRStrGTPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IRStrGT);

        ArrayList<IRLocalRegister> IRStrLEPara = new ArrayList<>();
        IRStrLEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IRStrLEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "lhs"));
        IRStrLEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "rhs"));
        IRFunction IRStrLE = new IRFunction(module, "s_le", new IRBoolType(), IRStrLEPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IRStrLE);

        ArrayList<IRLocalRegister> IRStrGEPara = new ArrayList<>();
        IRStrGEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        IRStrGEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "lhs"));
        IRStrGEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "rhs"));
        IRFunction IRStrGE = new IRFunction(module, "s_ge", new IRBoolType(), IRStrGEPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "strType"));
        module.addBuiltinFunction(IRStrGE);

        FuncSymbol Print = (FuncSymbol) node.getScope().findSymbol("print", node.getPos());
        ArrayList<IRLocalRegister> IRPrintPara = new ArrayList<>();
        IRPrintPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "str"));
        IRFunction IRPrint = new IRFunction(module, "g_print", new IRVoidType(), IRPrintPara, null);
        module.addBuiltinFunction(IRPrint);
        Print.setIrFunction(IRPrint);

        FuncSymbol Println = (FuncSymbol) node.getScope().findSymbol("println", node.getPos());
        ArrayList<IRLocalRegister> IRPrintlnPara = new ArrayList<>();
        IRPrintlnPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "str"));
        IRFunction IRPrintln = new IRFunction(module, "g_println", new IRVoidType(), IRPrintlnPara, null);
        module.addBuiltinFunction(IRPrintln);
        Println.setIrFunction(IRPrintln);

        FuncSymbol PrintInt = (FuncSymbol) node.getScope().findSymbol("printInt", node.getPos());
        ArrayList<IRLocalRegister> IRPrintIntPara = new ArrayList<>();
        IRPrintIntPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "n"));
        IRFunction IRPrintInt = new IRFunction(module, "g_printInt", new IRVoidType(), IRPrintIntPara, null);
        module.addBuiltinFunction(IRPrintInt);
        PrintInt.setIrFunction(IRPrintInt);

        FuncSymbol PrintlnInt = (FuncSymbol) node.getScope().findSymbol("printlnInt", node.getPos());
        ArrayList<IRLocalRegister> IRPrintlnIntPara = new ArrayList<>();
        IRPrintlnIntPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "n"));
        IRFunction IRPrintlnInt = new IRFunction(module, "g_printlnInt", new IRVoidType(), IRPrintlnIntPara, null);
        module.addBuiltinFunction(IRPrintlnInt);
        PrintlnInt.setIrFunction(IRPrintlnInt);

        FuncSymbol GetString = (FuncSymbol) node.getScope().findSymbol("getString", node.getPos());
        IRFunction IRGetString = new IRFunction(module, "g_getString", new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), new ArrayList<>(), null);
        module.addBuiltinFunction(IRGetString);
        GetString.setIrFunction(IRGetString);

        FuncSymbol GetInt = (FuncSymbol) node.getScope().findSymbol("getInt", node.getPos());
        IRFunction IRGetInt = new IRFunction(module, "g_getInt", new IRIntType(IRIntType.IntTypeBytes.Int32), new ArrayList<>(), null);
        module.addBuiltinFunction(IRGetInt);
        GetInt.setIrFunction(IRGetInt);

        FuncSymbol ToString = (FuncSymbol) node.getScope().findSymbol("toString", node.getPos());
        ArrayList<IRLocalRegister> IRToStringPara = new ArrayList<>();
        IRToStringPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "i"));
        IRFunction IRToString = new IRFunction(module, "g_toString", new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), IRToStringPara, null);
        module.addBuiltinFunction(IRToString);
        ToString.setIrFunction(IRToString);

        ArrayList<IRLocalRegister> IRMallocPara = new ArrayList<>();
        IRMallocPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "size"));
        IRFunction IRMalloc = new IRFunction(module, "g_malloc", new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), IRMallocPara, null);
        module.addBuiltinFunction(IRMalloc);

        module.addBuiltinType(new IRIntType(IRIntType.IntTypeBytes.Int32), "int");
        module.addBuiltinType(new IRBoolType(), "bool");
        module.addBuiltinType(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)), "string");
        module.addBuiltinType(new IRVoidType(), "void");

        for (ASTNode tmp: node.getDefinition()){
            if(tmp instanceof ClassdefNode){
                module.addClassType(new IRClassType(((ClassdefNode) tmp).getIdentifier()));
            }
        }
        for (ASTNode tmp: node.getDefinition()){
            if(tmp instanceof ClassdefNode){
                IRClassType classType = (IRClassType) (module.getClassType(((ClassdefNode) tmp).getIdentifier()));
                for (VardefNode classMember: ((ClassdefNode) tmp).getVarList()){
                    if(classMember.getSymbol().getType() instanceof ArrayType){
                        if(((ArrayType) classMember.getSymbol().getType()).getBaseType() instanceof BoolType){
                            IRType memberType = new IRBoolType();
                            for(int i = 0; i < classMember.getSymbol().getType().getDim(); ++i){
                                memberType = new IRPointerType(memberType);
                            }
                            classType.addMember(memberType);
                        }
                        else if(((ArrayType) classMember.getSymbol().getType()).getBaseType() instanceof ClassType){
                            IRType memberType = new IRPointerType(new IRClassType(classMember.getSymbol().getType().getType()));
                            for(int i = 0; i < classMember.getSymbol().getType().getDim(); ++i){
                                memberType = new IRPointerType(memberType);
                            }
                            classType.addMember(memberType);
                        }
                        else if(((ArrayType) classMember.getSymbol().getType()).getBaseType() instanceof IntType){
                            IRType memberType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                            for(int i = 0; i < classMember.getSymbol().getType().getDim(); ++i){
                                memberType = new IRPointerType(memberType);
                            }
                            classType.addMember(memberType);
                        }
                        else if(((ArrayType) classMember.getSymbol().getType()).getBaseType() instanceof StringType){
                            IRType memberType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                            for(int i = 0; i < classMember.getSymbol().getType().getDim(); ++i){
                                memberType = new IRPointerType(memberType);
                            }
                            classType.addMember(memberType);
                        }
                        else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                    }
                    else if(classMember.getSymbol().getType() instanceof BoolType){
                        classType.addMember(new IRBoolType());
                    }
                    else if(classMember.getSymbol().getType() instanceof ClassType){
                        classType.addMember(new IRPointerType(new IRClassType(classMember.getSymbol().getType().getType())));
                    }
                    else if(classMember.getSymbol().getType() instanceof IntType){
                        classType.addMember(new IRIntType(IRIntType.IntTypeBytes.Int32));
                    }
                    else if(classMember.getSymbol().getType() instanceof StringType){
                        classType.addMember(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8)));
                    }
                    else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                }
                if (classType.getSize() == 0){
                    classType.setSize(8);
                }
                for (FundefNode ClassFunc : ((ClassdefNode) tmp).getFuncList()){
                    IRType returnType;
                    if(ClassFunc.getSymbol().getType() instanceof ArrayType){
                        if(((ArrayType) ClassFunc.getSymbol().getType()).getBaseType() instanceof BoolType){
                            returnType = new IRBoolType();
                            for(int i = 0; i < ClassFunc.getSymbol().getType().getDim(); ++i){
                                returnType = new IRPointerType(returnType);
                            }
                        }
                        else if(((ArrayType) ClassFunc.getSymbol().getType()).getBaseType() instanceof ClassType){
                            returnType = new IRPointerType(new IRClassType(ClassFunc.getSymbol().getType().getType()));
                            for(int i = 0; i < ClassFunc.getSymbol().getType().getDim(); ++i){
                                returnType = new IRPointerType(returnType);
                            }
                        }
                        else if(((ArrayType) ClassFunc.getSymbol().getType()).getBaseType() instanceof IntType){
                            returnType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                            for(int i = 0; i < ClassFunc.getSymbol().getType().getDim(); ++i){
                                returnType = new IRPointerType(returnType);
                            }
                        }
                        else if(((ArrayType) ClassFunc.getSymbol().getType()).getBaseType() instanceof StringType){
                            returnType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                            for(int i = 0; i < ClassFunc.getSymbol().getType().getDim(); ++i){
                                returnType = new IRPointerType(returnType);
                            }
                        }
                        else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                    }
                    else if(ClassFunc.getSymbol().getType() instanceof BoolType){
                        returnType = new IRBoolType();
                    }
                    else if(ClassFunc.getSymbol().getType() instanceof ClassType){
                        returnType = new IRPointerType(new IRClassType(ClassFunc.getSymbol().getType().getType()));
                    }
                    else if(ClassFunc.getSymbol().getType() instanceof IntType){
                        returnType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                    }
                    else if(ClassFunc.getSymbol().getType() instanceof StringType){
                        returnType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                    }
                    else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                    ArrayList<IRLocalRegister> ClassFuncPara = new ArrayList<>();
                    ClassFuncPara.add(new IRLocalRegister(classType, ((ClassdefNode) tmp).getIdentifier()));
                    for (VardefNode para: ClassFunc.getParameterList()){
                        IRType paraType;
                        if(para.getSymbol().getType() instanceof ArrayType){
                            if(((ArrayType) para.getSymbol().getType()).getBaseType() instanceof BoolType){
                                paraType = new IRBoolType();
                                for(int i = 0; i < para.getSymbol().getType().getDim(); ++i){
                                    paraType = new IRPointerType(paraType);
                                }
                            }
                            else if(((ArrayType) para.getSymbol().getType()).getBaseType() instanceof ClassType){
                                paraType = new IRPointerType(new IRClassType(para.getSymbol().getType().getType()));
                                for(int i = 0; i < para.getSymbol().getType().getDim(); ++i){
                                    paraType = new IRPointerType(paraType);
                                }
                            }
                            else if(((ArrayType) para.getSymbol().getType()).getBaseType() instanceof IntType){
                                paraType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                                for(int i = 0; i < para.getSymbol().getType().getDim(); ++i){
                                    paraType = new IRPointerType(paraType);
                                }
                            }
                            else if(((ArrayType) para.getSymbol().getType()).getBaseType() instanceof StringType){
                                paraType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                                for(int i = 0; i < para.getSymbol().getType().getDim(); ++i){
                                    paraType = new IRPointerType(paraType);
                                }
                            }
                            else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                        }
                        else if(para.getSymbol().getType() instanceof BoolType){
                            paraType = new IRBoolType();
                        }
                        else if(para.getSymbol().getType() instanceof ClassType){
                            paraType = new IRPointerType(new IRClassType(para.getSymbol().getType().getType()));
                        }
                        else if(para.getSymbol().getType() instanceof IntType){
                            paraType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                        }
                        else if(para.getSymbol().getType() instanceof StringType){
                            paraType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                        }
                        else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                        ClassFuncPara.add(new IRLocalRegister(paraType, para.getIdentifier()));
                    }
                    IRFunction ClassFunction = new IRFunction(module, ((ClassdefNode) tmp).getIdentifier() + "." + ClassFunc.getIdentifier(), returnType, ClassFuncPara, new IRLocalRegister(classType, ((ClassdefNode) tmp).getIdentifier()));
                    module.addExternalFunction(ClassFunction);
                    ((FuncSymbol) ClassFunc.getScope().findSymbol(ClassFunc.getIdentifier(), ClassFunc.getPos())).setIrFunction(ClassFunction);
                }
            }
        }
        for (ASTNode tmp: node.getDefinition()){
            if(tmp instanceof FundefNode) {
                IRType returnType;
                if (((FundefNode) tmp).getSymbol().getType() instanceof ArrayType) {
                    if (((ArrayType) ((FundefNode) tmp).getSymbol().getType()).getBaseType() instanceof BoolType) {
                        returnType = new IRBoolType();
                        for (int i = 0; i < ((FundefNode) tmp).getSymbol().getType().getDim(); ++i) {
                            returnType = new IRPointerType(returnType);
                        }
                    } else if (((ArrayType) ((FundefNode) tmp).getSymbol().getType()).getBaseType() instanceof ClassType) {
                        returnType = new IRPointerType(new IRClassType(((FundefNode) tmp).getSymbol().getType().getType()));
                        for (int i = 0; i < ((FundefNode) tmp).getSymbol().getType().getDim(); ++i) {
                            returnType = new IRPointerType(returnType);
                        }
                    } else if (((ArrayType) ((FundefNode) tmp).getSymbol().getType()).getBaseType() instanceof IntType) {
                        returnType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                        for (int i = 0; i < ((FundefNode) tmp).getSymbol().getType().getDim(); ++i) {
                            returnType = new IRPointerType(returnType);
                        }
                    } else if (((ArrayType) ((FundefNode) tmp).getSymbol().getType()).getBaseType() instanceof StringType) {
                        returnType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                        for (int i = 0; i < ((FundefNode) tmp).getSymbol().getType().getDim(); ++i) {
                            returnType = new IRPointerType(returnType);
                        }
                    } else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                } else if (((FundefNode) tmp).getSymbol().getType() instanceof BoolType) {
                    returnType = new IRBoolType();
                } else if (((FundefNode) tmp).getSymbol().getType() instanceof ClassType) {
                    returnType = new IRPointerType(new IRClassType(((FundefNode) tmp).getSymbol().getType().getType()));
                } else if (((FundefNode) tmp).getSymbol().getType() instanceof IntType) {
                    returnType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                } else if (((FundefNode) tmp).getSymbol().getType() instanceof StringType) {
                    returnType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                } else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                ArrayList<IRLocalRegister> ClassFuncPara = new ArrayList<>();
                for (VardefNode para : ((FundefNode) tmp).getParameterList()) {
                    IRType paraType;
                    if (para.getSymbol().getType() instanceof ArrayType) {
                        if (((ArrayType) para.getSymbol().getType()).getBaseType() instanceof BoolType) {
                            paraType = new IRBoolType();
                            for (int i = 0; i < para.getSymbol().getType().getDim(); ++i) {
                                paraType = new IRPointerType(paraType);
                            }
                        } else if (((ArrayType) para.getSymbol().getType()).getBaseType() instanceof ClassType) {
                            paraType = new IRPointerType(new IRClassType(para.getSymbol().getType().getType()));
                            for (int i = 0; i < para.getSymbol().getType().getDim(); ++i) {
                                paraType = new IRPointerType(paraType);
                            }
                        } else if (((ArrayType) para.getSymbol().getType()).getBaseType() instanceof IntType) {
                            paraType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                            for (int i = 0; i < para.getSymbol().getType().getDim(); ++i) {
                                paraType = new IRPointerType(paraType);
                            }
                        } else if (((ArrayType) para.getSymbol().getType()).getBaseType() instanceof StringType) {
                            paraType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                            for (int i = 0; i < para.getSymbol().getType().getDim(); ++i) {
                                paraType = new IRPointerType(paraType);
                            }
                        } else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                    } else if (para.getSymbol().getType() instanceof BoolType) {
                        paraType = new IRBoolType();
                    } else if (para.getSymbol().getType() instanceof ClassType) {
                        paraType = new IRPointerType(new IRClassType(para.getSymbol().getType().getType()));
                    } else if (para.getSymbol().getType() instanceof IntType) {
                        paraType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                    } else if (para.getSymbol().getType() instanceof StringType) {
                        paraType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                    } else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                    ClassFuncPara.add(new IRLocalRegister(paraType, para.getIdentifier()));
                }
                IRFunction ClassFunction = new IRFunction(module, ((FundefNode) tmp).getIdentifier(), returnType, ClassFuncPara, null);
                module.addExternalFunction(ClassFunction);
                ((FuncSymbol) tmp.getScope().findSymbol(((FundefNode) tmp).getIdentifier(), tmp.getPos())).setIrFunction(ClassFunction);
            }
        }
        for (ASTNode tmp: node.getDefinition()){
            tmp.accept(this);
        }
    }

    @Override
    public void visit(VardefNode node) {

    }

    @Override
    public void visit(VardefListNode node) {
        node.getVarList().forEach(x->x.accept(this));
    }

    @Override
    public void visit(TypeNode node) {}

    @Override
    public void visit(NewtypeNode node) {

    }

    @Override
    public void visit(FundefNode node) {

    }

    @Override
    public void visit(SuiteNode node) {
        for(StatementNode statement: node.getStatementList()){
            statement.accept(this);
        }
    }

    @Override
    public void visit(ClassdefNode node) {

    }

    @Override
    public void visit(BreakstatementNode node) {
        currentBasicBlock.addInst(new Br(currentBasicBlock, null, Breaks.peek(), null));
    }

    @Override
    public void visit(ContinuestatementNode node) {
        currentBasicBlock.addInst(new Br(currentBasicBlock, null, Continues.peek(), null));
    }

    @Override
    public void visit(EmptystatementNode node) {}

    @Override
    public void visit(ExprstatementNode node) {
        node.getExpression().accept(this);
    }

    @Override
    public void visit(ForstatementNode node) {

    }

    @Override
    public void visit(IfstatementNode node) {

    }

    @Override
    public void visit(ReturnstatementNode node) {

    }

    @Override
    public void visit(VardefstatementNode node) {
        node.getVarList().accept(this);
    }

    @Override
    public void visit(WhilestatementNode node) {

    }

    @Override
    public void visit(NullliteralNode node) {
        node.setResult(new IRConstNull());
        node.setBasicResult(null);
    }

    @Override
    public void visit(BoolliteralNode node) {
        node.setResult(new IRConstBool(node.getVal()));
        node.setBasicResult(null);
    }

    @Override
    public void visit(IntegerliteralNode node) {
        node.setResult(new IRConstInt(node.getVal()));
        node.setBasicResult(null);
    }

    @Override
    public void visit(StringliteralNode node) {
        module.addConstString(node.getVal());

    }

    @Override
    public void visit(IdentifierNode node) {

    }

    @Override
    public void visit(BinaryexprNode node) {
        BinaryexprNode.BinaryOpType op = node.getOp();
        if(op == BinaryexprNode.BinaryOpType.AddBinary){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Binary_Add");
                currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.add, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                //todo
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.MinusBinary){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Binary_Minus");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.sub, lhs, rhs, result));
            node.setResult(result);
            node.setBasicResult(null);
        }
        else if(op == BinaryexprNode.BinaryOpType.Mul){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Binary_Mul");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.mul, lhs, rhs, result));
            node.setResult(result);
            node.setBasicResult(null);
        }
        else if(op == BinaryexprNode.BinaryOpType.Div){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Binary_Div");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.sdiv, lhs, rhs, result));
            node.setResult(result);
            node.setBasicResult(null);
        }
        else if(op == BinaryexprNode.BinaryOpType.Mod){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Binary_Mod");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.srem, lhs, rhs, result));
            node.setResult(result);
            node.setBasicResult(null);
        }
        else if(op == BinaryexprNode.BinaryOpType.LeftShift){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "LeftShift");
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.shl, lhs, rhs, result));
            node.setResult(result);
            node.setBasicResult(null);
        }
        else if(op == BinaryexprNode.BinaryOpType.RightShift){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "RightShift");
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.ashr, lhs, rhs, result));
            node.setResult(result);
            node.setBasicResult(null);
        }
        else if(op == BinaryexprNode.BinaryOpType.Less){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Less");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.slt, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                //todo
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR", node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.LessEqual){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "LessEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.sle, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                //todo
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.Greater){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Greater");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.sgt, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                //todo
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.GreaterEqual){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "GreaterEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.sge, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                //todo
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.Equal){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof BoolType && node.getRhs().getType() instanceof BoolType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                //todo
            }
            else if(node.getLhs().getType() instanceof ArrayType && node.getRhs().getType() instanceof NullType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof ArrayType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof ClassType && node.getRhs().getType() instanceof NullType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof ClassType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof NullType){
                node.setResult(new IRConstBool(true));
                node.setBasicResult(null);
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.NotEqual){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof BoolType && node.getRhs().getType() instanceof BoolType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                //todo
            }
            else if(node.getLhs().getType() instanceof ArrayType && node.getRhs().getType() instanceof NullType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof ArrayType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof ClassType && node.getRhs().getType() instanceof NullType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof ClassType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
                node.setBasicResult(null);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof NullType){
                node.setResult(new IRConstBool(false));
                node.setBasicResult(null);
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.AndAri){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "AndAri");
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.and, lhs, rhs, result));
            node.setResult(result);
            node.setBasicResult(null);
        }
        else if(op == BinaryexprNode.BinaryOpType.OrAri){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "OrAri");
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.or, lhs, rhs, result));
            node.setResult(result);
            node.setBasicResult(null);
        }
        else if(op == BinaryexprNode.BinaryOpType.AndLogic){

        }
        else if(op == BinaryexprNode.BinaryOpType.OrLogic){

        }
        else if(op == BinaryexprNode.BinaryOpType.XorAri){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = node.getLhs().getResult();
            IROperand rhs = node.getRhs().getResult();
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "XorAri");
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.xor, lhs, rhs, result));
            node.setResult(result);
            node.setBasicResult(null);
        }
        else if(op == BinaryexprNode.BinaryOpType.Assign){

        }
        else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR", node.getPos());
    }

    @Override
    public void visit(PrefixexprNode node) {
        PrefixexprNode.PrefixOpType op = node.getOp();
        node.getExpression().accept(this);
        IROperand result = node.getExpression().getResult();
        IROperand basicResult = node.getExpression().getBasicResult();
        if(op == PrefixexprNode.PrefixOpType.Add){
            node.setResult(result);
            node.setBasicResult(null);
        }
        else if(op == PrefixexprNode.PrefixOpType.Minus){
            node.setResult(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32),"Prefix_Minus"));
            node.setBasicResult(null);
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.sub, new IRConstInt(0), result, node.getResult()));
        }
        else if(op == PrefixexprNode.PrefixOpType.AddAdd){
            node.setResult(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Prefix_AddAdd"));
            node.setBasicResult(basicResult);
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.add, result, new IRConstInt(1), node.getResult()));
            currentBasicBlock.addInst(new Store(currentBasicBlock, node.getResult(), basicResult));
        }
        else if(op == PrefixexprNode.PrefixOpType.MinusMinus){
            node.setResult(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Prefix_MinusMinus"));
            node.setBasicResult(basicResult);
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.sub, result, new IRConstInt(1), node.getResult()));
            currentBasicBlock.addInst(new Store(currentBasicBlock, node.getResult(), basicResult));
        }
        else if(op == PrefixexprNode.PrefixOpType.NotLogic){
            node.setResult(new IRLocalRegister(new IRBoolType(), "Prefix_NotLogic"));
            node.setBasicResult(null);
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.xor, new IRConstBool(true), result, node.getResult()));
        }
        else if(op == PrefixexprNode.PrefixOpType.NotAri){
            node.setResult(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Prefix_NotAri"));
            node.setBasicResult(null);
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.xor, new IRConstInt(-1), result, node.getResult()));
        }
        else throw new ErrorMessage("IRBuilder VisitPrefixExprNode ERROR", node.getPos());
    }

    @Override
    public void visit(SuffixexprNode node) {
        SuffixexprNode.SuffixOpType op = node.getOp();
        node.getExpression().accept(this);
        IROperand result = node.getExpression().getResult();
        IROperand basicResult = node.getExpression().getBasicResult();
        if(op == SuffixexprNode.SuffixOpType.AddTwice){
            node.setResult(result);
            node.setBasicResult(null);
            IROperand Result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Suffix_AddAdd");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.add, result, new IRConstInt(1), Result));
            currentBasicBlock.addInst(new Store(currentBasicBlock, Result, basicResult));
        }
        else if(op == SuffixexprNode.SuffixOpType.MinusTwice){
            node.setResult(result);
            node.setBasicResult(null);
            IROperand Result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Suffix_MinusMinus");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.sub, result, new IRConstInt(1), Result));
            currentBasicBlock.addInst(new Store(currentBasicBlock, Result, basicResult));
        }
        else throw new ErrorMessage("IRBuilder VisitSuffixExprNode ERROR", node.getPos());
    }

    @Override
    public void visit(MemberexprNode node) {
        node.getExpression().accept(this);
        ExprNode.ExprType exprType = node.getExprType();
        if(exprType == ExprNode.ExprType.FUNCTION){
            node.setResult(node.getExpression().getResult());
            node.setBasicResult(null);
        }
        else if(exprType == ExprNode.ExprType.LVALUE){
            //todo
        }
        else throw new ErrorMessage("IRBuilder VisitMemberExprNode ERROR", node.getPos());
    }

    @Override
    public void visit(FuncexprNode node) {

    }

    @Override
    public void visit(SubarrayexprNode node) {
        node.getIdentifier().accept(this);
        node.getIndex().accept(this);
    }

    @Override
    public void visit(NewexprNode node) {

    }

    @Override
    public void visit(ThisexprNode node) {
        node.setResult(currentFunction.getClassPtr());
        node.setBasicResult(null);
    }
}

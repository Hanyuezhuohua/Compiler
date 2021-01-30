package backend;

import AST.*;
import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRLocalRegister;
import IR.IRtype.*;
import Util.error.ErrorMessage;
import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.type.*;

import java.util.ArrayList;

public class IRBuilder implements ASTVisitor {
    private IRModule module;
    private IRFunction currentFunction;
    private IRBasicBlock currentBasicBlock;
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
            if(tmp instanceof FundefNode){
                for (FundefNode Func : ((ClassdefNode) tmp).getFuncList()){
                    IRType returnType;
                    if(Func.getSymbol().getType() instanceof ArrayType){
                        if(((ArrayType) Func.getSymbol().getType()).getBaseType() instanceof BoolType){
                            returnType = new IRBoolType();
                            for(int i = 0; i < Func.getSymbol().getType().getDim(); ++i){
                                returnType = new IRPointerType(returnType);
                            }
                        }
                        else if(((ArrayType) Func.getSymbol().getType()).getBaseType() instanceof ClassType){
                            returnType = new IRPointerType(new IRClassType(Func.getSymbol().getType().getType()));
                            for(int i = 0; i < Func.getSymbol().getType().getDim(); ++i){
                                returnType = new IRPointerType(returnType);
                            }
                        }
                        else if(((ArrayType) Func.getSymbol().getType()).getBaseType() instanceof IntType){
                            returnType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                            for(int i = 0; i < Func.getSymbol().getType().getDim(); ++i){
                                returnType = new IRPointerType(returnType);
                            }
                        }
                        else if(((ArrayType) Func.getSymbol().getType()).getBaseType() instanceof StringType){
                            returnType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                            for(int i = 0; i < Func.getSymbol().getType().getDim(); ++i){
                                returnType = new IRPointerType(returnType);
                            }
                        }
                        else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                    }
                    else if(Func.getSymbol().getType() instanceof BoolType){
                        returnType = new IRBoolType();
                    }
                    else if(Func.getSymbol().getType() instanceof ClassType){
                        returnType = new IRPointerType(new IRClassType(Func.getSymbol().getType().getType()));
                    }
                    else if(Func.getSymbol().getType() instanceof IntType){
                        returnType = new IRIntType(IRIntType.IntTypeBytes.Int32);
                    }
                    else if(Func.getSymbol().getType() instanceof StringType){
                        returnType = new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8));
                    }
                    else throw new ErrorMessage("IRBuilder RootNode Error", node.getPos());
                    ArrayList<IRLocalRegister> ClassFuncPara = new ArrayList<>();
                    for (VardefNode para: Func.getParameterList()){
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
                    IRFunction ClassFunction = new IRFunction(module, Func.getIdentifier(), returnType, ClassFuncPara, null);
                    module.addExternalFunction(ClassFunction);
                    ((FuncSymbol) Func.getScope().findSymbol(Func.getIdentifier(), Func.getPos())).setIrFunction(ClassFunction);
                }
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

    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(NewtypeNode node) {

    }

    @Override
    public void visit(FundefNode node) {

    }

    @Override
    public void visit(SuiteNode node) {

    }

    @Override
    public void visit(ClassdefNode node) {

    }

    @Override
    public void visit(BreakstatementNode node) {

    }

    @Override
    public void visit(ContinuestatementNode node) {

    }

    @Override
    public void visit(EmptystatementNode node) {

    }

    @Override
    public void visit(ExprstatementNode node) {

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

    }

    @Override
    public void visit(WhilestatementNode node) {

    }

    @Override
    public void visit(NullliteralNode node) {

    }

    @Override
    public void visit(BoolliteralNode node) {

    }

    @Override
    public void visit(IntegerliteralNode node) {

    }

    @Override
    public void visit(StringliteralNode node) {

    }

    @Override
    public void visit(IdentifierNode node) {

    }

    @Override
    public void visit(BinaryexprNode node) {

    }

    @Override
    public void visit(PrefixexprNode node) {

    }

    @Override
    public void visit(SuffixexprNode node) {

    }

    @Override
    public void visit(MemberexprNode node) {

    }

    @Override
    public void visit(FuncexprNode node) {

    }

    @Override
    public void visit(SubarrayexprNode node) {

    }

    @Override
    public void visit(NewexprNode node) {

    }

    @Override
    public void visit(ThisexprNode node) {

    }
}

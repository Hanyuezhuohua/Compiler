package backend.AST_IR;

import AST.*;
import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.*;
import IR.IRtype.*;
import IR.IRutility.FuncBlockCollection;
import Optimization.IR.GlobalToLocal;
import Util.error.ErrorMessage;
import Util.scope.GlobalScope;
import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.symbol.Symbol;
import Util.symbol.VarSymbol;
import Util.type.*;
import frontend.LoopDepthAnalysis;

import java.util.ArrayList;
import java.util.Stack;

public class IRBuilder implements ASTVisitor {
    private IRModule module;
    private IRFunction currentFunction;
    private IRBasicBlock currentBasicBlock;
    private Stack<IRBasicBlock> Breaks = new Stack<>();
    private Stack<IRBasicBlock> Continues = new Stack<>();
    private IRBasicBlock RetBlock;
    private Phi RetPhi;
    private boolean optimize;
    public IRBuilder(boolean optimize){
        module = new IRModule();
        currentFunction = null;
        currentBasicBlock = null;
        RetBlock = null;
        RetPhi = null;
        this.optimize = optimize;
    }

    public IRModule getModule() {
        return module;
    }

    @Override
    public void visit(RootNode node) {
        FuncSymbol Length = (FuncSymbol) node.getScope().findClassSymbol("string", node.getPos()).getScope().findSymbol("length", node.getPos());
        ArrayList<IRLocalRegister> IRLengthPara = new ArrayList<>();
        IRFunction IRLength = new IRFunction(module, "_str_length", new IRIntType(IRIntType.IntTypeBytes.Int32), IRLengthPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "this"));
        module.addBuiltinFunction(IRLength);
        Length.setIrFunction(IRLength);

        FuncSymbol Substring = (FuncSymbol) node.getScope().findClassSymbol("string", node.getPos()).getScope().findSymbol("substring", node.getPos());
        ArrayList<IRLocalRegister> IRSubstringPara = new ArrayList<>();
        IRSubstringPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "left"));
        IRSubstringPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "right"));
        IRFunction IRSubstring = new IRFunction(module, "_str_substring", new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), IRSubstringPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "this"));
        module.addBuiltinFunction(IRSubstring);
        Substring.setIrFunction(IRSubstring);

        FuncSymbol ParseInt = (FuncSymbol) node.getScope().findClassSymbol("string", node.getPos()).getScope().findSymbol("parseInt", node.getPos());
        ArrayList<IRLocalRegister> IRParseIntPara = new ArrayList<>();
        IRFunction IRParseInt = new IRFunction(module, "_str_parseInt", new IRIntType(IRIntType.IntTypeBytes.Int32), IRParseIntPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "this"));
        module.addBuiltinFunction(IRParseInt);
        ParseInt.setIrFunction(IRParseInt);

        FuncSymbol Ord = (FuncSymbol) node.getScope().findClassSymbol("string", node.getPos()).getScope().findSymbol("ord", node.getPos());
        ArrayList<IRLocalRegister> IROrdPara = new ArrayList<>();
        IROrdPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "i"));
        IRFunction IROrd = new IRFunction(module, "_str_ord", new IRIntType(IRIntType.IntTypeBytes.Int32), IROrdPara, new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "this"));
        module.addBuiltinFunction(IROrd);
        Ord.setIrFunction(IROrd);

        ArrayList<IRLocalRegister> IRStrAddPara = new ArrayList<>();
        IRStrAddPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "lhs"));
        IRStrAddPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "rhs"));
        IRFunction IRStrAdd = new IRFunction(module, "_str_concat", new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), IRStrAddPara, null);
        module.addBuiltinFunction(IRStrAdd);

        ArrayList<IRLocalRegister> IRStrEQPara = new ArrayList<>();
        IRStrEQPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "lhs"));
        IRStrEQPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "rhs"));
        IRFunction IRStrEQ = new IRFunction(module, "_str_eq", new IRBoolType(), IRStrEQPara, null);
        module.addBuiltinFunction(IRStrEQ);

        ArrayList<IRLocalRegister> IRStrNEPara = new ArrayList<>();
        IRStrNEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "lhs"));
        IRStrNEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "rhs"));
        IRFunction IRStrNE = new IRFunction(module, "_str_ne", new IRBoolType(), IRStrNEPara, null);
        module.addBuiltinFunction(IRStrNE);

        ArrayList<IRLocalRegister> IRStrLTPara = new ArrayList<>();
        IRStrLTPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "lhs"));
        IRStrLTPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "rhs"));
        IRFunction IRStrLT = new IRFunction(module, "_str_lt", new IRBoolType(), IRStrLTPara, null);
        module.addBuiltinFunction(IRStrLT);

        ArrayList<IRLocalRegister> IRStrGTPara = new ArrayList<>();
        IRStrGTPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "lhs"));
        IRStrGTPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "rhs"));
        IRFunction IRStrGT = new IRFunction(module, "_str_gt", new IRBoolType(), IRStrGTPara, null);
        module.addBuiltinFunction(IRStrGT);

        ArrayList<IRLocalRegister> IRStrLEPara = new ArrayList<>();
        IRStrLEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "lhs"));
        IRStrLEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "rhs"));
        IRFunction IRStrLE = new IRFunction(module, "_str_le", new IRBoolType(), IRStrLEPara, null);
        module.addBuiltinFunction(IRStrLE);

        ArrayList<IRLocalRegister> IRStrGEPara = new ArrayList<>();
        IRStrGEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "lhs"));
        IRStrGEPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "rhs"));
        IRFunction IRStrGE = new IRFunction(module, "_str_ge", new IRBoolType(), IRStrGEPara, null);
        module.addBuiltinFunction(IRStrGE);

        FuncSymbol Print = (FuncSymbol) node.getScope().findSymbol("print", node.getPos());
        ArrayList<IRLocalRegister> IRPrintPara = new ArrayList<>();
        IRPrintPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "str"));
        IRFunction IRPrint = new IRFunction(module, "_gbl_print", new IRVoidType(), IRPrintPara, null);
        module.addBuiltinFunction(IRPrint);
        Print.setIrFunction(IRPrint);

        FuncSymbol Println = (FuncSymbol) node.getScope().findSymbol("println", node.getPos());
        ArrayList<IRLocalRegister> IRPrintlnPara = new ArrayList<>();
        IRPrintlnPara.add(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "str"));
        IRFunction IRPrintln = new IRFunction(module, "_gbl_println", new IRVoidType(), IRPrintlnPara, null);
        module.addBuiltinFunction(IRPrintln);
        Println.setIrFunction(IRPrintln);

        FuncSymbol PrintInt = (FuncSymbol) node.getScope().findSymbol("printInt", node.getPos());
        ArrayList<IRLocalRegister> IRPrintIntPara = new ArrayList<>();
        IRPrintIntPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "n"));
        IRFunction IRPrintInt = new IRFunction(module, "_gbl_printInt", new IRVoidType(), IRPrintIntPara, null);
        module.addBuiltinFunction(IRPrintInt);
        PrintInt.setIrFunction(IRPrintInt);

        FuncSymbol PrintlnInt = (FuncSymbol) node.getScope().findSymbol("printlnInt", node.getPos());
        ArrayList<IRLocalRegister> IRPrintlnIntPara = new ArrayList<>();
        IRPrintlnIntPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "n"));
        IRFunction IRPrintlnInt = new IRFunction(module, "_gbl_printlnInt", new IRVoidType(), IRPrintlnIntPara, null);
        module.addBuiltinFunction(IRPrintlnInt);
        PrintlnInt.setIrFunction(IRPrintlnInt);

        FuncSymbol GetString = (FuncSymbol) node.getScope().findSymbol("getString", node.getPos());
        IRFunction IRGetString = new IRFunction(module, "_gbl_getString", new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), new ArrayList<>(), null);
        module.addBuiltinFunction(IRGetString);
        GetString.setIrFunction(IRGetString);

        FuncSymbol GetInt = (FuncSymbol) node.getScope().findSymbol("getInt", node.getPos());
        IRFunction IRGetInt = new IRFunction(module, "_gbl_getInt", new IRIntType(IRIntType.IntTypeBytes.Int32), new ArrayList<>(), null);
        module.addBuiltinFunction(IRGetInt);
        GetInt.setIrFunction(IRGetInt);

        FuncSymbol ToString = (FuncSymbol) node.getScope().findSymbol("toString", node.getPos());
        ArrayList<IRLocalRegister> IRToStringPara = new ArrayList<>();
        IRToStringPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "i"));
        IRFunction IRToString = new IRFunction(module, "_gbl_toString", new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), IRToStringPara, null);
        module.addBuiltinFunction(IRToString);
        ToString.setIrFunction(IRToString);

        ArrayList<IRLocalRegister> IRMallocPara = new ArrayList<>();
        IRMallocPara.add(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "size"));
        IRFunction IRMalloc = new IRFunction(module, "malloc", new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), IRMallocPara, null);
        module.addBuiltinFunction(IRMalloc);

        IRFunction IRInit = new IRFunction(module, "g_init", new IRVoidType(), new ArrayList<>(), null);
        IRInit.setExit(IRInit.getEntry());
        module.addExternalFunction(IRInit);

        module.addBuiltinType(new IRIntType(IRIntType.IntTypeBytes.Int32), "int");
        module.addBuiltinType(new IRBoolType(), "bool");
        module.addBuiltinType(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "string");
        module.addBuiltinType(new IRVoidType(), "void");

        for (ASTNode tmp: node.getDefinition()){
            if(tmp instanceof ClassdefNode){
                IRClassType classType = new IRClassType(((ClassdefNode) tmp).getIdentifier());
                module.addClassType(classType);
                ((ClassSymbol)((ClassdefNode) tmp).getSymbol()).setClassType(classType);
            }
        }
        for (ASTNode tmp: node.getDefinition()){
            if(tmp instanceof ClassdefNode){
                IRClassType classType = ((ClassSymbol)((ClassdefNode) tmp).getSymbol()).getClassType();
                for (VardefNode classMember: ((ClassdefNode) tmp).getVarList()){
                    IRType memberType = Type_Change(classMember.getSymbol().getType(), true);
                    classType.addMember(memberType);
                    ((VarSymbol)(classMember.getSymbol())).setIndex(classType.getIndex());
                    classMember.accept(this);
                }
                for (FundefNode ClassFunc : ((ClassdefNode) tmp).getFuncList()){
                    IRType returnType = Type_Change(ClassFunc.getSymbol().getType(), false);
                    IRFunction ClassFunction = new IRFunction(module, ((ClassdefNode) tmp).getIdentifier() + "." + ClassFunc.getIdentifier(), returnType, new ArrayList<>(), new IRLocalRegister(new IRPointerType(classType, false), "this"));
                    module.addExternalFunction(ClassFunction);
                    for (VardefNode para: ClassFunc.getParameterList()){
                        currentFunction = ClassFunction;
                        currentBasicBlock = currentFunction.getEntry();
                        para.accept(this);
                    }
                    currentFunction = null;
                    currentBasicBlock = null;
                    ((FuncSymbol)(ClassFunc.getSymbol())).setIrFunction(ClassFunction);
                }
                if(((ClassdefNode) tmp).getConstructor() != null){
                    FundefNode ClassConstructor = ((ClassdefNode) tmp).getConstructor();
                    IRType returnType = Type_Change(ClassConstructor.getSymbol().getType(), false);
                    IRFunction ConstructFunction = new IRFunction(module, ((ClassdefNode) tmp).getIdentifier() + "." + ClassConstructor.getIdentifier(), returnType, new ArrayList<>(), new IRLocalRegister(new IRPointerType(classType, false), "this"));
                    module.addExternalFunction(ConstructFunction);
                    for (VardefNode para: ClassConstructor.getParameterList()){
                        currentFunction = ConstructFunction;
                        currentBasicBlock = currentFunction.getEntry();
                        para.accept(this);
                    }
                    currentFunction = null;
                    currentBasicBlock = null;
                    ((FuncSymbol)(ClassConstructor.getSymbol())).setIrFunction(ConstructFunction);
                }
            }
            if(tmp instanceof FundefNode) {
                IRType returnType = Type_Change(((FundefNode) tmp).getSymbol().getType(), false);
                IRFunction Function = new IRFunction(module, ((FundefNode) tmp).getIdentifier(), returnType, new ArrayList<>(), null);
                module.addExternalFunction(Function);
                for (VardefNode para : ((FundefNode) tmp).getParameterList()) {
                    currentFunction = Function;
                    currentBasicBlock = currentFunction.getEntry();
                    para.accept(this);
                }
                currentFunction = null;
                currentBasicBlock = null;
                ((FuncSymbol)(((FundefNode)tmp).getSymbol())).setIrFunction(Function);
            }
        }
        for (ASTNode tmp: node.getDefinition()){
            tmp.accept(this);
        }

        IRInit.getExit().addInst(new Ret(IRInit.getExit(), new IRConstVoid()));
        FuncBlockCollection collector = new FuncBlockCollection();
        currentFunction = IRInit;
        currentFunction.setBlockContain(collector.BlockCollecting(currentFunction));

        new GlobalToLocal(module).run();
    }

    @Override
    public void visit(VardefNode node) {
        VarSymbol varSymbol = (VarSymbol) node.getSymbol();
        IRType varType = Type_Change(varSymbol.getType(), true);
        if(varSymbol.getScope() instanceof GlobalScope){ //GlobalVariable
            if(node.getExpression() == null){
                IRGlobalVariable globalVar = new IRGlobalVariable(new IRPointerType(varType, true), varSymbol.getIdentifier(), varType.initValue(), false);
                module.addGlobalVariableList(globalVar);
                varSymbol.setOperand(globalVar);
            }
            else{
                IRGlobalVariable globalVar = new IRGlobalVariable(new IRPointerType(varType, true), varSymbol.getIdentifier(), varType.initValue(), false);
                module.addGlobalVariableList(globalVar);
                varSymbol.setOperand(globalVar);
                currentFunction = module.getFunction("g_init");
                currentBasicBlock = currentFunction.getExit();
                node.getExpression().accept(this);
                IROperand pointTo = Resolve_IRPointer(node.getExpression().getResult());
                if(pointTo instanceof IRConstBool){
                    currentBasicBlock.addInst(new Store(currentBasicBlock, new IRConstInt(((IRConstBool) pointTo).getValue()? 1 : 0, IRIntType.IntTypeBytes.Int8), globalVar));
                }
                else if(pointTo.getOperandType() instanceof IRBoolType){
                    IRLocalRegister ZextResult = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int8), pointTo.getIdentifier() + "_afterZext");
                    currentBasicBlock.addInst(new Zext(currentBasicBlock, pointTo, ZextResult));
                    currentBasicBlock.addInst(new Store(currentBasicBlock, ZextResult, globalVar));
                }
                else{
                    currentBasicBlock.addInst(new Store(currentBasicBlock, pointTo, globalVar));
                }
                currentFunction.setExit(currentBasicBlock);
                currentBasicBlock = null;
                currentFunction = null;
            }
        }
        else if(node.isParameter()){
            IRLocalRegister parameter = new IRLocalRegister(varType, varSymbol.getIdentifier() + "_para");
            currentFunction.addParameter(parameter);
            IRLocalRegister var = new IRLocalRegister((new IRPointerType(varType, true)), varSymbol.getIdentifier() + "_varAlloc");
            currentFunction.addVar(var);
            varSymbol.setOperand(var);
            currentBasicBlock.addInst(new Store(currentBasicBlock, parameter, var));
        }
        else if(currentFunction != null){
            IRLocalRegister var = new IRLocalRegister((new IRPointerType(varType, true)), varSymbol.getIdentifier() + "_varAlloc");
            currentFunction.addVar(var);
            varSymbol.setOperand(var);
            if(node.getExpression() != null){
                node.getExpression().accept(this);
                IROperand pointTo = Resolve_IRPointer(node.getExpression().getResult());
                if(pointTo instanceof IRConstBool){
                    currentBasicBlock.addInst(new Store(currentBasicBlock, new IRConstInt(((IRConstBool) pointTo).getValue()? 1 : 0, IRIntType.IntTypeBytes.Int8), var));
                }
                else if(pointTo.getOperandType() instanceof IRBoolType){
                    IRLocalRegister ZextResult = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int8), pointTo.getIdentifier() + "_afterZext");
                    currentBasicBlock.addInst(new Zext(currentBasicBlock, pointTo, ZextResult));
                    currentBasicBlock.addInst(new Store(currentBasicBlock, ZextResult, var));
                }
                else{
                    currentBasicBlock.addInst(new Store(currentBasicBlock, pointTo, var));
                }
            }
        }
        else{
            IRLocalRegister var = new IRLocalRegister(new IRPointerType(varType, true), varSymbol.getIdentifier() + "_member");
            varSymbol.setOperand(var);
        }
    }

    @Override
    public void visit(VardefListNode node) {
        node.getVarList().forEach(x->x.accept(this));
    }

    @Override
    public void visit(TypeNode node) {}

    @Override
    public void visit(NewtypeNode node) {}

    @Override
    public void visit(FundefNode node) {
        currentFunction = ((FuncSymbol) node.getSymbol()).getIrFunction();
        currentBasicBlock = currentFunction.getEntry();
//        IRLocalRegister tmp = null;
        if(node.getSymbol().getType() instanceof VoidType){
            RetBlock = new IRBasicBlock(currentFunction, "VoidReturn");
            RetBlock.addInst(new Ret(RetBlock, new IRConstVoid()));
            node.getSuite().accept(this);
            if(RetBlock.getPrev().size() == 0){
                currentBasicBlock.addInst(new Ret(currentBasicBlock, new IRConstVoid()));
            }
            else{
                currentBasicBlock.addInst(new Br(currentBasicBlock, null, RetBlock, null));
                currentBasicBlock = RetBlock;
            }
        }
        else{
            if(node.getIdentifier().equals("main")){
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("g_init"), new ArrayList<>(), new IRLocalRegister(new IRVoidType(), "FuncRet")));
            }
            RetBlock = new IRBasicBlock(currentFunction, "NonVoidReturn");
            IRLocalRegister operandReturn = new IRLocalRegister(Type_Change(node.getSymbol().getType(), true), "ReturnValue");
            RetPhi = new Phi(RetBlock, new ArrayList<>(), new ArrayList<>(), operandReturn);
            node.getSuite().accept(this);
            if(RetPhi.getLabels().size() == 0){
                currentBasicBlock.addInst(new Ret(currentBasicBlock, Type_Change(node.getSymbol().getType(), true).initValue()));
            }
            else if(RetPhi.getLabels().size() == 1){
                currentBasicBlock = RetPhi.getLabels().get(0);
                IRInstruction inst = currentBasicBlock.getTail();
                inst.Remove();
                currentBasicBlock.getNext().remove(RetBlock);
                RetBlock.getPrev().remove(currentBasicBlock);
                currentBasicBlock.addInst(new Ret(currentBasicBlock, RetPhi.getValues().get(0)));
            }
            else{
                currentBasicBlock = RetBlock;
                currentBasicBlock.addInst(RetPhi);
                currentBasicBlock.addInst(new Ret(currentBasicBlock, Resolve_IRPointer(operandReturn)));
            }
        }
        currentFunction.addAlloc();
        currentFunction.setExit(currentBasicBlock);
        FuncBlockCollection collector = new FuncBlockCollection();
        currentFunction.setBlockContain(collector.BlockCollecting(currentFunction));
        DominatorTree tree = new DominatorTree(currentFunction);
        tree.Lengauer_Tarjan();
        //  DominatorTree.Lengauer_Tarjan(currentFunction);
        currentFunction = null;
        currentBasicBlock = null;
        RetBlock = null;
        RetPhi = null;
    }

    @Override
    public void visit(SuiteNode node) {
        for(StatementNode statement: node.getStatementList()){
            statement.accept(this);
        }
    }

    @Override
    public void visit(ClassdefNode node) {
        //       for(VardefNode var : node.getVarList()){
        //           var.accept(this);
        //       }
        for(FundefNode func : node.getFuncList()){
            func.accept(this);
        }
        if(node.getConstructor() != null) {
            node.getConstructor().accept(this);
        }
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
        if(node.getExpression().isConst()) return;
        node.getExpression().accept(this);
    }

    private int loopUnrollSize = 1;

    @Override
    public void visit(ForstatementNode node) {
        if(node.getInitDef() != null){
            node.getInitDef().accept(this);
        }
        else if(node.getInitExpr() != null){
            node.getInitExpr().accept(this);
        }
        if(node.getCondition().isConst() && !((BoolliteralNode)node.getCondition().getConstant()).getVal()){
            return;
        }
        if(optimize && (node.getInitExpr() != null && node.getInitExpr() instanceof BinaryexprNode && ((BinaryexprNode) node.getInitExpr()).getOp() == BinaryexprNode.BinaryOpType.Assign && ((BinaryexprNode) node.getInitExpr()).getRhs().isConst())
                && (node.getCondition() instanceof BinaryexprNode && (((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.Greater || ((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.GreaterEqual || ((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.Less || ((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.Less) && ((BinaryexprNode) node.getCondition()).getRhs().isConst())
                && ((node.getIncr() != null && node.getIncr() instanceof SuffixexprNode) || (node.getIncr() != null && node.getIncr() instanceof PrefixexprNode && (((PrefixexprNode) node.getIncr()).getOp() == PrefixexprNode.PrefixOpType.AddAdd || ((PrefixexprNode) node.getIncr()).getOp() == PrefixexprNode.PrefixOpType.MinusMinus)))
                && (((BinaryexprNode) node.getInitExpr()).getLhs() instanceof IdentifierNode && ((BinaryexprNode) node.getCondition()).getLhs() instanceof IdentifierNode && ((node.getIncr() instanceof PrefixexprNode && ((PrefixexprNode) node.getIncr()).getExpression() instanceof IdentifierNode) || (node.getIncr() instanceof SuffixexprNode && ((SuffixexprNode) node.getIncr()).getExpression() instanceof IdentifierNode)))){
            Symbol symbol = ((IdentifierNode) ((BinaryexprNode) node.getInitExpr()).getLhs()).getSymbol();
            if(symbol == ((IdentifierNode) ((BinaryexprNode) node.getCondition()).getLhs()).getSymbol()
                    && (node.getIncr() instanceof PrefixexprNode && ((PrefixexprNode) node.getIncr()).getExpression() instanceof IdentifierNode && ((IdentifierNode) ((PrefixexprNode) node.getIncr()).getExpression()).getSymbol() == symbol)){
                int loopNum = 0;
                int step = ((PrefixexprNode) node.getIncr()).getOp() == PrefixexprNode.PrefixOpType.AddAdd ? 1 : -1;
                if(((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.Greater){
                    loopNum = ((IntegerliteralNode) (((BinaryexprNode) node.getCondition()).getRhs().getConstant())).getVal() - ((IntegerliteralNode) (((BinaryexprNode) node.getInitExpr()).getRhs().getConstant())).getVal();
                    loopNum /= step;
                }
                else if(((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.GreaterEqual){
                    loopNum = ((IntegerliteralNode) (((BinaryexprNode) node.getCondition()).getRhs().getConstant())).getVal() - ((IntegerliteralNode) (((BinaryexprNode) node.getInitExpr()).getRhs().getConstant())).getVal();
                    loopNum /= step;
                    loopNum += 1;
                }
                else if(((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.Less){
                    loopNum = ((IntegerliteralNode) (((BinaryexprNode) node.getCondition()).getRhs().getConstant())).getVal() - ((IntegerliteralNode) (((BinaryexprNode) node.getInitExpr()).getRhs().getConstant())).getVal();
                    loopNum /= step;
                }
                else if(((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.LessEqual){
                    loopNum = ((IntegerliteralNode) (((BinaryexprNode) node.getCondition()).getRhs().getConstant())).getVal() - ((IntegerliteralNode) (((BinaryexprNode) node.getInitExpr()).getRhs().getConstant())).getVal();
                    loopNum /= step;
                    loopNum += 1;
                }
                if(loopNum <= 0) return;
                int loopDepth = new LoopDepthAnalysis(node).run();
                if(loopDepth <= 2 && loopNum <= 10){
                    loopUnrollSize = loopNum * loopUnrollSize;
                    IRBasicBlock destBlock = new IRBasicBlock(currentFunction, "forDest");
                    ArrayList<IRBasicBlock> stmtBlocks = new ArrayList<>();
                    for(int i = 0; i < loopNum; ++i) stmtBlocks.add(new IRBasicBlock(currentFunction, "forStmt"));
                    currentBasicBlock.addInst(new Br(currentBasicBlock, null, stmtBlocks.get(0), null));
                    for(int i = 0; i < loopNum; ++i){
                        currentBasicBlock = stmtBlocks.get(i);
                        Breaks.push(destBlock);
                        Continues.push(i != loopNum - 1 ? stmtBlocks.get(i + 1) : destBlock);
                        if(i > 0) node.getIncr().accept(this);
                        node.getBlock().accept(this);
                        currentBasicBlock.addInst(new Br(currentBasicBlock, null, i != loopNum - 1 ? stmtBlocks.get(i + 1) : destBlock, null));
                    }
                    currentBasicBlock = destBlock;
                    node.getIncr().accept(this);
                    loopUnrollSize /= loopNum;
                    return;
                }
            }
            if(symbol == ((IdentifierNode) ((BinaryexprNode) node.getCondition()).getLhs()).getSymbol()
                    && (node.getIncr() instanceof SuffixexprNode && ((SuffixexprNode) node.getIncr()).getExpression() instanceof IdentifierNode && ((IdentifierNode) ((SuffixexprNode) node.getIncr()).getExpression()).getSymbol() == symbol)){
                int loopNum = 0;
                int step = ((SuffixexprNode) node.getIncr()).getOp() == SuffixexprNode.SuffixOpType.AddTwice ? 1 : -1;
                if(((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.Greater){
                    loopNum = ((IntegerliteralNode) (((BinaryexprNode) node.getCondition()).getRhs().getConstant())).getVal() - ((IntegerliteralNode) (((BinaryexprNode) node.getInitExpr()).getRhs().getConstant())).getVal();
                    loopNum /= step;
                }
                else if(((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.GreaterEqual){
                    loopNum = ((IntegerliteralNode) (((BinaryexprNode) node.getCondition()).getRhs().getConstant())).getVal() - ((IntegerliteralNode) (((BinaryexprNode) node.getInitExpr()).getRhs().getConstant())).getVal();
                    loopNum /= step;
                    loopNum += 1;
                }
                else if(((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.Less){
                    loopNum = ((IntegerliteralNode) (((BinaryexprNode) node.getCondition()).getRhs().getConstant())).getVal() - ((IntegerliteralNode) (((BinaryexprNode) node.getInitExpr()).getRhs().getConstant())).getVal();
                    loopNum /= step;
                }
                else if(((BinaryexprNode) node.getCondition()).getOp() == BinaryexprNode.BinaryOpType.LessEqual){
                    loopNum = ((IntegerliteralNode) (((BinaryexprNode) node.getCondition()).getRhs().getConstant())).getVal() - ((IntegerliteralNode) (((BinaryexprNode) node.getInitExpr()).getRhs().getConstant())).getVal();
                    loopNum /= step;
                    loopNum += 1;
                }
                if(loopNum <= 0) return;
                int loopDepth = new LoopDepthAnalysis(node).run();
                if(loopDepth <= 2 && loopNum <= 10){
                    loopUnrollSize = loopNum * loopUnrollSize;
                    IRBasicBlock destBlock = new IRBasicBlock(currentFunction, "forDest");
                    ArrayList<IRBasicBlock> stmtBlocks = new ArrayList<>();
                    for(int i = 0; i < loopNum; ++i) stmtBlocks.add(new IRBasicBlock(currentFunction, "forStmt"));
                    currentBasicBlock.addInst(new Br(currentBasicBlock, null, stmtBlocks.get(0), null));
                    for(int i = 0; i < loopNum; ++i){
                        currentBasicBlock = stmtBlocks.get(i);
                        Breaks.push(destBlock);
                        Continues.push(i != loopNum - 1 ? stmtBlocks.get(i + 1) : destBlock);
                        if(i > 0) node.getIncr().accept(this);
                        node.getBlock().accept(this);
                        Breaks.pop();
                        Continues.pop();
                        currentBasicBlock.addInst(new Br(currentBasicBlock, null, i != loopNum - 1 ? stmtBlocks.get(i + 1) : destBlock, null));
                    }
                    currentBasicBlock = destBlock;
                    node.getIncr().accept(this);
                    loopUnrollSize /= loopNum;
                    return;
                }
            }
        }
        IRBasicBlock condBlock = new IRBasicBlock(currentFunction, "forCond");
        IRBasicBlock stmtBlock = new IRBasicBlock(currentFunction, "forStmt");
        IRBasicBlock incrBlock = new IRBasicBlock(currentFunction, "forIncr");
        IRBasicBlock destBlock = new IRBasicBlock(currentFunction, "forDest");
        currentBasicBlock.addInst(new Br(currentBasicBlock, null, condBlock, null));
        currentBasicBlock = condBlock;
        node.getCondition().accept(this);
        currentBasicBlock.addInst(new Br(currentBasicBlock, Resolve_IRPointer(node.getCondition().getResult()), stmtBlock, destBlock));
        currentBasicBlock = stmtBlock;
        Breaks.push(destBlock);
        if(node.getIncr() != null){
            Continues.push(incrBlock);
        }
        else{
            Continues.push(condBlock);
        }
        node.getBlock().accept(this);
        Breaks.pop();
        Continues.pop();
        if(node.getIncr() != null){
            currentBasicBlock.addInst(new Br(currentBasicBlock, null, incrBlock, null));
            currentBasicBlock = incrBlock;
            node.getIncr().accept(this);
        }
        currentBasicBlock.addInst(new Br(currentBasicBlock, null, condBlock, null));
        currentBasicBlock = destBlock;
    }

    @Override
    public void visit(IfstatementNode node) {
        if(node.getCondition().isConst()){
            if(((BoolliteralNode)node.getCondition().getConstant()).getVal()){
                node.getTrueStat().accept(this);
            }
            else{
                if(node.getFalseStat() != null) node.getFalseStat().accept(this);
            }
            return;
        }
        node.getCondition().accept(this);
        IRBasicBlock trueBlock = new IRBasicBlock(currentFunction, "ifTrue");
        IRBasicBlock falseBlock = new IRBasicBlock(currentFunction, "ifFalse");
        IRBasicBlock destBlock = new IRBasicBlock(currentFunction, "ifDest");
        if(node.getFalseStat() == null){
            currentBasicBlock.addInst(new Br(currentBasicBlock, Resolve_IRPointer(node.getCondition().getResult()), trueBlock, destBlock));
            currentBasicBlock = trueBlock;
            node.getTrueStat().accept(this);
        }
        else{
            currentBasicBlock.addInst(new Br(currentBasicBlock, Resolve_IRPointer(node.getCondition().getResult()), trueBlock, falseBlock));
            currentBasicBlock = trueBlock;
            node.getTrueStat().accept(this);
            currentBasicBlock.addInst(new Br(currentBasicBlock, null, destBlock, null));
            currentBasicBlock = falseBlock;
            node.getFalseStat().accept(this);
        }
        currentBasicBlock.addInst(new Br(currentBasicBlock, null, destBlock, null));
        currentBasicBlock = destBlock;
    }

    @Override
    public void visit(ReturnstatementNode node) {
        if (node.getReturnVal() != null) {
            node.getReturnVal().accept(this);
//            currentBasicBlock.addInst(new Store(currentBasicBlock, Resolve_IRPointer(node.getReturnVal().getResult()), ((Alloca) (currentFunction.getEntry().getHead())).getResult()));
            RetPhi.addValue(Resolve_IRPointer(node.getReturnVal().getResult()));
            RetPhi.addBlock(currentBasicBlock);
        }
        currentBasicBlock.addInst(new Br(currentBasicBlock, null, RetBlock, null));
    }

    @Override
    public void visit(VardefstatementNode node) {
        node.getVarList().accept(this);
    }

    @Override
    public void visit(WhilestatementNode node) {
        if(node.getCondition().isConst() && !((BoolliteralNode) (node.getCondition().getConstant())).getVal()) return;
        IRBasicBlock condBlock = new IRBasicBlock(currentFunction, "whileCond");
        IRBasicBlock stmtBlock = new IRBasicBlock(currentFunction, "whileStmt");
        IRBasicBlock destBlock = new IRBasicBlock(currentFunction, "whileDest");
        currentBasicBlock.addInst(new Br(currentBasicBlock, null, condBlock, null));
        currentBasicBlock = condBlock;
        node.getCondition().accept(this);
        currentBasicBlock.addInst(new Br(currentBasicBlock, Resolve_IRPointer(node.getCondition().getResult()), stmtBlock, destBlock));
        currentBasicBlock = stmtBlock;
        Breaks.push(destBlock);
        Continues.push(condBlock);
        node.getBlock().accept(this);
        Breaks.pop();
        Continues.pop();
        currentBasicBlock.addInst(new Br(currentBasicBlock, null, condBlock, null));
        currentBasicBlock = destBlock;
    }

    @Override
    public void visit(NullliteralNode node) {
        node.setResult(new IRConstNull());
    }

    @Override
    public void visit(BoolliteralNode node) {
        node.setResult(new IRConstBool(node.getVal()));
    }

    @Override
    public void visit(IntegerliteralNode node) {
        node.setResult(new IRConstInt(node.getVal(), IRIntType.IntTypeBytes.Int32));
    }

    @Override
    public void visit(StringliteralNode node) {
        String val = node.getVal();
        module.addConstString(val);
        IRConstString ConstString = module.getConstString(val);
        node.setResult(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "str_addr"));
        ArrayList<IROperand> index = new ArrayList<>();
        index.add(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
        index.add(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
        currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, ConstString, index, node.getResult()));
    }

    @Override
    public void visit(IdentifierNode node) {
        if(node.isConst()){
            if(node.getConstant() instanceof BoolliteralNode){
                node.setResult(((BoolliteralNode) node.getConstant()).getVal() ? new IRConstBool(true) : new IRConstBool(false));
            }
            else if(node.getConstant() instanceof IntegerliteralNode){
                node.setResult(new IRConstInt(((IntegerliteralNode) node.getConstant()).getVal(), IRIntType.IntTypeBytes.Int32));
            }
            else if(node.getConstant() instanceof StringliteralNode){
                String val = ((StringliteralNode) node.getConstant()).getVal();
                module.addConstString(val);
                IRConstString ConstString = module.getConstString(val);
                node.setResult(new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "str_addr"));
                ArrayList<IROperand> index = new ArrayList<>();
                index.add(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                index.add(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, ConstString, index, node.getResult()));
            }
            else{
                node.setResult(new IRConstNull());
            }
            return;
        }
        Symbol symbol = node.getSymbol();
        if(symbol instanceof VarSymbol){
            if(((VarSymbol) symbol).isMember()){
                node.setResult(new IRLocalRegister(((VarSymbol) symbol).getOperand().getOperandType(), "this." + symbol.getIdentifier() + "_addr"));
                ArrayList<IROperand> index = new ArrayList<>();
                index.add(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                index.add(((VarSymbol) symbol).getIndex());
                currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, currentFunction.getClassPtr(), index, node.getResult()));
            }
            else{
                node.setResult(((VarSymbol) symbol).getOperand());
            }
        }
        else if(symbol instanceof FuncSymbol){
            if(((FuncSymbol) symbol).isMember()){
                node.setResult(currentFunction.getClassPtr());
            }
        }
        else throw new ErrorMessage("IRBuilder Visit IdentifierNode ERROR");
    }

    @Override
    public void visit(BinaryexprNode node) {
        if(node.isConst()){
            node.getConstant().accept(this);
            node.setResult(node.getConstant().getResult());
            return;
        }
        BinaryexprNode.BinaryOpType op = node.getOp();
        if(op == BinaryexprNode.BinaryOpType.AddBinary){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Binary_Add");
                currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.add, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                IROperand result = new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "String_Add");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_concat"), functionArgs, result));
                node.setResult(result);
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.MinusBinary){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Binary_Minus");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.sub, lhs, rhs, result));
            node.setResult(result);
        }
        else if(op == BinaryexprNode.BinaryOpType.Mul){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Binary_Mul");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.mul, lhs, rhs, result));
            node.setResult(result);
        }
        else if(op == BinaryexprNode.BinaryOpType.Div){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Binary_Div");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.sdiv, lhs, rhs, result));
            node.setResult(result);
        }
        else if(op == BinaryexprNode.BinaryOpType.Mod){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Binary_Mod");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.srem, lhs, rhs, result));
            node.setResult(result);
        }
        else if(op == BinaryexprNode.BinaryOpType.LeftShift){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "LeftShift");
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.shl, lhs, rhs, result));
            node.setResult(result);
        }
        else if(op == BinaryexprNode.BinaryOpType.RightShift){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "RightShift");
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.ashr, lhs, rhs, result));
            node.setResult(result);
        }
        else if(op == BinaryexprNode.BinaryOpType.Less){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Less");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.slt, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "String_Less");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_lt"), functionArgs, result));
                node.setResult(result);
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR", node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.LessEqual){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "LessEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.sle, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "String_LessEqual");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_le"), functionArgs, result));
                node.setResult(result);
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.Greater){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Greater");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.sgt, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "String_Greater");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_gt"), functionArgs, result));
                node.setResult(result);
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.GreaterEqual){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "GreaterEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.sge, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "String_GreaterEqual");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_ge"), functionArgs, result));
                node.setResult(result);
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.Equal){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof BoolType && node.getRhs().getType() instanceof BoolType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "String_Equal");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_eq"), functionArgs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof NullType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "String_Equal");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_eq"), functionArgs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof StringType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "String_Equal");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_eq"), functionArgs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof ArrayType && node.getRhs().getType() instanceof NullType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof ArrayType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof ClassType && node.getRhs().getType() instanceof NullType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof ClassType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "Equal");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.eq, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof NullType){
                node.setResult(new IRConstBool(true));
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.NotEqual){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof BoolType && node.getRhs().getType() instanceof BoolType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "String_NotEqual");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_ne"), functionArgs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof NullType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "String_NotEqual");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_ne"), functionArgs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof StringType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "String_NotEqual");
                ArrayList<IROperand> functionArgs = new ArrayList<>();
                functionArgs.add(lhs);
                functionArgs.add(rhs);
                currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("_str_ne"), functionArgs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof ArrayType && node.getRhs().getType() instanceof NullType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof ArrayType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof ClassType && node.getRhs().getType() instanceof NullType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof ClassType){
                IROperand result = new IRLocalRegister(new IRBoolType(), "NotEqual");
                currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.ne, lhs, rhs, result));
                node.setResult(result);
            }
            else if(node.getLhs().getType() instanceof NullType && node.getRhs().getType() instanceof NullType){
                node.setResult(new IRConstBool(false));
            }
            else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR" , node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.AndAri){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "AndAri");
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.and, lhs, rhs, result));
            node.setResult(result);
        }
        else if(op == BinaryexprNode.BinaryOpType.OrAri){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "OrAri");
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.or, lhs, rhs, result));
            node.setResult(result);
        }
        else if(op == BinaryexprNode.BinaryOpType.AndLogic){
            node.getLhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            ArrayList<IROperand> values = new ArrayList<>();
            ArrayList<IRBasicBlock> labels = new ArrayList<>();
            values.add(new IRConstBool(false));
            labels.add(currentBasicBlock);
            IRBasicBlock condBlock = new IRBasicBlock(currentFunction, "AndLogicCond");
            IRBasicBlock destBlock = new IRBasicBlock(currentFunction, "AndLogicDest");
            currentBasicBlock.addInst(new Br(currentBasicBlock, lhs, condBlock, destBlock));
            currentBasicBlock = condBlock;
            node.getRhs().accept(this);
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            values.add(rhs);
            labels.add(currentBasicBlock);
            currentBasicBlock.addInst(new Br(currentBasicBlock, null, destBlock, null));
            currentBasicBlock = destBlock;
            node.setResult(new IRLocalRegister(new IRBoolType(), "AndLogicRes"));
            currentBasicBlock.addInst(new Phi(currentBasicBlock, values, labels, node.getResult()));
        }
        else if(op == BinaryexprNode.BinaryOpType.OrLogic){
            node.getLhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            ArrayList<IROperand> values = new ArrayList<>();
            ArrayList<IRBasicBlock> labels = new ArrayList<>();
            values.add(new IRConstBool(true));
            labels.add(currentBasicBlock);
            IRBasicBlock condBlock = new IRBasicBlock(currentFunction, "OrLogicCond");
            IRBasicBlock destBlock = new IRBasicBlock(currentFunction, "OrLogicDest");
            currentBasicBlock.addInst(new Br(currentBasicBlock, lhs, destBlock, condBlock));
            currentBasicBlock = condBlock;
            node.getRhs().accept(this);
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            values.add(rhs);
            labels.add(currentBasicBlock);
            currentBasicBlock.addInst(new Br(currentBasicBlock, null, destBlock, null));
            currentBasicBlock = destBlock;
            node.setResult(new IRLocalRegister(new IRBoolType(), "OrLogicRes"));
            currentBasicBlock.addInst(new Phi(currentBasicBlock, values, labels, node.getResult()));
        }
        else if(op == BinaryexprNode.BinaryOpType.XorAri){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand lhs = Resolve_IRPointer(node.getLhs().getResult());
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            IROperand result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "XorAri");
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.xor, lhs, rhs, result));
            node.setResult(result);
        }
        else if(op == BinaryexprNode.BinaryOpType.Assign){
            node.getLhs().accept(this);
            node.getRhs().accept(this);
            IROperand rhs = Resolve_IRPointer(node.getRhs().getResult());
            if(rhs instanceof IRConstBool){
                currentBasicBlock.addInst(new Store(currentBasicBlock, new IRConstInt(((IRConstBool) rhs).getValue()? 1 : 0, IRIntType.IntTypeBytes.Int8), node.getLhs().getResult()));
            }
            else if(rhs.getOperandType() instanceof IRBoolType){
                IRLocalRegister ZextResult = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int8), rhs.getIdentifier() + "_afterZext");
                currentBasicBlock.addInst(new Zext(currentBasicBlock, rhs, ZextResult));
                currentBasicBlock.addInst(new Store(currentBasicBlock, ZextResult, node.getLhs().getResult()));
            }
            else{
                currentBasicBlock.addInst(new Store(currentBasicBlock, rhs, node.getLhs().getResult()));
            }
        }
        else throw new ErrorMessage("IRBuilder VisitBinaryExprNode ERROR", node.getPos());
    }

    @Override
    public void visit(PrefixexprNode node) {
        if(node.isConst()){
            node.getConstant().accept(this);
            node.setResult(node.getConstant().getResult());
            return;
        }
        PrefixexprNode.PrefixOpType op = node.getOp();
        node.getExpression().accept(this);
        if(op == PrefixexprNode.PrefixOpType.Add){
            node.setResult(node.getExpression().getResult());
        }
        else if(op == PrefixexprNode.PrefixOpType.Minus){
            node.setResult(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32),"Prefix_Minus"));
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.sub, new IRConstInt(0, IRIntType.IntTypeBytes.Int32), Resolve_IRPointer(node.getExpression().getResult()), node.getResult()));
        }
        else if(op == PrefixexprNode.PrefixOpType.AddAdd){
            IRLocalRegister result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Prefix_AddAdd");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.add, Resolve_IRPointer(node.getExpression().getResult()), new IRConstInt(1, IRIntType.IntTypeBytes.Int32), result));
            currentBasicBlock.addInst(new Store(currentBasicBlock, result, node.getExpression().getResult()));
            node.setResult(node.getExpression().getResult());
        }
        else if(op == PrefixexprNode.PrefixOpType.MinusMinus){
            IRLocalRegister result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Prefix_MinusMinus");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.sub, Resolve_IRPointer(node.getExpression().getResult()), new IRConstInt(1, IRIntType.IntTypeBytes.Int32), result));
            currentBasicBlock.addInst(new Store(currentBasicBlock, result, node.getExpression().getResult()));
            node.setResult(node.getExpression().getResult());
        }
        else if(op == PrefixexprNode.PrefixOpType.NotLogic){
            node.setResult(new IRLocalRegister(new IRBoolType(), "Prefix_NotLogic"));
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.xor, new IRConstBool(true), Resolve_IRPointer(node.getExpression().getResult()), node.getResult()));
        }
        else if(op == PrefixexprNode.PrefixOpType.NotAri){
            node.setResult(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Prefix_NotAri"));
            currentBasicBlock.addInst(new BitwiseBinary(currentBasicBlock, BitwiseBinary.IRBitwiseBinaryOpType.xor, new IRConstInt(-1, IRIntType.IntTypeBytes.Int32), Resolve_IRPointer(node.getExpression().getResult()), node.getResult()));
        }
        else throw new ErrorMessage("IRBuilder VisitPrefixExprNode ERROR", node.getPos());
    }

    @Override
    public void visit(SuffixexprNode node) {
        SuffixexprNode.SuffixOpType op = node.getOp();
        node.getExpression().accept(this);
        node.setResult(Resolve_IRPointer(node.getExpression().getResult()));
        if(op == SuffixexprNode.SuffixOpType.AddTwice){
            IRLocalRegister result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Suffix_AddAdd");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.add, node.getResult(), new IRConstInt(1, IRIntType.IntTypeBytes.Int32), result));
            currentBasicBlock.addInst(new Store(currentBasicBlock, result, node.getExpression().getResult()));
        }
        else if(op == SuffixexprNode.SuffixOpType.MinusTwice){
            IRLocalRegister result = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "Suffix_MinusMinus");
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.sub, node.getResult(), new IRConstInt(1, IRIntType.IntTypeBytes.Int32), result));
            currentBasicBlock.addInst(new Store(currentBasicBlock, result, node.getExpression().getResult()));
        }
        else throw new ErrorMessage("IRBuilder VisitSuffixExprNode ERROR", node.getPos());
    }

    @Override
    public void visit(MemberexprNode node) {
        node.getExpression().accept(this);
        if(node.getExprType() == ExprNode.ExprType.FUNCTION){
            node.setResult(node.getExpression().getResult());
        }
        else if(node.getExprType() == ExprNode.ExprType.LVALUE){
            node.setResult(new IRLocalRegister(((VarSymbol)(node.getSymbol())).getOperand().getOperandType(), "this." + node.getIdentifier()));
            ArrayList<IROperand> index = new ArrayList<>();
            index.add(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
            index.add(((VarSymbol) node.getSymbol()).getIndex());
            currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, Resolve_IRPointer(node.getExpression().getResult()), index, node.getResult()));
        }
        else throw new ErrorMessage("IRBuilder VisitMemberExprNode ERROR", node.getPos());
    }

    @Override
    public void visit(FuncexprNode node) {
        node.getExpression().accept(this);
        FuncSymbol funcSymbol = node.getFuncSymbol();
        if(funcSymbol.getIdentifier().equals("*size*")){
            node.setResult(new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "ArraySize"));
            IROperand ArrayPtr = Resolve_IRPointer(node.getExpression().getResult());
            IROperand ResultPtr = new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int32), true), "ArraySizePtr");
            if(((IRPointerType)(ArrayPtr.getOperandType())).getPointTo().getType().equals("i32")){
                ArrayList<IROperand> index = new ArrayList<>();
                index.add(new IRConstInt(-1, IRIntType.IntTypeBytes.Int32));
                currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, ArrayPtr, index, ResultPtr));
            }
            else{
                IROperand BitCastPtr = new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int32), true), "BitCastPtr");
                currentBasicBlock.addInst(new BitCast(currentBasicBlock, ArrayPtr, BitCastPtr));
                ArrayList<IROperand> index = new ArrayList<>();
                index.add(new IRConstInt(-1, IRIntType.IntTypeBytes.Int32));
                currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, BitCastPtr, index, ResultPtr));
            }
            currentBasicBlock.addInst(new Load(currentBasicBlock, ResultPtr, node.getResult()));
        }
        else{
            node.setResult(new IRLocalRegister(funcSymbol.getIrFunction().getReturnType(), "FuncRet"));
            ArrayList<IROperand> paraList = new ArrayList<>();
            if(funcSymbol.isMember()){
                paraList.add(Resolve_IRPointer(node.getExpression().getResult()));
            }
            for(ExprNode para: node.getParameterList()){
                para.accept(this);
                paraList.add(Resolve_IRPointer(para.getResult()));
            }
            currentBasicBlock.addInst(new Call(currentBasicBlock, funcSymbol.getIrFunction(), paraList, node.getResult()));
        }
    }

    @Override
    public void visit(SubarrayexprNode node) {
        node.getIdentifier().accept(this);
        node.getIndex().accept(this);
        node.setResult(new IRLocalRegister(new IRPointerType(Type_Change(node.getType(), true), true), "SubArray"));
        ArrayList<IROperand> index = new ArrayList<>();
        index.add(Resolve_IRPointer(node.getIndex().getResult()));
        currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, Resolve_IRPointer(node.getIdentifier().getResult()), index, node.getResult()));
    }

    @Override
    public void visit(NewexprNode node) {
        if(node.getType() instanceof ArrayType){
            node.setResult(new IRLocalRegister(Type_Change(node.getType(), false), "NewExprResult"));
            newArray(node.getNewtype().getKnown(), 0, node.getResult());
        }
        else if(node.getType() instanceof ClassType){
            IROperand mallocResult = new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "MallocResult");
            node.setResult(new IRLocalRegister(Type_Change(node.getType(), false), "BitCastResult"));
            ArrayList<IROperand> functionArgs = new ArrayList<>();
            functionArgs.add(new IRConstInt(((IRPointerType) Type_Change(node.getType(), false)).getPointTo().getSize() / 8, IRIntType.IntTypeBytes.Int32));
            currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("malloc"), functionArgs, mallocResult));
            currentBasicBlock.addInst(new BitCast(currentBasicBlock, mallocResult, node.getResult()));
            if(node.getFuncSymbol() != null){
                currentBasicBlock.addInst(new Call(currentBasicBlock, node.getFuncSymbol().getIrFunction(), new ArrayList<>() {{ add(node.getResult());}}, new IRLocalRegister(node.getFuncSymbol().getIrFunction().getReturnType(), "ConstructRet")));
            }
        }
        else throw new ErrorMessage("IRBuilder Visit NewExprNode Error!");
    }

    @Override
    public void visit(ThisexprNode node) {
        node.setResult(currentFunction.getClassPtr());
    }

    private IRType Type_Change(Type type, boolean memory){
        if(type instanceof ArrayType){
            IRType IRtype = Type_Change(((ArrayType) type).getBaseType(), memory);
            for(int i = 0; i < type.getDim(); ++i){
                IRtype = new IRPointerType(IRtype, false);
            }
            return IRtype;
        }
        else if(type instanceof BoolType){
            if(memory){
                return new IRIntType(IRIntType.IntTypeBytes.Int8);
            }
            else{
                return new IRBoolType();
            }
        }
        else if(type instanceof ClassType){
            return new IRPointerType(module.getClassType(type.getType()), false);
        }
        else if(type instanceof IntType){
            return new IRIntType(IRIntType.IntTypeBytes.Int32);
        }
        else if(type instanceof NullType){
            return new IRPointerType(new IRVoidType(), false);
        }
        else if(type instanceof StringType){
            return new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false);
        }
        else if(type instanceof VoidType){
            return new IRVoidType();
        }
        else throw new ErrorMessage("Type Change Error!");
    }

    private IROperand Resolve_IRPointer(IROperand operand){
        if(operand.getOperandType().resolvable()){
            if(((IRPointerType)(operand.getOperandType())).getPointTo() instanceof IRArrayType){
                IRLocalRegister loadStrResult = new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), operand.getIdentifier() + "_afterResolveStr");
                ArrayList<IROperand> index = new ArrayList<>();
                index.add(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                index.add(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, operand, index, loadStrResult));
                return loadStrResult;
            }
            IRLocalRegister loadResult = new IRLocalRegister(((IRPointerType)(operand.getOperandType())).getPointTo(), operand.getIdentifier() + "_afterResolve");
            currentBasicBlock.addInst(new Load(currentBasicBlock, operand, loadResult));
            if(loadResult.getOperandType().getType().equals("i8")){
                IRLocalRegister truncResult = new IRLocalRegister(new IRBoolType(), loadResult.getIdentifier() + "_afterTrunc");
                currentBasicBlock.addInst(new Trunc(currentBasicBlock, loadResult, truncResult));
                return truncResult;
            }
            return loadResult;
        }
        else{
            return operand;
        }
    }

    private void newArray(ArrayList<ExprNode> known, int currentDim, IROperand result){
        if(currentDim == known.size()){
            return;
        }
        IROperand arrayPtr = currentDim == 0? result : new IRLocalRegister(((IRPointerType) result.getOperandType()).getPointTo(), "newArrayPtr");
        known.get(currentDim).accept(this);

        IROperand newArraySize = Resolve_IRPointer(known.get(currentDim).getResult());
        IROperand DataWidth = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "DataWidth");
        IROperand TotalWidth = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "TotalWidth");
        IROperand MallocResult = new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int8), false), "MallocResult");
        IROperand BitCastResult = new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int32), false), "BitCastResult");

        if(newArraySize instanceof IRConstInt){
            TotalWidth = new IRConstInt(((IRConstInt) newArraySize).getValue() * ((IRPointerType)arrayPtr.getOperandType()).getPointTo().getSize() / 8 + 4, IRIntType.IntTypeBytes.Int32);
        }
        else{
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.mul, newArraySize, new IRConstInt(((IRPointerType)arrayPtr.getOperandType()).getPointTo().getSize() / 8, IRIntType.IntTypeBytes.Int32), DataWidth));
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.add, DataWidth, new IRConstInt(4, IRIntType.IntTypeBytes.Int32), TotalWidth));
        }
        ArrayList<IROperand> functionArgs = new ArrayList<>();
        functionArgs.add(TotalWidth);
        currentBasicBlock.addInst(new Call(currentBasicBlock, module.getFunction("malloc"), functionArgs, MallocResult));
        currentBasicBlock.addInst(new BitCast(currentBasicBlock, MallocResult, BitCastResult));
        currentBasicBlock.addInst(new Store(currentBasicBlock,newArraySize, BitCastResult));
        if(((IRPointerType)arrayPtr.getOperandType()).getPointTo().getType().equals("i32")){
            ArrayList<IROperand> index = new ArrayList<>();
            index.add(new IRConstInt(1, IRIntType.IntTypeBytes.Int32));
            currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, BitCastResult, index, arrayPtr));
        }
        else{
            IROperand GetElementPtrResult = new IRLocalRegister(new IRPointerType(new IRIntType(IRIntType.IntTypeBytes.Int32), false), "GetElementPtrResult");
            ArrayList<IROperand> index = new ArrayList<>();
            index.add(new IRConstInt(1, IRIntType.IntTypeBytes.Int32));
            currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, BitCastResult, index, GetElementPtrResult));
            currentBasicBlock.addInst(new BitCast(currentBasicBlock,GetElementPtrResult, arrayPtr));
        }
        if(currentDim > 0){
            currentBasicBlock.addInst(new Store(currentBasicBlock, arrayPtr, result));
        }
        if(currentDim < known.size() - 1){
            IRBasicBlock condBlock = new IRBasicBlock(currentFunction, "NewArrayCond");
            IRBasicBlock stmtBlock = new IRBasicBlock(currentFunction, "NewArrayStmt");
            IRBasicBlock destBlock = new IRBasicBlock(currentFunction, "NewArrayDest");
            ArrayList<IROperand> values = new ArrayList<>();
            ArrayList<IRBasicBlock> labels = new ArrayList<>();
            values.add(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
            labels.add(currentBasicBlock);
            currentBasicBlock.addInst(new Br(currentBasicBlock, null, condBlock, null));

            IROperand CurrentIndex = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "CurrentIndex");
            IROperand NextIndex = new IRLocalRegister(new IRIntType(IRIntType.IntTypeBytes.Int32), "NextIndex");
            IROperand IcmpResult =new IRLocalRegister(new IRBoolType(), "IcmpResult");
            IROperand GEPResult = new IRLocalRegister(arrayPtr.getOperandType(), "GEPResult");

            values.add(NextIndex);
            labels.add(stmtBlock);

            currentBasicBlock = condBlock;
            currentBasicBlock.addInst(new Phi(currentBasicBlock, values, labels, CurrentIndex));
            //           currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.add, CurrentIndex, new IRConstInt(1, IRIntType.IntTypeBytes.Int32), NextIndex));
            currentBasicBlock.addInst(new Icmp(currentBasicBlock, Icmp.IRIcmpOpType.slt, CurrentIndex, newArraySize, IcmpResult));
            currentBasicBlock.addInst(new Br(currentBasicBlock, IcmpResult, stmtBlock, destBlock));

            currentBasicBlock = stmtBlock;
            ArrayList<IROperand> index = new ArrayList<>();
            index.add(CurrentIndex);
            currentBasicBlock.addInst(new GetElementPtr(currentBasicBlock, arrayPtr, index, GEPResult));

            newArray(known, currentDim + 1, GEPResult);
            currentBasicBlock.addInst(new Binary(currentBasicBlock, Binary.IRBinaryOpType.add, CurrentIndex, new IRConstInt(1, IRIntType.IntTypeBytes.Int32), NextIndex));
            currentBasicBlock.addInst(new Br(currentBasicBlock, null, condBlock, null));
            currentBasicBlock = destBlock;
        }
    }
}
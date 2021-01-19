package frontend;

import AST.*;
import Util.error.ErrorMessage;
import Util.position;
import Util.scope.GlobalScope;
import Util.scope.LocalScope;
import Util.scope.Scope;
import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.symbol.Symbol;
import Util.symbol.VarSymbol;
import Util.type.*;

import java.util.ArrayList;

public class ScopeBuilder implements ASTVisitor {
    private Scope currentScope;
    private ClassSymbol currentClass;
    private FuncSymbol currentFunc;
    private StatementNode currentLoop;

    public ScopeBuilder(){
        currentScope = new GlobalScope();

        ClassSymbol Int = new ClassSymbol(new position(-1, -1), "int", new IntType(), new LocalScope(currentScope));
        ClassSymbol Bool = new ClassSymbol(new position(-1, -1), "bool", new BoolType(), new LocalScope(currentScope));
        ClassSymbol string = new ClassSymbol(new position(-1, -1), "string", new StringType(), new LocalScope(currentScope));
        ClassSymbol Void = new ClassSymbol(new position(-1, -1), "void", new VoidType(), new LocalScope(currentScope));

        FuncSymbol Length = new FuncSymbol(new position(-1, -1), "length", new IntType(), new LocalScope(string.getScope()));

        FuncSymbol Substring = new FuncSymbol(new position(-1, -1), "substring", new StringType());
        LocalScope SubstringScope = new LocalScope(string.getScope());
        VarSymbol Left = new VarSymbol(Substring.getPos(), "left", new IntType(), SubstringScope);
        VarSymbol Right = new VarSymbol(Substring.getPos(), "right", new IntType(), SubstringScope);
        SubstringScope.registerPara(Left);
        SubstringScope.registerPara(Right);
        Substring.setScope(SubstringScope);

        FuncSymbol ParseInt = new FuncSymbol(new position(-1, -1), "parseInt", new IntType(), new LocalScope(string.getScope()));

        FuncSymbol Ord = new FuncSymbol(new position(-1, -1), "ord", new IntType());
        LocalScope OrdScope = new LocalScope(string.getScope());
        VarSymbol Pos = new VarSymbol(Ord.getPos(), "pos", new IntType(), OrdScope);
        OrdScope.registerPara(Pos);
        Ord.setScope(OrdScope);

        LocalScope StringScope = new LocalScope(currentScope);
        StringScope.registerFunc(Length);
        StringScope.registerFunc(Substring);
        StringScope.registerFunc(ParseInt);
        StringScope.registerFunc(Ord);
        string.setScope(StringScope);

        currentScope.registerClass(Int);
        currentScope.registerClass(Bool);
        currentScope.registerClass(string);
        currentScope.registerClass(Void);

        FuncSymbol Print = new FuncSymbol(new position(-1, -1), "print", new VoidType());
        LocalScope PrintScope = new LocalScope(currentScope);
        VarSymbol StrPrint = new VarSymbol(Print.getPos(), "str", new StringType(), PrintScope);
        PrintScope.registerPara(StrPrint);
        Print.setScope(PrintScope);
        currentScope.registerFunc(Print);

        FuncSymbol Println = new FuncSymbol(new position(-1, -1), "println", new VoidType());
        LocalScope PrintlnScope = new LocalScope(currentScope);
        VarSymbol StrPrintln = new VarSymbol(Println.getPos(), "str", new StringType(), PrintlnScope);
        PrintlnScope.registerPara(StrPrintln);
        Println.setScope(PrintlnScope);
        currentScope.registerFunc(Println);

        FuncSymbol PrintInt = new FuncSymbol(new position(-1, -1), "printInt", new VoidType());
        LocalScope PrintIntScope = new LocalScope(currentScope);
        VarSymbol NPrintInt = new VarSymbol(PrintInt.getPos(), "n", new IntType(), PrintIntScope);
        PrintIntScope.registerPara(NPrintInt);
        PrintInt.setScope(PrintIntScope);
        currentScope.registerFunc(PrintInt);

        FuncSymbol PrintlnInt = new FuncSymbol(new position(-1, -1), "printlnInt", new VoidType());
        LocalScope PrintlnIntScope = new LocalScope(currentScope);
        VarSymbol NPrintlnInt = new VarSymbol(PrintInt.getPos(), "n", new IntType(), PrintlnIntScope);
        PrintlnIntScope.registerPara(NPrintlnInt);
        PrintlnInt.setScope(PrintlnIntScope);
        currentScope.registerFunc(PrintlnInt);

        FuncSymbol GetString = new FuncSymbol(new position(-1, -1), "getString", new StringType(), new LocalScope(currentScope));
        currentScope.registerFunc(GetString);

        FuncSymbol GetInt = new FuncSymbol(new position(-1, -1), "getInt", new IntType(), new LocalScope(currentScope));
        currentScope.registerFunc(GetInt);

        FuncSymbol ToString = new FuncSymbol(new position(-1, -1), "toString", new StringType());
        LocalScope ToStringScope = new LocalScope(currentScope);
        VarSymbol IToString = new VarSymbol(ToString.getPos(), "i", new IntType(), ToStringScope);
        ToStringScope.registerPara(IToString);
        ToString.setScope(ToStringScope);
        currentScope.registerFunc(ToString);

        FuncSymbol ArraySize = new FuncSymbol(new position(-1, -1), "*size*", new IntType(), new LocalScope(currentScope));
        currentScope.registerFunc(ArraySize);

        currentClass = null;
        currentFunc = null;
        currentLoop = null;
    }

    @Override
    public void visit(RootNode node) { //cope with forward reference first
        boolean CheckMain = false;
        node.setScope(currentScope);
        for(ASTNode tmp: node.getDefinition()){
            if(tmp instanceof ClassdefNode){
                if(((ClassdefNode) tmp).getIdentifier().equals("main")){
                    throw new ErrorMessage("main cannot be the class name", node.getPos());
                }
                ClassSymbol classSymbol = new ClassSymbol(tmp.getPos(), ((ClassdefNode) tmp).getIdentifier(), new ClassType(((ClassdefNode) tmp).getIdentifier()), new LocalScope(currentScope));
                currentScope.registerClass(classSymbol);
                ((ClassdefNode) tmp).setSymbol(classSymbol);
                tmp.setScope(classSymbol.getScope());
            }
        }
        for(ASTNode tmp: node.getDefinition()){
            if(tmp instanceof ClassdefNode){
                ClassSymbol classSymbol = (ClassSymbol) ((ClassdefNode)tmp).getSymbol();
                currentScope = classSymbol.getScope();
                ((ClassdefNode) tmp).getVarList().forEach(x-> x.accept(this));
                for (FundefNode func : ((ClassdefNode) tmp).getFuncList()){
                    FuncSymbol funcSymbol = new FuncSymbol(func.getPos(), func.getIdentifier(), null, func);
                    String baseType = func.getReturnType().getType();
                    int dim = func.getReturnType().getDim();
                    Type type = currentScope.findClassSymbol(baseType, func.getPos()).getType();
                    if(dim == 0){
                        funcSymbol.setType(type);
                    }
                    else{
                        ArrayType arrayType = new ArrayType(type, dim);
                        funcSymbol.setType(arrayType);
                    }
                    funcSymbol.setScope(new LocalScope(currentScope));
                    currentScope.registerFunc(funcSymbol);
                    func.setSymbol(funcSymbol);
                    func.setScope(funcSymbol.getScope());
                    currentScope = func.getScope();
                    func.getParameterList().forEach(x->x.accept(this)); //visit parameters first
                    currentScope = func.getScope().getParent();
                    if(func.getIdentifier().equals(((ClassdefNode) tmp).getIdentifier())){
                        throw new ErrorMessage("fake constructor ERROR", node.getPos());
                    }
                }
                if (((ClassdefNode) tmp).getConstructor() != null){
                    FundefNode func = ((ClassdefNode) tmp).getConstructor();
                    func.setConstructor(true);
                    if(!((ClassdefNode) tmp).getConstructor().getIdentifier().equals(((ClassdefNode) tmp).getIdentifier())){
                        throw new ErrorMessage("constructor name ERROR", node.getPos());
                    }
                    FuncSymbol funcSymbol = new FuncSymbol(func.getPos(), func.getIdentifier(), null, func);
                    classSymbol.setConstructor(funcSymbol);
                    String baseType = func.getReturnType().getType();
                    int dim = func.getReturnType().getDim();
                    Type type = currentScope.findClassSymbol(baseType, func.getPos()).getType();
                    if(dim == 0){
                        funcSymbol.setType(type);
                    }
                    else{
                        ArrayType arrayType = new ArrayType(type, dim);
                        funcSymbol.setType(arrayType);
                    }
                    funcSymbol.setScope(new LocalScope(currentScope));
                    ((LocalScope) currentScope).registerClassConstructor(funcSymbol);
                    func.setSymbol(funcSymbol);
                    func.setScope(funcSymbol.getScope());
                    currentScope = func.getScope();
                    func.getParameterList().forEach(x->x.accept(this)); //visit parameters first
                    currentScope = func.getScope().getParent();
                }
                currentScope = currentScope.getParent();
            }
        }
        for(ASTNode tmp: node.getDefinition()){
            if(tmp instanceof FundefNode){
                FuncSymbol funcSymbol = new FuncSymbol(tmp.getPos(), ((FundefNode) tmp).getIdentifier(), null, (FundefNode) tmp);
                String baseType = ((FundefNode) tmp).getReturnType().getType();
                int dim = ((FundefNode) tmp).getReturnType().getDim();
                Type type = currentScope.findClassSymbol(baseType, tmp.getPos()).getType();
                if(dim == 0){
                    funcSymbol.setType(type);
                }
                else{
                    ArrayType arrayType = new ArrayType(type, dim);
                    funcSymbol.setType(arrayType);
                }
                funcSymbol.setScope(new LocalScope(currentScope));
                currentScope.registerFunc(funcSymbol);
                ((FundefNode) tmp).setSymbol(funcSymbol);
                if(((FundefNode) tmp).getIdentifier().equals("main")){
                    if(!(funcSymbol.getType() instanceof IntType)){
                        throw new ErrorMessage("Main Func Type ERROR", node.getPos());
                    }
                    if(!(((FundefNode) tmp).getParameterList().isEmpty())){
                        throw new ErrorMessage("Main Func Para ERROR", node.getPos());
                    }
                    CheckMain = true;
                }
                tmp.setScope(funcSymbol.getScope());
                currentScope = tmp.getScope();
                ((FundefNode) tmp).getParameterList().forEach(x->x.accept(this)); //visit parameters first
                currentScope = tmp.getScope().getParent();
            }
        }
        if(!CheckMain) throw new ErrorMessage("No Main Func", node.getPos());
        Scope globalScope = currentScope;
        for(ASTNode tmp: node.getDefinition()){
            tmp.accept(this);
            currentScope = globalScope;
            currentFunc = null;
            currentClass = null;
            currentLoop = null;
        }
    }

    @Override
    public void visit(TypeNode node) {
        throw new ErrorMessage("TypeNode ERROR", node.getPos());
    }

    @Override
    public void visit(SuiteNode node) {
        LocalScope localScope = new LocalScope(currentScope);
        currentScope = localScope;
        node.setScope(currentScope);
        for (StatementNode statement: node.getStatementList()){
            statement.accept(this);
            currentScope = localScope;
        }
    }

    @Override
    public void visit(FundefNode node) {
        FuncSymbol funcSymbol = (FuncSymbol) node.getSymbol();
        currentFunc = funcSymbol;
        currentScope = funcSymbol.getScope();
        node.getSuite().accept(this);
        currentScope = funcSymbol.getScope();
        if(node.isConstructor() && node.ReturnExistence() == 2){
            throw new ErrorMessage("Constructor has no Return", node.getPos());
        }
        else if(node.getIdentifier().equals("main") && !(currentScope.getParent() instanceof GlobalScope)){
            throw new ErrorMessage("Main cannot be a class function", node.getPos());
        }
        else if(!node.isConstructor() && node.ReturnExistence() == 0 && !node.getIdentifier().equals("main") && !(node.getSymbol().getType() instanceof VoidType)){
            throw new ErrorMessage("simple function without Return ERROR", node.getPos());
        }
    }

    @Override
    public void visit(VardefNode node) {
        String baseType = node.getType().getType();
        Type type;
        int dim = node.getType().getDim();
        if(dim == 0){
            type = currentScope.findClassSymbol(baseType, node.getPos()).getType();
        }
        else{
            type = new ArrayType(currentScope.findClassSymbol(baseType, node.getPos()).getType(), dim);
        }
        if(node.getExpression() != null){
            node.getExpression().accept(this);
            type.checkAssignment(node.getExpression().getType(), node.getPos());
        }
        VarSymbol varSymbol = new VarSymbol(node.getPos(), node.getIdentifier(), type, currentScope);
        if(currentScope instanceof GlobalScope){
            currentScope.registerVar(varSymbol);
        }
        else ((LocalScope) currentScope).registerPara(varSymbol);
        node.setScope(currentScope);
        node.setSymbol(varSymbol);
    }

    @Override
    public void visit(NewexprNode node) {
        node.setScope(currentScope);
        node.getNewtype().accept(this);
        String basetype = node.getNewtype().getBasetype().getType();
        int dim = node.getNewtype().getBasetype().getDim();
        ClassSymbol classSymbol = currentScope.findClassSymbol(basetype, node.getPos());
        Type type = classSymbol.getType();
        if(dim == 0){
            node.setType(type);
            if(type instanceof ClassType){
                if(classSymbol.getConstructor() != null){
                    node.setFuncSymbol(classSymbol.getConstructor());
                }
            }
        }
        else{
            node.setType(new ArrayType(type, dim));
        }
        node.setExprType(ExprNode.ExprType.RVALUE);
    }

    @Override
    public void visit(NewtypeNode node) {
        node.setScope(currentScope);
        for (ExprNode exprNode : node.getKnown()){
            exprNode.accept(this);
            IntType intType = new IntType();
            intType.checkAssignment(exprNode.getType(), node.getPos());
            exprNode.isValue();
        }
    }

    @Override
    public void visit(ClassdefNode node) {
        ClassSymbol classSymbol = (ClassSymbol) node.getSymbol();
        currentClass = classSymbol;
        currentScope = classSymbol.getScope();
    //    node.getVarList().forEach(x-> x.accept(this));
    /*    for (FundefNode func : node.getFuncList()){
            FuncSymbol funcSymbol = new FuncSymbol(func.getPos(), func.getIdentifier(), null, func);
            String baseType = func.getReturnType().getType();
            int dim = func.getReturnType().getDim();
            Type type = currentScope.findClassSymbol(baseType, func.getPos()).getType();
            if(dim == 0){
                funcSymbol.setType(type);
            }
            else{
                ArrayType arrayType = new ArrayType(type, dim);
                funcSymbol.setType(arrayType);
            }
            funcSymbol.setScope(new LocalScope(currentScope));
            currentScope.registerFunc(funcSymbol);
            func.setSymbol(funcSymbol);
            func.setScope(funcSymbol.getScope());
            currentScope = func.getScope();
            func.getParameterList().forEach(x->x.accept(this)); //visit parameters first
            currentScope = func.getScope().getParent();
            if(func.getIdentifier().equals(node.getIdentifier())){
                throw new ErrorMessage("fake constructor ERROR", node.getPos());
            }
        }
        if (node.getConstructor() != null){
            FundefNode func = node.getConstructor();
            func.setConstructor(true);
            if(!node.getConstructor().getIdentifier().equals(node.getIdentifier())){
                throw new ErrorMessage("constructor name ERROR", node.getPos());
            }
            FuncSymbol funcSymbol = new FuncSymbol(func.getPos(), func.getIdentifier(), null, func);
            classSymbol.setConstructor(funcSymbol);
            String baseType = func.getReturnType().getType();
            int dim = func.getReturnType().getDim();
            Type type = currentScope.findClassSymbol(baseType, func.getPos()).getType();
            if(dim == 0){
                funcSymbol.setType(type);
            }
            else{
                ArrayType arrayType = new ArrayType(type, dim);
                funcSymbol.setType(arrayType);
            }
            funcSymbol.setScope(new LocalScope(currentScope));
            ((LocalScope) currentScope).registerClassConstructor(funcSymbol);
            func.setSymbol(funcSymbol);
            func.setScope(funcSymbol.getScope());
            currentScope = func.getScope();
            func.getParameterList().forEach(x->x.accept(this)); //visit parameters first
            currentScope = func.getScope().getParent();
        }*/
        for(FundefNode func : node.getFuncList()){
            func.accept(this);
            currentScope = func.getScope().getParent();
            currentFunc = null;
        }
        if(node.getConstructor() != null) {
            node.getConstructor().accept(this);
            currentScope = node.getConstructor().getScope().getParent();
            currentFunc = null;
        }
    }

    @Override
    public void visit(FuncexprNode node) {
        node.setScope(currentScope);
        node.getExpression().accept(this);
        if(!(node.getExpression().getExprType() == ExprNode.ExprType.FUNCTION)){
            throw new ErrorMessage("FuncExprNode Expression ERROR", node.getPos());
        }
        else{
            ExprNode expression = node.getExpression();
            FuncSymbol funcSymbol;
            if(expression instanceof IdentifierNode){
                funcSymbol = (FuncSymbol) ((IdentifierNode) expression).getSymbol();
            }
            else if(expression instanceof MemberexprNode){
                funcSymbol = (FuncSymbol) ((MemberexprNode) expression).getSymbol();
            }
            else throw new ErrorMessage("FuncExprNode Expression Instance ERROR", node.getPos());
            node.setFuncSymbol(funcSymbol);
            if (((LocalScope) funcSymbol.getScope()).getVarSymbolList().size() != node.getParameterList().size()){
                throw new ErrorMessage("FuncExprNode ParameterList Size ERROR", node.getPos());
            }
            else{
                for(int i = 0; i < node.getParameterList().size(); ++i){
                    node.getParameterList().get(i).accept(this);
                    node.getParameterList().get(i).isValue();
                    ((LocalScope) funcSymbol.getScope()).getVarSymbolList().get(i).getType().checkAssignment(node.getParameterList().get(i).getType(), node.getPos());
                }
                node.setType(funcSymbol.getType());
                node.setExprType(ExprNode.ExprType.RVALUE);
            }
        }
    }

    @Override
    public void visit(BinaryexprNode node) {
        node.setScope(currentScope);
        ExprNode lhs = node.getLhs();
        ExprNode rhs = node.getRhs();
        lhs.accept(this);
        rhs.accept(this);
        lhs.isValue();
        rhs.isValue();
        BinaryexprNode.BinaryOpType op = node.getOp();
        if(op == BinaryexprNode.BinaryOpType.MinusBinary
                || op == BinaryexprNode.BinaryOpType.Mul || op == BinaryexprNode.BinaryOpType.Div || op == BinaryexprNode.BinaryOpType.Mod
                || op == BinaryexprNode.BinaryOpType.LeftShift || op == BinaryexprNode.BinaryOpType.RightShift
                || op == BinaryexprNode.BinaryOpType.AndAri || op == BinaryexprNode.BinaryOpType.OrAri || op == BinaryexprNode.BinaryOpType.XorAri){
            IntType intType = new IntType();
            intType.checkAssignment(lhs.getType(), node.getPos());
            intType.checkAssignment(rhs.getType(), node.getPos());
            node.setType(intType);
            node.setExprType(ExprNode.ExprType.RVALUE);
        }
        else if(op == BinaryexprNode.BinaryOpType.AndLogic || op == BinaryexprNode.BinaryOpType.OrLogic){
            BoolType boolType = new BoolType();
            boolType.checkAssignment(lhs.getType(), node.getPos());
            boolType.checkAssignment(rhs.getType(), node.getPos());
            node.setType(boolType);
            node.setExprType(ExprNode.ExprType.RVALUE);
        }
        else if(op == BinaryexprNode.BinaryOpType.AddBinary){
            if(lhs.getType() instanceof IntType && rhs.getType() instanceof IntType){
                node.setType(new IntType());
                node.setExprType(ExprNode.ExprType.RVALUE);
            }
            else if(lhs.getType() instanceof StringType && rhs.getType() instanceof StringType){
                node.setType(new StringType());
                node.setExprType(ExprNode.ExprType.RVALUE);
            }
            else throw new ErrorMessage("BinaryExprNode AddBinary ERROR", node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.Less || op == BinaryexprNode.BinaryOpType.LessEqual
                || op == BinaryexprNode.BinaryOpType.Greater || op == BinaryexprNode.BinaryOpType.GreaterEqual){
            if(lhs.getType() instanceof IntType && rhs.getType() instanceof IntType){
                node.setType(new BoolType());
                node.setExprType(ExprNode.ExprType.RVALUE);
            }
            else if(lhs.getType() instanceof StringType && rhs.getType() instanceof StringType){
                node.setType(new BoolType());
                node.setExprType(ExprNode.ExprType.RVALUE);
            }
            else throw new ErrorMessage("BinaryExprNode Comparison ERROR", node.getPos());
        }
        else if(op == BinaryexprNode.BinaryOpType.Equal || op == BinaryexprNode.BinaryOpType.NotEqual){
            lhs.getType().checkEquality(rhs.getType(), node.getPos());
            node.setType(new BoolType());
            node.setExprType(ExprNode.ExprType.RVALUE);
        }
        else if(op == BinaryexprNode.BinaryOpType.Assign){
            if(lhs.getExprType() != ExprNode.ExprType.LVALUE){
                throw new ErrorMessage("BinaryExprNode LVALUE ERROR", node.getPos());
            }
            else{
                lhs.getType().checkAssignment(rhs.getType(), node.getPos());
                node.setType(lhs.getType());
                node.setExprType(ExprNode.ExprType.LVALUE);
            }
        }
        else throw new ErrorMessage("BinaryExprNode Op ERROR", node.getPos());
    }

    @Override
    public void visit(IdentifierNode node) {
        node.setScope(currentScope);
        Symbol symbol = currentScope.findSymbol(node.getIdentifier(), node.getPos());
        node.setSymbol(symbol);
        if(symbol instanceof VarSymbol){
            node.setType(symbol.getType());
            node.setExprType(ExprNode.ExprType.LVALUE);
        }
        else if(symbol instanceof FuncSymbol){
            node.setType(symbol.getType());
            node.setExprType(ExprNode.ExprType.FUNCTION);
        }
        else if(symbol instanceof ClassSymbol){
            node.setType(new ClassdefType());
            node.setExprType(ExprNode.ExprType.CLASS);
        }
        else throw new ErrorMessage("IdentifierNode Error", node.getPos());
    }

    @Override
    public void visit(MemberexprNode node) {
        node.setScope(currentScope);
        node.getExpression().accept(this);
        if((node.getExpression().getExprType() == ExprNode.ExprType.LVALUE || node.getExpression().getExprType() == ExprNode.ExprType.RVALUE)
                && (node.getExpression().getType() instanceof ClassType || node.getExpression().getType() instanceof StringType)){
            ClassSymbol classSymbol = currentScope.findClassSymbol(node.getExpression().getType().getType(), node.getPos());
            Symbol symbol = ((LocalScope)(classSymbol.getScope())).findSymbolLocal(node.getIdentifier(), node.getPos());
            if(symbol instanceof FuncSymbol){
                node.setExprType(ExprNode.ExprType.FUNCTION);
                node.setType(symbol.getType());
                node.setSymbol(symbol);
            }
            else if(symbol instanceof VarSymbol){
                node.setExprType(ExprNode.ExprType.LVALUE);
                node.setType(symbol.getType());
                node.setSymbol(symbol);
            }
            else throw new ErrorMessage("MemberExprNode Class ERROR", node.getPos());
        }
        else if((node.getExpression().getExprType() == ExprNode.ExprType.LVALUE || node.getExpression().getExprType() == ExprNode.ExprType.RVALUE)
                && node.getExpression().getType() instanceof ArrayType){
            if(node.getIdentifier().equals("size")){
                node.setType(new IntType());
                node.setExprType(ExprNode.ExprType.FUNCTION);
                node.setSymbol(currentScope.findSymbol("*size*", node.getPos()));
            }
            else throw new ErrorMessage("MemberExprNode Array ERROR", node.getPos());
        }
        else throw new ErrorMessage("MemberExprNode ERROR", node.getPos());
    }

    @Override
    public void visit(ThisexprNode node) {
        if(currentClass == null){
            throw new ErrorMessage("ThisExprNode ERROR", node.getPos());
        }
        node.setScope(currentScope);
        node.setSymbol(currentClass);
        node.setType(currentClass.getType());
        node.setExprType(ExprNode.ExprType.RVALUE);
    }

    @Override
    public void visit(PrefixexprNode node) {
        node.setScope(currentScope);
        node.getExpression().accept(this);
        node.getExpression().isValue();
        PrefixexprNode.PrefixOpType op = node.getOp();
        if(op == PrefixexprNode.PrefixOpType.Add || op == PrefixexprNode.PrefixOpType.Minus || op == PrefixexprNode.PrefixOpType.NotAri){
            IntType intType = new IntType();
            intType.checkAssignment(node.getExpression().getType(), node.getPos());
            node.setType(intType);
            node.setExprType(ExprNode.ExprType.RVALUE);
        }
        else if(op == PrefixexprNode.PrefixOpType.AddAdd || op == PrefixexprNode.PrefixOpType.MinusMinus){
            if(node.getExpression().getExprType() != ExprNode.ExprType.LVALUE){
                throw new ErrorMessage("PrefixExprNode LVALUE ERROR", node.getPos());
            }
            IntType intType = new IntType();
            intType.checkAssignment(node.getExpression().getType(), node.getPos());
            node.setType(intType);
            node.setExprType(ExprNode.ExprType.LVALUE); // not sure
        }
        else if(op == PrefixexprNode.PrefixOpType.NotLogic){
            BoolType boolType = new BoolType();
            boolType.checkAssignment(node.getExpression().getType(), node.getPos());
            node.setType(boolType);
            node.setExprType(ExprNode.ExprType.RVALUE);
        }
        else throw new ErrorMessage("PrefixExprNode Op ERROR", node.getPos());
    }

    @Override
    public void visit(SuffixexprNode node) {
        node.setScope(currentScope);
        node.getExpression().accept(this);
        node.getExpression().isValue();
        if(node.getExpression().getExprType() != ExprNode.ExprType.LVALUE){
            throw new ErrorMessage("SuffixExprNode LVALUE ERROR", node.getPos());
        }
        IntType intType = new IntType();
        intType.checkAssignment(node.getExpression().getType(), node.getPos());
        node.setType(intType);
        node.setExprType(ExprNode.ExprType.RVALUE);
    }

    @Override
    public void visit(VardefListNode node) {
        node.getVarList().forEach(x-> x.accept(this));
        node.setScope(currentScope);
    }

    @Override
    public void visit(BoolliteralNode node) {
        node.setType(new BoolType());
        node.setExprType(ExprNode.ExprType.RVALUE);
        node.setScope(currentScope);
    }

    @Override
    public void visit(IfstatementNode node) {
        node.setScope(currentScope);
        node.getCondition().accept(this);
        BoolType boolType = new BoolType();
        boolType.checkAssignment(node.getCondition().getType(), node.getPos());
        node.getTrueStat().accept(this);
        if(node.getFalseStat() != null){
            node.getFalseStat().accept(this);
        }
    }

    @Override
    public void visit(NullliteralNode node) {
        node.setType(new NullType());
        node.setExprType(ExprNode.ExprType.RVALUE);
        node.setScope(currentScope);
    }

    @Override
    public void visit(ForstatementNode node) {
        StatementNode upLoop = currentLoop;
        currentLoop = node;
        if(node.getInitDef() != null){
            LocalScope localScope = new LocalScope(currentScope);
            currentScope = localScope;
            node.setScope(currentScope);
            node.getInitDef().accept(this);
            if(node.getCondition() != null){
                node.getCondition().accept(this);
                BoolType boolType = new BoolType();
                boolType.checkAssignment(node.getCondition().getType(), node.getPos());
            }
            else{
                node.setCondition(new BoolliteralNode(node.getPos(), true));
            }
            if(node.getIncr() != null){
                node.getIncr().accept(this);
            }
            node.getBlock().accept(this);
            currentScope = localScope;
        }
        else{
            if(node.getInitExpr() != null){
                node.getInitExpr().accept(this);
            }
            if(node.getCondition() != null){
                node.getCondition().accept(this);
                BoolType boolType = new BoolType();
                boolType.checkAssignment(node.getCondition().getType(), node.getPos());
            }
            else{
                node.setCondition(new BoolliteralNode(node.getPos(), true));
            }
            if(node.getIncr() != null){
                node.getIncr().accept(this);
            }
            node.getBlock().accept(this);
        }
        currentLoop = upLoop;
    }

    @Override
    public void visit(SubarrayexprNode node) {
        node.setScope(currentScope);
        node.getIdentifier().accept(this);
        node.getIndex().accept(this);

        if(!(node.getIdentifier().getType() instanceof ArrayType)){
            throw new ErrorMessage("SubArrayExprNode Identifier ERROR", node.getPos());
        }
        if(!(node.getIndex().getType() instanceof  IntType)){
            throw new ErrorMessage("SubArrayExprNode Index ERROR", node.getPos());
        }
        node.getIdentifier().isValue();
        node.getIndex().isValue();
        node.setExprType(ExprNode.ExprType.LVALUE);
        ArrayType arrayType = (ArrayType) node.getIdentifier().getType();
        if(arrayType.getDim() == 1){
            node.setType(arrayType.getBaseType());
        }
        else{
            node.setType(new ArrayType(arrayType.getBaseType(), arrayType.getDim() - 1));
        }
    }

    @Override
    public void visit(ExprstatementNode node) {
        node.getExpression().accept(this);
        node.setScope(currentScope);
    }

    @Override
    public void visit(StringliteralNode node) {
        node.setType(new StringType());
        node.setExprType(ExprNode.ExprType.RVALUE);
        node.setScope(currentScope);
    }

    @Override
    public void visit(BreakstatementNode node) {
        node.setScope(currentScope);
        if(currentLoop == null){
            throw new ErrorMessage("BreakStatementNode ERROR", node.getPos());
        }
        node.setJumpto(currentLoop);
    }

    @Override
    public void visit(EmptystatementNode node) {
        node.setScope(currentScope);
    }

    @Override
    public void visit(IntegerliteralNode node) {
        node.setType(new IntType());
        node.setExprType(ExprNode.ExprType.RVALUE);
        node.setScope(currentScope);
    }

    @Override
    public void visit(WhilestatementNode node) {
        node.setScope(currentScope);
        StatementNode tmp = currentLoop;
        currentLoop = node;
        node.getCondition().accept(this);
        BoolType boolType = new BoolType();
        boolType.checkAssignment(node.getCondition().getType(), node.getPos());
        node.getBlock().accept(this);
        currentLoop = tmp;
    }

    @Override
    public void visit(ReturnstatementNode node) {
        node.setScope(currentScope);
        if(currentFunc == null){
            throw new ErrorMessage("ReturnStatementNode Func ERROR", node.getPos());
        }
        node.setSymbol(currentFunc);
        if(node.getReturnVal() == null){
            if(!(currentFunc.getType() instanceof VoidType)){
                throw new ErrorMessage("ReturnStatementNode Void ReturnType ERROR", node.getPos());
            }
            ((FundefNode) currentFunc.getDefinition()).setReturnExistence(1);
        }
        else{
            if(currentFunc.getType() instanceof VoidType){
                throw new ErrorMessage("ReturnStatementNode NotVoid ReturnType ERROR", node.getPos());
            }
            node.getReturnVal().accept(this);
            currentFunc.getType().checkAssignment(node.getReturnVal().getType(), node.getPos());
            ((FundefNode) currentFunc.getDefinition()).setReturnExistence(2);
        }
    }

    @Override
    public void visit(VardefstatementNode node) {
        node.setScope(currentScope);
        node.getVarList().accept(this);
    }

    @Override
    public void visit(ContinuestatementNode node) {
        node.setScope(currentScope);
        if(currentLoop == null){
            throw new ErrorMessage("ContinueStatementNode ERROR", node.getPos());
        }
        node.setJumpto(currentLoop);
    }
}

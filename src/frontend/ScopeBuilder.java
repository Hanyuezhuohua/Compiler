package frontend;

import AST.*;
import Util.position;
import Util.scope.GlobalScope;
import Util.scope.LocalScope;
import Util.scope.Scope;
import Util.symbol.ClassSymbol;
import Util.symbol.FuncSymbol;
import Util.symbol.VarSymbol;
import Util.type.*;

public class ScopeBuilder implements ASTVisitor {
    private Scope currentScope;
    private ClassSymbol currentClass;
    private FuncSymbol currentFunc;
    private StatementNode currentLoop;

    public ScopeBuilder(){
        currentScope = new GlobalScope();

        ClassSymbol Int = new ClassSymbol(new position(-1, -1), "int", new BaseType(), new LocalScope(currentScope));
        ClassSymbol Bool = new ClassSymbol(new position(-1, -1), "bool", new BaseType(), new LocalScope(currentScope));
        ClassSymbol string = new ClassSymbol(new position(-1, -1), "string", new BaseType());
        ClassSymbol Void = new ClassSymbol(new position(-1, -1), "void", new BaseType(), new LocalScope(currentScope));

        FuncSymbol Length = new FuncSymbol(new position(-1, -1), "length", new IntType(), new LocalScope(string.getScope()));

        FuncSymbol Substring = new FuncSymbol(new position(-1, -1), "substring", new StringType());
        LocalScope SubstringScope = new LocalScope(string.getScope());
        VarSymbol Left = new VarSymbol(Substring.getPos(), "left", new IntType(), new LocalScope(SubstringScope));
        VarSymbol Right = new VarSymbol(Substring.getPos(), "right", new IntType(), new LocalScope(SubstringScope));
        SubstringScope.registerVar(Left);
        SubstringScope.registerVar(Right);
        Substring.setScope(SubstringScope);

        FuncSymbol ParseInt = new FuncSymbol(new position(-1, -1), "parseInt", new IntType(), new LocalScope(string.getScope()));

        FuncSymbol Ord = new FuncSymbol(new position(-1, -1), "ord", new IntType());
        LocalScope OrdScope = new LocalScope(string.getScope());
        VarSymbol Pos = new VarSymbol(Ord.getPos(), "pos", new IntType(), new LocalScope(OrdScope));
        OrdScope.registerVar(Pos);
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
        VarSymbol StrPrint = new VarSymbol(Print.getPos(), "str", new StringType(), new LocalScope(PrintScope));
        PrintScope.registerVar(StrPrint);
        Print.setScope(PrintScope);
        currentScope.registerFunc(Print);

        FuncSymbol Println = new FuncSymbol(new position(-1, -1), "println", new VoidType());
        LocalScope PrintlnScope = new LocalScope(currentScope);
        VarSymbol StrPrintln = new VarSymbol(Println.getPos(), "str", new StringType(), new LocalScope(PrintlnScope));
        PrintlnScope.registerVar(StrPrintln);
        Println.setScope(PrintlnScope);
        currentScope.registerFunc(Println);

        FuncSymbol PrintInt = new FuncSymbol(new position(-1, -1), "printInt", new VoidType());
        LocalScope PrintIntScope = new LocalScope(currentScope);
        VarSymbol NPrintInt = new VarSymbol(PrintInt.getPos(), "n", new IntType(), new LocalScope(PrintIntScope));
        PrintIntScope.registerVar(NPrintInt);
        PrintInt.setScope(PrintlnScope);
        currentScope.registerFunc(PrintInt);

        FuncSymbol PrintlnInt = new FuncSymbol(new position(-1, -1), "printlnInt", new VoidType());
        LocalScope PrintlnIntScope = new LocalScope(currentScope);
        VarSymbol NPrintlnInt = new VarSymbol(PrintInt.getPos(), "n", new IntType(), new LocalScope(PrintlnIntScope));
        PrintlnIntScope.registerVar(NPrintlnInt);
        PrintlnInt.setScope(PrintlnIntScope);
        currentScope.registerFunc(PrintlnInt);

        FuncSymbol GetString = new FuncSymbol(new position(-1, -1), "getString", new StringType(), new LocalScope(currentScope));
        currentScope.registerFunc(GetString);

        FuncSymbol GetInt = new FuncSymbol(new position(-1, -1), "getInt", new IntType(), new LocalScope(currentScope));
        currentScope.registerFunc(GetInt);

        FuncSymbol ToString = new FuncSymbol(new position(-1, -1), "toString", new StringType());
        LocalScope ToStringScope = new LocalScope(currentScope);
        VarSymbol IToString = new VarSymbol(ToString.getPos(), "i", new IntType(), new LocalScope(ToStringScope));
        ToStringScope.registerVar(IToString);
        ToString.setScope(ToStringScope);
        currentScope.registerFunc(ToString);

        currentClass = null;
        currentFunc = null;
        currentLoop = null;
    }

    @Override
    public void visit(ExprNode node) {

    }

    @Override
    public void visit(RootNode node) {

    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(SuiteNode node) {

    }

    @Override
    public void visit(FundefNode node) {

    }

    @Override
    public void visit(VardefNode node) {

    }

    @Override
    public void visit(NewexprNode node) {

    }

    @Override
    public void visit(NewtypeNode node) {

    }

    @Override
    public void visit(ClassdefNode node) {

    }

    @Override
    public void visit(FuncexprNode node) {

    }

    @Override
    public void visit(StatementNode node) {

    }

    @Override
    public void visit(BinaryexprNode node) {

    }

    @Override
    public void visit(IdentifierNode node) {

    }

    @Override
    public void visit(MemberexprNode node) {

    }

    @Override
    public void visit(PrefixexprNode node) {

    }

    @Override
    public void visit(SuffixexprNode node) {

    }

    @Override
    public void visit(VardefListNode node) {

    }

    @Override
    public void visit(BoolliteralNode node) {

    }

    @Override
    public void visit(IfstatementNode node) {

    }

    @Override
    public void visit(NullliteralNode node) {

    }

    @Override
    public void visit(ForstatementNode node) {

    }

    @Override
    public void visit(SubarrayexprNode node) {

    }

    @Override
    public void visit(ExprstatementNode node) {

    }

    @Override
    public void visit(StringliteralNode node) {

    }

    @Override
    public void visit(BreakstatementNode node) {

    }

    @Override
    public void visit(EmptystatementNode node) {

    }

    @Override
    public void visit(IntegerliteralNode node) {

    }

    @Override
    public void visit(WhilestatementNode node) {

    }

    @Override
    public void visit(ReturnstatementNode node) {

    }

    @Override
    public void visit(VardefstatementNode node) {

    }

    @Override
    public void visit(ContinuestatementNode node) {

    }
}

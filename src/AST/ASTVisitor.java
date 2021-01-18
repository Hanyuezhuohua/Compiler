package AST;

public interface ASTVisitor {
    void visit(RootNode node);
    void visit(VardefNode node);
    void visit(VardefListNode node);
    void visit(TypeNode node);
    void visit(NewtypeNode node);
    void visit(FundefNode node);
    void visit(SuiteNode node);
    void visit(ClassdefNode node);
    void visit(BreakstatementNode node);
    void visit(ContinuestatementNode node);
    void visit(EmptystatementNode node);
    void visit(ExprstatementNode node);
    void visit(ForstatementNode node);
    void visit(IfstatementNode node);
    void visit(ReturnstatementNode node);
    void visit(VardefstatementNode node);
    void visit(WhilestatementNode node);
    void visit(NullliteralNode node);
    void visit(BoolliteralNode node);
    void visit(IntegerliteralNode node);
    void visit(StringliteralNode node);
    void visit(IdentifierNode node);
    void visit(BinaryexprNode node);
    void visit(PrefixexprNode node);
    void visit(SuffixexprNode node);
    void visit(MemberexprNode node);
    void visit(FuncexprNode node);
    void visit(SubarrayexprNode node);
    void visit(NewexprNode node);
    void visit(ThisexprNode node);
}

package AST;

public interface ASTVisitor {
    void visit(RootNode node);
    void visit(VardefNode node);
    void visit(VardefListNode node);
    void visit(TypeNode node);
    void visit(ExprNode node);
    void visit(NewtypeNode node);
    void visit(ClassNode node);
}

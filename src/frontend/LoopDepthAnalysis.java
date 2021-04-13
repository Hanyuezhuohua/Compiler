package frontend;

import AST.*;

public class LoopDepthAnalysis implements ASTVisitor {
    private int LoopDepth;
    private int maxLoopDepth;
    private ForstatementNode start;

    public LoopDepthAnalysis(ForstatementNode start){
        LoopDepth = 0;
        maxLoopDepth = 0;
        this.start = start;
    }

    public int getMaxLoopDepth() {
        return maxLoopDepth;
    }

    public int run(){
        start.accept(this);
        return maxLoopDepth;
    }

    @Override
    public void visit(RootNode node) {
        node.getDefinition().forEach(tmp -> tmp.accept(this));
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
        node.getSuite().accept(this);
    }

    @Override
    public void visit(SuiteNode node) {
        node.getStatementList().forEach(stmt -> stmt.accept(this));
    }

    @Override
    public void visit(ClassdefNode node) {
        node.getConstructor().accept(this);
        node.getFuncList().forEach(func -> func.accept(this));
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
        LoopDepth++;
        maxLoopDepth = Integer.max(LoopDepth, maxLoopDepth);
        node.getBlock().accept(this);
        LoopDepth--;
    }

    @Override
    public void visit(IfstatementNode node) {
        node.getTrueStat().accept(this);
        if(node.getFalseStat() != null){
            node.getFalseStat().accept(this);
        }
    }

    @Override
    public void visit(ReturnstatementNode node) {

    }

    @Override
    public void visit(VardefstatementNode node) {

    }

    @Override
    public void visit(WhilestatementNode node) {
        LoopDepth++;
        maxLoopDepth = Integer.max(LoopDepth, maxLoopDepth);
        node.getBlock().accept(this);
        LoopDepth--;
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

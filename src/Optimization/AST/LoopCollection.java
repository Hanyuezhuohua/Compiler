package Optimization.AST;

import AST.*;

import java.util.ArrayList;

public class LoopCollection implements ASTVisitor {
    private int length = 0;
    private ArrayList<ExprNode> nodes;
    public LoopCollection(){
        nodes = new ArrayList<>();
    }

    public ArrayList<ExprNode> getNodes() {
        return nodes;
    }

    @Override
    public void visit(RootNode node) {
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
        node.getStatementList().forEach(stmt -> stmt.accept(this));
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
        node.getCondition().accept(this);
        node.getBlock().accept(this);
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
        if(node.getOp() == BinaryexprNode.BinaryOpType.Assign) return;
        else{
            length++;
            if(length < 3){
                node.getLhs().accept(this);
                node.getRhs().accept(this);
                nodes.add(node);
            }
            length--;
        }
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

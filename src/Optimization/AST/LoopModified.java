package Optimization.AST;

import AST.*;
import Util.symbol.Symbol;
import java.util.HashSet;

public class LoopModified implements ASTVisitor {
    private HashSet<Symbol> modified;
    public LoopModified(){
        modified = new HashSet<>();
    }

    public HashSet<Symbol> getModified() {
        return modified;
    }

    @Override
    public void visit(RootNode node) {
        node.getDefinition().forEach(tmp -> tmp.accept(this));
    }

    @Override
    public void visit(VardefNode node) {
        modified.add(node.getSymbol());
    }

    @Override
    public void visit(VardefListNode node) {
        node.getVarList().forEach(tmp -> tmp.accept(this));
    }

    @Override
    public void visit(TypeNode node) {}

    @Override
    public void visit(NewtypeNode node) {
        node.getKnown().forEach(known -> known.accept(this));
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
        if(node.getConstructor() != null)node.getConstructor().accept(this);
        node.getFuncList().forEach(func -> func.accept(this));
    }

    @Override
    public void visit(BreakstatementNode node) {}

    @Override
    public void visit(ContinuestatementNode node) {}

    @Override
    public void visit(EmptystatementNode node) {}

    @Override
    public void visit(ExprstatementNode node) {
        node.getExpression().accept(this);
    }

    @Override
    public void visit(ForstatementNode node) {
        if(node.getInitExpr() != null) node.getInitExpr().accept(this);
        if(node.getInitDef() != null) node.getInitDef().accept(this);
        node.getCondition().accept(this);
        if(node.getIncr() != null) node.getIncr().accept(this);
        node.getBlock().accept(this);
    }

    @Override
    public void visit(IfstatementNode node) {
        node.getCondition().accept(this);
        node.getTrueStat().accept(this);
        if(node.getFalseStat() != null) node.getFalseStat().accept(this);
    }

    @Override
    public void visit(ReturnstatementNode node) {
        if(node.getReturnVal() != null) node.getReturnVal().accept(this);
    }

    @Override
    public void visit(VardefstatementNode node) {
        node.getVarList().accept(this);
    }

    @Override
    public void visit(WhilestatementNode node) {
        node.getCondition().accept(this);
        node.getBlock().accept(this);
    }

    @Override
    public void visit(NullliteralNode node) {}

    @Override
    public void visit(BoolliteralNode node) {}

    @Override
    public void visit(IntegerliteralNode node) {}

    @Override
    public void visit(StringliteralNode node) {}

    @Override
    public void visit(IdentifierNode node) {
    }

    @Override
    public void visit(BinaryexprNode node) {
        node.getLhs().accept(this);
        node.getRhs().accept(this);
        if(node.getOp() == BinaryexprNode.BinaryOpType.Assign && node.getLhs() instanceof IdentifierNode){
            modified.add(((IdentifierNode) node.getLhs()).getSymbol());
        }
    }

    @Override
    public void visit(PrefixexprNode node) {
        node.getExpression().accept(this);
        if (node.getOp() == PrefixexprNode.PrefixOpType.AddAdd || node.getOp() == PrefixexprNode.PrefixOpType.MinusMinus){
            if(node.getExpression() instanceof IdentifierNode){
                modified.add(((IdentifierNode) node.getExpression()).getSymbol());
            }
        }
    }

    @Override
    public void visit(SuffixexprNode node) {
        node.getExpression().accept(this);
        if(node.getExpression() instanceof IdentifierNode){
            modified.add(((IdentifierNode) node.getExpression()).getSymbol());
        }
    }

    @Override
    public void visit(MemberexprNode node) {
        node.getExpression().accept(this);
    }

    @Override
    public void visit(FuncexprNode node) {
        node.getExpression().accept(this);
        node.getParameterList().forEach(parameter -> parameter.accept(this));
    }

    @Override
    public void visit(SubarrayexprNode node) {
        node.getIdentifier().accept(this);
        node.getIndex().accept(this);
    }

    @Override
    public void visit(NewexprNode node) {
        node.getNewtype().accept(this);
    }

    @Override
    public void visit(ThisexprNode node) {}
}

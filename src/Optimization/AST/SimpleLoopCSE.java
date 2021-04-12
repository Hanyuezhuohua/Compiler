package Optimization.AST;

import AST.*;
import Util.position;
import Util.scope.LocalScope;
import Util.symbol.VarSymbol;
import Util.type.BoolType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SimpleLoopCSE implements ASTVisitor {
    private ArrayList<ExprNode> nodes;
    private boolean modify;
    private HashSet modified;

    public SimpleLoopCSE(){
        nodes = new ArrayList<>();
        modify = false;
        modified = new HashSet();
    }

    @Override
    public void visit(RootNode node) {
        node.getDefinition().forEach(tmp -> tmp.accept(this));
    }

    @Override
    public void visit(VardefNode node){

    }

    @Override
    public void visit(VardefListNode node) {}

    @Override
    public void visit(TypeNode node) {
    }

    @Override
    public void visit(NewtypeNode node) {}

    @Override
    public void visit(FundefNode node) {
        node.getSuite().accept(this);
    }

    @Override
    public void visit(SuiteNode node) {
        if(modify) return;
        int i;
        for(i = 0; i < node.getStatementList().size(); ++i){
            modify = false;
            node.getStatementList().get(i).accept(this);
            if(modify && nodes.get(0).getType() instanceof BoolType){
                break;
            }
        }
        if(modify){
            VarSymbol newSymbol = new VarSymbol(new position(0, 0), "_newFlag", new BoolType(), node.getScope());
            if(node.getScope() instanceof LocalScope) ((LocalScope) node.getScope()).registerPara(newSymbol);
            else node.getScope().registerVar(newSymbol);
            VardefNode flag = new VardefNode("_newFlag", new position(0, 0), false);
            flag.setSymbol(newSymbol);
            ExprNode copy = nodes.get(0).copy(node.getScope());
            flag.setExpression(copy);
            flag.setScope(node.getScope());
            List<VardefNode> varList = new ArrayList<>();
            varList.add(flag);
            VardefListNode list = new VardefListNode(varList, new position(0, 0));
            list.setScope(node.getScope());
            VardefstatementNode newNode = new VardefstatementNode(new position(0, 0), list);
            newNode.setScope(node.getScope());
            node.getStatementList().add(i, newNode);
            nodes.forEach(exprNode -> {
                assert exprNode instanceof BinaryexprNode;
                IdentifierNode identifier = new IdentifierNode(new position(0, 0), "_newFlag");
                identifier.setSymbol(newSymbol);
                identifier.setScope(exprNode.getScope());
                identifier.setType(new BoolType());
                identifier.setExprType(ExprNode.ExprType.LVALUE);
                ((BinaryexprNode) exprNode).setLhs(identifier);
                ((BinaryexprNode) exprNode).setOp(BinaryexprNode.BinaryOpType.Equal);
                BoolliteralNode value = new BoolliteralNode(new position(0, 0), true);
                value.setType(new BoolType());
                value.setScope(exprNode.getScope());
                ((BinaryexprNode) exprNode).setRhs(value);
                System.out.println("WWW");
            });
        }
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
    public void visit(EmptystatementNode node) {

    }

    @Override
    public void visit(ExprstatementNode node) {

    }

    @Override
    public void visit(ForstatementNode node) {
        LoopModified modify = new LoopModified();
        modify.visit(node);
        modified = modify.getModified();
        LoopCollection collection = new LoopCollection();
        collection.visit(node);
        nodes = collection.getNodes();
        for(int i = 0; i < nodes.size(); ++i){
            if(nodes.get(i).modified(modified)){
                nodes.remove(i);
                i--;
            }
        }
        HashSet<ExprNode> visited = new HashSet<>();
        for(int i = 0; i < nodes.size(); ++i){
            if(visited.contains(nodes.get(i))) continue;
            else{
                visited.add(nodes.get(i));
                ArrayList<ExprNode> same = new ArrayList<>();
                same.add(nodes.get(i));
                for(int j = i + 1; j < nodes.size(); ++j){
                    if(nodes.get(j).isSame(nodes.get(i))){
                        visited.add(nodes.get(j));
                        same.add(nodes.get(j));
                    }
                }
                if(same.size() > 5){
                    nodes = same;
                    this.modify = true;
                    return;
                }
            }
        }
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

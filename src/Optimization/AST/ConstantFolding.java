package Optimization.AST;

import AST.*;
import Util.scope.GlobalScope;
import Util.symbol.Symbol;
import Util.type.BoolType;
import Util.type.IntType;
import Util.type.StringType;

import java.util.HashMap;
import java.util.HashSet;

public class ConstantFolding implements ASTVisitor {
    private HashMap<Symbol, ExprNode> VarMap;
    private HashSet<Symbol> ReAssignSet;
    private boolean collectConst;
    private boolean newConst;
    public ConstantFolding(){
        VarMap = new HashMap<>();
        ReAssignSet = new HashSet<>();
        newConst = false;
    }

    @Override
    public void visit(RootNode node) {
        collectConst = true;
        node.getDefinition().forEach(tmp -> tmp.accept(this));
        collectConst = false;
        newConst = false;
        node.getDefinition().forEach(tmp -> tmp.accept(this));
        while (newConst){
            newConst = false;
            node.getDefinition().forEach(tmp -> tmp.accept(this));
        }
    }

    @Override
    public void visit(VardefNode node) {
        if(collectConst){
            Symbol var = node.getSymbol();
            if(var.getScope() instanceof GlobalScope){
                if(node.getExpression() == null){
                    if(var.getType() instanceof BoolType) VarMap.put(var, new BoolliteralNode(node.getPos(), false));
                    else if(var.getType() instanceof IntType) VarMap.put(var, new IntegerliteralNode(node.getPos(), 0));
                    else if(var.getType() instanceof StringType) VarMap.put(var, new StringliteralNode(node.getPos(), ""));
                    else VarMap.put(var, new NullliteralNode(node.getPos()));
                }
                else{
                    node.getExpression().accept(this);
                    if(node.getExpression().isConst()) VarMap.put(var, node.getExpression().getConstant());
                }
            }
        }
        else{
            if(!VarMap.containsKey(node.getSymbol())){
                if(node.getExpression() != null){
                    node.getExpression().accept(this);
                    if(node.getExpression().isConst()){
                        VarMap.put(node.getSymbol(), node.getExpression().getConstant());
                        newConst = true;
                    }
                }
            }
        }
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
        if(!collectConst){
            if(VarMap.containsKey(node.getSymbol()) && !ReAssignSet.contains(node.getSymbol()) && !node.isConst()){
                node.setConstant(VarMap.get(node.getSymbol()));
                newConst = true;
            }
        }
    }

    @Override
    public void visit(BinaryexprNode node) {
        node.getLhs().accept(this);
        node.getRhs().accept(this);
        if(collectConst){
            if(node.getOp() == BinaryexprNode.BinaryOpType.Assign && node.getLhs() instanceof IdentifierNode){
                ReAssignSet.add(((IdentifierNode) node.getLhs()).getSymbol());
            }
        }
        else{
            if(node.getLhs().isConst() && node.getRhs().isConst() && !node.isConst()){
                newConst = true;
                if(node.getOp() == BinaryexprNode.BinaryOpType.AddBinary){
                    if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                        node.setConstant(new IntegerliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() + ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                    }
                    else{
                        node.setConstant(new StringliteralNode(node.getPos(), ((StringliteralNode) node.getLhs().getConstant()).getVal() + ((StringliteralNode) node.getRhs().getConstant()).getVal()));
                    }
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.MinusBinary){
                    node.setConstant(new IntegerliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() - ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.Mul){
                    node.setConstant(new IntegerliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() * ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.Div && ((IntegerliteralNode) node.getRhs().getConstant()).getVal() != 0){
                    node.setConstant(new IntegerliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() / ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.Mod && ((IntegerliteralNode) node.getRhs().getConstant()).getVal() != 0){
                    node.setConstant(new IntegerliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() % ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.LeftShift){
                    node.setConstant(new IntegerliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() << ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.RightShift){
                    node.setConstant(new IntegerliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() >> ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.Less){
                    if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                        node.setConstant(new BoolliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() < ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                    }
                    else{
                        node.setConstant(new BoolliteralNode(node.getPos(), ((StringliteralNode) node.getLhs().getConstant()).getVal().compareTo(((StringliteralNode) node.getRhs().getConstant()).getVal()) < 0));
                    }
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.LessEqual){
                    if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                        node.setConstant(new BoolliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() <= ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                    }
                    else{
                        node.setConstant(new BoolliteralNode(node.getPos(), ((StringliteralNode) node.getLhs().getConstant()).getVal().compareTo(((StringliteralNode) node.getRhs().getConstant()).getVal()) <= 0));
                    }
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.Greater){
                    if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                        node.setConstant(new BoolliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() > ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                    }
                    else{
                        node.setConstant(new BoolliteralNode(node.getPos(), ((StringliteralNode) node.getLhs().getConstant()).getVal().compareTo(((StringliteralNode) node.getRhs().getConstant()).getVal()) > 0));
                    }
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.GreaterEqual){
                    if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                        node.setConstant(new BoolliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() >= ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                    }
                    else{
                        node.setConstant(new BoolliteralNode(node.getPos(), ((StringliteralNode) node.getLhs().getConstant()).getVal().compareTo(((StringliteralNode) node.getRhs().getConstant()).getVal()) >= 0));
                    }
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.Equal){
                    if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                        node.setConstant(new BoolliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() == ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                    }
                    else if(node.getLhs().getType() instanceof BoolType && node.getRhs().getType() instanceof BoolType){
                        node.setConstant(new BoolliteralNode(node.getPos(),((BoolliteralNode) node.getLhs().getConstant()).getVal() == ((BoolliteralNode) node.getRhs().getConstant()).getVal()));
                    }
                    else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                        node.setConstant(new BoolliteralNode(node.getPos(), ((StringliteralNode) node.getLhs().getConstant()).getVal().compareTo(((StringliteralNode) node.getRhs().getConstant()).getVal()) == 0));
                    }
                    else node.setConstant(new BoolliteralNode(node.getPos(), true));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.NotEqual){
                    if(node.getLhs().getType() instanceof IntType && node.getRhs().getType() instanceof IntType){
                        node.setConstant(new BoolliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() != ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                    }
                    else if(node.getLhs().getType() instanceof BoolType && node.getRhs().getType() instanceof BoolType){
                        node.setConstant(new BoolliteralNode(node.getPos(),((BoolliteralNode) node.getLhs().getConstant()).getVal() != ((BoolliteralNode) node.getRhs().getConstant()).getVal()));
                    }
                    else if(node.getLhs().getType() instanceof StringType && node.getRhs().getType() instanceof StringType){
                        node.setConstant(new BoolliteralNode(node.getPos(), ((StringliteralNode) node.getLhs().getConstant()).getVal().compareTo(((StringliteralNode) node.getRhs().getConstant()).getVal()) != 0));
                    }
                    else node.setConstant(new BoolliteralNode(node.getPos(), false));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.AndAri){
                    node.setConstant(new IntegerliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() & ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.OrAri){
                    node.setConstant(new IntegerliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() | ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.AndLogic){
                    node.setConstant(new BoolliteralNode(node.getPos(),((BoolliteralNode) node.getLhs().getConstant()).getVal() & ((BoolliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.OrLogic){
                    node.setConstant(new BoolliteralNode(node.getPos(),((BoolliteralNode) node.getLhs().getConstant()).getVal() | ((BoolliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else if(node.getOp() == BinaryexprNode.BinaryOpType.XorAri){
                    node.setConstant(new IntegerliteralNode(node.getPos(),((IntegerliteralNode) node.getLhs().getConstant()).getVal() ^ ((IntegerliteralNode) node.getRhs().getConstant()).getVal()));
                }
                else newConst = false;
            }
        }
    }

    @Override
    public void visit(PrefixexprNode node) {
        node.getExpression().accept(this);
        if(collectConst){
            if (node.getOp() == PrefixexprNode.PrefixOpType.AddAdd || node.getOp() == PrefixexprNode.PrefixOpType.MinusMinus){
                if(node.getExpression() instanceof IdentifierNode){
                    ReAssignSet.add(((IdentifierNode) node.getExpression()).getSymbol());
                }
            }
        }
        else{
            if(node.getExpression().isConst() && !node.isConst()){
                newConst = true;
                if(node.getOp() == PrefixexprNode.PrefixOpType.Add){
                    node.setConstant(new IntegerliteralNode(node.getPos(), ((IntegerliteralNode) node.getExpression().getConstant()).getVal()));
                }
                else if(node.getOp() == PrefixexprNode.PrefixOpType.Minus){
                    node.setConstant(new IntegerliteralNode(node.getPos(), -((IntegerliteralNode) node.getExpression().getConstant()).getVal()));
                }
                else if(node.getOp() == PrefixexprNode.PrefixOpType.NotAri){
                    node.setConstant(new IntegerliteralNode(node.getPos(), ~((IntegerliteralNode) node.getExpression().getConstant()).getVal()));
                }
                else if(node.getOp() == PrefixexprNode.PrefixOpType.NotLogic){
                    node.setConstant(new BoolliteralNode(node.getPos(), !((BoolliteralNode) node.getExpression().getConstant()).getVal()));
                }
            }
        }
    }

    @Override
    public void visit(SuffixexprNode node) {
        node.getExpression().accept(this);
        if(collectConst){
            if(node.getExpression() instanceof IdentifierNode){
                ReAssignSet.add(((IdentifierNode) node.getExpression()).getSymbol());
            }
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

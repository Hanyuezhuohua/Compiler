package frontend;

import AST.*;
import Util.error.ErrorMessage;
import parser.MymxParser;
import parser.MymxBaseVisitor;
import Util.position;
import org.antlr.v4.runtime.ParserRuleContext;
import Util.type.*;

import java.util.ArrayList;
import java.util.List;

public class ASTbuilder extends MymxBaseVisitor<ASTNode>{
    @Override
    public ASTNode visitComplication_code(MymxParser.Complication_codeContext ctx) {
        RootNode code = new RootNode(new position(ctx));
        for(var child : ctx.children){
            if(child instanceof MymxParser.Function_def_unitContext){
                ASTNode tmp = visit(child);
                code.AddNode(tmp);
            }
            else if(child instanceof MymxParser.Class_def_unitContext){
                ASTNode tmp = visit(child);
                code.AddNode(tmp);
            }
            else if(child instanceof MymxParser.Var_def_unitContext){
                ASTNode tmp = visit(child);
                if(tmp instanceof VardefNode){
                    code.AddNode(tmp);
                }
                else if(tmp instanceof VardefListNode){
                    code.AddNode((((VardefListNode) tmp).getVarList()));
                }
            }
        }
        return code;
    }

    @Override
    public ASTNode visitFunction_def_unit(MymxParser.Function_def_unitContext ctx) {
        FundefNode res = new FundefNode(ctx.IDENTIFIER().getText(), (SuiteNode) visit(ctx.suite()), new position(ctx));
        if(ctx.parameter_list() != null){
            res.setParameterList(((VardefListNode) visit(ctx.parameter_list())).getVarList());
        }
        if(ctx.type() != null){
            res.setReturnType((TypeNode) visit(ctx.type()));
        }
        else if(ctx.VOID() != null){
            res.setReturnType(new TypeNode(new position(ctx.VOID()), ctx.VOID().getText(), 0));
        }
        return res;
    }

    @Override
    public ASTNode visitClass_def_unit(MymxParser.Class_def_unitContext ctx) {
        ClassdefNode res = new ClassdefNode(new position(ctx), ctx.IDENTIFIER().getText());
        for (var v : ctx.var_def_unit()){
            res.addVarList(((VardefListNode) visit(v)).getVarList());
        }
        for (var f : ctx.function_def_unit()){
            res.addFunc((FundefNode) visit(f));
        }
        for (var c : ctx.constructor_def_unit()){
            res.setConstructor((FundefNode) visit(c));
        }
        return res;
    }

    @Override
    public ASTNode visitConstructor_def_unit(MymxParser.Constructor_def_unitContext ctx) {
        FundefNode res = new FundefNode(ctx.IDENTIFIER().getText(), (SuiteNode) visit(ctx.suite()), new position(ctx));
        if(ctx.parameter_list() != null){
            res.setParameterList(((VardefListNode) visit(ctx.parameter_list())).getVarList());
        }
        res.setReturnType(new TypeNode(new position(-1, -1), "void", 0));
        return res;
    }

    @Override
    public ASTNode visitVar_def_unit(MymxParser.Var_def_unitContext ctx) {
        return visit(ctx.variable_list());
    }

    @Override
    public ASTNode visitExpression_list(MymxParser.Expression_listContext ctx) {
        throw new ErrorMessage("ASTbuilder visitExpression_list Error", new position(ctx));
    }

    @Override
    public ASTNode visitParameter_list(MymxParser.Parameter_listContext ctx) {
        VardefListNode res = new VardefListNode(new position(ctx));
        for(int i = 0; i < ctx.IDENTIFIER().size(); ++i){
            res.addVar(new VardefNode(ctx.IDENTIFIER(i).getText(), (TypeNode) visit(ctx.type(i)), new position(ctx)));
        }
        return res;
    }

    @Override
    public ASTNode visitVariable_list(MymxParser.Variable_listContext ctx) {
        TypeNode type = (TypeNode) visit(ctx.type());
        VardefListNode res = new VardefListNode(new position(ctx));
        if(!ctx.variable_decl().isEmpty()){
            for(ParserRuleContext Variable :  ctx.variable_decl()){
                res.addVar((VardefNode) visit(Variable));
            }
        }
        res.setType(type);
        return res;
    }

    @Override
    public ASTNode visitVariable_decl(MymxParser.Variable_declContext ctx) {
        VardefNode res = new VardefNode(ctx.IDENTIFIER().getText(), new position(ctx));
        if(ctx.expression() != null) res.setExpression((ExprNode) visit(ctx.expression()));
        return res;
    }

    @Override
    public ASTNode visitSuite(MymxParser.SuiteContext ctx) {
        SuiteNode res = new SuiteNode(new position(ctx));
        for (var stat : ctx.statement()){
            StatementNode tmp = (StatementNode) visit(stat);
            if (tmp instanceof EmptystatementNode){
                continue;
            }
            else if(tmp instanceof BreakstatementNode || tmp instanceof ContinuestatementNode || tmp instanceof  ReturnstatementNode){
                res.addStat(tmp);
                break;
            }
            else res.addStat(tmp);
        }
        return res;
    }

    @Override
    public ASTNode visitBlockStat(MymxParser.BlockStatContext ctx) {
        return visit(ctx.suite());
    }

    @Override
    public ASTNode visitVardefStat(MymxParser.VardefStatContext ctx) {
        return new VardefstatementNode(new position(ctx), (VardefListNode) visit(ctx.var_def_unit()));
    }

    @Override
    public ASTNode visitIfStat(MymxParser.IfStatContext ctx) {
        if(ctx.elsestatement != null) {
            SuiteNode trueSuite;
            SuiteNode falseSuite;
            StatementNode trueStatementNode = (StatementNode) visit(ctx.ifstatement);
            if(trueStatementNode instanceof SuiteNode){
                trueSuite = (SuiteNode) trueStatementNode;
            }
            else{
                trueSuite = new SuiteNode(trueStatementNode.getPos());
                trueSuite.addStat(trueStatementNode);
            }
            StatementNode falseStatementNode = (StatementNode) visit(ctx.elsestatement);
            if(falseStatementNode instanceof SuiteNode){
                falseSuite = (SuiteNode) falseStatementNode;
            }
            else{
                falseSuite = new SuiteNode(falseStatementNode.getPos());
                falseSuite.addStat(falseStatementNode);
            }
            return new IfstatementNode(new position(ctx), (ExprNode) visit(ctx.expression()), trueSuite, falseSuite);
        }
        else{
            SuiteNode trueSuite;
            StatementNode trueStatementNode = (StatementNode) visit(ctx.ifstatement);
            if(trueStatementNode instanceof SuiteNode){
                trueSuite = (SuiteNode) trueStatementNode;
            }
            else{
                trueSuite = new SuiteNode(trueStatementNode.getPos());
                trueSuite.addStat(trueStatementNode);
            }
            return new IfstatementNode(new position(ctx), (ExprNode) visit(ctx.expression()), trueSuite, null);
        }
    }

    @Override
    public ASTNode visitWhileStat(MymxParser.WhileStatContext ctx) {
        SuiteNode suite;
        StatementNode statementNode = (StatementNode) visit(ctx.statement());
        if(statementNode instanceof SuiteNode){
            suite = (SuiteNode) statementNode;
        }
        else {
            suite = new SuiteNode(statementNode.getPos());
            suite.addStat(statementNode);
        }
        return new WhilestatementNode(new position(ctx), (ExprNode) visit(ctx.expression()), suite);
    }

    @Override
    public ASTNode visitForStat(MymxParser.ForStatContext ctx) {
        ExprNode condition = ctx.condition == null ? null : (ExprNode) visit(ctx.condition);
        ExprNode incr = ctx.incr == null ? null : (ExprNode) visit(ctx.incr);
        SuiteNode suite;
        StatementNode statementNode = (StatementNode) visit(ctx.statement());
        if(statementNode instanceof SuiteNode){
            suite = (SuiteNode) statementNode;
        }
        else {
            suite = new SuiteNode(statementNode.getPos());
            suite.addStat(statementNode);
        }
        if(ctx.initexpr != null){
            return new ForstatementNode(new position(ctx), (ExprNode) visit(ctx.initexpr), condition, incr, suite);
        }
        else if(ctx.initvar != null){
            return new ForstatementNode(new position(ctx), (VardefListNode) visit(ctx.initvar), condition, incr, suite);
        }
        else return new ForstatementNode(new position(ctx), condition, incr, suite);
    }

    @Override
    public ASTNode visitBreakStat(MymxParser.BreakStatContext ctx) {
        return new BreakstatementNode(new position(ctx));
    }

    @Override
    public ASTNode visitReturnStat(MymxParser.ReturnStatContext ctx) {
        if(ctx.expression() != null) {
            return new ReturnstatementNode(new position(ctx), (ExprNode) visit(ctx.expression()));
        }
        else return new ReturnstatementNode(new position(ctx), null);
    }

    @Override
    public ASTNode visitContinueStat(MymxParser.ContinueStatContext ctx) {
        return new ContinuestatementNode(new position(ctx));
    }

    @Override
    public ASTNode visitExprStat(MymxParser.ExprStatContext ctx) {
        return new ExprstatementNode(new position(ctx), (ExprNode) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitEmptyStat(MymxParser.EmptyStatContext ctx) {
        return new EmptystatementNode(new position(ctx));
    }

    @Override
    public ASTNode visitNewExpr(MymxParser.NewExprContext ctx) {
        return new NewexprNode(new position(ctx), (NewtypeNode) visit(ctx.newtype()));
    }

    @Override
    public ASTNode visitPrefixExpr(MymxParser.PrefixExprContext ctx) {
        if(ctx.ADD() != null){
            return new PrefixexprNode(new position(ctx), PrefixexprNode.PrefixOpType.Add, (ExprNode) visit(ctx.expression()));
        }
        else if(ctx.MINUS() != null){
            return new PrefixexprNode(new position(ctx), PrefixexprNode.PrefixOpType.Minus, (ExprNode) visit(ctx.expression()));
        }
        else if(ctx.ADD_ADD() != null){
            return new PrefixexprNode(new position(ctx), PrefixexprNode.PrefixOpType.AddAdd, (ExprNode) visit(ctx.expression()));
        }
        else if(ctx.MINUS_MINUS() != null){
            return new PrefixexprNode(new position(ctx), PrefixexprNode.PrefixOpType.MinusMinus, (ExprNode) visit(ctx.expression()));
        }
        else if(ctx.NOT_LOGIC() != null){
            return new PrefixexprNode(new position(ctx), PrefixexprNode.PrefixOpType.NotLogic, (ExprNode) visit(ctx.expression()));
        }
        else if(ctx.NOT_ARI() != null){
            return new PrefixexprNode(new position(ctx), PrefixexprNode.PrefixOpType.NotAri, (ExprNode) visit(ctx.expression()));
        }
        else throw new ErrorMessage("ASTbuilder visitPrefixExpr Error", new position(ctx));
    }

    @Override
    public ASTNode visitFuncExpr(MymxParser.FuncExprContext ctx) {
        List<ExprNode> parameterList = new ArrayList<>();
        if(ctx.expression_list() != null){
            for (var para : ctx.expression_list().expression()){
                parameterList.add((ExprNode) visit(para));
            }
        }
        return new FuncexprNode(new position(ctx), (ExprNode) visit(ctx.expression()), parameterList);
    }

    @Override
    public ASTNode visitSubarrayExpr(MymxParser.SubarrayExprContext ctx) {
        return new SubarrayexprNode(new position(ctx), (ExprNode) visit(ctx.identifier), (ExprNode) visit(ctx.index));
    }

    @Override
    public ASTNode visitSuffixExpr(MymxParser.SuffixExprContext ctx) {
        if(ctx.ADD_ADD() != null){
            return new SuffixexprNode(new position(ctx), SuffixexprNode.SuffixOpType.AddTwice, (ExprNode) visit(ctx.expression()));
        }
        else if(ctx.MINUS_MINUS() != null){
            return new SuffixexprNode(new position(ctx), SuffixexprNode.SuffixOpType.MinusTwice, (ExprNode) visit(ctx.expression()));
        }
        else throw new ErrorMessage("ASTbuilder visitSuffixExpr Error", new position(ctx));
    }

    @Override
    public ASTNode visitMemberExpr(MymxParser.MemberExprContext ctx) {
        return new MemberexprNode(new position(ctx), (ExprNode) visit(ctx.expression()), ctx.IDENTIFIER().getText());
    }

    @Override
    public ASTNode visitAtomExpr(MymxParser.AtomExprContext ctx) {
        if(ctx.THIS() != null){
            return new ThisexprNode(new position(ctx));
        }
        else if(ctx.BOOL_LITERAL() != null){
            return new BoolliteralNode(new position(ctx), Boolean.parseBoolean(ctx.BOOL_LITERAL().getText()));
        }
        else if(ctx.INTEGER_LITERAL() != null){
            return new IntegerliteralNode(new position(ctx), Integer.parseInt(ctx.INTEGER_LITERAL().getText()));
        }
        else if(ctx.STRING_LITERAL() != null){
            return new StringliteralNode(new position(ctx), ctx.STRING_LITERAL().getText());
        }
        else if(ctx.NULL_LITERAL() != null){
            return new NullliteralNode(new position(ctx));
        }
        else if(ctx.IDENTIFIER() != null){
            return new IdentifierNode(new position(ctx), ctx.IDENTIFIER().getText());
        }
        else if(ctx.expression() != null){
            return visit(ctx.expression());
        }
        else throw new ErrorMessage("ASTbuilder visitAtomExpr Error", new position(ctx));
    }

    @Override
    public ASTNode visitBinaryExpr(MymxParser.BinaryExprContext ctx) {
        if(ctx.ADD() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.AddBinary);
        }
        else if(ctx.MINUS() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.MinusBinary);
        }
        else if(ctx.MUL() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.Mul);
        }
        else if(ctx.DIV() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.Div);
        }
        else if(ctx.MOD() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.Mod);
        }
        else if(ctx.LEFT_SHIFT() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.LeftShift);
        }
        else if(ctx.RIGHT_SHIFT() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.RightShift);
        }
        else if(ctx.LESS() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.Less);
        }
        else if(ctx.LESS_EQUAL() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.LessEqual);
        }
        else if(ctx.GREATER() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.Greater);
        }
        else if(ctx.GREATER_EQUAL() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.GreaterEqual);
        }
        else if(ctx.EQUAL() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.Equal);
        }
        else if(ctx.NOT_EQUAL() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.NotEqual);
        }
        else if(ctx.AND_ARI() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.AndAri);
        }
        else if(ctx.OR_ARI() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.OrAri);
        }
        else if(ctx.AND_LOGIC() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.AndLogic);
        }
        else if(ctx.OR_LOGIC() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.OrLogic);
        }
        else if(ctx.XOR_ARI() != null){
            return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.XorAri);
        }
        else throw new ErrorMessage("ASTbuilder visitBinaryExpr Error", new position(ctx));
    }

    @Override
    public ASTNode visitAssignExpr(MymxParser.AssignExprContext ctx) {
        return new BinaryexprNode(new position(ctx), (ExprNode) visit(ctx.lhs), (ExprNode) visit(ctx.rhs), BinaryexprNode.BinaryOpType.Assign);
    }

    @Override
    public ASTNode visitType(MymxParser.TypeContext ctx) {
        TypeNode res = new TypeNode(new position(ctx));
        res.setDim(getDim(ctx, 0));
        res.setType(getType(ctx));
        return res;
    }

    private int getDim(MymxParser.TypeContext ctx, int dimnow){ // maybe right
        if (ctx.BOOL() != null || ctx.INT() != null || ctx.STRING() != null || ctx.IDENTIFIER() != null) return dimnow;
        else return getDim(ctx.type(), dimnow + 1);
    }

    private String getType(MymxParser.TypeContext ctx){
        if(ctx.IDENTIFIER() != null) return ctx.IDENTIFIER().getText();
        else if(ctx.INT() != null) return ctx.INT().getText();
        else if(ctx.BOOL() != null) return ctx.BOOL().getText();
        else if(ctx.STRING() != null) return ctx.STRING().getText();
        else return getType(ctx.type());
    }

    @Override
    public ASTNode visitBasicNewtype(MymxParser.BasicNewtypeContext ctx) {
        NewtypeNode res = new NewtypeNode(new position(ctx));
        if(ctx.INT() != null){
            res.setBasetype(new TypeNode(new position(ctx.INT()), ctx.INT().getText(), 0));
        }
        else if(ctx.BOOL() != null){
            res.setBasetype(new TypeNode(new position(ctx.BOOL()), ctx.BOOL().getText(), 0));
        }
        else if(ctx.STRING() != null){
            res.setBasetype(new TypeNode(new position(ctx.STRING()), ctx.STRING().getText(), 0));
        }
        else if(ctx.IDENTIFIER() != null){
            res.setBasetype(new TypeNode(new position(ctx.IDENTIFIER()), ctx.IDENTIFIER().getText(), 0));
        }
        return res;
    }

    @Override
    public ASTNode visitClassNewtype(MymxParser.ClassNewtypeContext ctx) {
        NewtypeNode res = new NewtypeNode(new position(ctx));
        if(ctx.INT() != null){
            res.setBasetype(new TypeNode(new position(ctx.INT()), ctx.INT().getText(), 0));
        }
        else if(ctx.BOOL() != null){
            res.setBasetype(new TypeNode(new position(ctx.BOOL()), ctx.BOOL().getText(), 0));
        }
        else if(ctx.STRING() != null){
            res.setBasetype(new TypeNode(new position(ctx.STRING()), ctx.STRING().getText(), 0));
        }
        else if(ctx.IDENTIFIER() != null){
            res.setBasetype(new TypeNode(new position(ctx.IDENTIFIER()), ctx.IDENTIFIER().getText(), 0));
        }
        return res;
    }

    @Override
    public ASTNode visitArrayNewtype(MymxParser.ArrayNewtypeContext ctx) {
        NewtypeNode res = new NewtypeNode(new position(ctx));
        if(ctx.INT() != null){
            res.setBasetype(new TypeNode(new position(ctx.INT()), ctx.INT().getText(), ctx.LEFT_BRACKET().size()));
        }
        else if(ctx.BOOL() != null){
            res.setBasetype(new TypeNode(new position(ctx.BOOL()), ctx.BOOL().getText(), ctx.LEFT_BRACKET().size()));
        }
        else if(ctx.STRING() != null){
            res.setBasetype(new TypeNode(new position(ctx.STRING()), ctx.STRING().getText(), ctx.LEFT_BRACKET().size()));
        }
        else if(ctx.IDENTIFIER() != null){
            res.setBasetype(new TypeNode(new position(ctx.IDENTIFIER()), ctx.IDENTIFIER().getText(), ctx.LEFT_BRACKET().size()));
        }
        for(var expression : ctx.expression()){
            res.add_known((ExprNode) visit(expression));
        }
        return res;
    }

    @Override
    public ASTNode visitErrorNewtype(MymxParser.ErrorNewtypeContext ctx) {
        throw new ErrorMessage("ASTbuilder visitErrorNewtype Error", new position(ctx));
    }
}

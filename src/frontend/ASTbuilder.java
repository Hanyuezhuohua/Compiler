package frontend;

import AST.*;
import parser.MymxParser;
import parser.MymxBaseVisitor;
import Util.position;
import org.antlr.v4.runtime.ParserRuleContext;
import Util.type.*;

import java.util.ArrayList;

public class ASTbuilder extends MymxBaseVisitor<ASTNode>{
    @Override
    public ASTNode visitComplication_code(MymxParser.Complication_codeContext ctx) {
        RootNode code = new RootNode(new position(ctx));
        for(var child : ctx.children){
            if(child instanceof MymxParser.Function_def_unitContext){
                ASTNode tmp = visit(child);
                code.defination.add(tmp);
            }
            else if(child instanceof  MymxParser.Class_def_unitContext){
                ASTNode tmp = visit(child);
                code.defination.add(tmp);
            }
            else if(child instanceof MymxParser.Var_def_unitContext){
                ASTNode tmp = visit(child);
                if(tmp instanceof VardefNode){
                    code.defination.add(tmp);
                }
                else if(tmp instanceof VardefListNode){
                //    code.defination.addAll(); to do
                }
            }
        }
        return code;
    }

    @Override
    public ASTNode visitFunction_def_unit(MymxParser.Function_def_unitContext ctx) {
        return super.visitFunction_def_unit(ctx);
    }

    @Override
    public ASTNode visitClass_def_unit(MymxParser.Class_def_unitContext ctx) {
        return super.visitClass_def_unit(ctx);
    }

    @Override
    public ASTNode visitConstructor_def_unit(MymxParser.Constructor_def_unitContext ctx) {
        return super.visitConstructor_def_unit(ctx);
    }

    @Override
    public ASTNode visitVar_def_unit(MymxParser.Var_def_unitContext ctx) {
        return super.visitVar_def_unit(ctx);
    }

    @Override
    public ASTNode visitExpression_list(MymxParser.Expression_listContext ctx) {
        return super.visitExpression_list(ctx);
    }

    @Override
    public ASTNode visitParameter_list(MymxParser.Parameter_listContext ctx) {
        return super.visitParameter_list(ctx);
    }

    @Override
    public ASTNode visitVariable_list(MymxParser.Variable_listContext ctx) {
        return super.visitVariable_list(ctx);
    }

    @Override
    public ASTNode visitVariable_decl(MymxParser.Variable_declContext ctx) {
        return super.visitVariable_decl(ctx);
    }

    @Override
    public ASTNode visitSuite(MymxParser.SuiteContext ctx) {
        return super.visitSuite(ctx);
    }

    @Override
    public ASTNode visitBlockStat(MymxParser.BlockStatContext ctx) {
        return super.visitBlockStat(ctx);
    }

    @Override
    public ASTNode visitVardefStat(MymxParser.VardefStatContext ctx) {
        return super.visitVardefStat(ctx);
    }

    @Override
    public ASTNode visitIfStat(MymxParser.IfStatContext ctx) {
        return super.visitIfStat(ctx);
    }

    @Override
    public ASTNode visitWhileStat(MymxParser.WhileStatContext ctx) {
        return super.visitWhileStat(ctx);
    }

    @Override
    public ASTNode visitForStat(MymxParser.ForStatContext ctx) {
        return super.visitForStat(ctx);
    }

    @Override
    public ASTNode visitBreakStat(MymxParser.BreakStatContext ctx) {
        return super.visitBreakStat(ctx);
    }

    @Override
    public ASTNode visitReturnStat(MymxParser.ReturnStatContext ctx) {
        return super.visitReturnStat(ctx);
    }

    @Override
    public ASTNode visitContinueStat(MymxParser.ContinueStatContext ctx) {
        return super.visitContinueStat(ctx);
    }

    @Override
    public ASTNode visitExprStat(MymxParser.ExprStatContext ctx) {
        return super.visitExprStat(ctx);
    }

    @Override
    public ASTNode visitNewExpr(MymxParser.NewExprContext ctx) {
        return super.visitNewExpr(ctx);
    }

    @Override
    public ASTNode visitPrefixExpr(MymxParser.PrefixExprContext ctx) {
        return super.visitPrefixExpr(ctx);
    }

    @Override
    public ASTNode visitFuncExpr(MymxParser.FuncExprContext ctx) {
        return super.visitFuncExpr(ctx);
    }

    @Override
    public ASTNode visitSubarrayExpr(MymxParser.SubarrayExprContext ctx) {
        return super.visitSubarrayExpr(ctx);
    }

    @Override
    public ASTNode visitSuffixExpr(MymxParser.SuffixExprContext ctx) {
        return super.visitSuffixExpr(ctx);
    }

    @Override
    public ASTNode visitMemberExpr(MymxParser.MemberExprContext ctx) {
        return super.visitMemberExpr(ctx);
    }

    @Override
    public ASTNode visitAtomExpr(MymxParser.AtomExprContext ctx) {
        return super.visitAtomExpr(ctx);
    }

    @Override
    public ASTNode visitBinaryExpr(MymxParser.BinaryExprContext ctx) {
        return super.visitBinaryExpr(ctx);
    }

    @Override
    public ASTNode visitAssignExpr(MymxParser.AssignExprContext ctx) {
        return super.visitAssignExpr(ctx);
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
        if(ctx.IDENTIFIER() != null) return ctx.IDENTIFIER().toString();
        else if(ctx.INT() != null) return ctx.INT().toString();
        else if(ctx.BOOL() != null) return ctx.BOOL().toString();
        else if(ctx.STRING() != null) return ctx.STRING().toString();
        else return getType(ctx.type());
    }

    @Override
    public ASTNode visitBasicNewtype(MymxParser.BasicNewtypeContext ctx) {
        NewtypeNode res = new NewtypeNode(new position(ctx));
        if(ctx.INT() != null){
            res.setBasetype(new TypeNode(new position(ctx.INT()), ctx.INT().toString(), 0));
        }
        else if(ctx.BOOL() != null){
            res.setBasetype(new TypeNode(new position(ctx.BOOL()), ctx.BOOL().toString(), 0));
        }
        else if(ctx.STRING() != null){
            res.setBasetype(new TypeNode(new position(ctx.STRING()), ctx.STRING().toString(), 0));
        }
        else if(ctx.IDENTIFIER() != null){
            res.setBasetype(new TypeNode(new position(ctx.IDENTIFIER()), ctx.IDENTIFIER().toString(), 0));
        }
        return res;
    }

    @Override
    public ASTNode visitClassNewtype(MymxParser.ClassNewtypeContext ctx) {
        NewtypeNode res = new NewtypeNode(new position(ctx));
        if(ctx.INT() != null){
            res.setBasetype(new TypeNode(new position(ctx.INT()), ctx.INT().toString(), 0));
        }
        else if(ctx.BOOL() != null){
            res.setBasetype(new TypeNode(new position(ctx.BOOL()), ctx.BOOL().toString(), 0));
        }
        else if(ctx.STRING() != null){
            res.setBasetype(new TypeNode(new position(ctx.STRING()), ctx.STRING().toString(), 0));
        }
        else if(ctx.IDENTIFIER() != null){
            res.setBasetype(new TypeNode(new position(ctx.IDENTIFIER()), ctx.IDENTIFIER().toString(), 0));
        }
        return res;
    }

    @Override
    public ASTNode visitArrayNewtype(MymxParser.ArrayNewtypeContext ctx) {
        NewtypeNode res = new NewtypeNode(new position(ctx));
        if(ctx.INT() != null){
            res.setBasetype(new TypeNode(new position(ctx.INT()), ctx.INT().toString(), ctx.LEFT_BRACKET().size()));
        }
        else if(ctx.BOOL() != null){
            res.setBasetype(new TypeNode(new position(ctx.BOOL()), ctx.BOOL().toString(), ctx.LEFT_BRACKET().size()));
        }
        else if(ctx.STRING() != null){
            res.setBasetype(new TypeNode(new position(ctx.STRING()), ctx.STRING().toString(), ctx.LEFT_BRACKET().size()));
        }
        else if(ctx.IDENTIFIER() != null){
            res.setBasetype(new TypeNode(new position(ctx.IDENTIFIER()), ctx.IDENTIFIER().toString(), ctx.LEFT_BRACKET().size()));
        }
        return res;
    }

    @Override
    public ASTNode visitErrorNewtype(MymxParser.ErrorNewtypeContext ctx) {
        return super.visitErrorNewtype(ctx); //modify later
    }
}

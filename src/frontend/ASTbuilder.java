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

        return super.visitComplication_code(ctx);
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
    public ASTNode visitStatement(MymxParser.StatementContext ctx) {
        return super.visitStatement(ctx);
    }

    @Override
    public ASTNode visitExpression(MymxParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public ASTNode visitType(MymxParser.TypeContext ctx) {
        return super.visitType(ctx);
    }

    @Override
    public ASTNode visitNewtype(MymxParser.NewtypeContext ctx) {
        return super.visitNewtype(ctx);
    }
}

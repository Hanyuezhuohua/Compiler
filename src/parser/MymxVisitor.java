// Generated from D:/Compiler\Mymx.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MymxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MymxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MymxParser#complication_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplication_code(MymxParser.Complication_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#function_def_unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_def_unit(MymxParser.Function_def_unitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#class_def_unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_def_unit(MymxParser.Class_def_unitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#constructor_def_unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor_def_unit(MymxParser.Constructor_def_unitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#var_def_unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_def_unit(MymxParser.Var_def_unitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#expression_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list(MymxParser.Expression_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#parameter_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_list(MymxParser.Parameter_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#variable_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_list(MymxParser.Variable_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#variable_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_decl(MymxParser.Variable_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(MymxParser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MymxParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MymxParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MymxParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewtype(MymxParser.NewtypeContext ctx);
}
package parser;// Generated from D:/Compiler/src\Mymx.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MymxParser}.
 */
public interface MymxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MymxParser#complication_code}.
	 * @param ctx the parse tree
	 */
	void enterComplication_code(MymxParser.Complication_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#complication_code}.
	 * @param ctx the parse tree
	 */
	void exitComplication_code(MymxParser.Complication_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#function_def_unit}.
	 * @param ctx the parse tree
	 */
	void enterFunction_def_unit(MymxParser.Function_def_unitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#function_def_unit}.
	 * @param ctx the parse tree
	 */
	void exitFunction_def_unit(MymxParser.Function_def_unitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#class_def_unit}.
	 * @param ctx the parse tree
	 */
	void enterClass_def_unit(MymxParser.Class_def_unitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#class_def_unit}.
	 * @param ctx the parse tree
	 */
	void exitClass_def_unit(MymxParser.Class_def_unitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#constructor_def_unit}.
	 * @param ctx the parse tree
	 */
	void enterConstructor_def_unit(MymxParser.Constructor_def_unitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#constructor_def_unit}.
	 * @param ctx the parse tree
	 */
	void exitConstructor_def_unit(MymxParser.Constructor_def_unitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#var_def_unit}.
	 * @param ctx the parse tree
	 */
	void enterVar_def_unit(MymxParser.Var_def_unitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#var_def_unit}.
	 * @param ctx the parse tree
	 */
	void exitVar_def_unit(MymxParser.Var_def_unitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list(MymxParser.Expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list(MymxParser.Expression_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#parameter_list}.
	 * @param ctx the parse tree
	 */
	void enterParameter_list(MymxParser.Parameter_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#parameter_list}.
	 * @param ctx the parse tree
	 */
	void exitParameter_list(MymxParser.Parameter_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#variable_list}.
	 * @param ctx the parse tree
	 */
	void enterVariable_list(MymxParser.Variable_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#variable_list}.
	 * @param ctx the parse tree
	 */
	void exitVariable_list(MymxParser.Variable_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#variable_decl}.
	 * @param ctx the parse tree
	 */
	void enterVariable_decl(MymxParser.Variable_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#variable_decl}.
	 * @param ctx the parse tree
	 */
	void exitVariable_decl(MymxParser.Variable_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(MymxParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(MymxParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MymxParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MymxParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MymxParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MymxParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MymxParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MymxParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 */
	void enterNewtype(MymxParser.NewtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 */
	void exitNewtype(MymxParser.NewtypeContext ctx);
}
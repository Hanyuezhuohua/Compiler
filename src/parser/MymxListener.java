// Generated from D:/Compiler/src/parser\Mymx.g4 by ANTLR 4.9
package parser;
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
	 * Enter a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(MymxParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(MymxParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code vardefStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVardefStat(MymxParser.VardefStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code vardefStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVardefStat(MymxParser.VardefStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(MymxParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(MymxParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(MymxParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(MymxParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStat(MymxParser.ForStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStat(MymxParser.ForStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStat(MymxParser.ContinueStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStat(MymxParser.ContinueStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStat(MymxParser.BreakStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStat(MymxParser.BreakStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(MymxParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(MymxParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStat(MymxParser.ExprStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStat(MymxParser.ExprStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStat(MymxParser.EmptyStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStat(MymxParser.EmptyStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MymxParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MymxParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpr(MymxParser.PrefixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpr(MymxParser.PrefixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncExpr(MymxParser.FuncExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncExpr(MymxParser.FuncExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subarrayExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubarrayExpr(MymxParser.SubarrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subarrayExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubarrayExpr(MymxParser.SubarrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixExpr(MymxParser.SuffixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixExpr(MymxParser.SuffixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpr(MymxParser.MemberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpr(MymxParser.MemberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(MymxParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(MymxParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MymxParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MymxParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(MymxParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(MymxParser.AssignExprContext ctx);
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
	 * Enter a parse tree produced by the {@code errorNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 */
	void enterErrorNewtype(MymxParser.ErrorNewtypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code errorNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 */
	void exitErrorNewtype(MymxParser.ErrorNewtypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 */
	void enterArrayNewtype(MymxParser.ArrayNewtypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 */
	void exitArrayNewtype(MymxParser.ArrayNewtypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 */
	void enterClassNewtype(MymxParser.ClassNewtypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 */
	void exitClassNewtype(MymxParser.ClassNewtypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basicNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 */
	void enterBasicNewtype(MymxParser.BasicNewtypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basicNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 */
	void exitBasicNewtype(MymxParser.BasicNewtypeContext ctx);
}
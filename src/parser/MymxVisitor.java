// Generated from D:/Compiler/src/parser\Mymx.g4 by ANTLR 4.9
package parser;
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
	 * Visit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStat(MymxParser.BlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vardefStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardefStat(MymxParser.VardefStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(MymxParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(MymxParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStat(MymxParser.ForStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStat(MymxParser.ContinueStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStat(MymxParser.BreakStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(MymxParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStat(MymxParser.ExprStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyStat}
	 * labeled alternative in {@link MymxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStat(MymxParser.EmptyStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(MymxParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixExpr(MymxParser.PrefixExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncExpr(MymxParser.FuncExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subarrayExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubarrayExpr(MymxParser.SubarrayExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code suffixExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuffixExpr(MymxParser.SuffixExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExpr(MymxParser.MemberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExpr(MymxParser.AtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(MymxParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MymxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(MymxParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MymxParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MymxParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code errorNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitErrorNewtype(MymxParser.ErrorNewtypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayNewtype(MymxParser.ArrayNewtypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassNewtype(MymxParser.ClassNewtypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code basicNewtype}
	 * labeled alternative in {@link MymxParser#newtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicNewtype(MymxParser.BasicNewtypeContext ctx);
}
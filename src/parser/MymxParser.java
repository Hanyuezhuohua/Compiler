// Generated from D:/Compiler/src/parser\Mymx.g4 by ANTLR 4.9
package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MymxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Whitespace=1, Newline=2, BlockComment=3, LineComment=4, BOOL_LITERAL=5, 
		INTEGER_LITERAL=6, STRING_LITERAL=7, NULL_LITERAL=8, INT=9, BOOL=10, STRING=11, 
		NULL=12, VOID=13, TRUE=14, FALSE=15, IF=16, ELSE=17, FOR=18, WHILE=19, 
		BREAK=20, CONTINUE=21, RETURN=22, NEW=23, CLASS=24, THIS=25, IDENTIFIER=26, 
		SPACE=27, COMMA=28, SEMICOLON=29, DOT=30, LEFT_PARENTNESS=31, RIGHT_PARENTNESS=32, 
		LEFT_BRACKET=33, RIGHT_BRACKET=34, LEFT_BRACE=35, RIGHT_BRACE=36, LESS=37, 
		LESS_EQUAL=38, GREATER=39, GREATER_EQUAL=40, EQUAL=41, NOT_EQUAL=42, LEFT_SHIFT=43, 
		RIGHT_SHIFT=44, ASSIGN=45, ADD_ADD=46, MINUS_MINUS=47, ADD=48, MINUS=49, 
		MUL=50, DIV=51, MOD=52, AND_ARI=53, OR_ARI=54, AND_LOGIC=55, OR_LOGIC=56, 
		XOR_ARI=57, NOT_LOGIC=58, NOT_ARI=59;
	public static final int
		RULE_complication_code = 0, RULE_function_def_unit = 1, RULE_class_def_unit = 2, 
		RULE_constructor_def_unit = 3, RULE_var_def_unit = 4, RULE_expression_list = 5, 
		RULE_parameter_list = 6, RULE_variable_list = 7, RULE_variable_decl = 8, 
		RULE_suite = 9, RULE_statement = 10, RULE_expression = 11, RULE_type = 12, 
		RULE_newtype = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"complication_code", "function_def_unit", "class_def_unit", "constructor_def_unit", 
			"var_def_unit", "expression_list", "parameter_list", "variable_list", 
			"variable_decl", "suite", "statement", "expression", "type", "newtype"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "'int'", "'bool'", 
			"'string'", "'null'", "'void'", "'true'", "'false'", "'if'", "'else'", 
			"'for'", "'while'", "'break'", "'continue'", "'return'", "'new'", "'class'", 
			"'this'", null, "' '", "','", "';'", "'.'", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'<<'", "'>>'", 
			"'='", "'++'", "'--'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&'", "'|'", 
			"'&&'", "'||'", "'^'", "'!'", "'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Whitespace", "Newline", "BlockComment", "LineComment", "BOOL_LITERAL", 
			"INTEGER_LITERAL", "STRING_LITERAL", "NULL_LITERAL", "INT", "BOOL", "STRING", 
			"NULL", "VOID", "TRUE", "FALSE", "IF", "ELSE", "FOR", "WHILE", "BREAK", 
			"CONTINUE", "RETURN", "NEW", "CLASS", "THIS", "IDENTIFIER", "SPACE", 
			"COMMA", "SEMICOLON", "DOT", "LEFT_PARENTNESS", "RIGHT_PARENTNESS", "LEFT_BRACKET", 
			"RIGHT_BRACKET", "LEFT_BRACE", "RIGHT_BRACE", "LESS", "LESS_EQUAL", "GREATER", 
			"GREATER_EQUAL", "EQUAL", "NOT_EQUAL", "LEFT_SHIFT", "RIGHT_SHIFT", "ASSIGN", 
			"ADD_ADD", "MINUS_MINUS", "ADD", "MINUS", "MUL", "DIV", "MOD", "AND_ARI", 
			"OR_ARI", "AND_LOGIC", "OR_LOGIC", "XOR_ARI", "NOT_LOGIC", "NOT_ARI"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mymx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MymxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Complication_codeContext extends ParserRuleContext {
		public List<Function_def_unitContext> function_def_unit() {
			return getRuleContexts(Function_def_unitContext.class);
		}
		public Function_def_unitContext function_def_unit(int i) {
			return getRuleContext(Function_def_unitContext.class,i);
		}
		public List<Class_def_unitContext> class_def_unit() {
			return getRuleContexts(Class_def_unitContext.class);
		}
		public Class_def_unitContext class_def_unit(int i) {
			return getRuleContext(Class_def_unitContext.class,i);
		}
		public List<Var_def_unitContext> var_def_unit() {
			return getRuleContexts(Var_def_unitContext.class);
		}
		public Var_def_unitContext var_def_unit(int i) {
			return getRuleContext(Var_def_unitContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(MymxParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MymxParser.SEMICOLON, i);
		}
		public Complication_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complication_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterComplication_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitComplication_code(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitComplication_code(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Complication_codeContext complication_code() throws RecognitionException {
		Complication_codeContext _localctx = new Complication_codeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_complication_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << IDENTIFIER) | (1L << SEMICOLON))) != 0)) {
				{
				setState(32);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(28);
					function_def_unit();
					}
					break;
				case 2:
					{
					setState(29);
					class_def_unit();
					}
					break;
				case 3:
					{
					setState(30);
					var_def_unit();
					}
					break;
				case 4:
					{
					setState(31);
					match(SEMICOLON);
					}
					break;
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_def_unitContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public TerminalNode LEFT_PARENTNESS() { return getToken(MymxParser.LEFT_PARENTNESS, 0); }
		public TerminalNode RIGHT_PARENTNESS() { return getToken(MymxParser.RIGHT_PARENTNESS, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode VOID() { return getToken(MymxParser.VOID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public Function_def_unitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_def_unit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterFunction_def_unit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitFunction_def_unit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitFunction_def_unit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_def_unitContext function_def_unit() throws RecognitionException {
		Function_def_unitContext _localctx = new Function_def_unitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_function_def_unit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				{
				setState(37);
				match(VOID);
				}
				break;
			case INT:
			case BOOL:
			case STRING:
			case IDENTIFIER:
				{
				setState(38);
				type(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(41);
			match(IDENTIFIER);
			setState(42);
			match(LEFT_PARENTNESS);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(43);
				parameter_list();
				}
			}

			setState(46);
			match(RIGHT_PARENTNESS);
			setState(47);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_def_unitContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(MymxParser.CLASS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public TerminalNode LEFT_BRACE() { return getToken(MymxParser.LEFT_BRACE, 0); }
		public TerminalNode RIGHT_BRACE() { return getToken(MymxParser.RIGHT_BRACE, 0); }
		public TerminalNode SEMICOLON() { return getToken(MymxParser.SEMICOLON, 0); }
		public List<Var_def_unitContext> var_def_unit() {
			return getRuleContexts(Var_def_unitContext.class);
		}
		public Var_def_unitContext var_def_unit(int i) {
			return getRuleContext(Var_def_unitContext.class,i);
		}
		public List<Function_def_unitContext> function_def_unit() {
			return getRuleContexts(Function_def_unitContext.class);
		}
		public Function_def_unitContext function_def_unit(int i) {
			return getRuleContext(Function_def_unitContext.class,i);
		}
		public List<Constructor_def_unitContext> constructor_def_unit() {
			return getRuleContexts(Constructor_def_unitContext.class);
		}
		public Constructor_def_unitContext constructor_def_unit(int i) {
			return getRuleContext(Constructor_def_unitContext.class,i);
		}
		public Class_def_unitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_def_unit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterClass_def_unit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitClass_def_unit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitClass_def_unit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_def_unitContext class_def_unit() throws RecognitionException {
		Class_def_unitContext _localctx = new Class_def_unitContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_class_def_unit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(CLASS);
			setState(50);
			match(IDENTIFIER);
			setState(51);
			match(LEFT_BRACE);
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << VOID) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(55);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(52);
					var_def_unit();
					}
					break;
				case 2:
					{
					setState(53);
					function_def_unit();
					}
					break;
				case 3:
					{
					setState(54);
					constructor_def_unit();
					}
					break;
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(RIGHT_BRACE);
			setState(61);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constructor_def_unitContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public TerminalNode LEFT_PARENTNESS() { return getToken(MymxParser.LEFT_PARENTNESS, 0); }
		public TerminalNode RIGHT_PARENTNESS() { return getToken(MymxParser.RIGHT_PARENTNESS, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public Constructor_def_unitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor_def_unit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterConstructor_def_unit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitConstructor_def_unit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitConstructor_def_unit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constructor_def_unitContext constructor_def_unit() throws RecognitionException {
		Constructor_def_unitContext _localctx = new Constructor_def_unitContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_constructor_def_unit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(IDENTIFIER);
			setState(64);
			match(LEFT_PARENTNESS);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(65);
				parameter_list();
				}
			}

			setState(68);
			match(RIGHT_PARENTNESS);
			setState(69);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_def_unitContext extends ParserRuleContext {
		public Variable_listContext variable_list() {
			return getRuleContext(Variable_listContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MymxParser.SEMICOLON, 0); }
		public Var_def_unitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_def_unit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterVar_def_unit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitVar_def_unit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitVar_def_unit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_def_unitContext var_def_unit() throws RecognitionException {
		Var_def_unitContext _localctx = new Var_def_unitContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_var_def_unit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			variable_list();
			setState(72);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression_listContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MymxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MymxParser.COMMA, i);
		}
		public Expression_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterExpression_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitExpression_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitExpression_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_listContext expression_list() throws RecognitionException {
		Expression_listContext _localctx = new Expression_listContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			expression(0);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(75);
				match(COMMA);
				setState(76);
				expression(0);
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_listContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(MymxParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MymxParser.IDENTIFIER, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MymxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MymxParser.COMMA, i);
		}
		public Parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterParameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitParameter_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitParameter_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameter_listContext parameter_list() throws RecognitionException {
		Parameter_listContext _localctx = new Parameter_listContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			type(0);
			setState(83);
			match(IDENTIFIER);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(84);
				match(COMMA);
				setState(85);
				type(0);
				setState(86);
				match(IDENTIFIER);
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_listContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<Variable_declContext> variable_decl() {
			return getRuleContexts(Variable_declContext.class);
		}
		public Variable_declContext variable_decl(int i) {
			return getRuleContext(Variable_declContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MymxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MymxParser.COMMA, i);
		}
		public Variable_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterVariable_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitVariable_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitVariable_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_listContext variable_list() throws RecognitionException {
		Variable_listContext _localctx = new Variable_listContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variable_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			type(0);
			setState(94);
			variable_decl();
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(95);
				match(COMMA);
				setState(96);
				variable_decl();
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_declContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(MymxParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Variable_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterVariable_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitVariable_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitVariable_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_declContext variable_decl() throws RecognitionException {
		Variable_declContext _localctx = new Variable_declContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_variable_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(IDENTIFIER);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(103);
				match(ASSIGN);
				setState(104);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LEFT_BRACE() { return getToken(MymxParser.LEFT_BRACE, 0); }
		public TerminalNode RIGHT_BRACE() { return getToken(MymxParser.RIGHT_BRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(LEFT_BRACE);
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << SEMICOLON) | (1L << LEFT_PARENTNESS) | (1L << LEFT_BRACE) | (1L << ADD_ADD) | (1L << MINUS_MINUS) | (1L << ADD) | (1L << MINUS) | (1L << NOT_LOGIC) | (1L << NOT_ARI))) != 0)) {
				{
				{
				setState(108);
				statement();
				}
				}
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(114);
			match(RIGHT_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IfStatContext extends StatementContext {
		public StatementContext ifstatement;
		public StatementContext elsestatement;
		public TerminalNode IF() { return getToken(MymxParser.IF, 0); }
		public TerminalNode LEFT_PARENTNESS() { return getToken(MymxParser.LEFT_PARENTNESS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PARENTNESS() { return getToken(MymxParser.RIGHT_PARENTNESS, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MymxParser.ELSE, 0); }
		public IfStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterIfStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitIfStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitIfStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStatContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public BlockStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterBlockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitBlockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitBlockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprStatContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MymxParser.SEMICOLON, 0); }
		public ExprStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterExprStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitExprStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitExprStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStatContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(MymxParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(MymxParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterReturnStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitReturnStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitReturnStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStatContext extends StatementContext {
		public TerminalNode CONTINUE() { return getToken(MymxParser.CONTINUE, 0); }
		public TerminalNode SEMICOLON() { return getToken(MymxParser.SEMICOLON, 0); }
		public ContinueStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterContinueStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitContinueStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitContinueStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VardefStatContext extends StatementContext {
		public Var_def_unitContext var_def_unit() {
			return getRuleContext(Var_def_unitContext.class,0);
		}
		public VardefStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterVardefStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitVardefStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitVardefStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStatContext extends StatementContext {
		public TerminalNode BREAK() { return getToken(MymxParser.BREAK, 0); }
		public TerminalNode SEMICOLON() { return getToken(MymxParser.SEMICOLON, 0); }
		public BreakStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterBreakStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitBreakStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitBreakStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyStatContext extends StatementContext {
		public TerminalNode SEMICOLON() { return getToken(MymxParser.SEMICOLON, 0); }
		public EmptyStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterEmptyStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitEmptyStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitEmptyStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStatContext extends StatementContext {
		public ExpressionContext initexpr;
		public Variable_listContext initvar;
		public ExpressionContext condition;
		public ExpressionContext incr;
		public TerminalNode FOR() { return getToken(MymxParser.FOR, 0); }
		public TerminalNode LEFT_PARENTNESS() { return getToken(MymxParser.LEFT_PARENTNESS, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(MymxParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MymxParser.SEMICOLON, i);
		}
		public TerminalNode RIGHT_PARENTNESS() { return getToken(MymxParser.RIGHT_PARENTNESS, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Variable_listContext variable_list() {
			return getRuleContext(Variable_listContext.class,0);
		}
		public ForStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterForStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitForStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitForStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStatContext extends StatementContext {
		public TerminalNode WHILE() { return getToken(MymxParser.WHILE, 0); }
		public TerminalNode LEFT_PARENTNESS() { return getToken(MymxParser.LEFT_PARENTNESS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PARENTNESS() { return getToken(MymxParser.RIGHT_PARENTNESS, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterWhileStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitWhileStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitWhileStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_statement);
		int _la;
		try {
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				suite();
				}
				break;
			case 2:
				_localctx = new VardefStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				var_def_unit();
				}
				break;
			case 3:
				_localctx = new IfStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				match(IF);
				setState(119);
				match(LEFT_PARENTNESS);
				setState(120);
				expression(0);
				setState(121);
				match(RIGHT_PARENTNESS);
				setState(122);
				((IfStatContext)_localctx).ifstatement = statement();
				setState(125);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(123);
					match(ELSE);
					setState(124);
					((IfStatContext)_localctx).elsestatement = statement();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new WhileStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(127);
				match(WHILE);
				setState(128);
				match(LEFT_PARENTNESS);
				setState(129);
				expression(0);
				setState(130);
				match(RIGHT_PARENTNESS);
				setState(131);
				statement();
				}
				break;
			case 5:
				_localctx = new ForStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(133);
				match(FOR);
				setState(134);
				match(LEFT_PARENTNESS);
				setState(137);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(135);
					((ForStatContext)_localctx).initexpr = expression(0);
					}
					break;
				case 2:
					{
					setState(136);
					((ForStatContext)_localctx).initvar = variable_list();
					}
					break;
				}
				setState(139);
				match(SEMICOLON);
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << LEFT_PARENTNESS) | (1L << ADD_ADD) | (1L << MINUS_MINUS) | (1L << ADD) | (1L << MINUS) | (1L << NOT_LOGIC) | (1L << NOT_ARI))) != 0)) {
					{
					setState(140);
					((ForStatContext)_localctx).condition = expression(0);
					}
				}

				setState(143);
				match(SEMICOLON);
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << LEFT_PARENTNESS) | (1L << ADD_ADD) | (1L << MINUS_MINUS) | (1L << ADD) | (1L << MINUS) | (1L << NOT_LOGIC) | (1L << NOT_ARI))) != 0)) {
					{
					setState(144);
					((ForStatContext)_localctx).incr = expression(0);
					}
				}

				setState(147);
				match(RIGHT_PARENTNESS);
				setState(148);
				statement();
				}
				break;
			case 6:
				_localctx = new ContinueStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(149);
				match(CONTINUE);
				setState(150);
				match(SEMICOLON);
				}
				break;
			case 7:
				_localctx = new BreakStatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(151);
				match(BREAK);
				setState(152);
				match(SEMICOLON);
				}
				break;
			case 8:
				_localctx = new ReturnStatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(153);
				match(RETURN);
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << LEFT_PARENTNESS) | (1L << ADD_ADD) | (1L << MINUS_MINUS) | (1L << ADD) | (1L << MINUS) | (1L << NOT_LOGIC) | (1L << NOT_ARI))) != 0)) {
					{
					setState(154);
					expression(0);
					}
				}

				setState(157);
				match(SEMICOLON);
				}
				break;
			case 9:
				_localctx = new ExprStatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(158);
				expression(0);
				setState(159);
				match(SEMICOLON);
				}
				break;
			case 10:
				_localctx = new EmptyStatContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(161);
				match(SEMICOLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExpressionContext {
		public TerminalNode NEW() { return getToken(MymxParser.NEW, 0); }
		public NewtypeContext newtype() {
			return getRuleContext(NewtypeContext.class,0);
		}
		public NewExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrefixExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ADD_ADD() { return getToken(MymxParser.ADD_ADD, 0); }
		public TerminalNode MINUS_MINUS() { return getToken(MymxParser.MINUS_MINUS, 0); }
		public TerminalNode ADD() { return getToken(MymxParser.ADD, 0); }
		public TerminalNode MINUS() { return getToken(MymxParser.MINUS, 0); }
		public TerminalNode NOT_LOGIC() { return getToken(MymxParser.NOT_LOGIC, 0); }
		public TerminalNode NOT_ARI() { return getToken(MymxParser.NOT_ARI, 0); }
		public PrefixExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterPrefixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitPrefixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitPrefixExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LEFT_PARENTNESS() { return getToken(MymxParser.LEFT_PARENTNESS, 0); }
		public TerminalNode RIGHT_PARENTNESS() { return getToken(MymxParser.RIGHT_PARENTNESS, 0); }
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public FuncExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubarrayExprContext extends ExpressionContext {
		public ExpressionContext identifier;
		public ExpressionContext index;
		public TerminalNode LEFT_BRACKET() { return getToken(MymxParser.LEFT_BRACKET, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(MymxParser.RIGHT_BRACKET, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SubarrayExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterSubarrayExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitSubarrayExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitSubarrayExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuffixExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ADD_ADD() { return getToken(MymxParser.ADD_ADD, 0); }
		public TerminalNode MINUS_MINUS() { return getToken(MymxParser.MINUS_MINUS, 0); }
		public SuffixExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterSuffixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitSuffixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitSuffixExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MymxParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public MemberExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitMemberExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomExprContext extends ExpressionContext {
		public TerminalNode THIS() { return getToken(MymxParser.THIS, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(MymxParser.BOOL_LITERAL, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(MymxParser.INTEGER_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(MymxParser.STRING_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(MymxParser.NULL_LITERAL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public TerminalNode LEFT_PARENTNESS() { return getToken(MymxParser.LEFT_PARENTNESS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PARENTNESS() { return getToken(MymxParser.RIGHT_PARENTNESS, 0); }
		public AtomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitAtomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token op;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(MymxParser.ADD, 0); }
		public TerminalNode MINUS() { return getToken(MymxParser.MINUS, 0); }
		public TerminalNode MUL() { return getToken(MymxParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(MymxParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MymxParser.MOD, 0); }
		public TerminalNode LEFT_SHIFT() { return getToken(MymxParser.LEFT_SHIFT, 0); }
		public TerminalNode RIGHT_SHIFT() { return getToken(MymxParser.RIGHT_SHIFT, 0); }
		public TerminalNode LESS() { return getToken(MymxParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(MymxParser.GREATER, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(MymxParser.LESS_EQUAL, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(MymxParser.GREATER_EQUAL, 0); }
		public TerminalNode EQUAL() { return getToken(MymxParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(MymxParser.NOT_EQUAL, 0); }
		public TerminalNode AND_ARI() { return getToken(MymxParser.AND_ARI, 0); }
		public TerminalNode XOR_ARI() { return getToken(MymxParser.XOR_ARI, 0); }
		public TerminalNode OR_ARI() { return getToken(MymxParser.OR_ARI, 0); }
		public TerminalNode AND_LOGIC() { return getToken(MymxParser.AND_LOGIC, 0); }
		public TerminalNode OR_LOGIC() { return getToken(MymxParser.OR_LOGIC, 0); }
		public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitBinaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignExprContext extends ExpressionContext {
		public ExpressionContext lhs;
		public Token op;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(MymxParser.ASSIGN, 0); }
		public AssignExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case THIS:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(165);
				match(THIS);
				}
				break;
			case BOOL_LITERAL:
			case INTEGER_LITERAL:
			case STRING_LITERAL:
			case NULL_LITERAL:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(166);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(167);
				match(IDENTIFIER);
				}
				break;
			case NEW:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(168);
				match(NEW);
				setState(169);
				newtype();
				}
				break;
			case ADD_ADD:
			case MINUS_MINUS:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(170);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD_ADD || _la==MINUS_MINUS) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(171);
				expression(10);
				}
				break;
			case ADD:
			case MINUS:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(172);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==MINUS) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(173);
				expression(9);
				}
				break;
			case NOT_LOGIC:
			case NOT_ARI:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==NOT_LOGIC || _la==NOT_ARI) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(175);
				expression(8);
				}
				break;
			case LEFT_PARENTNESS:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(176);
				match(LEFT_PARENTNESS);
				setState(177);
				expression(0);
				setState(178);
				match(RIGHT_PARENTNESS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(218);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(216);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(182);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(183);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==MINUS) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(184);
						((BinaryExprContext)_localctx).rhs = expression(8);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(185);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(186);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(187);
						((BinaryExprContext)_localctx).rhs = expression(7);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(188);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(189);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LEFT_SHIFT || _la==RIGHT_SHIFT) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(190);
						((BinaryExprContext)_localctx).rhs = expression(6);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(191);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(192);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS) | (1L << LESS_EQUAL) | (1L << GREATER) | (1L << GREATER_EQUAL) | (1L << EQUAL) | (1L << NOT_EQUAL))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(193);
						((BinaryExprContext)_localctx).rhs = expression(5);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(194);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(195);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND_ARI) | (1L << OR_ARI) | (1L << AND_LOGIC) | (1L << OR_LOGIC) | (1L << XOR_ARI))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(196);
						((BinaryExprContext)_localctx).rhs = expression(4);
						}
						break;
					case 6:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						((AssignExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(197);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(198);
						((AssignExprContext)_localctx).op = match(ASSIGN);
						setState(199);
						((AssignExprContext)_localctx).rhs = expression(2);
						}
						break;
					case 7:
						{
						_localctx = new SuffixExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(200);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(201);
						((SuffixExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD_ADD || _la==MINUS_MINUS) ) {
							((SuffixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 8:
						{
						_localctx = new MemberExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(202);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(203);
						match(DOT);
						setState(204);
						match(IDENTIFIER);
						}
						break;
					case 9:
						{
						_localctx = new FuncExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(205);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(206);
						match(LEFT_PARENTNESS);
						setState(208);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << LEFT_PARENTNESS) | (1L << ADD_ADD) | (1L << MINUS_MINUS) | (1L << ADD) | (1L << MINUS) | (1L << NOT_LOGIC) | (1L << NOT_ARI))) != 0)) {
							{
							setState(207);
							expression_list();
							}
						}

						setState(210);
						match(RIGHT_PARENTNESS);
						}
						break;
					case 10:
						{
						_localctx = new SubarrayExprContext(new ExpressionContext(_parentctx, _parentState));
						((SubarrayExprContext)_localctx).identifier = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(211);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(212);
						match(LEFT_BRACKET);
						setState(213);
						((SubarrayExprContext)_localctx).index = expression(0);
						setState(214);
						match(RIGHT_BRACKET);
						}
						break;
					}
					} 
				}
				setState(220);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MymxParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(MymxParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(MymxParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LEFT_BRACKET() { return getToken(MymxParser.LEFT_BRACKET, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(MymxParser.RIGHT_BRACKET, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				{
				setState(222);
				match(INT);
				}
				break;
			case BOOL:
				{
				setState(223);
				match(BOOL);
				}
				break;
			case STRING:
				{
				setState(224);
				match(STRING);
				}
				break;
			case IDENTIFIER:
				{
				setState(225);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(233);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(228);
					if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
					setState(229);
					match(LEFT_BRACKET);
					setState(230);
					match(RIGHT_BRACKET);
					}
					} 
				}
				setState(235);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NewtypeContext extends ParserRuleContext {
		public NewtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newtype; }
	 
		public NewtypeContext() { }
		public void copyFrom(NewtypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BasicNewtypeContext extends NewtypeContext {
		public TerminalNode INT() { return getToken(MymxParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(MymxParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(MymxParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public BasicNewtypeContext(NewtypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterBasicNewtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitBasicNewtype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitBasicNewtype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ErrorNewtypeContext extends NewtypeContext {
		public TerminalNode INT() { return getToken(MymxParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(MymxParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(MymxParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public List<TerminalNode> LEFT_BRACKET() { return getTokens(MymxParser.LEFT_BRACKET); }
		public TerminalNode LEFT_BRACKET(int i) {
			return getToken(MymxParser.LEFT_BRACKET, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RIGHT_BRACKET() { return getTokens(MymxParser.RIGHT_BRACKET); }
		public TerminalNode RIGHT_BRACKET(int i) {
			return getToken(MymxParser.RIGHT_BRACKET, i);
		}
		public ErrorNewtypeContext(NewtypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterErrorNewtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitErrorNewtype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitErrorNewtype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassNewtypeContext extends NewtypeContext {
		public TerminalNode LEFT_PARENTNESS() { return getToken(MymxParser.LEFT_PARENTNESS, 0); }
		public TerminalNode RIGHT_PARENTNESS() { return getToken(MymxParser.RIGHT_PARENTNESS, 0); }
		public TerminalNode INT() { return getToken(MymxParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(MymxParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(MymxParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public ClassNewtypeContext(NewtypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterClassNewtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitClassNewtype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitClassNewtype(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayNewtypeContext extends NewtypeContext {
		public TerminalNode INT() { return getToken(MymxParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(MymxParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(MymxParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public List<TerminalNode> LEFT_BRACKET() { return getTokens(MymxParser.LEFT_BRACKET); }
		public TerminalNode LEFT_BRACKET(int i) {
			return getToken(MymxParser.LEFT_BRACKET, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RIGHT_BRACKET() { return getTokens(MymxParser.RIGHT_BRACKET); }
		public TerminalNode RIGHT_BRACKET(int i) {
			return getToken(MymxParser.RIGHT_BRACKET, i);
		}
		public ArrayNewtypeContext(NewtypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterArrayNewtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitArrayNewtype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitArrayNewtype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewtypeContext newtype() throws RecognitionException {
		NewtypeContext _localctx = new NewtypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_newtype);
		int _la;
		try {
			int _alt;
			setState(279);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				_localctx = new ErrorNewtypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(241); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(237);
						match(LEFT_BRACKET);
						setState(238);
						expression(0);
						setState(239);
						match(RIGHT_BRACKET);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(243); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(247); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(245);
						match(LEFT_BRACKET);
						setState(246);
						match(RIGHT_BRACKET);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(249); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(255); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(251);
						match(LEFT_BRACKET);
						setState(252);
						expression(0);
						setState(253);
						match(RIGHT_BRACKET);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(257); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				_localctx = new ArrayNewtypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(264); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(260);
						match(LEFT_BRACKET);
						setState(261);
						expression(0);
						setState(262);
						match(RIGHT_BRACKET);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(266); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(272);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(268);
						match(LEFT_BRACKET);
						setState(269);
						match(RIGHT_BRACKET);
						}
						} 
					}
					setState(274);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				}
				}
				break;
			case 3:
				_localctx = new ClassNewtypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(275);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(276);
				match(LEFT_PARENTNESS);
				setState(277);
				match(RIGHT_PARENTNESS);
				}
				break;
			case 4:
				_localctx = new BasicNewtypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(278);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 12:
			return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		case 6:
			return precpred(_ctx, 15);
		case 7:
			return precpred(_ctx, 14);
		case 8:
			return precpred(_ctx, 13);
		case 9:
			return precpred(_ctx, 11);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u011c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\7\2#\n\2\f\2\16"+
		"\2&\13\2\3\3\3\3\5\3*\n\3\3\3\3\3\3\3\5\3/\n\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\7\4:\n\4\f\4\16\4=\13\4\3\4\3\4\3\4\3\5\3\5\3\5\5\5E\n\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\7\7P\n\7\f\7\16\7S\13\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\7\b[\n\b\f\b\16\b^\13\b\3\t\3\t\3\t\3\t\7\td\n\t\f\t\16"+
		"\tg\13\t\3\n\3\n\3\n\5\nl\n\n\3\13\3\13\7\13p\n\13\f\13\16\13s\13\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0080\n\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u008c\n\f\3\f\3\f\5\f\u0090\n\f\3\f\3"+
		"\f\5\f\u0094\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u009e\n\f\3\f\3\f"+
		"\3\f\3\f\3\f\5\f\u00a5\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\5\r\u00b7\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5"+
		"\r\u00d3\n\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00db\n\r\f\r\16\r\u00de\13\r"+
		"\3\16\3\16\3\16\3\16\3\16\5\16\u00e5\n\16\3\16\3\16\3\16\7\16\u00ea\n"+
		"\16\f\16\16\16\u00ed\13\16\3\17\3\17\3\17\3\17\3\17\6\17\u00f4\n\17\r"+
		"\17\16\17\u00f5\3\17\3\17\6\17\u00fa\n\17\r\17\16\17\u00fb\3\17\3\17\3"+
		"\17\3\17\6\17\u0102\n\17\r\17\16\17\u0103\3\17\3\17\3\17\3\17\3\17\6\17"+
		"\u010b\n\17\r\17\16\17\u010c\3\17\3\17\7\17\u0111\n\17\f\17\16\17\u0114"+
		"\13\17\3\17\3\17\3\17\3\17\5\17\u011a\n\17\3\17\2\4\30\32\20\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\2\13\3\2\7\n\3\2\60\61\3\2\62\63\3\2<=\3\2"+
		"\64\66\3\2-.\3\2\',\3\2\67;\4\2\13\r\34\34\2\u0149\2$\3\2\2\2\4)\3\2\2"+
		"\2\6\63\3\2\2\2\bA\3\2\2\2\nI\3\2\2\2\fL\3\2\2\2\16T\3\2\2\2\20_\3\2\2"+
		"\2\22h\3\2\2\2\24m\3\2\2\2\26\u00a4\3\2\2\2\30\u00b6\3\2\2\2\32\u00e4"+
		"\3\2\2\2\34\u0119\3\2\2\2\36#\5\4\3\2\37#\5\6\4\2 #\5\n\6\2!#\7\37\2\2"+
		"\"\36\3\2\2\2\"\37\3\2\2\2\" \3\2\2\2\"!\3\2\2\2#&\3\2\2\2$\"\3\2\2\2"+
		"$%\3\2\2\2%\3\3\2\2\2&$\3\2\2\2\'*\7\17\2\2(*\5\32\16\2)\'\3\2\2\2)(\3"+
		"\2\2\2*+\3\2\2\2+,\7\34\2\2,.\7!\2\2-/\5\16\b\2.-\3\2\2\2./\3\2\2\2/\60"+
		"\3\2\2\2\60\61\7\"\2\2\61\62\5\24\13\2\62\5\3\2\2\2\63\64\7\32\2\2\64"+
		"\65\7\34\2\2\65;\7%\2\2\66:\5\n\6\2\67:\5\4\3\28:\5\b\5\29\66\3\2\2\2"+
		"9\67\3\2\2\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2\2=;\3\2\2"+
		"\2>?\7&\2\2?@\7\37\2\2@\7\3\2\2\2AB\7\34\2\2BD\7!\2\2CE\5\16\b\2DC\3\2"+
		"\2\2DE\3\2\2\2EF\3\2\2\2FG\7\"\2\2GH\5\24\13\2H\t\3\2\2\2IJ\5\20\t\2J"+
		"K\7\37\2\2K\13\3\2\2\2LQ\5\30\r\2MN\7\36\2\2NP\5\30\r\2OM\3\2\2\2PS\3"+
		"\2\2\2QO\3\2\2\2QR\3\2\2\2R\r\3\2\2\2SQ\3\2\2\2TU\5\32\16\2U\\\7\34\2"+
		"\2VW\7\36\2\2WX\5\32\16\2XY\7\34\2\2Y[\3\2\2\2ZV\3\2\2\2[^\3\2\2\2\\Z"+
		"\3\2\2\2\\]\3\2\2\2]\17\3\2\2\2^\\\3\2\2\2_`\5\32\16\2`e\5\22\n\2ab\7"+
		"\36\2\2bd\5\22\n\2ca\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\21\3\2\2\2"+
		"ge\3\2\2\2hk\7\34\2\2ij\7/\2\2jl\5\30\r\2ki\3\2\2\2kl\3\2\2\2l\23\3\2"+
		"\2\2mq\7%\2\2np\5\26\f\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2"+
		"\2\2sq\3\2\2\2tu\7&\2\2u\25\3\2\2\2v\u00a5\5\24\13\2w\u00a5\5\n\6\2xy"+
		"\7\22\2\2yz\7!\2\2z{\5\30\r\2{|\7\"\2\2|\177\5\26\f\2}~\7\23\2\2~\u0080"+
		"\5\26\f\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u00a5\3\2\2\2\u0081\u0082"+
		"\7\25\2\2\u0082\u0083\7!\2\2\u0083\u0084\5\30\r\2\u0084\u0085\7\"\2\2"+
		"\u0085\u0086\5\26\f\2\u0086\u00a5\3\2\2\2\u0087\u0088\7\24\2\2\u0088\u008b"+
		"\7!\2\2\u0089\u008c\5\30\r\2\u008a\u008c\5\20\t\2\u008b\u0089\3\2\2\2"+
		"\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008f"+
		"\7\37\2\2\u008e\u0090\5\30\r\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2"+
		"\u0090\u0091\3\2\2\2\u0091\u0093\7\37\2\2\u0092\u0094\5\30\r\2\u0093\u0092"+
		"\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\7\"\2\2\u0096"+
		"\u00a5\5\26\f\2\u0097\u0098\7\27\2\2\u0098\u00a5\7\37\2\2\u0099\u009a"+
		"\7\26\2\2\u009a\u00a5\7\37\2\2\u009b\u009d\7\30\2\2\u009c\u009e\5\30\r"+
		"\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a5"+
		"\7\37\2\2\u00a0\u00a1\5\30\r\2\u00a1\u00a2\7\37\2\2\u00a2\u00a5\3\2\2"+
		"\2\u00a3\u00a5\7\37\2\2\u00a4v\3\2\2\2\u00a4w\3\2\2\2\u00a4x\3\2\2\2\u00a4"+
		"\u0081\3\2\2\2\u00a4\u0087\3\2\2\2\u00a4\u0097\3\2\2\2\u00a4\u0099\3\2"+
		"\2\2\u00a4\u009b\3\2\2\2\u00a4\u00a0\3\2\2\2\u00a4\u00a3\3\2\2\2\u00a5"+
		"\27\3\2\2\2\u00a6\u00a7\b\r\1\2\u00a7\u00b7\7\33\2\2\u00a8\u00b7\t\2\2"+
		"\2\u00a9\u00b7\7\34\2\2\u00aa\u00ab\7\31\2\2\u00ab\u00b7\5\34\17\2\u00ac"+
		"\u00ad\t\3\2\2\u00ad\u00b7\5\30\r\f\u00ae\u00af\t\4\2\2\u00af\u00b7\5"+
		"\30\r\13\u00b0\u00b1\t\5\2\2\u00b1\u00b7\5\30\r\n\u00b2\u00b3\7!\2\2\u00b3"+
		"\u00b4\5\30\r\2\u00b4\u00b5\7\"\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00a6\3"+
		"\2\2\2\u00b6\u00a8\3\2\2\2\u00b6\u00a9\3\2\2\2\u00b6\u00aa\3\2\2\2\u00b6"+
		"\u00ac\3\2\2\2\u00b6\u00ae\3\2\2\2\u00b6\u00b0\3\2\2\2\u00b6\u00b2\3\2"+
		"\2\2\u00b7\u00dc\3\2\2\2\u00b8\u00b9\f\t\2\2\u00b9\u00ba\t\4\2\2\u00ba"+
		"\u00db\5\30\r\n\u00bb\u00bc\f\b\2\2\u00bc\u00bd\t\6\2\2\u00bd\u00db\5"+
		"\30\r\t\u00be\u00bf\f\7\2\2\u00bf\u00c0\t\7\2\2\u00c0\u00db\5\30\r\b\u00c1"+
		"\u00c2\f\6\2\2\u00c2\u00c3\t\b\2\2\u00c3\u00db\5\30\r\7\u00c4\u00c5\f"+
		"\5\2\2\u00c5\u00c6\t\t\2\2\u00c6\u00db\5\30\r\6\u00c7\u00c8\f\4\2\2\u00c8"+
		"\u00c9\7/\2\2\u00c9\u00db\5\30\r\4\u00ca\u00cb\f\21\2\2\u00cb\u00db\t"+
		"\3\2\2\u00cc\u00cd\f\20\2\2\u00cd\u00ce\7 \2\2\u00ce\u00db\7\34\2\2\u00cf"+
		"\u00d0\f\17\2\2\u00d0\u00d2\7!\2\2\u00d1\u00d3\5\f\7\2\u00d2\u00d1\3\2"+
		"\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00db\7\"\2\2\u00d5"+
		"\u00d6\f\r\2\2\u00d6\u00d7\7#\2\2\u00d7\u00d8\5\30\r\2\u00d8\u00d9\7$"+
		"\2\2\u00d9\u00db\3\2\2\2\u00da\u00b8\3\2\2\2\u00da\u00bb\3\2\2\2\u00da"+
		"\u00be\3\2\2\2\u00da\u00c1\3\2\2\2\u00da\u00c4\3\2\2\2\u00da\u00c7\3\2"+
		"\2\2\u00da\u00ca\3\2\2\2\u00da\u00cc\3\2\2\2\u00da\u00cf\3\2\2\2\u00da"+
		"\u00d5\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2"+
		"\2\2\u00dd\31\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\b\16\1\2\u00e0\u00e5"+
		"\7\13\2\2\u00e1\u00e5\7\f\2\2\u00e2\u00e5\7\r\2\2\u00e3\u00e5\7\34\2\2"+
		"\u00e4\u00df\3\2\2\2\u00e4\u00e1\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e3"+
		"\3\2\2\2\u00e5\u00eb\3\2\2\2\u00e6\u00e7\f\7\2\2\u00e7\u00e8\7#\2\2\u00e8"+
		"\u00ea\7$\2\2\u00e9\u00e6\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2"+
		"\2\2\u00eb\u00ec\3\2\2\2\u00ec\33\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f3"+
		"\t\n\2\2\u00ef\u00f0\7#\2\2\u00f0\u00f1\5\30\r\2\u00f1\u00f2\7$\2\2\u00f2"+
		"\u00f4\3\2\2\2\u00f3\u00ef\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f3\3\2"+
		"\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f8\7#\2\2\u00f8"+
		"\u00fa\7$\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2"+
		"\2\2\u00fb\u00fc\3\2\2\2\u00fc\u0101\3\2\2\2\u00fd\u00fe\7#\2\2\u00fe"+
		"\u00ff\5\30\r\2\u00ff\u0100\7$\2\2\u0100\u0102\3\2\2\2\u0101\u00fd\3\2"+
		"\2\2\u0102\u0103\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104"+
		"\u011a\3\2\2\2\u0105\u010a\t\n\2\2\u0106\u0107\7#\2\2\u0107\u0108\5\30"+
		"\r\2\u0108\u0109\7$\2\2\u0109\u010b\3\2\2\2\u010a\u0106\3\2\2\2\u010b"+
		"\u010c\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u0112\3\2"+
		"\2\2\u010e\u010f\7#\2\2\u010f\u0111\7$\2\2\u0110\u010e\3\2\2\2\u0111\u0114"+
		"\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u011a\3\2\2\2\u0114"+
		"\u0112\3\2\2\2\u0115\u0116\t\n\2\2\u0116\u0117\7!\2\2\u0117\u011a\7\""+
		"\2\2\u0118\u011a\t\n\2\2\u0119\u00ee\3\2\2\2\u0119\u0105\3\2\2\2\u0119"+
		"\u0115\3\2\2\2\u0119\u0118\3\2\2\2\u011a\35\3\2\2\2 \"$).9;DQ\\ekq\177"+
		"\u008b\u008f\u0093\u009d\u00a4\u00b6\u00d2\u00da\u00dc\u00e4\u00eb\u00f5"+
		"\u00fb\u0103\u010c\u0112\u0119";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
// Generated from D:/Compiler\Mymx.g4 by ANTLR 4.9
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, INT=33, BOOL=34, STRING=35, NULL=36, VOID=37, TRUE=38, FALSE=39, 
		IF=40, ELSE=41, FOR=42, WHILE=43, BREAK=44, CONTINUE=45, RETURN=46, NEW=47, 
		CLASS=48, THIS=49, IDENTIFIER=50, BOOL_LITERAL=51, INTEGER_LITERAL=52, 
		STRING_LITERAL=53, NULL_LITERAL=54, WHITESPACE=55, LINECOMMENT=56, BLOCKCOMMENT=57;
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
			null, "';'", "'('", "')'", "'{'", "'}'", "','", "'='", "'++'", "'--'", 
			"'.'", "'['", "']'", "'+'", "'-'", "'!'", "'~'", "'*'", "'/'", "'%'", 
			"'<<'", "'>>'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='", "'&'", 
			"'^'", "'|'", "'&&'", "'||'", "'int'", "'bool'", "'string'", "'null'", 
			"'void'", "'true'", "'false'", "'if'", "'else'", "'for'", "'while'", 
			"'break'", "'continue'", "'return'", "'new'", "'class'", "'this'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "INT", "BOOL", 
			"STRING", "NULL", "VOID", "TRUE", "FALSE", "IF", "ELSE", "FOR", "WHILE", 
			"BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", "THIS", "IDENTIFIER", 
			"BOOL_LITERAL", "INTEGER_LITERAL", "STRING_LITERAL", "NULL_LITERAL", 
			"WHITESPACE", "LINECOMMENT", "BLOCKCOMMENT"
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << IDENTIFIER))) != 0)) {
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
					match(T__0);
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
			match(T__1);
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
			match(T__2);
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
			match(T__3);
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
			match(T__4);
			setState(61);
			match(T__0);
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
			match(T__1);
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
			match(T__2);
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
			match(T__0);
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
			while (_la==T__5) {
				{
				{
				setState(75);
				match(T__5);
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
			while (_la==T__5) {
				{
				{
				setState(84);
				match(T__5);
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
			while (_la==T__5) {
				{
				{
				setState(95);
				match(T__5);
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
			if (_la==T__6) {
				{
				setState(103);
				match(T__6);
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
			match(T__3);
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL))) != 0)) {
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
			match(T__4);
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
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public Var_def_unitContext var_def_unit() {
			return getRuleContext(Var_def_unitContext.class,0);
		}
		public TerminalNode IF() { return getToken(MymxParser.IF, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MymxParser.ELSE, 0); }
		public TerminalNode WHILE() { return getToken(MymxParser.WHILE, 0); }
		public TerminalNode FOR() { return getToken(MymxParser.FOR, 0); }
		public Variable_listContext variable_list() {
			return getRuleContext(Variable_listContext.class,0);
		}
		public TerminalNode CONTINUE() { return getToken(MymxParser.CONTINUE, 0); }
		public TerminalNode BREAK() { return getToken(MymxParser.BREAK, 0); }
		public TerminalNode RETURN() { return getToken(MymxParser.RETURN, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitStatement(this);
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
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				suite();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				var_def_unit();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				match(IF);
				setState(119);
				match(T__1);
				setState(120);
				expression(0);
				setState(121);
				match(T__2);
				setState(122);
				statement();
				setState(125);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(123);
					match(ELSE);
					setState(124);
					statement();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(127);
				match(WHILE);
				setState(128);
				match(T__1);
				setState(129);
				expression(0);
				setState(130);
				match(T__2);
				setState(131);
				statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(133);
				match(FOR);
				setState(134);
				match(T__1);
				setState(137);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(135);
					expression(0);
					}
					break;
				case 2:
					{
					setState(136);
					variable_list();
					}
					break;
				}
				setState(139);
				match(T__0);
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL))) != 0)) {
					{
					setState(140);
					expression(0);
					}
				}

				setState(143);
				match(T__0);
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL))) != 0)) {
					{
					setState(144);
					expression(0);
					}
				}

				setState(147);
				match(T__2);
				setState(148);
				statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(149);
				match(CONTINUE);
				setState(150);
				match(T__0);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(151);
				match(BREAK);
				setState(152);
				match(T__0);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(153);
				match(RETURN);
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL))) != 0)) {
					{
					setState(154);
					expression(0);
					}
				}

				setState(157);
				match(T__0);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL))) != 0)) {
					{
					setState(158);
					expression(0);
					}
				}

				setState(161);
				match(T__0);
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
		public TerminalNode THIS() { return getToken(MymxParser.THIS, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(MymxParser.BOOL_LITERAL, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(MymxParser.INTEGER_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(MymxParser.STRING_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(MymxParser.NULL_LITERAL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode NEW() { return getToken(MymxParser.NEW, 0); }
		public NewtypeContext newtype() {
			return getRuleContext(NewtypeContext.class,0);
		}
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitExpression(this);
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
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case THIS:
				{
				setState(165);
				match(THIS);
				}
				break;
			case BOOL_LITERAL:
				{
				setState(166);
				match(BOOL_LITERAL);
				}
				break;
			case INTEGER_LITERAL:
				{
				setState(167);
				match(INTEGER_LITERAL);
				}
				break;
			case STRING_LITERAL:
				{
				setState(168);
				match(STRING_LITERAL);
				}
				break;
			case NULL_LITERAL:
				{
				setState(169);
				match(NULL_LITERAL);
				}
				break;
			case IDENTIFIER:
				{
				setState(170);
				match(IDENTIFIER);
				}
				break;
			case T__7:
			case T__8:
				{
				setState(171);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(172);
				expression(11);
				}
				break;
			case T__12:
			case T__13:
				{
				setState(173);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(174);
				expression(10);
				}
				break;
			case T__14:
			case T__15:
				{
				setState(175);
				_la = _input.LA(1);
				if ( !(_la==T__14 || _la==T__15) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(176);
				expression(9);
				}
				break;
			case T__1:
				{
				setState(177);
				match(T__1);
				setState(178);
				expression(0);
				setState(179);
				match(T__2);
				}
				break;
			case NEW:
				{
				setState(181);
				match(NEW);
				setState(182);
				newtype();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(221);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(219);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(185);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(186);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__13) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(187);
						expression(9);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(188);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(189);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(190);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(191);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(192);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(193);
						expression(7);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(194);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(195);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(196);
						expression(6);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(197);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(198);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(199);
						expression(5);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(200);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(201);
						match(T__6);
						setState(202);
						expression(3);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(203);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(204);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
						_errHandler.recoverInline(this);
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
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(205);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(206);
						match(T__9);
						setState(207);
						match(IDENTIFIER);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(209);
						match(T__1);
						setState(211);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << NEW) | (1L << THIS) | (1L << IDENTIFIER) | (1L << BOOL_LITERAL) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL))) != 0)) {
							{
							setState(210);
							expression_list();
							}
						}

						setState(213);
						match(T__2);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(215);
						match(T__10);
						setState(216);
						expression(0);
						setState(217);
						match(T__11);
						}
						break;
					}
					} 
				}
				setState(223);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				{
				setState(225);
				match(INT);
				}
				break;
			case BOOL:
				{
				setState(226);
				match(BOOL);
				}
				break;
			case STRING:
				{
				setState(227);
				match(STRING);
				}
				break;
			case IDENTIFIER:
				{
				setState(228);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(236);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(231);
					if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
					setState(232);
					match(T__10);
					setState(233);
					match(T__11);
					}
					} 
				}
				setState(238);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
		public TerminalNode INT() { return getToken(MymxParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(MymxParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(MymxParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MymxParser.IDENTIFIER, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NewtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newtype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).enterNewtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MymxListener ) ((MymxListener)listener).exitNewtype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MymxVisitor ) return ((MymxVisitor<? extends T>)visitor).visitNewtype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewtypeContext newtype() throws RecognitionException {
		NewtypeContext _localctx = new NewtypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_newtype);
		int _la;
		try {
			int _alt;
			setState(285);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(240);
				match(BOOL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(241);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(242);
				match(IDENTIFIER);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(243);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(244);
				match(T__1);
				setState(245);
				match(T__2);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(246);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(251); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(247);
						match(T__10);
						setState(248);
						expression(0);
						setState(249);
						match(T__11);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(253); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(255);
						match(T__10);
						setState(256);
						match(T__11);
						}
						} 
					}
					setState(261);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(262);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(267); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(263);
						match(T__10);
						setState(264);
						expression(0);
						setState(265);
						match(T__11);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(269); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(273); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(271);
						match(T__10);
						setState(272);
						match(T__11);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(275); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(281); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(277);
						match(T__10);
						setState(278);
						expression(0);
						setState(279);
						match(T__11);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(283); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 15);
		case 7:
			return precpred(_ctx, 14);
		case 8:
			return precpred(_ctx, 13);
		case 9:
			return precpred(_ctx, 12);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3;\u0122\4\2\t\2\4"+
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
		"\5\f\u00a2\n\f\3\f\5\f\u00a5\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00ba\n\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u00d6\n\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00de\n\r\f"+
		"\r\16\r\u00e1\13\r\3\16\3\16\3\16\3\16\3\16\5\16\u00e8\n\16\3\16\3\16"+
		"\3\16\7\16\u00ed\n\16\f\16\16\16\u00f0\13\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u00fe\n\17\r\17\16\17\u00ff\3"+
		"\17\3\17\7\17\u0104\n\17\f\17\16\17\u0107\13\17\3\17\3\17\3\17\3\17\3"+
		"\17\6\17\u010e\n\17\r\17\16\17\u010f\3\17\3\17\6\17\u0114\n\17\r\17\16"+
		"\17\u0115\3\17\3\17\3\17\3\17\6\17\u011c\n\17\r\17\16\17\u011d\5\17\u0120"+
		"\n\17\3\17\2\4\30\32\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\n\3\2\n"+
		"\13\3\2\17\20\3\2\21\22\3\2\23\25\3\2\26\27\3\2\30\35\3\2\36\"\4\2#%\64"+
		"\64\2\u0155\2$\3\2\2\2\4)\3\2\2\2\6\63\3\2\2\2\bA\3\2\2\2\nI\3\2\2\2\f"+
		"L\3\2\2\2\16T\3\2\2\2\20_\3\2\2\2\22h\3\2\2\2\24m\3\2\2\2\26\u00a4\3\2"+
		"\2\2\30\u00b9\3\2\2\2\32\u00e7\3\2\2\2\34\u011f\3\2\2\2\36#\5\4\3\2\37"+
		"#\5\6\4\2 #\5\n\6\2!#\7\3\2\2\"\36\3\2\2\2\"\37\3\2\2\2\" \3\2\2\2\"!"+
		"\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\3\3\2\2\2&$\3\2\2\2\'*\7\'\2"+
		"\2(*\5\32\16\2)\'\3\2\2\2)(\3\2\2\2*+\3\2\2\2+,\7\64\2\2,.\7\4\2\2-/\5"+
		"\16\b\2.-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\7\5\2\2\61\62\5\24\13\2"+
		"\62\5\3\2\2\2\63\64\7\62\2\2\64\65\7\64\2\2\65;\7\6\2\2\66:\5\n\6\2\67"+
		":\5\4\3\28:\5\b\5\29\66\3\2\2\29\67\3\2\2\298\3\2\2\2:=\3\2\2\2;9\3\2"+
		"\2\2;<\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\7\7\2\2?@\7\3\2\2@\7\3\2\2\2AB\7"+
		"\64\2\2BD\7\4\2\2CE\5\16\b\2DC\3\2\2\2DE\3\2\2\2EF\3\2\2\2FG\7\5\2\2G"+
		"H\5\24\13\2H\t\3\2\2\2IJ\5\20\t\2JK\7\3\2\2K\13\3\2\2\2LQ\5\30\r\2MN\7"+
		"\b\2\2NP\5\30\r\2OM\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2R\r\3\2\2\2S"+
		"Q\3\2\2\2TU\5\32\16\2U\\\7\64\2\2VW\7\b\2\2WX\5\32\16\2XY\7\64\2\2Y[\3"+
		"\2\2\2ZV\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\17\3\2\2\2^\\\3\2\2"+
		"\2_`\5\32\16\2`e\5\22\n\2ab\7\b\2\2bd\5\22\n\2ca\3\2\2\2dg\3\2\2\2ec\3"+
		"\2\2\2ef\3\2\2\2f\21\3\2\2\2ge\3\2\2\2hk\7\64\2\2ij\7\t\2\2jl\5\30\r\2"+
		"ki\3\2\2\2kl\3\2\2\2l\23\3\2\2\2mq\7\6\2\2np\5\26\f\2on\3\2\2\2ps\3\2"+
		"\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\7\7\2\2u\25\3\2\2\2v\u00a5"+
		"\5\24\13\2w\u00a5\5\n\6\2xy\7*\2\2yz\7\4\2\2z{\5\30\r\2{|\7\5\2\2|\177"+
		"\5\26\f\2}~\7+\2\2~\u0080\5\26\f\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080"+
		"\u00a5\3\2\2\2\u0081\u0082\7-\2\2\u0082\u0083\7\4\2\2\u0083\u0084\5\30"+
		"\r\2\u0084\u0085\7\5\2\2\u0085\u0086\5\26\f\2\u0086\u00a5\3\2\2\2\u0087"+
		"\u0088\7,\2\2\u0088\u008b\7\4\2\2\u0089\u008c\5\30\r\2\u008a\u008c\5\20"+
		"\t\2\u008b\u0089\3\2\2\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008d\3\2\2\2\u008d\u008f\7\3\2\2\u008e\u0090\5\30\r\2\u008f\u008e\3"+
		"\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\7\3\2\2\u0092"+
		"\u0094\5\30\r\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3"+
		"\2\2\2\u0095\u0096\7\5\2\2\u0096\u00a5\5\26\f\2\u0097\u0098\7/\2\2\u0098"+
		"\u00a5\7\3\2\2\u0099\u009a\7.\2\2\u009a\u00a5\7\3\2\2\u009b\u009d\7\60"+
		"\2\2\u009c\u009e\5\30\r\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u00a5\7\3\2\2\u00a0\u00a2\5\30\r\2\u00a1\u00a0\3"+
		"\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a5\7\3\2\2\u00a4"+
		"v\3\2\2\2\u00a4w\3\2\2\2\u00a4x\3\2\2\2\u00a4\u0081\3\2\2\2\u00a4\u0087"+
		"\3\2\2\2\u00a4\u0097\3\2\2\2\u00a4\u0099\3\2\2\2\u00a4\u009b\3\2\2\2\u00a4"+
		"\u00a1\3\2\2\2\u00a5\27\3\2\2\2\u00a6\u00a7\b\r\1\2\u00a7\u00ba\7\63\2"+
		"\2\u00a8\u00ba\7\65\2\2\u00a9\u00ba\7\66\2\2\u00aa\u00ba\7\67\2\2\u00ab"+
		"\u00ba\78\2\2\u00ac\u00ba\7\64\2\2\u00ad\u00ae\t\2\2\2\u00ae\u00ba\5\30"+
		"\r\r\u00af\u00b0\t\3\2\2\u00b0\u00ba\5\30\r\f\u00b1\u00b2\t\4\2\2\u00b2"+
		"\u00ba\5\30\r\13\u00b3\u00b4\7\4\2\2\u00b4\u00b5\5\30\r\2\u00b5\u00b6"+
		"\7\5\2\2\u00b6\u00ba\3\2\2\2\u00b7\u00b8\7\61\2\2\u00b8\u00ba\5\34\17"+
		"\2\u00b9\u00a6\3\2\2\2\u00b9\u00a8\3\2\2\2\u00b9\u00a9\3\2\2\2\u00b9\u00aa"+
		"\3\2\2\2\u00b9\u00ab\3\2\2\2\u00b9\u00ac\3\2\2\2\u00b9\u00ad\3\2\2\2\u00b9"+
		"\u00af\3\2\2\2\u00b9\u00b1\3\2\2\2\u00b9\u00b3\3\2\2\2\u00b9\u00b7\3\2"+
		"\2\2\u00ba\u00df\3\2\2\2\u00bb\u00bc\f\n\2\2\u00bc\u00bd\t\3\2\2\u00bd"+
		"\u00de\5\30\r\13\u00be\u00bf\f\t\2\2\u00bf\u00c0\t\5\2\2\u00c0\u00de\5"+
		"\30\r\n\u00c1\u00c2\f\b\2\2\u00c2\u00c3\t\6\2\2\u00c3\u00de\5\30\r\t\u00c4"+
		"\u00c5\f\7\2\2\u00c5\u00c6\t\7\2\2\u00c6\u00de\5\30\r\b\u00c7\u00c8\f"+
		"\6\2\2\u00c8\u00c9\t\b\2\2\u00c9\u00de\5\30\r\7\u00ca\u00cb\f\5\2\2\u00cb"+
		"\u00cc\7\t\2\2\u00cc\u00de\5\30\r\5\u00cd\u00ce\f\21\2\2\u00ce\u00de\t"+
		"\2\2\2\u00cf\u00d0\f\20\2\2\u00d0\u00d1\7\f\2\2\u00d1\u00de\7\64\2\2\u00d2"+
		"\u00d3\f\17\2\2\u00d3\u00d5\7\4\2\2\u00d4\u00d6\5\f\7\2\u00d5\u00d4\3"+
		"\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00de\7\5\2\2\u00d8"+
		"\u00d9\f\16\2\2\u00d9\u00da\7\r\2\2\u00da\u00db\5\30\r\2\u00db\u00dc\7"+
		"\16\2\2\u00dc\u00de\3\2\2\2\u00dd\u00bb\3\2\2\2\u00dd\u00be\3\2\2\2\u00dd"+
		"\u00c1\3\2\2\2\u00dd\u00c4\3\2\2\2\u00dd\u00c7\3\2\2\2\u00dd\u00ca\3\2"+
		"\2\2\u00dd\u00cd\3\2\2\2\u00dd\u00cf\3\2\2\2\u00dd\u00d2\3\2\2\2\u00dd"+
		"\u00d8\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2"+
		"\2\2\u00e0\31\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\b\16\1\2\u00e3\u00e8"+
		"\7#\2\2\u00e4\u00e8\7$\2\2\u00e5\u00e8\7%\2\2\u00e6\u00e8\7\64\2\2\u00e7"+
		"\u00e2\3\2\2\2\u00e7\u00e4\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e6\3\2"+
		"\2\2\u00e8\u00ee\3\2\2\2\u00e9\u00ea\f\7\2\2\u00ea\u00eb\7\r\2\2\u00eb"+
		"\u00ed\7\16\2\2\u00ec\u00e9\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3"+
		"\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\33\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1"+
		"\u0120\7#\2\2\u00f2\u0120\7$\2\2\u00f3\u0120\7%\2\2\u00f4\u0120\7\64\2"+
		"\2\u00f5\u00f6\t\t\2\2\u00f6\u00f7\7\4\2\2\u00f7\u0120\7\5\2\2\u00f8\u00fd"+
		"\t\t\2\2\u00f9\u00fa\7\r\2\2\u00fa\u00fb\5\30\r\2\u00fb\u00fc\7\16\2\2"+
		"\u00fc\u00fe\3\2\2\2\u00fd\u00f9\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u00fd"+
		"\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0105\3\2\2\2\u0101\u0102\7\r\2\2\u0102"+
		"\u0104\7\16\2\2\u0103\u0101\3\2\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3"+
		"\2\2\2\u0105\u0106\3\2\2\2\u0106\u0120\3\2\2\2\u0107\u0105\3\2\2\2\u0108"+
		"\u010d\t\t\2\2\u0109\u010a\7\r\2\2\u010a\u010b\5\30\r\2\u010b\u010c\7"+
		"\16\2\2\u010c\u010e\3\2\2\2\u010d\u0109\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u0112\7\r"+
		"\2\2\u0112\u0114\7\16\2\2\u0113\u0111\3\2\2\2\u0114\u0115\3\2\2\2\u0115"+
		"\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u011b\3\2\2\2\u0117\u0118\7\r"+
		"\2\2\u0118\u0119\5\30\r\2\u0119\u011a\7\16\2\2\u011a\u011c\3\2\2\2\u011b"+
		"\u0117\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2"+
		"\2\2\u011e\u0120\3\2\2\2\u011f\u00f1\3\2\2\2\u011f\u00f2\3\2\2\2\u011f"+
		"\u00f3\3\2\2\2\u011f\u00f4\3\2\2\2\u011f\u00f5\3\2\2\2\u011f\u00f8\3\2"+
		"\2\2\u011f\u0108\3\2\2\2\u0120\35\3\2\2\2!\"$).9;DQ\\ekq\177\u008b\u008f"+
		"\u0093\u009d\u00a1\u00a4\u00b9\u00d5\u00dd\u00df\u00e7\u00ee\u00ff\u0105"+
		"\u010f\u0115\u011d\u011f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
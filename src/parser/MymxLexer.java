// Generated from D:/Compiler/src/parser\Mymx.g4 by ANTLR 4.9
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MymxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Whitespace=1, Newline=2, BlockComment=3, LineComment=4, INT=5, BOOL=6, 
		STRING=7, NULL=8, VOID=9, TRUE=10, FALSE=11, IF=12, ELSE=13, FOR=14, WHILE=15, 
		BREAK=16, CONTINUE=17, RETURN=18, NEW=19, CLASS=20, THIS=21, IDENTIFIER=22, 
		BOOL_LITERAL=23, INTEGER_LITERAL=24, STRING_LITERAL=25, NULL_LITERAL=26, 
		SPACE=27, COMMA=28, SEMICOLON=29, DOT=30, LEFT_PARENTNESS=31, RIGHT_PARENTNESS=32, 
		LEFT_BRACKET=33, RIGHT_BRACKET=34, LEFT_BRACE=35, RIGHT_BRACE=36, LESS=37, 
		LESS_EQUAL=38, GREATER=39, GREATER_EQUAL=40, EQUAL=41, NOT_EQUAL=42, LEFT_SHIFT=43, 
		RIGHT_SHIFT=44, ASSIGN=45, ADD_ADD=46, MINUS_MINUS=47, ADD=48, MINUS=49, 
		MUL=50, DIV=51, MOD=52, AND_ARI=53, OR_ARI=54, AND_LOGIC=55, OR_LOGIC=56, 
		XOR_ARI=57, NOT_LOGIC=58, NOT_ARI=59;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Whitespace", "Newline", "BlockComment", "LineComment", "INT", "BOOL", 
			"STRING", "NULL", "VOID", "TRUE", "FALSE", "IF", "ELSE", "FOR", "WHILE", 
			"BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", "THIS", "IDENTIFIER", 
			"BOOL_LITERAL", "INTEGER_LITERAL", "STRING_LITERAL", "NULL_LITERAL", 
			"SPACE", "COMMA", "SEMICOLON", "DOT", "LEFT_PARENTNESS", "RIGHT_PARENTNESS", 
			"LEFT_BRACKET", "RIGHT_BRACKET", "LEFT_BRACE", "RIGHT_BRACE", "LESS", 
			"LESS_EQUAL", "GREATER", "GREATER_EQUAL", "EQUAL", "NOT_EQUAL", "LEFT_SHIFT", 
			"RIGHT_SHIFT", "ASSIGN", "ADD_ADD", "MINUS_MINUS", "ADD", "MINUS", "MUL", 
			"DIV", "MOD", "AND_ARI", "OR_ARI", "AND_LOGIC", "OR_LOGIC", "XOR_ARI", 
			"NOT_LOGIC", "NOT_ARI"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'int'", "'bool'", "'string'", "'null'", 
			"'void'", "'true'", "'false'", "'if'", "'else'", "'for'", "'while'", 
			"'break'", "'continue'", "'return'", "'new'", "'class'", "'this'", null, 
			null, null, null, null, "' '", "','", "';'", "'.'", "'('", "')'", "'['", 
			"']'", "'{'", "'}'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'<<'", 
			"'>>'", "'='", "'++'", "'--'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&'", 
			"'|'", "'&&'", "'||'", "'^'", "'!'", "'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Whitespace", "Newline", "BlockComment", "LineComment", "INT", 
			"BOOL", "STRING", "NULL", "VOID", "TRUE", "FALSE", "IF", "ELSE", "FOR", 
			"WHILE", "BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", "THIS", "IDENTIFIER", 
			"BOOL_LITERAL", "INTEGER_LITERAL", "STRING_LITERAL", "NULL_LITERAL", 
			"SPACE", "COMMA", "SEMICOLON", "DOT", "LEFT_PARENTNESS", "RIGHT_PARENTNESS", 
			"LEFT_BRACKET", "RIGHT_BRACKET", "LEFT_BRACE", "RIGHT_BRACE", "LESS", 
			"LESS_EQUAL", "GREATER", "GREATER_EQUAL", "EQUAL", "NOT_EQUAL", "LEFT_SHIFT", 
			"RIGHT_SHIFT", "ASSIGN", "ADD_ADD", "MINUS_MINUS", "ADD", "MINUS", "MUL", 
			"DIV", "MOD", "AND_ARI", "OR_ARI", "AND_LOGIC", "OR_LOGIC", "XOR_ARI", 
			"NOT_LOGIC", "NOT_ARI"
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


	public MymxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mymx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2=\u016c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2\6"+
		"\2{\n\2\r\2\16\2|\3\2\3\2\3\3\3\3\5\3\u0083\n\3\3\3\5\3\u0086\n\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\7\4\u008e\n\4\f\4\16\4\u0091\13\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\7\5\u009c\n\5\f\5\16\5\u009f\13\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\27\3\27\7\27\u0101\n\27\f\27\16\27\u0104\13\27\3\30\3\30\5\30\u0108\n"+
		"\30\3\31\3\31\3\31\7\31\u010d\n\31\f\31\16\31\u0110\13\31\5\31\u0112\n"+
		"\31\3\32\3\32\3\32\3\32\7\32\u0118\n\32\f\32\16\32\u011b\13\32\3\32\3"+
		"\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\""+
		"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3)\3*\3*\3*\3+\3"+
		"+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\62\3"+
		"\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\38\39\39\3"+
		"9\3:\3:\3;\3;\3<\3<\3\u008f\2=\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61"+
		"a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=\3\2\n\4\2\13\13\"\"\4\2\f\f\17\17"+
		"\4\2C\\c|\6\2\62;C\\aac|\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\5\2$$^^pp\2"+
		"\u0176\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2"+
		"G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3"+
		"\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2"+
		"\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2"+
		"m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\3z\3"+
		"\2\2\2\5\u0085\3\2\2\2\7\u0089\3\2\2\2\t\u0097\3\2\2\2\13\u00a2\3\2\2"+
		"\2\r\u00a6\3\2\2\2\17\u00ab\3\2\2\2\21\u00b2\3\2\2\2\23\u00b7\3\2\2\2"+
		"\25\u00bc\3\2\2\2\27\u00c1\3\2\2\2\31\u00c7\3\2\2\2\33\u00ca\3\2\2\2\35"+
		"\u00cf\3\2\2\2\37\u00d3\3\2\2\2!\u00d9\3\2\2\2#\u00df\3\2\2\2%\u00e8\3"+
		"\2\2\2\'\u00ef\3\2\2\2)\u00f3\3\2\2\2+\u00f9\3\2\2\2-\u00fe\3\2\2\2/\u0107"+
		"\3\2\2\2\61\u0111\3\2\2\2\63\u0113\3\2\2\2\65\u011e\3\2\2\2\67\u0120\3"+
		"\2\2\29\u0122\3\2\2\2;\u0124\3\2\2\2=\u0126\3\2\2\2?\u0128\3\2\2\2A\u012a"+
		"\3\2\2\2C\u012c\3\2\2\2E\u012e\3\2\2\2G\u0130\3\2\2\2I\u0132\3\2\2\2K"+
		"\u0134\3\2\2\2M\u0136\3\2\2\2O\u0139\3\2\2\2Q\u013b\3\2\2\2S\u013e\3\2"+
		"\2\2U\u0141\3\2\2\2W\u0144\3\2\2\2Y\u0147\3\2\2\2[\u014a\3\2\2\2]\u014c"+
		"\3\2\2\2_\u014f\3\2\2\2a\u0152\3\2\2\2c\u0154\3\2\2\2e\u0156\3\2\2\2g"+
		"\u0158\3\2\2\2i\u015a\3\2\2\2k\u015c\3\2\2\2m\u015e\3\2\2\2o\u0160\3\2"+
		"\2\2q\u0163\3\2\2\2s\u0166\3\2\2\2u\u0168\3\2\2\2w\u016a\3\2\2\2y{\t\2"+
		"\2\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\b\2\2\2\177"+
		"\4\3\2\2\2\u0080\u0082\7\17\2\2\u0081\u0083\7\f\2\2\u0082\u0081\3\2\2"+
		"\2\u0082\u0083\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0086\7\f\2\2\u0085\u0080"+
		"\3\2\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\3\2\2\u0088"+
		"\6\3\2\2\2\u0089\u008a\7\61\2\2\u008a\u008b\7,\2\2\u008b\u008f\3\2\2\2"+
		"\u008c\u008e\13\2\2\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u0090"+
		"\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u008f\3\2\2\2\u0092"+
		"\u0093\7,\2\2\u0093\u0094\7\61\2\2\u0094\u0095\3\2\2\2\u0095\u0096\b\4"+
		"\2\2\u0096\b\3\2\2\2\u0097\u0098\7\61\2\2\u0098\u0099\7\61\2\2\u0099\u009d"+
		"\3\2\2\2\u009a\u009c\n\3\2\2\u009b\u009a\3\2\2\2\u009c\u009f\3\2\2\2\u009d"+
		"\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a0\3\2\2\2\u009f\u009d\3\2"+
		"\2\2\u00a0\u00a1\b\5\2\2\u00a1\n\3\2\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4"+
		"\7p\2\2\u00a4\u00a5\7v\2\2\u00a5\f\3\2\2\2\u00a6\u00a7\7d\2\2\u00a7\u00a8"+
		"\7q\2\2\u00a8\u00a9\7q\2\2\u00a9\u00aa\7n\2\2\u00aa\16\3\2\2\2\u00ab\u00ac"+
		"\7u\2\2\u00ac\u00ad\7v\2\2\u00ad\u00ae\7t\2\2\u00ae\u00af\7k\2\2\u00af"+
		"\u00b0\7p\2\2\u00b0\u00b1\7i\2\2\u00b1\20\3\2\2\2\u00b2\u00b3\7p\2\2\u00b3"+
		"\u00b4\7w\2\2\u00b4\u00b5\7n\2\2\u00b5\u00b6\7n\2\2\u00b6\22\3\2\2\2\u00b7"+
		"\u00b8\7x\2\2\u00b8\u00b9\7q\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7f\2\2"+
		"\u00bb\24\3\2\2\2\u00bc\u00bd\7v\2\2\u00bd\u00be\7t\2\2\u00be\u00bf\7"+
		"w\2\2\u00bf\u00c0\7g\2\2\u00c0\26\3\2\2\2\u00c1\u00c2\7h\2\2\u00c2\u00c3"+
		"\7c\2\2\u00c3\u00c4\7n\2\2\u00c4\u00c5\7u\2\2\u00c5\u00c6\7g\2\2\u00c6"+
		"\30\3\2\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7h\2\2\u00c9\32\3\2\2\2\u00ca"+
		"\u00cb\7g\2\2\u00cb\u00cc\7n\2\2\u00cc\u00cd\7u\2\2\u00cd\u00ce\7g\2\2"+
		"\u00ce\34\3\2\2\2\u00cf\u00d0\7h\2\2\u00d0\u00d1\7q\2\2\u00d1\u00d2\7"+
		"t\2\2\u00d2\36\3\2\2\2\u00d3\u00d4\7y\2\2\u00d4\u00d5\7j\2\2\u00d5\u00d6"+
		"\7k\2\2\u00d6\u00d7\7n\2\2\u00d7\u00d8\7g\2\2\u00d8 \3\2\2\2\u00d9\u00da"+
		"\7d\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7c\2\2\u00dd"+
		"\u00de\7m\2\2\u00de\"\3\2\2\2\u00df\u00e0\7e\2\2\u00e0\u00e1\7q\2\2\u00e1"+
		"\u00e2\7p\2\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\7k\2\2\u00e4\u00e5\7p\2\2"+
		"\u00e5\u00e6\7w\2\2\u00e6\u00e7\7g\2\2\u00e7$\3\2\2\2\u00e8\u00e9\7t\2"+
		"\2\u00e9\u00ea\7g\2\2\u00ea\u00eb\7v\2\2\u00eb\u00ec\7w\2\2\u00ec\u00ed"+
		"\7t\2\2\u00ed\u00ee\7p\2\2\u00ee&\3\2\2\2\u00ef\u00f0\7p\2\2\u00f0\u00f1"+
		"\7g\2\2\u00f1\u00f2\7y\2\2\u00f2(\3\2\2\2\u00f3\u00f4\7e\2\2\u00f4\u00f5"+
		"\7n\2\2\u00f5\u00f6\7c\2\2\u00f6\u00f7\7u\2\2\u00f7\u00f8\7u\2\2\u00f8"+
		"*\3\2\2\2\u00f9\u00fa\7v\2\2\u00fa\u00fb\7j\2\2\u00fb\u00fc\7k\2\2\u00fc"+
		"\u00fd\7u\2\2\u00fd,\3\2\2\2\u00fe\u0102\t\4\2\2\u00ff\u0101\t\5\2\2\u0100"+
		"\u00ff\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103.\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0108\5\25\13\2\u0106\u0108"+
		"\5\27\f\2\u0107\u0105\3\2\2\2\u0107\u0106\3\2\2\2\u0108\60\3\2\2\2\u0109"+
		"\u0112\7\62\2\2\u010a\u010e\t\6\2\2\u010b\u010d\t\7\2\2\u010c\u010b\3"+
		"\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0109\3\2\2\2\u0111\u010a\3\2"+
		"\2\2\u0112\62\3\2\2\2\u0113\u0119\7$\2\2\u0114\u0118\n\b\2\2\u0115\u0116"+
		"\7^\2\2\u0116\u0118\t\t\2\2\u0117\u0114\3\2\2\2\u0117\u0115\3\2\2\2\u0118"+
		"\u011b\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011c\3\2"+
		"\2\2\u011b\u0119\3\2\2\2\u011c\u011d\7$\2\2\u011d\64\3\2\2\2\u011e\u011f"+
		"\5\21\t\2\u011f\66\3\2\2\2\u0120\u0121\7\"\2\2\u01218\3\2\2\2\u0122\u0123"+
		"\7.\2\2\u0123:\3\2\2\2\u0124\u0125\7=\2\2\u0125<\3\2\2\2\u0126\u0127\7"+
		"\60\2\2\u0127>\3\2\2\2\u0128\u0129\7*\2\2\u0129@\3\2\2\2\u012a\u012b\7"+
		"+\2\2\u012bB\3\2\2\2\u012c\u012d\7]\2\2\u012dD\3\2\2\2\u012e\u012f\7_"+
		"\2\2\u012fF\3\2\2\2\u0130\u0131\7}\2\2\u0131H\3\2\2\2\u0132\u0133\7\177"+
		"\2\2\u0133J\3\2\2\2\u0134\u0135\7>\2\2\u0135L\3\2\2\2\u0136\u0137\7>\2"+
		"\2\u0137\u0138\7?\2\2\u0138N\3\2\2\2\u0139\u013a\7@\2\2\u013aP\3\2\2\2"+
		"\u013b\u013c\7@\2\2\u013c\u013d\7?\2\2\u013dR\3\2\2\2\u013e\u013f\7?\2"+
		"\2\u013f\u0140\7?\2\2\u0140T\3\2\2\2\u0141\u0142\7#\2\2\u0142\u0143\7"+
		"?\2\2\u0143V\3\2\2\2\u0144\u0145\7>\2\2\u0145\u0146\7>\2\2\u0146X\3\2"+
		"\2\2\u0147\u0148\7@\2\2\u0148\u0149\7@\2\2\u0149Z\3\2\2\2\u014a\u014b"+
		"\7?\2\2\u014b\\\3\2\2\2\u014c\u014d\7-\2\2\u014d\u014e\7-\2\2\u014e^\3"+
		"\2\2\2\u014f\u0150\7/\2\2\u0150\u0151\7/\2\2\u0151`\3\2\2\2\u0152\u0153"+
		"\7-\2\2\u0153b\3\2\2\2\u0154\u0155\7/\2\2\u0155d\3\2\2\2\u0156\u0157\7"+
		",\2\2\u0157f\3\2\2\2\u0158\u0159\7\61\2\2\u0159h\3\2\2\2\u015a\u015b\7"+
		"\'\2\2\u015bj\3\2\2\2\u015c\u015d\7(\2\2\u015dl\3\2\2\2\u015e\u015f\7"+
		"~\2\2\u015fn\3\2\2\2\u0160\u0161\7(\2\2\u0161\u0162\7(\2\2\u0162p\3\2"+
		"\2\2\u0163\u0164\7~\2\2\u0164\u0165\7~\2\2\u0165r\3\2\2\2\u0166\u0167"+
		"\7`\2\2\u0167t\3\2\2\2\u0168\u0169\7#\2\2\u0169v\3\2\2\2\u016a\u016b\7"+
		"\u0080\2\2\u016bx\3\2\2\2\16\2|\u0082\u0085\u008f\u009d\u0102\u0107\u010e"+
		"\u0111\u0117\u0119\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
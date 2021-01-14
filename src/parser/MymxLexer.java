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
		INT=1, BOOL=2, STRING=3, NULL=4, VOID=5, TRUE=6, FALSE=7, IF=8, ELSE=9, 
		FOR=10, WHILE=11, BREAK=12, CONTINUE=13, RETURN=14, NEW=15, CLASS=16, 
		THIS=17, IDENTIFIER=18, BOOL_LITERAL=19, INTEGER_LITERAL=20, STRING_LITERAL=21, 
		NULL_LITERAL=22, SPACE=23, COMMA=24, SEMICOLON=25, DOT=26, LEFT_PARENTNESS=27, 
		RIGHT_PARENTNESS=28, LEFT_BRACKET=29, RIGHT_BRACKET=30, LEFT_BRACE=31, 
		RIGHT_BRACE=32, LESS=33, LESS_EQUAL=34, GREATER=35, GREATER_EQUAL=36, 
		EQUAL=37, NOT_EQUAL=38, LEFT_SHIFT=39, RIGHT_SHIFT=40, ASSIGN=41, ADD_ADD=42, 
		MINUS_MINUS=43, ADD=44, MINUS=45, MUL=46, DIV=47, MOD=48, AND_ARI=49, 
		OR_ARI=50, AND_LOGIC=51, OR_LOGIC=52, XOR_ARI=53, NOT_LOGIC=54, NOT_ARI=55, 
		WHITESPACE=56, LINECOMMENT=57, BLOCKCOMMENT=58;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"INT", "BOOL", "STRING", "NULL", "VOID", "TRUE", "FALSE", "IF", "ELSE", 
			"FOR", "WHILE", "BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", "THIS", 
			"IDENTIFIER", "BOOL_LITERAL", "INTEGER_LITERAL", "STRING_LITERAL", "NULL_LITERAL", 
			"SPACE", "COMMA", "SEMICOLON", "DOT", "LEFT_PARENTNESS", "RIGHT_PARENTNESS", 
			"LEFT_BRACKET", "RIGHT_BRACKET", "LEFT_BRACE", "RIGHT_BRACE", "LESS", 
			"LESS_EQUAL", "GREATER", "GREATER_EQUAL", "EQUAL", "NOT_EQUAL", "LEFT_SHIFT", 
			"RIGHT_SHIFT", "ASSIGN", "ADD_ADD", "MINUS_MINUS", "ADD", "MINUS", "MUL", 
			"DIV", "MOD", "AND_ARI", "OR_ARI", "AND_LOGIC", "OR_LOGIC", "XOR_ARI", 
			"NOT_LOGIC", "NOT_ARI", "WHITESPACE", "LINECOMMENT", "BLOCKCOMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'bool'", "'string'", "'null'", "'void'", "'true'", "'false'", 
			"'if'", "'else'", "'for'", "'while'", "'break'", "'continue'", "'return'", 
			"'new'", "'class'", "'this'", null, null, null, null, null, "' '", "','", 
			"';'", "'.'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'<'", "'<='", 
			"'>'", "'>='", "'=='", "'!='", "'<<'", "'>>'", "'='", "'++'", "'--'", 
			"'+'", "'-'", "'*'", "'/'", "'%'", "'&'", "'|'", "'&&'", "'||'", "'^'", 
			"'!'", "'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT", "BOOL", "STRING", "NULL", "VOID", "TRUE", "FALSE", "IF", 
			"ELSE", "FOR", "WHILE", "BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", 
			"THIS", "IDENTIFIER", "BOOL_LITERAL", "INTEGER_LITERAL", "STRING_LITERAL", 
			"NULL_LITERAL", "SPACE", "COMMA", "SEMICOLON", "DOT", "LEFT_PARENTNESS", 
			"RIGHT_PARENTNESS", "LEFT_BRACKET", "RIGHT_BRACKET", "LEFT_BRACE", "RIGHT_BRACE", 
			"LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", "EQUAL", "NOT_EQUAL", 
			"LEFT_SHIFT", "RIGHT_SHIFT", "ASSIGN", "ADD_ADD", "MINUS_MINUS", "ADD", 
			"MINUS", "MUL", "DIV", "MOD", "AND_ARI", "OR_ARI", "AND_LOGIC", "OR_LOGIC", 
			"XOR_ARI", "NOT_LOGIC", "NOT_ARI", "WHITESPACE", "LINECOMMENT", "BLOCKCOMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2<\u0161\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\7\23\u00d6\n\23\f\23"+
		"\16\23\u00d9\13\23\3\24\3\24\5\24\u00dd\n\24\3\25\3\25\3\25\7\25\u00e2"+
		"\n\25\f\25\16\25\u00e5\13\25\5\25\u00e7\n\25\3\26\3\26\3\26\3\26\7\26"+
		"\u00ed\n\26\f\26\16\26\u00f0\13\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3"+
		" \3!\3!\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3("+
		"\3)\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61"+
		"\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\67\3\67"+
		"\38\38\39\69\u0143\n9\r9\169\u0144\39\39\3:\3:\3:\3:\7:\u014d\n:\f:\16"+
		":\u0150\13:\3:\3:\3;\3;\3;\3;\7;\u0158\n;\f;\16;\u015b\13;\3;\3;\3;\3"+
		";\3;\3\u0159\2<\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64"+
		"g\65i\66k\67m8o9q:s;u<\3\2\n\4\2C\\c|\6\2\62;C\\aac|\3\2\63;\3\2\62;\6"+
		"\2\f\f\17\17$$^^\5\2$$^^pp\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u0169\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2"+
		"\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2"+
		"\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\3w\3\2\2\2\5{\3\2\2\2\7\u0080"+
		"\3\2\2\2\t\u0087\3\2\2\2\13\u008c\3\2\2\2\r\u0091\3\2\2\2\17\u0096\3\2"+
		"\2\2\21\u009c\3\2\2\2\23\u009f\3\2\2\2\25\u00a4\3\2\2\2\27\u00a8\3\2\2"+
		"\2\31\u00ae\3\2\2\2\33\u00b4\3\2\2\2\35\u00bd\3\2\2\2\37\u00c4\3\2\2\2"+
		"!\u00c8\3\2\2\2#\u00ce\3\2\2\2%\u00d3\3\2\2\2\'\u00dc\3\2\2\2)\u00e6\3"+
		"\2\2\2+\u00e8\3\2\2\2-\u00f3\3\2\2\2/\u00f5\3\2\2\2\61\u00f7\3\2\2\2\63"+
		"\u00f9\3\2\2\2\65\u00fb\3\2\2\2\67\u00fd\3\2\2\29\u00ff\3\2\2\2;\u0101"+
		"\3\2\2\2=\u0103\3\2\2\2?\u0105\3\2\2\2A\u0107\3\2\2\2C\u0109\3\2\2\2E"+
		"\u010b\3\2\2\2G\u010e\3\2\2\2I\u0110\3\2\2\2K\u0113\3\2\2\2M\u0116\3\2"+
		"\2\2O\u0119\3\2\2\2Q\u011c\3\2\2\2S\u011f\3\2\2\2U\u0121\3\2\2\2W\u0124"+
		"\3\2\2\2Y\u0127\3\2\2\2[\u0129\3\2\2\2]\u012b\3\2\2\2_\u012d\3\2\2\2a"+
		"\u012f\3\2\2\2c\u0131\3\2\2\2e\u0133\3\2\2\2g\u0135\3\2\2\2i\u0138\3\2"+
		"\2\2k\u013b\3\2\2\2m\u013d\3\2\2\2o\u013f\3\2\2\2q\u0142\3\2\2\2s\u0148"+
		"\3\2\2\2u\u0153\3\2\2\2wx\7k\2\2xy\7p\2\2yz\7v\2\2z\4\3\2\2\2{|\7d\2\2"+
		"|}\7q\2\2}~\7q\2\2~\177\7n\2\2\177\6\3\2\2\2\u0080\u0081\7u\2\2\u0081"+
		"\u0082\7v\2\2\u0082\u0083\7t\2\2\u0083\u0084\7k\2\2\u0084\u0085\7p\2\2"+
		"\u0085\u0086\7i\2\2\u0086\b\3\2\2\2\u0087\u0088\7p\2\2\u0088\u0089\7w"+
		"\2\2\u0089\u008a\7n\2\2\u008a\u008b\7n\2\2\u008b\n\3\2\2\2\u008c\u008d"+
		"\7x\2\2\u008d\u008e\7q\2\2\u008e\u008f\7k\2\2\u008f\u0090\7f\2\2\u0090"+
		"\f\3\2\2\2\u0091\u0092\7v\2\2\u0092\u0093\7t\2\2\u0093\u0094\7w\2\2\u0094"+
		"\u0095\7g\2\2\u0095\16\3\2\2\2\u0096\u0097\7h\2\2\u0097\u0098\7c\2\2\u0098"+
		"\u0099\7n\2\2\u0099\u009a\7u\2\2\u009a\u009b\7g\2\2\u009b\20\3\2\2\2\u009c"+
		"\u009d\7k\2\2\u009d\u009e\7h\2\2\u009e\22\3\2\2\2\u009f\u00a0\7g\2\2\u00a0"+
		"\u00a1\7n\2\2\u00a1\u00a2\7u\2\2\u00a2\u00a3\7g\2\2\u00a3\24\3\2\2\2\u00a4"+
		"\u00a5\7h\2\2\u00a5\u00a6\7q\2\2\u00a6\u00a7\7t\2\2\u00a7\26\3\2\2\2\u00a8"+
		"\u00a9\7y\2\2\u00a9\u00aa\7j\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7n\2\2"+
		"\u00ac\u00ad\7g\2\2\u00ad\30\3\2\2\2\u00ae\u00af\7d\2\2\u00af\u00b0\7"+
		"t\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7m\2\2\u00b3\32"+
		"\3\2\2\2\u00b4\u00b5\7e\2\2\u00b5\u00b6\7q\2\2\u00b6\u00b7\7p\2\2\u00b7"+
		"\u00b8\7v\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba\7p\2\2\u00ba\u00bb\7w\2\2"+
		"\u00bb\u00bc\7g\2\2\u00bc\34\3\2\2\2\u00bd\u00be\7t\2\2\u00be\u00bf\7"+
		"g\2\2\u00bf\u00c0\7v\2\2\u00c0\u00c1\7w\2\2\u00c1\u00c2\7t\2\2\u00c2\u00c3"+
		"\7p\2\2\u00c3\36\3\2\2\2\u00c4\u00c5\7p\2\2\u00c5\u00c6\7g\2\2\u00c6\u00c7"+
		"\7y\2\2\u00c7 \3\2\2\2\u00c8\u00c9\7e\2\2\u00c9\u00ca\7n\2\2\u00ca\u00cb"+
		"\7c\2\2\u00cb\u00cc\7u\2\2\u00cc\u00cd\7u\2\2\u00cd\"\3\2\2\2\u00ce\u00cf"+
		"\7v\2\2\u00cf\u00d0\7j\2\2\u00d0\u00d1\7k\2\2\u00d1\u00d2\7u\2\2\u00d2"+
		"$\3\2\2\2\u00d3\u00d7\t\2\2\2\u00d4\u00d6\t\3\2\2\u00d5\u00d4\3\2\2\2"+
		"\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8&\3"+
		"\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00dd\5\r\7\2\u00db\u00dd\5\17\b\2\u00dc"+
		"\u00da\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd(\3\2\2\2\u00de\u00e7\7\62\2\2"+
		"\u00df\u00e3\t\4\2\2\u00e0\u00e2\t\5\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e5"+
		"\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e6\u00de\3\2\2\2\u00e6\u00df\3\2\2\2\u00e7*\3\2\2\2"+
		"\u00e8\u00ee\7$\2\2\u00e9\u00ed\n\6\2\2\u00ea\u00eb\7^\2\2\u00eb\u00ed"+
		"\t\7\2\2\u00ec\u00e9\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee"+
		"\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee\3\2"+
		"\2\2\u00f1\u00f2\7$\2\2\u00f2,\3\2\2\2\u00f3\u00f4\5\t\5\2\u00f4.\3\2"+
		"\2\2\u00f5\u00f6\7\"\2\2\u00f6\60\3\2\2\2\u00f7\u00f8\7.\2\2\u00f8\62"+
		"\3\2\2\2\u00f9\u00fa\7=\2\2\u00fa\64\3\2\2\2\u00fb\u00fc\7\60\2\2\u00fc"+
		"\66\3\2\2\2\u00fd\u00fe\7*\2\2\u00fe8\3\2\2\2\u00ff\u0100\7+\2\2\u0100"+
		":\3\2\2\2\u0101\u0102\7]\2\2\u0102<\3\2\2\2\u0103\u0104\7_\2\2\u0104>"+
		"\3\2\2\2\u0105\u0106\7}\2\2\u0106@\3\2\2\2\u0107\u0108\7\177\2\2\u0108"+
		"B\3\2\2\2\u0109\u010a\7>\2\2\u010aD\3\2\2\2\u010b\u010c\7>\2\2\u010c\u010d"+
		"\7?\2\2\u010dF\3\2\2\2\u010e\u010f\7@\2\2\u010fH\3\2\2\2\u0110\u0111\7"+
		"@\2\2\u0111\u0112\7?\2\2\u0112J\3\2\2\2\u0113\u0114\7?\2\2\u0114\u0115"+
		"\7?\2\2\u0115L\3\2\2\2\u0116\u0117\7#\2\2\u0117\u0118\7?\2\2\u0118N\3"+
		"\2\2\2\u0119\u011a\7>\2\2\u011a\u011b\7>\2\2\u011bP\3\2\2\2\u011c\u011d"+
		"\7@\2\2\u011d\u011e\7@\2\2\u011eR\3\2\2\2\u011f\u0120\7?\2\2\u0120T\3"+
		"\2\2\2\u0121\u0122\7-\2\2\u0122\u0123\7-\2\2\u0123V\3\2\2\2\u0124\u0125"+
		"\7/\2\2\u0125\u0126\7/\2\2\u0126X\3\2\2\2\u0127\u0128\7-\2\2\u0128Z\3"+
		"\2\2\2\u0129\u012a\7/\2\2\u012a\\\3\2\2\2\u012b\u012c\7,\2\2\u012c^\3"+
		"\2\2\2\u012d\u012e\7\61\2\2\u012e`\3\2\2\2\u012f\u0130\7\'\2\2\u0130b"+
		"\3\2\2\2\u0131\u0132\7(\2\2\u0132d\3\2\2\2\u0133\u0134\7~\2\2\u0134f\3"+
		"\2\2\2\u0135\u0136\7(\2\2\u0136\u0137\7(\2\2\u0137h\3\2\2\2\u0138\u0139"+
		"\7~\2\2\u0139\u013a\7~\2\2\u013aj\3\2\2\2\u013b\u013c\7`\2\2\u013cl\3"+
		"\2\2\2\u013d\u013e\7#\2\2\u013en\3\2\2\2\u013f\u0140\7\u0080\2\2\u0140"+
		"p\3\2\2\2\u0141\u0143\t\b\2\2\u0142\u0141\3\2\2\2\u0143\u0144\3\2\2\2"+
		"\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147"+
		"\b9\2\2\u0147r\3\2\2\2\u0148\u0149\7\61\2\2\u0149\u014a\7\61\2\2\u014a"+
		"\u014e\3\2\2\2\u014b\u014d\n\t\2\2\u014c\u014b\3\2\2\2\u014d\u0150\3\2"+
		"\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0151\3\2\2\2\u0150"+
		"\u014e\3\2\2\2\u0151\u0152\b:\2\2\u0152t\3\2\2\2\u0153\u0154\7\61\2\2"+
		"\u0154\u0155\7,\2\2\u0155\u0159\3\2\2\2\u0156\u0158\13\2\2\2\u0157\u0156"+
		"\3\2\2\2\u0158\u015b\3\2\2\2\u0159\u015a\3\2\2\2\u0159\u0157\3\2\2\2\u015a"+
		"\u015c\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u015d\7,\2\2\u015d\u015e\7\61"+
		"\2\2\u015e\u015f\3\2\2\2\u015f\u0160\b;\2\2\u0160v\3\2\2\2\f\2\u00d7\u00dc"+
		"\u00e3\u00e6\u00ec\u00ee\u0144\u014e\u0159\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
// Generated from DBMSGrammer.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DBMSGrammerParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, ALPHA=38, 
		DIGIT=39, WS=40;
	public static final int
		RULE_query = 0, RULE_relationname = 1, RULE_identifier = 2, RULE_alpha = 3, 
		RULE_digit = 4, RULE_expr = 5, RULE_atomicexpr = 6, RULE_selection = 7, 
		RULE_condition = 8, RULE_conjunction = 9, RULE_comparison = 10, RULE_op = 11, 
		RULE_operand = 12, RULE_attributename = 13, RULE_literal = 14, RULE_projection = 15, 
		RULE_attributelist = 16, RULE_renaming = 17, RULE_union = 18, RULE_difference = 19, 
		RULE_product = 20, RULE_command = 21, RULE_opencmd = 22, RULE_closecmd = 23, 
		RULE_writecmd = 24, RULE_exitcmd = 25, RULE_showcmd = 26, RULE_createcmd = 27, 
		RULE_updatecmd = 28, RULE_insertcmd = 29, RULE_deletecmd = 30, RULE_typedattributelist = 31, 
		RULE_type = 32, RULE_integer = 33, RULE_program = 34;
	public static final String[] ruleNames = {
		"query", "relationname", "identifier", "alpha", "digit", "expr", "atomicexpr", 
		"selection", "condition", "conjunction", "comparison", "op", "operand", 
		"attributename", "literal", "projection", "attributelist", "renaming", 
		"union", "difference", "product", "command", "opencmd", "closecmd", "writecmd", 
		"exitcmd", "showcmd", "createcmd", "updatecmd", "insertcmd", "deletecmd", 
		"typedattributelist", "type", "integer", "program"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'<-'", "';'", "'('", "')'", "'select('", "'||'", "'&&'", "'=='", 
		"'!='", "'<'", "'>'", "'<='", "'>='", "'\"'", "'project('", "','", "'rename('", 
		"'+'", "'-'", "'*'", "'OPEN'", "'CLOSE'", "'WRITE'", "'EXIT'", "'SHOW'", 
		"'CREATE TABLE'", "') PRIMARY KEY ('", "'UPDATE'", "'SET'", "'='", "'WHERE'", 
		"'INSERT INTO'", "'VALUES FROM ('", "'VALUES FROM RELATION'", "'DELETE FROM'", 
		"'VARCHAR ('", "'INTEGER'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "ALPHA", "DIGIT", "WS"
	};
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
	public String getGrammarFileName() { return "DBMSGrammer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DBMSGrammerParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public RelationnameContext relationname() {
			return getRuleContext(RelationnameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			relationname();
			setState(71);
			match(T__0);
			setState(72);
			expr();
			setState(73);
			match(T__1);
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

	public static class RelationnameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public RelationnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterRelationname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitRelationname(this);
		}
	}

	public final RelationnameContext relationname() throws RecognitionException {
		RelationnameContext _localctx = new RelationnameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_relationname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			identifier();
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

	public static class IdentifierContext extends ParserRuleContext {
		public List<AlphaContext> alpha() {
			return getRuleContexts(AlphaContext.class);
		}
		public AlphaContext alpha(int i) {
			return getRuleContext(AlphaContext.class,i);
		}
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			alpha();
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ALPHA || _la==DIGIT) {
				{
				setState(80);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ALPHA:
					{
					setState(78);
					alpha();
					}
					break;
				case DIGIT:
					{
					setState(79);
					digit();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(84);
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

	public static class AlphaContext extends ParserRuleContext {
		public TerminalNode ALPHA() { return getToken(DBMSGrammerParser.ALPHA, 0); }
		public AlphaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alpha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterAlpha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitAlpha(this);
		}
	}

	public final AlphaContext alpha() throws RecognitionException {
		AlphaContext _localctx = new AlphaContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_alpha);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(ALPHA);
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

	public static class DigitContext extends ParserRuleContext {
		public TerminalNode DIGIT() { return getToken(DBMSGrammerParser.DIGIT, 0); }
		public DigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitDigit(this);
		}
	}

	public final DigitContext digit() throws RecognitionException {
		DigitContext _localctx = new DigitContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_digit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(DIGIT);
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

	public static class ExprContext extends ParserRuleContext {
		public AtomicexprContext atomicexpr() {
			return getRuleContext(AtomicexprContext.class,0);
		}
		public SelectionContext selection() {
			return getRuleContext(SelectionContext.class,0);
		}
		public ProjectionContext projection() {
			return getRuleContext(ProjectionContext.class,0);
		}
		public RenamingContext renaming() {
			return getRuleContext(RenamingContext.class,0);
		}
		public UnionContext union() {
			return getRuleContext(UnionContext.class,0);
		}
		public DifferenceContext difference() {
			return getRuleContext(DifferenceContext.class,0);
		}
		public ProductContext product() {
			return getRuleContext(ProductContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(89);
				atomicexpr();
				}
				break;
			case 2:
				{
				setState(90);
				selection();
				}
				break;
			case 3:
				{
				setState(91);
				projection();
				}
				break;
			case 4:
				{
				setState(92);
				renaming();
				}
				break;
			case 5:
				{
				setState(93);
				union();
				}
				break;
			case 6:
				{
				setState(94);
				difference();
				}
				break;
			case 7:
				{
				setState(95);
				product();
				}
				break;
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

	public static class AtomicexprContext extends ParserRuleContext {
		public RelationnameContext relationname() {
			return getRuleContext(RelationnameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AtomicexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomicexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterAtomicexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitAtomicexpr(this);
		}
	}

	public final AtomicexprContext atomicexpr() throws RecognitionException {
		AtomicexprContext _localctx = new AtomicexprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_atomicexpr);
		try {
			setState(103);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ALPHA:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				relationname();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(T__2);
				setState(100);
				expr();
				setState(101);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class SelectionContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public AtomicexprContext atomicexpr() {
			return getRuleContext(AtomicexprContext.class,0);
		}
		public SelectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterSelection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitSelection(this);
		}
	}

	public final SelectionContext selection() throws RecognitionException {
		SelectionContext _localctx = new SelectionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_selection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__4);
			setState(106);
			condition();
			setState(107);
			match(T__3);
			setState(108);
			atomicexpr();
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

	public static class ConditionContext extends ParserRuleContext {
		public List<ConjunctionContext> conjunction() {
			return getRuleContexts(ConjunctionContext.class);
		}
		public ConjunctionContext conjunction(int i) {
			return getRuleContext(ConjunctionContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			conjunction();
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(111);
				match(T__5);
				setState(112);
				conjunction();
				}
				}
				setState(117);
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

	public static class ConjunctionContext extends ParserRuleContext {
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_conjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			comparison();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(119);
				match(T__6);
				setState(120);
				comparison();
				}
				}
				setState(125);
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

	public static class ComparisonContext extends ParserRuleContext {
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
		}
		public List<ConjunctionContext> conjunction() {
			return getRuleContexts(ConjunctionContext.class);
		}
		public ConjunctionContext conjunction(int i) {
			return getRuleContext(ConjunctionContext.class,i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_comparison);
		int _la;
		try {
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case ALPHA:
			case DIGIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				operand();
				setState(127);
				op();
				setState(128);
				operand();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				match(T__2);
				setState(131);
				conjunction();
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(132);
					match(T__5);
					setState(133);
					conjunction();
					}
					}
					setState(138);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(139);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class OpContext extends ParserRuleContext {
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitOp(this);
		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class OperandContext extends ParserRuleContext {
		public AttributenameContext attributename() {
			return getRuleContext(AttributenameContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitOperand(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operand);
		try {
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ALPHA:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				attributename();
				}
				break;
			case T__1:
			case T__3:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case DIGIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				literal();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class AttributenameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AttributenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterAttributename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitAttributename(this);
		}
	}

	public final AttributenameContext attributename() throws RecognitionException {
		AttributenameContext _localctx = new AttributenameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_attributename);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			identifier();
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

	public static class LiteralContext extends ParserRuleContext {
		public List<AlphaContext> alpha() {
			return getRuleContexts(AlphaContext.class);
		}
		public AlphaContext alpha(int i) {
			return getRuleContext(AlphaContext.class,i);
		}
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_literal);
		int _la;
		try {
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				match(T__13);
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ALPHA || _la==DIGIT) {
					{
					setState(154);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case ALPHA:
						{
						setState(152);
						alpha();
						}
						break;
					case DIGIT:
						{
						setState(153);
						digit();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(158);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(159);
				match(T__13);
				}
				break;
			case T__1:
			case T__3:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__15:
			case T__30:
			case DIGIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DIGIT) {
					{
					{
					setState(160);
					digit();
					}
					}
					setState(165);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ProjectionContext extends ParserRuleContext {
		public AttributelistContext attributelist() {
			return getRuleContext(AttributelistContext.class,0);
		}
		public AtomicexprContext atomicexpr() {
			return getRuleContext(AtomicexprContext.class,0);
		}
		public ProjectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterProjection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitProjection(this);
		}
	}

	public final ProjectionContext projection() throws RecognitionException {
		ProjectionContext _localctx = new ProjectionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_projection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__14);
			setState(169);
			attributelist();
			setState(170);
			match(T__3);
			setState(171);
			atomicexpr();
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

	public static class AttributelistContext extends ParserRuleContext {
		public List<AttributenameContext> attributename() {
			return getRuleContexts(AttributenameContext.class);
		}
		public AttributenameContext attributename(int i) {
			return getRuleContext(AttributenameContext.class,i);
		}
		public AttributelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributelist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterAttributelist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitAttributelist(this);
		}
	}

	public final AttributelistContext attributelist() throws RecognitionException {
		AttributelistContext _localctx = new AttributelistContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_attributelist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			attributename();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(174);
				match(T__15);
				setState(175);
				attributename();
				}
				}
				setState(180);
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

	public static class RenamingContext extends ParserRuleContext {
		public AttributelistContext attributelist() {
			return getRuleContext(AttributelistContext.class,0);
		}
		public AtomicexprContext atomicexpr() {
			return getRuleContext(AtomicexprContext.class,0);
		}
		public RenamingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renaming; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterRenaming(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitRenaming(this);
		}
	}

	public final RenamingContext renaming() throws RecognitionException {
		RenamingContext _localctx = new RenamingContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_renaming);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(T__16);
			setState(182);
			attributelist();
			setState(183);
			match(T__3);
			setState(184);
			atomicexpr();
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

	public static class UnionContext extends ParserRuleContext {
		public List<AtomicexprContext> atomicexpr() {
			return getRuleContexts(AtomicexprContext.class);
		}
		public AtomicexprContext atomicexpr(int i) {
			return getRuleContext(AtomicexprContext.class,i);
		}
		public UnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_union; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitUnion(this);
		}
	}

	public final UnionContext union() throws RecognitionException {
		UnionContext _localctx = new UnionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_union);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			atomicexpr();
			setState(187);
			match(T__17);
			setState(188);
			atomicexpr();
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

	public static class DifferenceContext extends ParserRuleContext {
		public List<AtomicexprContext> atomicexpr() {
			return getRuleContexts(AtomicexprContext.class);
		}
		public AtomicexprContext atomicexpr(int i) {
			return getRuleContext(AtomicexprContext.class,i);
		}
		public DifferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_difference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterDifference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitDifference(this);
		}
	}

	public final DifferenceContext difference() throws RecognitionException {
		DifferenceContext _localctx = new DifferenceContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_difference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			atomicexpr();
			setState(191);
			match(T__18);
			setState(192);
			atomicexpr();
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

	public static class ProductContext extends ParserRuleContext {
		public List<AtomicexprContext> atomicexpr() {
			return getRuleContexts(AtomicexprContext.class);
		}
		public AtomicexprContext atomicexpr(int i) {
			return getRuleContext(AtomicexprContext.class,i);
		}
		public ProductContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_product; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterProduct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitProduct(this);
		}
	}

	public final ProductContext product() throws RecognitionException {
		ProductContext _localctx = new ProductContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_product);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			atomicexpr();
			setState(195);
			match(T__19);
			setState(196);
			atomicexpr();
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

	public static class CommandContext extends ParserRuleContext {
		public OpencmdContext opencmd() {
			return getRuleContext(OpencmdContext.class,0);
		}
		public ClosecmdContext closecmd() {
			return getRuleContext(ClosecmdContext.class,0);
		}
		public WritecmdContext writecmd() {
			return getRuleContext(WritecmdContext.class,0);
		}
		public ExitcmdContext exitcmd() {
			return getRuleContext(ExitcmdContext.class,0);
		}
		public ShowcmdContext showcmd() {
			return getRuleContext(ShowcmdContext.class,0);
		}
		public CreatecmdContext createcmd() {
			return getRuleContext(CreatecmdContext.class,0);
		}
		public UpdatecmdContext updatecmd() {
			return getRuleContext(UpdatecmdContext.class,0);
		}
		public InsertcmdContext insertcmd() {
			return getRuleContext(InsertcmdContext.class,0);
		}
		public DeletecmdContext deletecmd() {
			return getRuleContext(DeletecmdContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitCommand(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				{
				setState(198);
				opencmd();
				}
				break;
			case T__21:
				{
				setState(199);
				closecmd();
				}
				break;
			case T__22:
				{
				setState(200);
				writecmd();
				}
				break;
			case T__23:
				{
				setState(201);
				exitcmd();
				}
				break;
			case T__24:
				{
				setState(202);
				showcmd();
				}
				break;
			case T__25:
				{
				setState(203);
				createcmd();
				}
				break;
			case T__27:
				{
				setState(204);
				updatecmd();
				}
				break;
			case T__31:
				{
				setState(205);
				insertcmd();
				}
				break;
			case T__34:
				{
				setState(206);
				deletecmd();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(209);
			match(T__1);
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

	public static class OpencmdContext extends ParserRuleContext {
		public RelationnameContext relationname() {
			return getRuleContext(RelationnameContext.class,0);
		}
		public OpencmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opencmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterOpencmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitOpencmd(this);
		}
	}

	public final OpencmdContext opencmd() throws RecognitionException {
		OpencmdContext _localctx = new OpencmdContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_opencmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(T__20);
			setState(212);
			relationname();
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

	public static class ClosecmdContext extends ParserRuleContext {
		public RelationnameContext relationname() {
			return getRuleContext(RelationnameContext.class,0);
		}
		public ClosecmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_closecmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterClosecmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitClosecmd(this);
		}
	}

	public final ClosecmdContext closecmd() throws RecognitionException {
		ClosecmdContext _localctx = new ClosecmdContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_closecmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(T__21);
			setState(215);
			relationname();
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

	public static class WritecmdContext extends ParserRuleContext {
		public RelationnameContext relationname() {
			return getRuleContext(RelationnameContext.class,0);
		}
		public WritecmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_writecmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterWritecmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitWritecmd(this);
		}
	}

	public final WritecmdContext writecmd() throws RecognitionException {
		WritecmdContext _localctx = new WritecmdContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_writecmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(T__22);
			setState(218);
			relationname();
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

	public static class ExitcmdContext extends ParserRuleContext {
		public ExitcmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exitcmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterExitcmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitExitcmd(this);
		}
	}

	public final ExitcmdContext exitcmd() throws RecognitionException {
		ExitcmdContext _localctx = new ExitcmdContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_exitcmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(T__23);
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

	public static class ShowcmdContext extends ParserRuleContext {
		public AtomicexprContext atomicexpr() {
			return getRuleContext(AtomicexprContext.class,0);
		}
		public ShowcmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showcmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterShowcmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitShowcmd(this);
		}
	}

	public final ShowcmdContext showcmd() throws RecognitionException {
		ShowcmdContext _localctx = new ShowcmdContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_showcmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(T__24);
			setState(223);
			atomicexpr();
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

	public static class CreatecmdContext extends ParserRuleContext {
		public RelationnameContext relationname() {
			return getRuleContext(RelationnameContext.class,0);
		}
		public TypedattributelistContext typedattributelist() {
			return getRuleContext(TypedattributelistContext.class,0);
		}
		public AttributelistContext attributelist() {
			return getRuleContext(AttributelistContext.class,0);
		}
		public CreatecmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createcmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterCreatecmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitCreatecmd(this);
		}
	}

	public final CreatecmdContext createcmd() throws RecognitionException {
		CreatecmdContext _localctx = new CreatecmdContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_createcmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			match(T__25);
			setState(226);
			relationname();
			setState(227);
			match(T__2);
			setState(228);
			typedattributelist();
			setState(229);
			match(T__26);
			setState(230);
			attributelist();
			setState(231);
			match(T__3);
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

	public static class UpdatecmdContext extends ParserRuleContext {
		public RelationnameContext relationname() {
			return getRuleContext(RelationnameContext.class,0);
		}
		public List<AttributenameContext> attributename() {
			return getRuleContexts(AttributenameContext.class);
		}
		public AttributenameContext attributename(int i) {
			return getRuleContext(AttributenameContext.class,i);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public UpdatecmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updatecmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterUpdatecmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitUpdatecmd(this);
		}
	}

	public final UpdatecmdContext updatecmd() throws RecognitionException {
		UpdatecmdContext _localctx = new UpdatecmdContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_updatecmd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(T__27);
			setState(234);
			relationname();
			setState(235);
			match(T__28);
			setState(236);
			attributename();
			setState(237);
			match(T__29);
			setState(238);
			literal();
			setState(246);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(239);
				match(T__15);
				setState(240);
				attributename();
				setState(241);
				match(T__29);
				setState(242);
				literal();
				}
				}
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(249);
			match(T__30);
			setState(250);
			condition();
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

	public static class InsertcmdContext extends ParserRuleContext {
		public RelationnameContext relationname() {
			return getRuleContext(RelationnameContext.class,0);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InsertcmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertcmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterInsertcmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitInsertcmd(this);
		}
	}

	public final InsertcmdContext insertcmd() throws RecognitionException {
		InsertcmdContext _localctx = new InsertcmdContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_insertcmd);
		int _la;
		try {
			setState(270);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(252);
				match(T__31);
				setState(253);
				relationname();
				setState(254);
				match(T__32);
				setState(255);
				literal();
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15) {
					{
					{
					setState(256);
					match(T__15);
					setState(257);
					literal();
					}
					}
					setState(262);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(263);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(265);
				match(T__31);
				setState(266);
				relationname();
				setState(267);
				match(T__33);
				setState(268);
				expr();
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

	public static class DeletecmdContext extends ParserRuleContext {
		public RelationnameContext relationname() {
			return getRuleContext(RelationnameContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public DeletecmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deletecmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterDeletecmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitDeletecmd(this);
		}
	}

	public final DeletecmdContext deletecmd() throws RecognitionException {
		DeletecmdContext _localctx = new DeletecmdContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_deletecmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(T__34);
			setState(273);
			relationname();
			setState(274);
			match(T__30);
			setState(275);
			condition();
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

	public static class TypedattributelistContext extends ParserRuleContext {
		public List<AttributenameContext> attributename() {
			return getRuleContexts(AttributenameContext.class);
		}
		public AttributenameContext attributename(int i) {
			return getRuleContext(AttributenameContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TypedattributelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedattributelist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterTypedattributelist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitTypedattributelist(this);
		}
	}

	public final TypedattributelistContext typedattributelist() throws RecognitionException {
		TypedattributelistContext _localctx = new TypedattributelistContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_typedattributelist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			attributename();
			setState(278);
			type();
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(279);
				match(T__15);
				setState(280);
				attributename();
				setState(281);
				type();
				}
				}
				setState(287);
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

	public static class TypeContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_type);
		try {
			setState(293);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__35:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				match(T__35);
				setState(289);
				integer();
				setState(290);
				match(T__3);
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				match(T__36);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class IntegerContext extends ParserRuleContext {
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitInteger(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			digit();
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DIGIT) {
				{
				{
				setState(296);
				digit();
				}
				}
				setState(301);
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

	public static class ProgramContext extends ParserRuleContext {
		public List<QueryContext> query() {
			return getRuleContexts(QueryContext.class);
		}
		public QueryContext query(int i) {
			return getRuleContext(QueryContext.class,i);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DBMSGrammerListener ) ((DBMSGrammerListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__27) | (1L << T__31) | (1L << T__34) | (1L << ALPHA))) != 0)) {
				{
				setState(304);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ALPHA:
					{
					setState(302);
					query();
					}
					break;
				case T__20:
				case T__21:
				case T__22:
				case T__23:
				case T__24:
				case T__25:
				case T__27:
				case T__31:
				case T__34:
					{
					setState(303);
					command();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(308);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u0138\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\7\4S\n"+
		"\4\f\4\16\4V\13\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7c\n\7"+
		"\3\b\3\b\3\b\3\b\3\b\5\bj\n\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\7\nt\n\n"+
		"\f\n\16\nw\13\n\3\13\3\13\3\13\7\13|\n\13\f\13\16\13\177\13\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u0089\n\f\f\f\16\f\u008c\13\f\3\f\3\f\5\f"+
		"\u0090\n\f\3\r\3\r\3\16\3\16\5\16\u0096\n\16\3\17\3\17\3\20\3\20\3\20"+
		"\7\20\u009d\n\20\f\20\16\20\u00a0\13\20\3\20\3\20\7\20\u00a4\n\20\f\20"+
		"\16\20\u00a7\13\20\5\20\u00a9\n\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\7\22\u00b3\n\22\f\22\16\22\u00b6\13\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00d2\n\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\7\36\u00f7\n\36\f\36\16\36\u00fa\13\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\7\37\u0105\n\37\f\37\16\37\u0108\13\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0111\n\37\3 \3 \3 \3 \3 \3!"+
		"\3!\3!\3!\3!\3!\7!\u011e\n!\f!\16!\u0121\13!\3\"\3\"\3\"\3\"\3\"\5\"\u0128"+
		"\n\"\3#\3#\7#\u012c\n#\f#\16#\u012f\13#\3$\3$\7$\u0133\n$\f$\16$\u0136"+
		"\13$\3$\2\2%\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>@BDF\2\3\3\2\n\17\2\u0137\2H\3\2\2\2\4M\3\2\2\2\6O\3\2\2\2\bW\3"+
		"\2\2\2\nY\3\2\2\2\fb\3\2\2\2\16i\3\2\2\2\20k\3\2\2\2\22p\3\2\2\2\24x\3"+
		"\2\2\2\26\u008f\3\2\2\2\30\u0091\3\2\2\2\32\u0095\3\2\2\2\34\u0097\3\2"+
		"\2\2\36\u00a8\3\2\2\2 \u00aa\3\2\2\2\"\u00af\3\2\2\2$\u00b7\3\2\2\2&\u00bc"+
		"\3\2\2\2(\u00c0\3\2\2\2*\u00c4\3\2\2\2,\u00d1\3\2\2\2.\u00d5\3\2\2\2\60"+
		"\u00d8\3\2\2\2\62\u00db\3\2\2\2\64\u00de\3\2\2\2\66\u00e0\3\2\2\28\u00e3"+
		"\3\2\2\2:\u00eb\3\2\2\2<\u0110\3\2\2\2>\u0112\3\2\2\2@\u0117\3\2\2\2B"+
		"\u0127\3\2\2\2D\u0129\3\2\2\2F\u0134\3\2\2\2HI\5\4\3\2IJ\7\3\2\2JK\5\f"+
		"\7\2KL\7\4\2\2L\3\3\2\2\2MN\5\6\4\2N\5\3\2\2\2OT\5\b\5\2PS\5\b\5\2QS\5"+
		"\n\6\2RP\3\2\2\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\7\3\2\2\2VT"+
		"\3\2\2\2WX\7(\2\2X\t\3\2\2\2YZ\7)\2\2Z\13\3\2\2\2[c\5\16\b\2\\c\5\20\t"+
		"\2]c\5 \21\2^c\5$\23\2_c\5&\24\2`c\5(\25\2ac\5*\26\2b[\3\2\2\2b\\\3\2"+
		"\2\2b]\3\2\2\2b^\3\2\2\2b_\3\2\2\2b`\3\2\2\2ba\3\2\2\2c\r\3\2\2\2dj\5"+
		"\4\3\2ef\7\5\2\2fg\5\f\7\2gh\7\6\2\2hj\3\2\2\2id\3\2\2\2ie\3\2\2\2j\17"+
		"\3\2\2\2kl\7\7\2\2lm\5\22\n\2mn\7\6\2\2no\5\16\b\2o\21\3\2\2\2pu\5\24"+
		"\13\2qr\7\b\2\2rt\5\24\13\2sq\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2v\23"+
		"\3\2\2\2wu\3\2\2\2x}\5\26\f\2yz\7\t\2\2z|\5\26\f\2{y\3\2\2\2|\177\3\2"+
		"\2\2}{\3\2\2\2}~\3\2\2\2~\25\3\2\2\2\177}\3\2\2\2\u0080\u0081\5\32\16"+
		"\2\u0081\u0082\5\30\r\2\u0082\u0083\5\32\16\2\u0083\u0090\3\2\2\2\u0084"+
		"\u0085\7\5\2\2\u0085\u008a\5\24\13\2\u0086\u0087\7\b\2\2\u0087\u0089\5"+
		"\24\13\2\u0088\u0086\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e\7\6"+
		"\2\2\u008e\u0090\3\2\2\2\u008f\u0080\3\2\2\2\u008f\u0084\3\2\2\2\u0090"+
		"\27\3\2\2\2\u0091\u0092\t\2\2\2\u0092\31\3\2\2\2\u0093\u0096\5\34\17\2"+
		"\u0094\u0096\5\36\20\2\u0095\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096\33"+
		"\3\2\2\2\u0097\u0098\5\6\4\2\u0098\35\3\2\2\2\u0099\u009e\7\20\2\2\u009a"+
		"\u009d\5\b\5\2\u009b\u009d\5\n\6\2\u009c\u009a\3\2\2\2\u009c\u009b\3\2"+
		"\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a9\7\20\2\2\u00a2\u00a4\5"+
		"\n\6\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u0099\3\2"+
		"\2\2\u00a8\u00a5\3\2\2\2\u00a9\37\3\2\2\2\u00aa\u00ab\7\21\2\2\u00ab\u00ac"+
		"\5\"\22\2\u00ac\u00ad\7\6\2\2\u00ad\u00ae\5\16\b\2\u00ae!\3\2\2\2\u00af"+
		"\u00b4\5\34\17\2\u00b0\u00b1\7\22\2\2\u00b1\u00b3\5\34\17\2\u00b2\u00b0"+
		"\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"#\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\7\23\2\2\u00b8\u00b9\5\"\22"+
		"\2\u00b9\u00ba\7\6\2\2\u00ba\u00bb\5\16\b\2\u00bb%\3\2\2\2\u00bc\u00bd"+
		"\5\16\b\2\u00bd\u00be\7\24\2\2\u00be\u00bf\5\16\b\2\u00bf\'\3\2\2\2\u00c0"+
		"\u00c1\5\16\b\2\u00c1\u00c2\7\25\2\2\u00c2\u00c3\5\16\b\2\u00c3)\3\2\2"+
		"\2\u00c4\u00c5\5\16\b\2\u00c5\u00c6\7\26\2\2\u00c6\u00c7\5\16\b\2\u00c7"+
		"+\3\2\2\2\u00c8\u00d2\5.\30\2\u00c9\u00d2\5\60\31\2\u00ca\u00d2\5\62\32"+
		"\2\u00cb\u00d2\5\64\33\2\u00cc\u00d2\5\66\34\2\u00cd\u00d2\58\35\2\u00ce"+
		"\u00d2\5:\36\2\u00cf\u00d2\5<\37\2\u00d0\u00d2\5> \2\u00d1\u00c8\3\2\2"+
		"\2\u00d1\u00c9\3\2\2\2\u00d1\u00ca\3\2\2\2\u00d1\u00cb\3\2\2\2\u00d1\u00cc"+
		"\3\2\2\2\u00d1\u00cd\3\2\2\2\u00d1\u00ce\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1"+
		"\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\7\4\2\2\u00d4-\3\2\2\2"+
		"\u00d5\u00d6\7\27\2\2\u00d6\u00d7\5\4\3\2\u00d7/\3\2\2\2\u00d8\u00d9\7"+
		"\30\2\2\u00d9\u00da\5\4\3\2\u00da\61\3\2\2\2\u00db\u00dc\7\31\2\2\u00dc"+
		"\u00dd\5\4\3\2\u00dd\63\3\2\2\2\u00de\u00df\7\32\2\2\u00df\65\3\2\2\2"+
		"\u00e0\u00e1\7\33\2\2\u00e1\u00e2\5\16\b\2\u00e2\67\3\2\2\2\u00e3\u00e4"+
		"\7\34\2\2\u00e4\u00e5\5\4\3\2\u00e5\u00e6\7\5\2\2\u00e6\u00e7\5@!\2\u00e7"+
		"\u00e8\7\35\2\2\u00e8\u00e9\5\"\22\2\u00e9\u00ea\7\6\2\2\u00ea9\3\2\2"+
		"\2\u00eb\u00ec\7\36\2\2\u00ec\u00ed\5\4\3\2\u00ed\u00ee\7\37\2\2\u00ee"+
		"\u00ef\5\34\17\2\u00ef\u00f0\7 \2\2\u00f0\u00f8\5\36\20\2\u00f1\u00f2"+
		"\7\22\2\2\u00f2\u00f3\5\34\17\2\u00f3\u00f4\7 \2\2\u00f4\u00f5\5\36\20"+
		"\2\u00f5\u00f7\3\2\2\2\u00f6\u00f1\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6"+
		"\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fb\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb"+
		"\u00fc\7!\2\2\u00fc\u00fd\5\22\n\2\u00fd;\3\2\2\2\u00fe\u00ff\7\"\2\2"+
		"\u00ff\u0100\5\4\3\2\u0100\u0101\7#\2\2\u0101\u0106\5\36\20\2\u0102\u0103"+
		"\7\22\2\2\u0103\u0105\5\36\20\2\u0104\u0102\3\2\2\2\u0105\u0108\3\2\2"+
		"\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0109\3\2\2\2\u0108\u0106"+
		"\3\2\2\2\u0109\u010a\7\6\2\2\u010a\u0111\3\2\2\2\u010b\u010c\7\"\2\2\u010c"+
		"\u010d\5\4\3\2\u010d\u010e\7$\2\2\u010e\u010f\5\f\7\2\u010f\u0111\3\2"+
		"\2\2\u0110\u00fe\3\2\2\2\u0110\u010b\3\2\2\2\u0111=\3\2\2\2\u0112\u0113"+
		"\7%\2\2\u0113\u0114\5\4\3\2\u0114\u0115\7!\2\2\u0115\u0116\5\22\n\2\u0116"+
		"?\3\2\2\2\u0117\u0118\5\34\17\2\u0118\u011f\5B\"\2\u0119\u011a\7\22\2"+
		"\2\u011a\u011b\5\34\17\2\u011b\u011c\5B\"\2\u011c\u011e\3\2\2\2\u011d"+
		"\u0119\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2"+
		"\2\2\u0120A\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0123\7&\2\2\u0123\u0124"+
		"\5D#\2\u0124\u0125\7\6\2\2\u0125\u0128\3\2\2\2\u0126\u0128\7\'\2\2\u0127"+
		"\u0122\3\2\2\2\u0127\u0126\3\2\2\2\u0128C\3\2\2\2\u0129\u012d\5\n\6\2"+
		"\u012a\u012c\5\n\6\2\u012b\u012a\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b"+
		"\3\2\2\2\u012d\u012e\3\2\2\2\u012eE\3\2\2\2\u012f\u012d\3\2\2\2\u0130"+
		"\u0133\5\2\2\2\u0131\u0133\5,\27\2\u0132\u0130\3\2\2\2\u0132\u0131\3\2"+
		"\2\2\u0133\u0136\3\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135"+
		"G\3\2\2\2\u0136\u0134\3\2\2\2\31RTbiu}\u008a\u008f\u0095\u009c\u009e\u00a5"+
		"\u00a8\u00b4\u00d1\u00f8\u0106\u0110\u011f\u0127\u012d\u0132\u0134";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
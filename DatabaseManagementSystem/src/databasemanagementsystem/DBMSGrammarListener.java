// Generated from DBMSGrammar.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DBMSGrammarParser}.
 */
public interface DBMSGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(DBMSGrammarParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(DBMSGrammarParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#relationname}.
	 * @param ctx the parse tree
	 */
	void enterRelationname(DBMSGrammarParser.RelationnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#relationname}.
	 * @param ctx the parse tree
	 */
	void exitRelationname(DBMSGrammarParser.RelationnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(DBMSGrammarParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(DBMSGrammarParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#alpha}.
	 * @param ctx the parse tree
	 */
	void enterAlpha(DBMSGrammarParser.AlphaContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#alpha}.
	 * @param ctx the parse tree
	 */
	void exitAlpha(DBMSGrammarParser.AlphaContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#digit}.
	 * @param ctx the parse tree
	 */
	void enterDigit(DBMSGrammarParser.DigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#digit}.
	 * @param ctx the parse tree
	 */
	void exitDigit(DBMSGrammarParser.DigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(DBMSGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(DBMSGrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#atomicexpr}.
	 * @param ctx the parse tree
	 */
	void enterAtomicexpr(DBMSGrammarParser.AtomicexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#atomicexpr}.
	 * @param ctx the parse tree
	 */
	void exitAtomicexpr(DBMSGrammarParser.AtomicexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#selection}.
	 * @param ctx the parse tree
	 */
	void enterSelection(DBMSGrammarParser.SelectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#selection}.
	 * @param ctx the parse tree
	 */
	void exitSelection(DBMSGrammarParser.SelectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(DBMSGrammarParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(DBMSGrammarParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(DBMSGrammarParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(DBMSGrammarParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(DBMSGrammarParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(DBMSGrammarParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(DBMSGrammarParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(DBMSGrammarParser.OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(DBMSGrammarParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(DBMSGrammarParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#attributename}.
	 * @param ctx the parse tree
	 */
	void enterAttributename(DBMSGrammarParser.AttributenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#attributename}.
	 * @param ctx the parse tree
	 */
	void exitAttributename(DBMSGrammarParser.AttributenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(DBMSGrammarParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(DBMSGrammarParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#projection}.
	 * @param ctx the parse tree
	 */
	void enterProjection(DBMSGrammarParser.ProjectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#projection}.
	 * @param ctx the parse tree
	 */
	void exitProjection(DBMSGrammarParser.ProjectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#attributelist}.
	 * @param ctx the parse tree
	 */
	void enterAttributelist(DBMSGrammarParser.AttributelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#attributelist}.
	 * @param ctx the parse tree
	 */
	void exitAttributelist(DBMSGrammarParser.AttributelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#renaming}.
	 * @param ctx the parse tree
	 */
	void enterRenaming(DBMSGrammarParser.RenamingContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#renaming}.
	 * @param ctx the parse tree
	 */
	void exitRenaming(DBMSGrammarParser.RenamingContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(DBMSGrammarParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(DBMSGrammarParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#difference}.
	 * @param ctx the parse tree
	 */
	void enterDifference(DBMSGrammarParser.DifferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#difference}.
	 * @param ctx the parse tree
	 */
	void exitDifference(DBMSGrammarParser.DifferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#product}.
	 * @param ctx the parse tree
	 */
	void enterProduct(DBMSGrammarParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#product}.
	 * @param ctx the parse tree
	 */
	void exitProduct(DBMSGrammarParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(DBMSGrammarParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(DBMSGrammarParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#opencmd}.
	 * @param ctx the parse tree
	 */
	void enterOpencmd(DBMSGrammarParser.OpencmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#opencmd}.
	 * @param ctx the parse tree
	 */
	void exitOpencmd(DBMSGrammarParser.OpencmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#closecmd}.
	 * @param ctx the parse tree
	 */
	void enterClosecmd(DBMSGrammarParser.ClosecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#closecmd}.
	 * @param ctx the parse tree
	 */
	void exitClosecmd(DBMSGrammarParser.ClosecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#writecmd}.
	 * @param ctx the parse tree
	 */
	void enterWritecmd(DBMSGrammarParser.WritecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#writecmd}.
	 * @param ctx the parse tree
	 */
	void exitWritecmd(DBMSGrammarParser.WritecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#exitcmd}.
	 * @param ctx the parse tree
	 */
	void enterExitcmd(DBMSGrammarParser.ExitcmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#exitcmd}.
	 * @param ctx the parse tree
	 */
	void exitExitcmd(DBMSGrammarParser.ExitcmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#showcmd}.
	 * @param ctx the parse tree
	 */
	void enterShowcmd(DBMSGrammarParser.ShowcmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#showcmd}.
	 * @param ctx the parse tree
	 */
	void exitShowcmd(DBMSGrammarParser.ShowcmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#createcmd}.
	 * @param ctx the parse tree
	 */
	void enterCreatecmd(DBMSGrammarParser.CreatecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#createcmd}.
	 * @param ctx the parse tree
	 */
	void exitCreatecmd(DBMSGrammarParser.CreatecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#updatecmd}.
	 * @param ctx the parse tree
	 */
	void enterUpdatecmd(DBMSGrammarParser.UpdatecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#updatecmd}.
	 * @param ctx the parse tree
	 */
	void exitUpdatecmd(DBMSGrammarParser.UpdatecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#insertcmd}.
	 * @param ctx the parse tree
	 */
	void enterInsertcmd(DBMSGrammarParser.InsertcmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#insertcmd}.
	 * @param ctx the parse tree
	 */
	void exitInsertcmd(DBMSGrammarParser.InsertcmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#deletecmd}.
	 * @param ctx the parse tree
	 */
	void enterDeletecmd(DBMSGrammarParser.DeletecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#deletecmd}.
	 * @param ctx the parse tree
	 */
	void exitDeletecmd(DBMSGrammarParser.DeletecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#typedattributelist}.
	 * @param ctx the parse tree
	 */
	void enterTypedattributelist(DBMSGrammarParser.TypedattributelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#typedattributelist}.
	 * @param ctx the parse tree
	 */
	void exitTypedattributelist(DBMSGrammarParser.TypedattributelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(DBMSGrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(DBMSGrammarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(DBMSGrammarParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(DBMSGrammarParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DBMSGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DBMSGrammarParser.ProgramContext ctx);
}
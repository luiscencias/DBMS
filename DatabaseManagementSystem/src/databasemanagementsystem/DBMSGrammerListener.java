// Generated from DBMSGrammer.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DBMSGrammerParser}.
 */
public interface DBMSGrammerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(DBMSGrammerParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(DBMSGrammerParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#relationname}.
	 * @param ctx the parse tree
	 */
	void enterRelationname(DBMSGrammerParser.RelationnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#relationname}.
	 * @param ctx the parse tree
	 */
	void exitRelationname(DBMSGrammerParser.RelationnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(DBMSGrammerParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(DBMSGrammerParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#alpha}.
	 * @param ctx the parse tree
	 */
	void enterAlpha(DBMSGrammerParser.AlphaContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#alpha}.
	 * @param ctx the parse tree
	 */
	void exitAlpha(DBMSGrammerParser.AlphaContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#digit}.
	 * @param ctx the parse tree
	 */
	void enterDigit(DBMSGrammerParser.DigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#digit}.
	 * @param ctx the parse tree
	 */
	void exitDigit(DBMSGrammerParser.DigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(DBMSGrammerParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(DBMSGrammerParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#atomicexpr}.
	 * @param ctx the parse tree
	 */
	void enterAtomicexpr(DBMSGrammerParser.AtomicexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#atomicexpr}.
	 * @param ctx the parse tree
	 */
	void exitAtomicexpr(DBMSGrammerParser.AtomicexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#selection}.
	 * @param ctx the parse tree
	 */
	void enterSelection(DBMSGrammerParser.SelectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#selection}.
	 * @param ctx the parse tree
	 */
	void exitSelection(DBMSGrammerParser.SelectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(DBMSGrammerParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(DBMSGrammerParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(DBMSGrammerParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(DBMSGrammerParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(DBMSGrammerParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(DBMSGrammerParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(DBMSGrammerParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(DBMSGrammerParser.OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(DBMSGrammerParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(DBMSGrammerParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#attributename}.
	 * @param ctx the parse tree
	 */
	void enterAttributename(DBMSGrammerParser.AttributenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#attributename}.
	 * @param ctx the parse tree
	 */
	void exitAttributename(DBMSGrammerParser.AttributenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(DBMSGrammerParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(DBMSGrammerParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#projection}.
	 * @param ctx the parse tree
	 */
	void enterProjection(DBMSGrammerParser.ProjectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#projection}.
	 * @param ctx the parse tree
	 */
	void exitProjection(DBMSGrammerParser.ProjectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#attributelist}.
	 * @param ctx the parse tree
	 */
	void enterAttributelist(DBMSGrammerParser.AttributelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#attributelist}.
	 * @param ctx the parse tree
	 */
	void exitAttributelist(DBMSGrammerParser.AttributelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#renaming}.
	 * @param ctx the parse tree
	 */
	void enterRenaming(DBMSGrammerParser.RenamingContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#renaming}.
	 * @param ctx the parse tree
	 */
	void exitRenaming(DBMSGrammerParser.RenamingContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(DBMSGrammerParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(DBMSGrammerParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#difference}.
	 * @param ctx the parse tree
	 */
	void enterDifference(DBMSGrammerParser.DifferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#difference}.
	 * @param ctx the parse tree
	 */
	void exitDifference(DBMSGrammerParser.DifferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#product}.
	 * @param ctx the parse tree
	 */
	void enterProduct(DBMSGrammerParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#product}.
	 * @param ctx the parse tree
	 */
	void exitProduct(DBMSGrammerParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(DBMSGrammerParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(DBMSGrammerParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#opencmd}.
	 * @param ctx the parse tree
	 */
	void enterOpencmd(DBMSGrammerParser.OpencmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#opencmd}.
	 * @param ctx the parse tree
	 */
	void exitOpencmd(DBMSGrammerParser.OpencmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#closecmd}.
	 * @param ctx the parse tree
	 */
	void enterClosecmd(DBMSGrammerParser.ClosecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#closecmd}.
	 * @param ctx the parse tree
	 */
	void exitClosecmd(DBMSGrammerParser.ClosecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#writecmd}.
	 * @param ctx the parse tree
	 */
	void enterWritecmd(DBMSGrammerParser.WritecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#writecmd}.
	 * @param ctx the parse tree
	 */
	void exitWritecmd(DBMSGrammerParser.WritecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#exitcmd}.
	 * @param ctx the parse tree
	 */
	void enterExitcmd(DBMSGrammerParser.ExitcmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#exitcmd}.
	 * @param ctx the parse tree
	 */
	void exitExitcmd(DBMSGrammerParser.ExitcmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#showcmd}.
	 * @param ctx the parse tree
	 */
	void enterShowcmd(DBMSGrammerParser.ShowcmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#showcmd}.
	 * @param ctx the parse tree
	 */
	void exitShowcmd(DBMSGrammerParser.ShowcmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#createcmd}.
	 * @param ctx the parse tree
	 */
	void enterCreatecmd(DBMSGrammerParser.CreatecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#createcmd}.
	 * @param ctx the parse tree
	 */
	void exitCreatecmd(DBMSGrammerParser.CreatecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#updatecmd}.
	 * @param ctx the parse tree
	 */
	void enterUpdatecmd(DBMSGrammerParser.UpdatecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#updatecmd}.
	 * @param ctx the parse tree
	 */
	void exitUpdatecmd(DBMSGrammerParser.UpdatecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#insertcmd}.
	 * @param ctx the parse tree
	 */
	void enterInsertcmd(DBMSGrammerParser.InsertcmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#insertcmd}.
	 * @param ctx the parse tree
	 */
	void exitInsertcmd(DBMSGrammerParser.InsertcmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#deletecmd}.
	 * @param ctx the parse tree
	 */
	void enterDeletecmd(DBMSGrammerParser.DeletecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#deletecmd}.
	 * @param ctx the parse tree
	 */
	void exitDeletecmd(DBMSGrammerParser.DeletecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#typedattributelist}.
	 * @param ctx the parse tree
	 */
	void enterTypedattributelist(DBMSGrammerParser.TypedattributelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#typedattributelist}.
	 * @param ctx the parse tree
	 */
	void exitTypedattributelist(DBMSGrammerParser.TypedattributelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(DBMSGrammerParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(DBMSGrammerParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(DBMSGrammerParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(DBMSGrammerParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link DBMSGrammerParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DBMSGrammerParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DBMSGrammerParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DBMSGrammerParser.ProgramContext ctx);
}
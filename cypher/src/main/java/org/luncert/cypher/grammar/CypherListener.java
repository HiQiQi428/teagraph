package org.luncert.cypher.grammar;
// Generated from Cypher.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CypherParser}.
 */
public interface CypherListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 */
	void enterCypher(CypherParser.CypherContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 */
	void exitCypher(CypherParser.CypherContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CypherParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CypherParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(CypherParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(CypherParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 */
	void enterRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 */
	void exitRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void enterSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void exitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(CypherParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(CypherParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#standaloneCall}.
	 * @param ctx the parse tree
	 */
	void enterStandaloneCall(CypherParser.StandaloneCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#standaloneCall}.
	 * @param ctx the parse tree
	 */
	void exitStandaloneCall(CypherParser.StandaloneCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#explicitProcedureInvocation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitProcedureInvocation(CypherParser.ExplicitProcedureInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#explicitProcedureInvocation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitProcedureInvocation(CypherParser.ExplicitProcedureInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#procedureName}.
	 * @param ctx the parse tree
	 */
	void enterProcedureName(CypherParser.ProcedureNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#procedureName}.
	 * @param ctx the parse tree
	 */
	void exitProcedureName(CypherParser.ProcedureNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#namespace}.
	 * @param ctx the parse tree
	 */
	void enterNamespace(CypherParser.NamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#namespace}.
	 * @param ctx the parse tree
	 */
	void exitNamespace(CypherParser.NamespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#implicitProcedureInvocation}.
	 * @param ctx the parse tree
	 */
	void enterImplicitProcedureInvocation(CypherParser.ImplicitProcedureInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#implicitProcedureInvocation}.
	 * @param ctx the parse tree
	 */
	void exitImplicitProcedureInvocation(CypherParser.ImplicitProcedureInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#yieldItems}.
	 * @param ctx the parse tree
	 */
	void enterYieldItems(CypherParser.YieldItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#yieldItems}.
	 * @param ctx the parse tree
	 */
	void exitYieldItems(CypherParser.YieldItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#yieldItem}.
	 * @param ctx the parse tree
	 */
	void enterYieldItem(CypherParser.YieldItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#yieldItem}.
	 * @param ctx the parse tree
	 */
	void exitYieldItem(CypherParser.YieldItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#procedureResultField}.
	 * @param ctx the parse tree
	 */
	void enterProcedureResultField(CypherParser.ProcedureResultFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#procedureResultField}.
	 * @param ctx the parse tree
	 */
	void exitProcedureResultField(CypherParser.ProcedureResultFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#singlePartQuery}.
	 * @param ctx the parse tree
	 */
	void enterSinglePartQuery(CypherParser.SinglePartQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#singlePartQuery}.
	 * @param ctx the parse tree
	 */
	void exitSinglePartQuery(CypherParser.SinglePartQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#readingClause}.
	 * @param ctx the parse tree
	 */
	void enterReadingClause(CypherParser.ReadingClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#readingClause}.
	 * @param ctx the parse tree
	 */
	void exitReadingClause(CypherParser.ReadingClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#updatingClause}.
	 * @param ctx the parse tree
	 */
	void enterUpdatingClause(CypherParser.UpdatingClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#updatingClause}.
	 * @param ctx the parse tree
	 */
	void exitUpdatingClause(CypherParser.UpdatingClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 */
	void enterMatch(CypherParser.MatchContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 */
	void exitMatch(CypherParser.MatchContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unwind}.
	 * @param ctx the parse tree
	 */
	void enterUnwind(CypherParser.UnwindContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unwind}.
	 * @param ctx the parse tree
	 */
	void exitUnwind(CypherParser.UnwindContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#inQueryCall}.
	 * @param ctx the parse tree
	 */
	void enterInQueryCall(CypherParser.InQueryCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#inQueryCall}.
	 * @param ctx the parse tree
	 */
	void exitInQueryCall(CypherParser.InQueryCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#create}.
	 * @param ctx the parse tree
	 */
	void enterCreate(CypherParser.CreateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#create}.
	 * @param ctx the parse tree
	 */
	void exitCreate(CypherParser.CreateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#merge}.
	 * @param ctx the parse tree
	 */
	void enterMerge(CypherParser.MergeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#merge}.
	 * @param ctx the parse tree
	 */
	void exitMerge(CypherParser.MergeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#delete}.
	 * @param ctx the parse tree
	 */
	void enterDelete(CypherParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#delete}.
	 * @param ctx the parse tree
	 */
	void exitDelete(CypherParser.DeleteContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(CypherParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(CypherParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#remove}.
	 * @param ctx the parse tree
	 */
	void enterRemove(CypherParser.RemoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#remove}.
	 * @param ctx the parse tree
	 */
	void exitRemove(CypherParser.RemoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(CypherParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(CypherParser.WhereContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(CypherParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(CypherParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void enterPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void exitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 */
	void enterMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 */
	void exitMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 */
	void enterPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 */
	void exitPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 */
	void enterPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 */
	void exitPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 */
	void enterSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 */
	void exitSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 */
	void enterRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 */
	void exitRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 */
	void enterPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 */
	void exitPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#ret}.
	 * @param ctx the parse tree
	 */
	void enterRet(CypherParser.RetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#ret}.
	 * @param ctx the parse tree
	 */
	void exitRet(CypherParser.RetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 */
	void enterReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 */
	void exitReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 */
	void enterReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 */
	void exitReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 */
	void enterReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 */
	void exitReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 */
	void enterOrder(CypherParser.OrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 */
	void exitOrder(CypherParser.OrderContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 */
	void enterSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 */
	void exitSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 */
	void enterSkip(CypherParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 */
	void exitSkip(CypherParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 */
	void enterLimit(CypherParser.LimitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 */
	void exitLimit(CypherParser.LimitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#multiPartQuery}.
	 * @param ctx the parse tree
	 */
	void enterMultiPartQuery(CypherParser.MultiPartQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#multiPartQuery}.
	 * @param ctx the parse tree
	 */
	void exitMultiPartQuery(CypherParser.MultiPartQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 */
	void enterWith(CypherParser.WithContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 */
	void exitWith(CypherParser.WithContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(CypherParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(CypherParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#xorExpression}.
	 * @param ctx the parse tree
	 */
	void enterXorExpression(CypherParser.XorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#xorExpression}.
	 * @param ctx the parse tree
	 */
	void exitXorExpression(CypherParser.XorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(CypherParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(CypherParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#notExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(CypherParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#notExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(CypherParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(CypherParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(CypherParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#addSubExpression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpression(CypherParser.AddSubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#addSubExpression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpression(CypherParser.AddSubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mulDivModExpression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivModExpression(CypherParser.MulDivModExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mulDivModExpression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivModExpression(CypherParser.MulDivModExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#powerOfExpression}.
	 * @param ctx the parse tree
	 */
	void enterPowerOfExpression(CypherParser.PowerOfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#powerOfExpression}.
	 * @param ctx the parse tree
	 */
	void exitPowerOfExpression(CypherParser.PowerOfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unaryaddSubExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryaddSubExpression(CypherParser.UnaryaddSubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unaryaddSubExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryaddSubExpression(CypherParser.UnaryaddSubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#stringListNullOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringListNullOperatorExpression(CypherParser.StringListNullOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#stringListNullOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringListNullOperatorExpression(CypherParser.StringListNullOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyOrLabelsExpression}.
	 * @param ctx the parse tree
	 */
	void enterPropertyOrLabelsExpression(CypherParser.PropertyOrLabelsExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyOrLabelsExpression}.
	 * @param ctx the parse tree
	 */
	void exitPropertyOrLabelsExpression(CypherParser.PropertyOrLabelsExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 */
	void enterPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 */
	void exitPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 */
	void enterLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 */
	void exitLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(CypherParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(CypherParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#caseAlternatives}.
	 * @param ctx the parse tree
	 */
	void enterCaseAlternatives(CypherParser.CaseAlternativesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#caseAlternatives}.
	 * @param ctx the parse tree
	 */
	void exitCaseAlternatives(CypherParser.CaseAlternativesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 */
	void enterListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 */
	void exitListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternComprehension}.
	 * @param ctx the parse tree
	 */
	void enterPatternComprehension(CypherParser.PatternComprehensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternComprehension}.
	 * @param ctx the parse tree
	 */
	void exitPatternComprehension(CypherParser.PatternComprehensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#idInColl}.
	 * @param ctx the parse tree
	 */
	void enterIdInColl(CypherParser.IdInCollContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#idInColl}.
	 * @param ctx the parse tree
	 */
	void exitIdInColl(CypherParser.IdInCollContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 */
	void enterRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 */
	void exitRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 */
	void enterLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 */
	void exitLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 */
	void enterRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 */
	void exitRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 */
	void enterDash(CypherParser.DashContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 */
	void exitDash(CypherParser.DashContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void enterNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void exitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(CypherParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(CypherParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(CypherParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(CypherParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 */
	void enterMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 */
	void exitMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 */
	void enterListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 */
	void exitListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(CypherParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(CypherParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(CypherParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(CypherParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#hexInteger}.
	 * @param ctx the parse tree
	 */
	void enterHexInteger(CypherParser.HexIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#hexInteger}.
	 * @param ctx the parse tree
	 */
	void exitHexInteger(CypherParser.HexIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#hexDigit}.
	 * @param ctx the parse tree
	 */
	void enterHexDigit(CypherParser.HexDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#hexDigit}.
	 * @param ctx the parse tree
	 */
	void exitHexDigit(CypherParser.HexDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#octalInteger}.
	 * @param ctx the parse tree
	 */
	void enterOctalInteger(CypherParser.OctalIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#octalInteger}.
	 * @param ctx the parse tree
	 */
	void exitOctalInteger(CypherParser.OctalIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#octDigit}.
	 * @param ctx the parse tree
	 */
	void enterOctDigit(CypherParser.OctDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#octDigit}.
	 * @param ctx the parse tree
	 */
	void exitOctDigit(CypherParser.OctDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#decimalInteger}.
	 * @param ctx the parse tree
	 */
	void enterDecimalInteger(CypherParser.DecimalIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#decimalInteger}.
	 * @param ctx the parse tree
	 */
	void exitDecimalInteger(CypherParser.DecimalIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#exponentDecimalReal}.
	 * @param ctx the parse tree
	 */
	void enterExponentDecimalReal(CypherParser.ExponentDecimalRealContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#exponentDecimalReal}.
	 * @param ctx the parse tree
	 */
	void exitExponentDecimalReal(CypherParser.ExponentDecimalRealContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#regularDecimalReal}.
	 * @param ctx the parse tree
	 */
	void enterRegularDecimalReal(CypherParser.RegularDecimalRealContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#regularDecimalReal}.
	 * @param ctx the parse tree
	 */
	void exitRegularDecimalReal(CypherParser.RegularDecimalRealContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(CypherParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(CypherParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#escapedChar}.
	 * @param ctx the parse tree
	 */
	void enterEscapedChar(CypherParser.EscapedCharContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#escapedChar}.
	 * @param ctx the parse tree
	 */
	void exitEscapedChar(CypherParser.EscapedCharContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#escapedHexDigit}.
	 * @param ctx the parse tree
	 */
	void enterEscapedHexDigit(CypherParser.EscapedHexDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#escapedHexDigit}.
	 * @param ctx the parse tree
	 */
	void exitEscapedHexDigit(CypherParser.EscapedHexDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#digit}.
	 * @param ctx the parse tree
	 */
	void enterDigit(CypherParser.DigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#digit}.
	 * @param ctx the parse tree
	 */
	void exitDigit(CypherParser.DigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#zeroDigit}.
	 * @param ctx the parse tree
	 */
	void enterZeroDigit(CypherParser.ZeroDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#zeroDigit}.
	 * @param ctx the parse tree
	 */
	void exitZeroDigit(CypherParser.ZeroDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nonZeroDigit}.
	 * @param ctx the parse tree
	 */
	void enterNonZeroDigit(CypherParser.NonZeroDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nonZeroDigit}.
	 * @param ctx the parse tree
	 */
	void exitNonZeroDigit(CypherParser.NonZeroDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nonZeroOctDigit}.
	 * @param ctx the parse tree
	 */
	void enterNonZeroOctDigit(CypherParser.NonZeroOctDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nonZeroOctDigit}.
	 * @param ctx the parse tree
	 */
	void exitNonZeroOctDigit(CypherParser.NonZeroOctDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#schemaName}.
	 * @param ctx the parse tree
	 */
	void enterSchemaName(CypherParser.SchemaNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#schemaName}.
	 * @param ctx the parse tree
	 */
	void exitSchemaName(CypherParser.SchemaNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 */
	void enterSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 */
	void exitSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unescapedSymbolicName}.
	 * @param ctx the parse tree
	 */
	void enterUnescapedSymbolicName(CypherParser.UnescapedSymbolicNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unescapedSymbolicName}.
	 * @param ctx the parse tree
	 */
	void exitUnescapedSymbolicName(CypherParser.UnescapedSymbolicNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#identifierStart}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierStart(CypherParser.IdentifierStartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#identifierStart}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierStart(CypherParser.IdentifierStartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#identifierExt}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExt(CypherParser.IdentifierExtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#identifierExt}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExt(CypherParser.IdentifierExtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#escapedSymbolicName}.
	 * @param ctx the parse tree
	 */
	void enterEscapedSymbolicName(CypherParser.EscapedSymbolicNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#escapedSymbolicName}.
	 * @param ctx the parse tree
	 */
	void exitEscapedSymbolicName(CypherParser.EscapedSymbolicNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#hexLetter}.
	 * @param ctx the parse tree
	 */
	void enterHexLetter(CypherParser.HexLetterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#hexLetter}.
	 * @param ctx the parse tree
	 */
	void exitHexLetter(CypherParser.HexLetterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#w_count}.
	 * @param ctx the parse tree
	 */
	void enterW_count(CypherParser.W_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#w_count}.
	 * @param ctx the parse tree
	 */
	void exitW_count(CypherParser.W_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#w_filter}.
	 * @param ctx the parse tree
	 */
	void enterW_filter(CypherParser.W_filterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#w_filter}.
	 * @param ctx the parse tree
	 */
	void exitW_filter(CypherParser.W_filterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#w_extract}.
	 * @param ctx the parse tree
	 */
	void enterW_extract(CypherParser.W_extractContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#w_extract}.
	 * @param ctx the parse tree
	 */
	void exitW_extract(CypherParser.W_extractContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#w_any}.
	 * @param ctx the parse tree
	 */
	void enterW_any(CypherParser.W_anyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#w_any}.
	 * @param ctx the parse tree
	 */
	void exitW_any(CypherParser.W_anyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#w_none}.
	 * @param ctx the parse tree
	 */
	void enterW_none(CypherParser.W_noneContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#w_none}.
	 * @param ctx the parse tree
	 */
	void exitW_none(CypherParser.W_noneContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#w_single}.
	 * @param ctx the parse tree
	 */
	void enterW_single(CypherParser.W_singleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#w_single}.
	 * @param ctx the parse tree
	 */
	void exitW_single(CypherParser.W_singleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#w_call}.
	 * @param ctx the parse tree
	 */
	void enterW_call(CypherParser.W_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#w_call}.
	 * @param ctx the parse tree
	 */
	void exitW_call(CypherParser.W_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#w_yield}.
	 * @param ctx the parse tree
	 */
	void enterW_yield(CypherParser.W_yieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#w_yield}.
	 * @param ctx the parse tree
	 */
	void exitW_yield(CypherParser.W_yieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#reserveWords}.
	 * @param ctx the parse tree
	 */
	void enterReserveWords(CypherParser.ReserveWordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#reserveWords}.
	 * @param ctx the parse tree
	 */
	void exitReserveWords(CypherParser.ReserveWordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_all}.
	 * @param ctx the parse tree
	 */
	void enterRw_all(CypherParser.Rw_allContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_all}.
	 * @param ctx the parse tree
	 */
	void exitRw_all(CypherParser.Rw_allContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_asc}.
	 * @param ctx the parse tree
	 */
	void enterRw_asc(CypherParser.Rw_ascContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_asc}.
	 * @param ctx the parse tree
	 */
	void exitRw_asc(CypherParser.Rw_ascContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_ascending}.
	 * @param ctx the parse tree
	 */
	void enterRw_ascending(CypherParser.Rw_ascendingContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_ascending}.
	 * @param ctx the parse tree
	 */
	void exitRw_ascending(CypherParser.Rw_ascendingContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_by}.
	 * @param ctx the parse tree
	 */
	void enterRw_by(CypherParser.Rw_byContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_by}.
	 * @param ctx the parse tree
	 */
	void exitRw_by(CypherParser.Rw_byContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_create}.
	 * @param ctx the parse tree
	 */
	void enterRw_create(CypherParser.Rw_createContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_create}.
	 * @param ctx the parse tree
	 */
	void exitRw_create(CypherParser.Rw_createContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_else}.
	 * @param ctx the parse tree
	 */
	void enterRw_else(CypherParser.Rw_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_else}.
	 * @param ctx the parse tree
	 */
	void exitRw_else(CypherParser.Rw_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_end}.
	 * @param ctx the parse tree
	 */
	void enterRw_end(CypherParser.Rw_endContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_end}.
	 * @param ctx the parse tree
	 */
	void exitRw_end(CypherParser.Rw_endContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_delete}.
	 * @param ctx the parse tree
	 */
	void enterRw_delete(CypherParser.Rw_deleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_delete}.
	 * @param ctx the parse tree
	 */
	void exitRw_delete(CypherParser.Rw_deleteContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_desc}.
	 * @param ctx the parse tree
	 */
	void enterRw_desc(CypherParser.Rw_descContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_desc}.
	 * @param ctx the parse tree
	 */
	void exitRw_desc(CypherParser.Rw_descContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_descending}.
	 * @param ctx the parse tree
	 */
	void enterRw_descending(CypherParser.Rw_descendingContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_descending}.
	 * @param ctx the parse tree
	 */
	void exitRw_descending(CypherParser.Rw_descendingContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_detach}.
	 * @param ctx the parse tree
	 */
	void enterRw_detach(CypherParser.Rw_detachContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_detach}.
	 * @param ctx the parse tree
	 */
	void exitRw_detach(CypherParser.Rw_detachContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_exists}.
	 * @param ctx the parse tree
	 */
	void enterRw_exists(CypherParser.Rw_existsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_exists}.
	 * @param ctx the parse tree
	 */
	void exitRw_exists(CypherParser.Rw_existsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_limit}.
	 * @param ctx the parse tree
	 */
	void enterRw_limit(CypherParser.Rw_limitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_limit}.
	 * @param ctx the parse tree
	 */
	void exitRw_limit(CypherParser.Rw_limitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_match}.
	 * @param ctx the parse tree
	 */
	void enterRw_match(CypherParser.Rw_matchContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_match}.
	 * @param ctx the parse tree
	 */
	void exitRw_match(CypherParser.Rw_matchContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_merge}.
	 * @param ctx the parse tree
	 */
	void enterRw_merge(CypherParser.Rw_mergeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_merge}.
	 * @param ctx the parse tree
	 */
	void exitRw_merge(CypherParser.Rw_mergeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_on}.
	 * @param ctx the parse tree
	 */
	void enterRw_on(CypherParser.Rw_onContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_on}.
	 * @param ctx the parse tree
	 */
	void exitRw_on(CypherParser.Rw_onContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_optional}.
	 * @param ctx the parse tree
	 */
	void enterRw_optional(CypherParser.Rw_optionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_optional}.
	 * @param ctx the parse tree
	 */
	void exitRw_optional(CypherParser.Rw_optionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_order}.
	 * @param ctx the parse tree
	 */
	void enterRw_order(CypherParser.Rw_orderContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_order}.
	 * @param ctx the parse tree
	 */
	void exitRw_order(CypherParser.Rw_orderContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_remove}.
	 * @param ctx the parse tree
	 */
	void enterRw_remove(CypherParser.Rw_removeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_remove}.
	 * @param ctx the parse tree
	 */
	void exitRw_remove(CypherParser.Rw_removeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_return}.
	 * @param ctx the parse tree
	 */
	void enterRw_return(CypherParser.Rw_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_return}.
	 * @param ctx the parse tree
	 */
	void exitRw_return(CypherParser.Rw_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_set}.
	 * @param ctx the parse tree
	 */
	void enterRw_set(CypherParser.Rw_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_set}.
	 * @param ctx the parse tree
	 */
	void exitRw_set(CypherParser.Rw_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_skip}.
	 * @param ctx the parse tree
	 */
	void enterRw_skip(CypherParser.Rw_skipContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_skip}.
	 * @param ctx the parse tree
	 */
	void exitRw_skip(CypherParser.Rw_skipContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_where}.
	 * @param ctx the parse tree
	 */
	void enterRw_where(CypherParser.Rw_whereContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_where}.
	 * @param ctx the parse tree
	 */
	void exitRw_where(CypherParser.Rw_whereContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_with}.
	 * @param ctx the parse tree
	 */
	void enterRw_with(CypherParser.Rw_withContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_with}.
	 * @param ctx the parse tree
	 */
	void exitRw_with(CypherParser.Rw_withContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_union}.
	 * @param ctx the parse tree
	 */
	void enterRw_union(CypherParser.Rw_unionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_union}.
	 * @param ctx the parse tree
	 */
	void exitRw_union(CypherParser.Rw_unionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_unwind}.
	 * @param ctx the parse tree
	 */
	void enterRw_unwind(CypherParser.Rw_unwindContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_unwind}.
	 * @param ctx the parse tree
	 */
	void exitRw_unwind(CypherParser.Rw_unwindContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_and}.
	 * @param ctx the parse tree
	 */
	void enterRw_and(CypherParser.Rw_andContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_and}.
	 * @param ctx the parse tree
	 */
	void exitRw_and(CypherParser.Rw_andContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_as}.
	 * @param ctx the parse tree
	 */
	void enterRw_as(CypherParser.Rw_asContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_as}.
	 * @param ctx the parse tree
	 */
	void exitRw_as(CypherParser.Rw_asContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_contains}.
	 * @param ctx the parse tree
	 */
	void enterRw_contains(CypherParser.Rw_containsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_contains}.
	 * @param ctx the parse tree
	 */
	void exitRw_contains(CypherParser.Rw_containsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_distinct}.
	 * @param ctx the parse tree
	 */
	void enterRw_distinct(CypherParser.Rw_distinctContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_distinct}.
	 * @param ctx the parse tree
	 */
	void exitRw_distinct(CypherParser.Rw_distinctContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_ends}.
	 * @param ctx the parse tree
	 */
	void enterRw_ends(CypherParser.Rw_endsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_ends}.
	 * @param ctx the parse tree
	 */
	void exitRw_ends(CypherParser.Rw_endsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_in}.
	 * @param ctx the parse tree
	 */
	void enterRw_in(CypherParser.Rw_inContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_in}.
	 * @param ctx the parse tree
	 */
	void exitRw_in(CypherParser.Rw_inContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_is}.
	 * @param ctx the parse tree
	 */
	void enterRw_is(CypherParser.Rw_isContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_is}.
	 * @param ctx the parse tree
	 */
	void exitRw_is(CypherParser.Rw_isContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_not}.
	 * @param ctx the parse tree
	 */
	void enterRw_not(CypherParser.Rw_notContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_not}.
	 * @param ctx the parse tree
	 */
	void exitRw_not(CypherParser.Rw_notContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_or}.
	 * @param ctx the parse tree
	 */
	void enterRw_or(CypherParser.Rw_orContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_or}.
	 * @param ctx the parse tree
	 */
	void exitRw_or(CypherParser.Rw_orContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_starts}.
	 * @param ctx the parse tree
	 */
	void enterRw_starts(CypherParser.Rw_startsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_starts}.
	 * @param ctx the parse tree
	 */
	void exitRw_starts(CypherParser.Rw_startsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_xor}.
	 * @param ctx the parse tree
	 */
	void enterRw_xor(CypherParser.Rw_xorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_xor}.
	 * @param ctx the parse tree
	 */
	void exitRw_xor(CypherParser.Rw_xorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_false}.
	 * @param ctx the parse tree
	 */
	void enterRw_false(CypherParser.Rw_falseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_false}.
	 * @param ctx the parse tree
	 */
	void exitRw_false(CypherParser.Rw_falseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_true}.
	 * @param ctx the parse tree
	 */
	void enterRw_true(CypherParser.Rw_trueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_true}.
	 * @param ctx the parse tree
	 */
	void exitRw_true(CypherParser.Rw_trueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_null}.
	 * @param ctx the parse tree
	 */
	void enterRw_null(CypherParser.Rw_nullContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_null}.
	 * @param ctx the parse tree
	 */
	void exitRw_null(CypherParser.Rw_nullContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_constraint}.
	 * @param ctx the parse tree
	 */
	void enterRw_constraint(CypherParser.Rw_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_constraint}.
	 * @param ctx the parse tree
	 */
	void exitRw_constraint(CypherParser.Rw_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_do}.
	 * @param ctx the parse tree
	 */
	void enterRw_do(CypherParser.Rw_doContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_do}.
	 * @param ctx the parse tree
	 */
	void exitRw_do(CypherParser.Rw_doContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_for}.
	 * @param ctx the parse tree
	 */
	void enterRw_for(CypherParser.Rw_forContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_for}.
	 * @param ctx the parse tree
	 */
	void exitRw_for(CypherParser.Rw_forContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_require}.
	 * @param ctx the parse tree
	 */
	void enterRw_require(CypherParser.Rw_requireContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_require}.
	 * @param ctx the parse tree
	 */
	void exitRw_require(CypherParser.Rw_requireContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_unique}.
	 * @param ctx the parse tree
	 */
	void enterRw_unique(CypherParser.Rw_uniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_unique}.
	 * @param ctx the parse tree
	 */
	void exitRw_unique(CypherParser.Rw_uniqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_case}.
	 * @param ctx the parse tree
	 */
	void enterRw_case(CypherParser.Rw_caseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_case}.
	 * @param ctx the parse tree
	 */
	void exitRw_case(CypherParser.Rw_caseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_when}.
	 * @param ctx the parse tree
	 */
	void enterRw_when(CypherParser.Rw_whenContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_when}.
	 * @param ctx the parse tree
	 */
	void exitRw_when(CypherParser.Rw_whenContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_then}.
	 * @param ctx the parse tree
	 */
	void enterRw_then(CypherParser.Rw_thenContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_then}.
	 * @param ctx the parse tree
	 */
	void exitRw_then(CypherParser.Rw_thenContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_mandatory}.
	 * @param ctx the parse tree
	 */
	void enterRw_mandatory(CypherParser.Rw_mandatoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_mandatory}.
	 * @param ctx the parse tree
	 */
	void exitRw_mandatory(CypherParser.Rw_mandatoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_scalar}.
	 * @param ctx the parse tree
	 */
	void enterRw_scalar(CypherParser.Rw_scalarContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_scalar}.
	 * @param ctx the parse tree
	 */
	void exitRw_scalar(CypherParser.Rw_scalarContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_of}.
	 * @param ctx the parse tree
	 */
	void enterRw_of(CypherParser.Rw_ofContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_of}.
	 * @param ctx the parse tree
	 */
	void exitRw_of(CypherParser.Rw_ofContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_add}.
	 * @param ctx the parse tree
	 */
	void enterRw_add(CypherParser.Rw_addContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_add}.
	 * @param ctx the parse tree
	 */
	void exitRw_add(CypherParser.Rw_addContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rw_drop}.
	 * @param ctx the parse tree
	 */
	void enterRw_drop(CypherParser.Rw_dropContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rw_drop}.
	 * @param ctx the parse tree
	 */
	void exitRw_drop(CypherParser.Rw_dropContext ctx);
}
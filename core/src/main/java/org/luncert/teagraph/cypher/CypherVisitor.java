package org.luncert.teagraph.cypher;

// Generated from Cypher.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CypherParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CypherVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCypher(CypherParser.CypherContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CypherParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(CypherParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnion(CypherParser.UnionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#standaloneCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStandaloneCall(CypherParser.StandaloneCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#explicitProcedureInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplicitProcedureInvocation(CypherParser.ExplicitProcedureInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#procedureName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureName(CypherParser.ProcedureNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespace(CypherParser.NamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#implicitProcedureInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplicitProcedureInvocation(CypherParser.ImplicitProcedureInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#yieldItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYieldItems(CypherParser.YieldItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#yieldItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYieldItem(CypherParser.YieldItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#procedureResultField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureResultField(CypherParser.ProcedureResultFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#singlePartQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinglePartQuery(CypherParser.SinglePartQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#readingClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadingClause(CypherParser.ReadingClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#updatingClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdatingClause(CypherParser.UpdatingClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatch(CypherParser.MatchContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unwind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnwind(CypherParser.UnwindContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#inQueryCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInQueryCall(CypherParser.InQueryCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#create}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate(CypherParser.CreateContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#merge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMerge(CypherParser.MergeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete(CypherParser.DeleteContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(CypherParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#remove}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemove(CypherParser.RemoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere(CypherParser.WhereContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(CypherParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#ret}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRet(CypherParser.RetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrder(CypherParser.OrderContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(CypherParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimit(CypherParser.LimitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#multiPartQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiPartQuery(CypherParser.MultiPartQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith(CypherParser.WithContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#orExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(CypherParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#xorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorExpression(CypherParser.XorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(CypherParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#notExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(CypherParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(CypherParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#addSubExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpression(CypherParser.AddSubExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mulDivModExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExpression(CypherParser.MulDivModExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#powerOfExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowerOfExpression(CypherParser.PowerOfExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unaryaddSubExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryaddSubExpression(CypherParser.UnaryaddSubExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#stringListNullOperatorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringListNullOperatorExpression(CypherParser.StringListNullOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyOrLabelsExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyOrLabelsExpression(CypherParser.PropertyOrLabelsExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(CypherParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#caseAlternatives}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseAlternatives(CypherParser.CaseAlternativesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternComprehension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternComprehension(CypherParser.PatternComprehensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#idInColl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdInColl(CypherParser.IdInCollContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDash(CypherParser.DashContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(CypherParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(CypherParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(CypherParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(CypherParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#hexInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexInteger(CypherParser.HexIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#hexDigit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexDigit(CypherParser.HexDigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#octalInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctalInteger(CypherParser.OctalIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#octDigit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctDigit(CypherParser.OctDigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#decimalInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalInteger(CypherParser.DecimalIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#exponentDecimalReal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentDecimalReal(CypherParser.ExponentDecimalRealContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#regularDecimalReal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegularDecimalReal(CypherParser.RegularDecimalRealContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(CypherParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#escapedChar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEscapedChar(CypherParser.EscapedCharContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#escapedHexDigit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEscapedHexDigit(CypherParser.EscapedHexDigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit(CypherParser.DigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#zeroDigit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZeroDigit(CypherParser.ZeroDigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nonZeroDigit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonZeroDigit(CypherParser.NonZeroDigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nonZeroOctDigit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonZeroOctDigit(CypherParser.NonZeroOctDigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#schemaName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchemaName(CypherParser.SchemaNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unescapedSymbolicName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnescapedSymbolicName(CypherParser.UnescapedSymbolicNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#identifierStart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierStart(CypherParser.IdentifierStartContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#identifierExt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExt(CypherParser.IdentifierExtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#escapedSymbolicName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEscapedSymbolicName(CypherParser.EscapedSymbolicNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#hexLetter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexLetter(CypherParser.HexLetterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#w_count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitW_count(CypherParser.W_countContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#w_filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitW_filter(CypherParser.W_filterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#w_extract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitW_extract(CypherParser.W_extractContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#w_any}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitW_any(CypherParser.W_anyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#w_none}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitW_none(CypherParser.W_noneContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#w_single}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitW_single(CypherParser.W_singleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#w_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitW_call(CypherParser.W_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#w_yield}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitW_yield(CypherParser.W_yieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#reserveWords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReserveWords(CypherParser.ReserveWordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_all}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_all(CypherParser.Rw_allContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_asc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_asc(CypherParser.Rw_ascContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_ascending}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_ascending(CypherParser.Rw_ascendingContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_by}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_by(CypherParser.Rw_byContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_create}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_create(CypherParser.Rw_createContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_else(CypherParser.Rw_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_end}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_end(CypherParser.Rw_endContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_delete(CypherParser.Rw_deleteContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_desc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_desc(CypherParser.Rw_descContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_descending}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_descending(CypherParser.Rw_descendingContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_detach}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_detach(CypherParser.Rw_detachContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_exists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_exists(CypherParser.Rw_existsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_limit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_limit(CypherParser.Rw_limitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_match}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_match(CypherParser.Rw_matchContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_merge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_merge(CypherParser.Rw_mergeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_on}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_on(CypherParser.Rw_onContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_optional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_optional(CypherParser.Rw_optionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_order}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_order(CypherParser.Rw_orderContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_remove}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_remove(CypherParser.Rw_removeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_return(CypherParser.Rw_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_set(CypherParser.Rw_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_skip}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_skip(CypherParser.Rw_skipContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_where}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_where(CypherParser.Rw_whereContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_with}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_with(CypherParser.Rw_withContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_union}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_union(CypherParser.Rw_unionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_unwind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_unwind(CypherParser.Rw_unwindContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_and(CypherParser.Rw_andContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_as}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_as(CypherParser.Rw_asContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_contains}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_contains(CypherParser.Rw_containsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_distinct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_distinct(CypherParser.Rw_distinctContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_ends}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_ends(CypherParser.Rw_endsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_in}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_in(CypherParser.Rw_inContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_is}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_is(CypherParser.Rw_isContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_not(CypherParser.Rw_notContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_or(CypherParser.Rw_orContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_starts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_starts(CypherParser.Rw_startsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_xor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_xor(CypherParser.Rw_xorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_false}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_false(CypherParser.Rw_falseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_true}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_true(CypherParser.Rw_trueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_null}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_null(CypherParser.Rw_nullContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_constraint(CypherParser.Rw_constraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_do}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_do(CypherParser.Rw_doContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_for(CypherParser.Rw_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_require}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_require(CypherParser.Rw_requireContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_unique}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_unique(CypherParser.Rw_uniqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_case}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_case(CypherParser.Rw_caseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_when}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_when(CypherParser.Rw_whenContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_then}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_then(CypherParser.Rw_thenContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_mandatory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_mandatory(CypherParser.Rw_mandatoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_scalar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_scalar(CypherParser.Rw_scalarContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_of}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_of(CypherParser.Rw_ofContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_add(CypherParser.Rw_addContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rw_drop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRw_drop(CypherParser.Rw_dropContext ctx);
}

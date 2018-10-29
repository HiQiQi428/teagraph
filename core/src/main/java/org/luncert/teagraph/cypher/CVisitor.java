package org.luncert.teagraph.cypher;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.luncert.teagraph.cypher.CypherParser.*;

public class CVisitor implements CypherVisitor<Object> {

    private static class Observer {

        List<Predicate<ParseTree>> pls = new LinkedList<>();
        List<Function<ParseTree, Object>> fls = new LinkedList<>();

        Observer regisOption(Predicate<ParseTree> check, Function<ParseTree, Object> handle) {
            pls.add(check);
            fls.add(handle);
            return this;
        }

        Object handle(ParseTree tree) {
            ParseTree tmp;
            int listenerSize = pls.size();
            for (int i = 0, limit = tree.getChildCount(); i < limit; i++) {
                tmp = tree.getChild(i);
                for (int j = 0; j < listenerSize; j++) {
                    if (pls.get(j).test(tmp))
                        return fls.get(j).apply(tmp);
                }
            }
            return null;
        }

    }

    public Object visit(ParseTree tree) {
        visitCypher((CypherContext)tree);
        return null;
    }

    public Object visitChildren(RuleNode node) {
        return null;
    }

    public Object visitTerminal(TerminalNode node) {
        return null;
    }

    public Object visitErrorNode(ErrorNode node) {
        return null;
    }

    // entity

    public Object visitCypher(CypherContext ctx) {
        return new Observer()
        .regisOption(
            (t) -> (t instanceof StatementContext),
            (t) -> visitStatement(StatementContext.class.cast(t))
        ).handle(ctx);
    }

    public Object visitStatement(StatementContext ctx) {
        return new Observer()
        .regisOption(
            (t) -> (t instanceof QueryContext),
            (t) -> visitQuery(QueryContext.class.cast(t))
        ).handle(ctx);
    }

    public Object visitQuery(QueryContext ctx) {
        return new Observer()
        .regisOption(
            (t) -> (t instanceof RegularQueryContext),
            (t) -> visitRegularQuery(RegularQueryContext.class.cast(t))
        ).regisOption(
            (t) -> (t instanceof SingleQueryContext),
            (t) -> visitSingleQuery(SingleQueryContext.class.cast(t))
        ).handle(ctx);
    }

    @Override
    public Object visitRegularQuery(RegularQueryContext ctx) {
        return new Observer()
        .regisOption(
            (t) -> (t instanceof SingleQueryContext),
            (t) -> visitSingleQuery(SingleQueryContext.class.cast(t))
        ).regisOption(
            (t) -> (t instanceof UnionContext),
            (t) -> visitUnion(UnionContext.class.cast(t))
        ).handle(ctx);
    }

    @Override
    public Object visitSingleQuery(SingleQueryContext ctx) {
        return new Observer()
        .regisOption(
            (t) -> (t instanceof SinglePartQueryContext),
            (t) -> visitSinglePartQuery(SinglePartQueryContext.class.cast(t))
        ).regisOption(
            (t) -> (t instanceof MultiPartQueryContext),
            (t) -> visitMultiPartQuery(MultiPartQueryContext.class.cast(t))
        ).handle(ctx);
    }

    @Override
    public Object visitUnion(UnionContext ctx) {
        return null;
    }

    @Override
    public Object visitStandaloneCall(StandaloneCallContext ctx) {
        return null;
    }

    @Override
    public Object visitExplicitProcedureInvocation(ExplicitProcedureInvocationContext ctx) {
        return null;
    }

    @Override
    public Object visitProcedureName(ProcedureNameContext ctx) {
        return null;
    }

    @Override
    public Object visitNamespace(NamespaceContext ctx) {
        return null;
    }

    @Override
    public Object visitImplicitProcedureInvocation(ImplicitProcedureInvocationContext ctx) {
        return null;
    }

    @Override
    public Object visitYieldItems(YieldItemsContext ctx) {
        return null;
    }

    @Override
    public Object visitYieldItem(YieldItemContext ctx) {
        return null;
    }

    @Override
    public Object visitProcedureResultField(ProcedureResultFieldContext ctx) {
        return null;
    }

    @Override
    public Object visitSinglePartQuery(SinglePartQueryContext ctx) {
        return null;
    }

    @Override
    public Object visitReadingClause(ReadingClauseContext ctx) {
        return null;
    }

    @Override
    public Object visitUpdatingClause(UpdatingClauseContext ctx) {
        return null;
    }

    @Override
    public Object visitMatch(MatchContext ctx) {
        return null;
    }

    @Override
    public Object visitUnwind(UnwindContext ctx) {
        return null;
    }

    @Override
    public Object visitInQueryCall(InQueryCallContext ctx) {
        return null;
    }

    @Override
    public Object visitCreate(CreateContext ctx) {
        return null;
    }

    @Override
    public Object visitMerge(MergeContext ctx) {
        return null;
    }

    @Override
    public Object visitDelete(DeleteContext ctx) {
        return null;
    }

    @Override
    public Object visitSet(SetContext ctx) {
        return null;
    }

    @Override
    public Object visitRemove(RemoveContext ctx) {
        return null;
    }

    @Override
    public Object visitWhere(WhereContext ctx) {
        return null;
    }

    @Override
    public Object visitPattern(PatternContext ctx) {
        return null;
    }

    @Override
    public Object visitPatternPart(PatternPartContext ctx) {
        return null;
    }

    @Override
    public Object visitMergeAction(MergeActionContext ctx) {
        return null;
    }

    @Override
    public Object visitPatternElement(PatternElementContext ctx) {
        return null;
    }

    @Override
    public Object visitPatternElementChain(PatternElementChainContext ctx) {
        return null;
    }

    @Override
    public Object visitSetItem(SetItemContext ctx) {
        return null;
    }

    @Override
    public Object visitRemoveItem(RemoveItemContext ctx) {
        return null;
    }

    @Override
    public Object visitPropertyExpression(PropertyExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitRet(RetContext ctx) {
        return null;
    }

    @Override
    public Object visitReturnBody(ReturnBodyContext ctx) {
        return null;
    }

    @Override
    public Object visitReturnItems(ReturnItemsContext ctx) {
        return null;
    }

    @Override
    public Object visitReturnItem(ReturnItemContext ctx) {
        return null;
    }

    @Override
    public Object visitOrder(OrderContext ctx) {
        return null;
    }

    @Override
    public Object visitSortItem(SortItemContext ctx) {
        return null;
    }

    @Override
    public Object visitSkip(SkipContext ctx) {
        return null;
    }

    @Override
    public Object visitLimit(LimitContext ctx) {
        return null;
    }

    @Override
    public Object visitMultiPartQuery(MultiPartQueryContext ctx) {
        return null;
    }

    @Override
    public Object visitWith(WithContext ctx) {
        return null;
    }

    @Override
    public Object visitExpression(ExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitOrExpression(OrExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitXorExpression(XorExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitAndExpression(AndExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitNotExpression(NotExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitComparisonExpression(ComparisonExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitAddSubExpression(AddSubExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitPartialComparisonExpression(PartialComparisonExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitMulDivModExpression(MulDivModExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitPowerOfExpression(PowerOfExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitUnaryaddSubExpression(UnaryaddSubExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitStringListNullOperatorExpression(StringListNullOperatorExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitPropertyOrLabelsExpression(PropertyOrLabelsExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitPropertyLookup(PropertyLookupContext ctx) {
        return null;
    }

    @Override
    public Object visitPropertyKeyName(PropertyKeyNameContext ctx) {
        return null;
    }

    @Override
    public Object visitNodeLabels(NodeLabelsContext ctx) {
        return null;
    }

    @Override
    public Object visitNodeLabel(NodeLabelContext ctx) {
        return null;
    }

    @Override
    public Object visitLabelName(LabelNameContext ctx) {
        return null;
    }

    @Override
    public Object visitAtom(AtomContext ctx) {
        return null;
    }

    @Override
    public Object visitCaseExpression(CaseExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitCaseAlternatives(CaseAlternativesContext ctx) {
        return null;
    }

    @Override
    public Object visitListComprehension(ListComprehensionContext ctx) {
        return null;
    }

    @Override
    public Object visitPatternComprehension(PatternComprehensionContext ctx) {
        return null;
    }

    @Override
    public Object visitRelationshipsPattern(RelationshipsPatternContext ctx) {
        return null;
    }

    @Override
    public Object visitFilterExpression(FilterExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitIdInColl(IdInCollContext ctx) {
        return null;
    }

    @Override
    public Object visitParenthesizedExpression(ParenthesizedExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitFunctionInvocation(FunctionInvocationContext ctx) {
        return null;
    }

    @Override
    public Object visitFunctionName(FunctionNameContext ctx) {
        return null;
    }

    @Override
    public Object visitRelationshipPattern(RelationshipPatternContext ctx) {
        return null;
    }

    @Override
    public Object visitRelationshipDetail(RelationshipDetailContext ctx) {
        return null;
    }

    @Override
    public Object visitRelationshipTypes(RelationshipTypesContext ctx) {
        return null;
    }

    @Override
    public Object visitRelTypeName(RelTypeNameContext ctx) {
        return null;
    }

    @Override
    public Object visitRangeLiteral(RangeLiteralContext ctx) {
        return null;
    }

    @Override
    public Object visitLeftArrowHead(LeftArrowHeadContext ctx) {
        return null;
    }

    @Override
    public Object visitRightArrowHead(RightArrowHeadContext ctx) {
        return null;
    }

    @Override
    public Object visitDash(DashContext ctx) {
        return null;
    }

    @Override
    public Object visitNodePattern(NodePatternContext ctx) {
        return null;
    }

    @Override
    public Object visitProperties(PropertiesContext ctx) {
        return null;
    }

    @Override
    public Object visitVariable(VariableContext ctx) {
        return null;
    }

    @Override
    public Object visitLiteral(LiteralContext ctx) {
        return null;
    }

    @Override
    public Object visitParameter(ParameterContext ctx) {
        return null;
    }

    @Override
    public Object visitMapLiteral(MapLiteralContext ctx) {
        return null;
    }

    @Override
    public Object visitListLiteral(ListLiteralContext ctx) {
        return null;
    }

    @Override
    public Object visitBooleanLiteral(BooleanLiteralContext ctx) {
        return null;
    }

    @Override
    public Object visitNumberLiteral(NumberLiteralContext ctx) {
        return null;
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteralContext ctx) {
        return null;
    }

    @Override
    public Object visitHexInteger(HexIntegerContext ctx) {
        return null;
    }

    @Override
    public Object visitHexDigit(HexDigitContext ctx) {
        return null;
    }

    @Override
    public Object visitOctalInteger(OctalIntegerContext ctx) {
        return null;
    }

    @Override
    public Object visitOctDigit(OctDigitContext ctx) {
        return null;
    }

    @Override
    public Object visitDecimalInteger(DecimalIntegerContext ctx) {
        return null;
    }

    @Override
    public Object visitDoubleLiteral(DoubleLiteralContext ctx) {
        return null;
    }

    @Override
    public Object visitExponentDecimalReal(ExponentDecimalRealContext ctx) {
        return null;
    }

    @Override
    public Object visitRegularDecimalReal(RegularDecimalRealContext ctx) {
        return null;
    }

    @Override
    public Object visitStringLiteral(StringLiteralContext ctx) {
        return null;
    }

    @Override
    public Object visitEscapedChar(EscapedCharContext ctx) {
        return null;
    }

    @Override
    public Object visitEscapedHexDigit(EscapedHexDigitContext ctx) {
        return null;
    }

    @Override
    public Object visitDigit(DigitContext ctx) {
        return null;
    }

    @Override
    public Object visitZeroDigit(ZeroDigitContext ctx) {
        return null;
    }

    @Override
    public Object visitNonZeroDigit(NonZeroDigitContext ctx) {
        return null;
    }

    @Override
    public Object visitNonZeroOctDigit(NonZeroOctDigitContext ctx) {
        return null;
    }

    @Override
    public Object visitSchemaName(SchemaNameContext ctx) {
        return null;
    }

    @Override
    public Object visitSymbolicName(SymbolicNameContext ctx) {
        return null;
    }

    @Override
    public Object visitUnescapedSymbolicName(UnescapedSymbolicNameContext ctx) {
        return null;
    }

    @Override
    public Object visitIdentifierStart(IdentifierStartContext ctx) {
        return null;
    }

    @Override
    public Object visitIdentifierExt(IdentifierExtContext ctx) {
        return null;
    }

    @Override
    public Object visitEscapedSymbolicName(EscapedSymbolicNameContext ctx) {
        return null;
    }

    @Override
    public Object visitHexLetter(HexLetterContext ctx) {
        return null;
    }

    @Override
    public Object visitW_count(W_countContext ctx) {
        return null;
    }

    @Override
    public Object visitW_filter(W_filterContext ctx) {
        return null;
    }

    @Override
    public Object visitW_extract(W_extractContext ctx) {
        return null;
    }

    @Override
    public Object visitW_any(W_anyContext ctx) {
        return null;
    }

    @Override
    public Object visitW_none(W_noneContext ctx) {
        return null;
    }

    @Override
    public Object visitW_single(W_singleContext ctx) {
        return null;
    }

    @Override
    public Object visitW_call(W_callContext ctx) {
        return null;
    }

    @Override
    public Object visitW_yield(W_yieldContext ctx) {
        return null;
    }

    @Override
    public Object visitReserveWords(ReserveWordsContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_all(Rw_allContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_asc(Rw_ascContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_ascending(Rw_ascendingContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_by(Rw_byContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_create(Rw_createContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_else(Rw_elseContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_end(Rw_endContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_delete(Rw_deleteContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_desc(Rw_descContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_descending(Rw_descendingContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_detach(Rw_detachContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_exists(Rw_existsContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_limit(Rw_limitContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_match(Rw_matchContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_merge(Rw_mergeContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_on(Rw_onContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_optional(Rw_optionalContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_order(Rw_orderContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_remove(Rw_removeContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_return(Rw_returnContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_set(Rw_setContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_skip(Rw_skipContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_where(Rw_whereContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_with(Rw_withContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_union(Rw_unionContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_unwind(Rw_unwindContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_and(Rw_andContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_as(Rw_asContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_contains(Rw_containsContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_distinct(Rw_distinctContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_ends(Rw_endsContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_in(Rw_inContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_is(Rw_isContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_not(Rw_notContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_or(Rw_orContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_starts(Rw_startsContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_xor(Rw_xorContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_false(Rw_falseContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_true(Rw_trueContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_null(Rw_nullContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_constraint(Rw_constraintContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_do(Rw_doContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_for(Rw_forContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_require(Rw_requireContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_unique(Rw_uniqueContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_case(Rw_caseContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_when(Rw_whenContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_then(Rw_thenContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_mandatory(Rw_mandatoryContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_scalar(Rw_scalarContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_of(Rw_ofContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_add(Rw_addContext ctx) {
        return null;
    }

    @Override
    public Object visitRw_drop(Rw_dropContext ctx) {
        return null;
    }

}
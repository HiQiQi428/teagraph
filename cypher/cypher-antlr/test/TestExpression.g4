grammar TestExpression ;
import UnicodeSet, BaseName, TestAtom;

expression : orExpression ;

orExpression : xorExpression (SP (O R) SP xorExpression)* ;

xorExpression : andExpression (SP (X O R) SP andExpression)* ;

andExpression : notExpression (SP (A N D) SP notExpression)* ;

notExpression : ((N O T) SP?)* comparisonExpression ;

comparisonExpression : addSubExpression (SP? partialComparisonExpression)* ;

addSubExpression : mulDivModExpression
    ( (SP? '+' SP? mulDivModExpression)
    | (SP? '-' SP? mulDivModExpression) )* ;

partialComparisonExpression : ('=' | '<>' | '<' | '>' | '<=' | '>=') SP? addSubExpression ;

mulDivModExpression : powerOfExpression (SP? ('*' | '/' | '%') SP? powerOfExpression)* ;

powerOfExpression : unaryaddSubExpression (SP? '^' SP? unaryaddSubExpression)* ;

unaryaddSubExpression : (('+' | '-') SP?)* stringListNullOperatorExpression ;

stringListNullOperatorExpression : propertyOrLabelsExpression
    ( (SP? '[' expression ']')
    | (SP? '[' expression? '..' expression? ']')
    | (SP (I S) SP (N U L L))
    | (SP (I S) SP (N O T) SP (N U L L))
    | ( ((SP (I N)) | (SP (S T A R T S) SP (W I T H)) | (SP (E N D S) SP (W I T H)) | (SP (C O N T A I N S))) SP? propertyOrLabelsExpression ) )* ;

propertyOrLabelsExpression : atom (SP? propertyLookup)* (SP? nodeLabels)? ;

propertyLookup : '.' SP? propertyKeyName ;

propertyKeyName : schemaName ;

nodeLabels : nodeLabel (SP? nodeLabel)* ;

nodeLabel : ':' SP? labelName ;

labelName : schemaName ;
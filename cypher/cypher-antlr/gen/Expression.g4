grammar Expression ;
import UnicodeSet;
import BaseName;

expression : orExpression ;

orExpression : xorExpression (SP (O R) SP xorExpression)* ;

xorExpression : andExpression (SP (X O R) SP andExpression)* ;

andExpression : notExpression (SP (A N D) SP notExpression)* ;

notExpression : ((N O T) SP?)* comparisonExpression ;

comparisonExpression : addOrSubtractExpression (SP? partialComparisonExpression)* ;

addOrSubtractExpression : multiplyDivideModuloExpression (
    (SP? '+' SP? multiplyDivideModuloExpression)
    | (SP? '-' SP? multiplyDivideModuloExpression))*
    ;

multiplyDivideModuloExpression : powerOfExpression (
    (SP? '+' SP? multiplyDivideModuloExpression)
    | (SP? '/' SP powerOfExpression)
    | (SP? '%' SP? powerOfExpression))*
    ;

powerOfExpression : unaryAddOrSubtractExpression (SP? '^' SP? unaryAddOrSubtractExpression)* ;

unaryAddOrSubtractExpression : (('+' | '-') SP?)* stringListNullOperatorExpression ;

stringListNullOperatorExpression : propertyOrLabelsExpression (
    (SP? '[' expression ']')
    | (SP? '[' expression? '..' expression? ']')
    | ( ((SP (I N)) | (SP (S T A R T S) SP (W I T H)) | (SP (E N D S) SP (W I T H)) | (SP (C O N T A I N S))) SP? propertyOrLabelsExpression )
    | (SP (I S) SP (N U L L))
    | (SP (I S) SP (N O T) SP (N U L L))
    )* ;

propertyOrLabelsExpression : atom (SP? propertyLookup)* (SP? nodeLabels)? ;

nodeLabels : nodeLabel (SP? nodeLabel)* ;

nodeLabel : ':' SP? labelName ;

labelName : schemaName ;

partialComparisonExpression : ('=' SP? addOrSubtractExpression)
    | ('<>' SP? addOrSubtractExpression)
    | ('<' SP? addOrSubtractExpression)
    | ('>' SP? addOrSubtractExpression)
    | ('<=' SP? addOrSubtractExpression)
    | ('>=' SP? addOrSubtractExpression)
    ;

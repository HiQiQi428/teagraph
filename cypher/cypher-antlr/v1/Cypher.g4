grammar Cypher;
import UnicodeSet;

// [] -> ?
// {} -> *
// <> ->
// ::= -> :

// Cypher

cypher : SP? statement (SP? ';')? SP? EOI ;

statement : query ;

query : regularQuery
    | standabloneCall
    ;

regularQuery : singleQuery (SP? union)* ;

singleQuery : singlePartQuery
    | multiPartQuery
    ;

union : (U N I O N) SP (A L L) SP? singleQuery
    | (U N I O N) SP? singleQuery
    ;

// StandaloneCall

standabloneCall : (C A L L) SP explicitProcedureInvocation
    | implicitProcedureInvocation (SP (Y I E L D) SP yieldItems)?
    ;

yieldItems : (yieldItem (SP? ',' SP? yieldItem)*)
    | '-'
    ;

yieldItem : (procedureResultField SP (A S) SP)? variable;

procedureResultField : symbolicName;

variable : symbolicName;

// ExplicitProcedureInvocation & ImplicitProcedureInvocation

explicitProcedureInvocation : procedureName SP? '(' SP? (expression SP? (',' SP? expression SP?)*)? ')' ;

implicitProcedureInvocation : procedureName ;

procedureName : namespace symbolicName ;

namespace : (symbolicName '.')*;

// SinglePartQuery

singlePartQuery : ((readingClause SP?)* return)
    | ((readingClause SP?)* updatingClause (SP? updatingClause)* (SP? return)?)
    ;

readingClause : match
    | unwind
    | inQueryCall
    ;

updatingClause : create
    | merge
    | delete
    | set
    | remove
    ;

// Match

match : ((O P T I O N A L) SP)? (M A T C H) SP? pattern (SP? where)? ;

pattern : patternPart (SP? ',' SP? patternPart)* ;

patternPart : (variable SP? '=' SP? anonymousPatternPart)
    | anonymousPatternPart ;

anonymousPatternPart : patternElement ;

patternElement : (nodePattern (SP? patternElementChain)*) | ('(' patternElement ')') ;

// Unwind

unwind : (U N W I N D) SP? expression SP (A S) SP variable ;

//InQueryCall

inQueryCall : (C A L L) SP explicitProcedureInvocation (SP? (Y I E L D) SP yieldItems)? ;

// Create

create : (C R E A T E) SP? pattern ;

// Merge

merge : (M E R G E) SP? patternPart (SP mergeAction)* ;

mergeAction : ((O N) SP (M A T C H) SP set) | ((O N) SP (C R E A T E) SP set) ;

// Delete

delete : ((D E T A C H) SP)? (D E L E T E) SP? expression (SP? ',' SP? expression)* ;

// Remove

remove : (R E M O V E) SP removeItem (SP? ',' SP? removeItem)* ;

removeItem : (variable nodeLabels) | propertyExpression ;

// Set

set : (S E T) SP? setItem (',' setItem)* ;

setItem : (propertyExpression SP? '=' SP? expression)
    | (variable SP? '=' SP? expression)
    | (variable SP? '+=' SP? expression)
    | (variable SP? nodeLabels) ;

propertyExpression : atom (SP? propertyLookup)* ;

// Return

return : (R E T U R N) (SP? (D I S T I N C T))? SP returnBody ;

// ReturnBody

returnBody : returnItems (SP order)? (SP skip)? (SP limit)?;

returnItems : ('*' (SP? ',' SP? returnItem)*)
    | (returnItem (SP? ',' SP? returnItem)*)
    ;

returnItem : (expression SP (A S) SP variable)
    | expression
    ;

order : (O R D E R) SP (B Y) SP sortItem (',' SP? sortItem)* ;

sortItem : expression (SP? ((A S C E N D I N G) | (A S C) | (D E S C E N D I N G) | (D E S C)))? ;

skip : (S K I P) SP expression ;

limit : (L I M I T) SP expression ;

// MultiPartQuery

multiPartQuery : ((readingClause SP?)* (updatingClause SP?)* with SP?)* singlePartQuery;

// With

with : (W I T H) (SP? (D I S T I N C T))? SP returnBody (SP? where)? ;

where : (W H E R E) SP expression;

// SymbolicName

symbolicName : unescapedSymbolicName
    | escapedSymbolicName
    | hexLetter
    | (C O U N T)
    | (F I L T E R)
    | (E X T R A C T)
    | (A N Y)
    | (N O N E)
    | (S I N G L E)
    ;

unescapedSymbolicName : identifierStart+ ;

identifierStart : ID_Start | Pc ;

identifierPart : ID_Continue | Sc ;

escapedSymbolicName : ('`' (ANY? '`'?)* '`')* ;

// Expression

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

// PropertyLookup

propertyLookup : '.' SP? propertyKeyName ;

// Atom

atom : literal
    | parameter
    | caseExpression
    | ((C O U N T) SP? '(' SP? '*' SP? ')')
    | listComprehension
    | patternComprehension
    | ((F I L T E R) SP? '(' SP? filterExpression SP? ')')
    | ((E X T R A C T) SP? '(' SP? filterExpression SP? (SP? '|' expression)? ')')
    | ((A L L) SP? '(' SP? filterExpression SP? ')')
    | ((A N Y) SP? '(' SP? filterExpression SP? ')')
    | ((N O N E) SP? '(' SP? filterExpression SP? ')')
    | ((S I N G L E) SP? '(' SP? filterExpression SP? ')')
    | relationshipsPattern
    | parenthesizedExpression
    | functionInvocation
    | variable
    ;

listComprehension : '[' SP? filterExpression (SP? '|' SP? expression)? SP? ']' ;

patternComprehension : '[' SP? (variable SP? '=' SP?)? relationshipsPattern SP? ((W H E R E) SP? expression SP?)? '|' SP? expression SP? ']' ;

relationshipsPattern : nodePattern (SP? patternElementChain)* ;

patternElementChain : relationshipPattern SP? nodePattern ;

parenthesizedExpression : '(' SP? expression SP? ')' ;

// Literal

literal : numberLiteral | stringLiteral | booleanLiteral | (N U L L) | mapLiteral | listLiteral ;

// NumberLiteral

numberLiteral : doubleLiteral | integerLiteral ;

doubleLiteral : exponentDecimalReal | regularDecimalReal ;

exponentDecimalReal : (digit* | (digit* '.' digit*) | ('.' digit*)) E '-'? digit? ;

regularDecimalReal : digit* '.' digit* ;

//StringLiteral

stringLiteral : ('"' (ANY ('"' | '\\') | escapedChar)* '"')
    | ('\'' (ANY ('\'' | '\\') | escapedChar)* '\'') ;

escapedChar : '\\' ('\\' | '\'' | '"' | B | F | N | R | T | (U hexDigit{4}) | (U hexDigit{8})) ;

// BooleanLiteral

booleanLiteral : (T R U E) | (F A L S E) ;

// MapLiteral

mapLiteral : '{' SP? (propertyKeyName SP? ':' SP? expression SP? (',' SP? propertyKeyName SP? ':' SP? expression SP?)*)? '}' ;

propertyKeyName : schemaName ;

// listLiteral

listLiteral : '[' SP? (expression SP? (',' SP? expression SP?)*)? ']' ;

// CaseExpression

caseExpression : (((C A S E) (SP? caseAlternatives)*) | ((C A S E) SP? expression (SP? caseAlternatives)*)) (SP? (E L S E) SP? expression)? SP? (E N D) ;

caseAlternatives : (W H E N) SP? expression SP? (T H E N) SP? expression ;

// FcuntionInvocation

functionInvocation : functionName SP? '(' SP? ((D I S T I N C T) SP?)? (expression SP? (',' SP? expression SP?)*)? ')' ;

functionName : symbolicName | (E X I S T S) ;

// RelationshipPattern

relationshipPattern : (leftArrowHead SP? dash SP? relationshipDetail? SP? dash SP? rightArrowHead)
    | (leftArrowHead SP? dash SP? relationshipDetail? SP? dash)
    | (dash SP? relationshipDetail? SP? dash SP? rightArrowHead)
    | (dash SP? relationshipDetail? SP? dash)
    ;

leftArrowHead : '<' | '⟨' | '〈' | '﹤' | '＜' ;

rightArrowHead : '>' | '⟩' | '〉' | '﹥' | '＞' ;

dash : '-' | '­' | '‐' | '‑' | '‒' | '–' | '—' | '―' | '−' | '﹘' | '﹣' | '－' ;

// RelationshipDetail

relationshipDetail : '[' SP? (variable SP?)? (relationshipTypes SP? rangeLiteral?)? (properties SP?)? ']' ;

relationshipTypes : ':' SP? relTypeName (SP? '|' ':'? SP? relTypeName)* ;

relTypeName : schemaName ;

rangeLiteral : '*' SP? (integerLiteral SP?)? ('..' SP? (integerLiteral SP?)?)? ;

integerLiteral : hexInteger | octalInteger | decimalInteger ;

// FilterExpression

filterExpression : idInColl (SP? where)? ;

idInColl : variable SP (I N) SP expression ;

// NodePattern

nodePattern : '(' SP? (variable SP?)? (nodeLabels SP?)? (properties SP?)? ')' ;

properties : mapLiteral
    | parameter
    ;

// SchemaName

schemaName : symbolicName | reservedWord ;

// ReservedWord

reservedWord : (A L L) | (A S C) | (A S C E N D I N G) | (B Y) | (C R E A T E) | (D E L E T E) | (D E S C) | (D E S C E N D I N G) | (D E T A C H) | (E X I S T S) | (L I M I T) | (M A T C H) | (M E R G E) | (O N) | (O P T I O N A L) | (O R D E R) | (R E M O V E) | (R E T U R N) | (S E T) | (S K I P) | (W H E R E) | (W I T H) | (U N I O N) | (U N W I N D) | (A N D) | (A S) | (C O N T A I N S) | (D I S T I N C T) | (E N D S) | (I N) | (I S) | (N O T) | (O R) | (S T A R T S) | (X O R) | (F A L S E) | (T R U E) | (N U L L) | (C O N S T R A I N T) | (D O) | (F O R) | (R E Q U I R E) | (U N I Q U E) | (C A S E) | (W H E N) | (T H E N) | (E L S E) | (E N D) | (M A N D A T O R Y) | (S C A L A R) | (O F) | (A D D) | (D R O P) ;

// Parameter

parameter : '$' (symbolicName | decimalInteger) ;

// HexInteger

hexInteger : '0x' hexDigit* ;

hexDigit : digit | hexLetter ;

hexLetter : A | B | C | D | E | F ;

// OctalInteger

octalInteger : zeroDigit octDigit* ;

octDigit : zeroDigit | nonZeroDigit ;

// DecimalInteger

decimalInteger : zeroDigit | (nonZeroDigit digit*) ;

digit : zeroDigit | nonZeroDigit ;

zeroDigit : '0' ;

nonZeroDigit : nonZeroOctDigit | '8' | '9' ;

nonZeroOctDigit : '1' | '2' | '3' | '4' | '5' | '6' | '7' ;
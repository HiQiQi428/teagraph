grammar Cypher;

cypher : SP? statement (SP? ';')? SP? ;

statement : query ;

query : regularQuery | standaloneCall ;

regularQuery : singleQuery (SP? union)* ;

singleQuery : singlePartQuery | multiPartQuery ;

union : (rw_union SP rw_all SP? singleQuery)
    | (rw_union SP? singleQuery) ;

// StandaloneCall

standaloneCall : w_call SP (explicitProcedureInvocation | implicitProcedureInvocation) (SP w_yield SP yieldItems)? ;

explicitProcedureInvocation : procedureName SP? '(' SP? (expression SP? (',' SP? expression SP?)*)? ')' ;

procedureName : namespace symbolicName ;

namespace : (symbolicName '.')* ;

implicitProcedureInvocation : procedureName ;

yieldItems : (yieldItem (SP? ',' SP? yieldItem)*) | '-' ;

yieldItem : (procedureResultField SP rw_as SP)? variable ;

procedureResultField : symbolicName ;

// SinglePartQuery

singlePartQuery : (readingClause SP?)* (ret | (updatingClause (SP? updatingClause)* (SP? ret)?)) ;

readingClause : match | unwind | inQueryCall ;

updatingClause : create | merge | delete | set | remove ;

match : (rw_optional SP)? rw_match SP? pattern (SP? where)? ;

unwind : rw_unwind SP? expression SP rw_as SP variable ;

inQueryCall : w_call SP explicitProcedureInvocation (SP? w_yield SP yieldItems) ;

create : rw_create SP? pattern ;

merge : rw_merge SP? patternPart (SP mergeAction)* ;

delete : (rw_detach SP)? rw_delete SP? expression (SP? ',' SP? expression)* ;

set : rw_set SP? setItem (',' setItem)* ;

remove : rw_remove SP removeItem (SP? ',' SP? removeItem)* ;

where : rw_where SP expression ;

pattern : patternPart (SP? ',' SP? patternPart)* ;

patternPart : (variable SP? '=' SP? patternElement) | patternElement ;

mergeAction : (rw_on SP rw_match SP set)
    | (rw_on SP rw_create SP set)
    ;

patternElement : (nodePattern (SP? patternElementChain)*)
    | ('(' patternElement ')')
    ;

patternElementChain : relationshipPattern SP? nodePattern ;

setItem : (propertyExpression SP? '=' SP? expression)
    | (variable SP? ( ('=' SP? expression) | ('+=' SP? expression) | nodeLabels )) ;

removeItem : (variable nodeLabels) | propertyExpression ;

propertyExpression : atom (SP? propertyLookup)* ;

// Return

ret : rw_return (SP? rw_distinct)? SP returnBody ;

returnBody : returnItems (SP order)? (SP skip)? (SP limit)? ;

returnItems : ('*' (SP? ',' SP? returnItem)*)
    | (returnItem (SP? ',' SP? returnItem)*) ;

returnItem : (expression SP rw_as SP variable) | expression ;

order : rw_order SP rw_by SP sortItem (',' SP? sortItem)* ;

sortItem : expression (SP? (rw_ascending | rw_asc | rw_descending | rw_desc))? ;

skip : rw_skip SP expression ;

limit : rw_limit SP expression ;

// MultiPartQuery

multiPartQuery : ((readingClause SP?)* (updatingClause SP?)* with SP?)* singlePartQuery ;

with : rw_with (SP? rw_distinct)? SP returnBody (SP? where)? ;

// -----------------------------------------------------------------------------------------------------

// Expression

expression : orExpression ;

orExpression : xorExpression (SP rw_or SP xorExpression)* ;

xorExpression : andExpression (SP rw_xor SP andExpression)* ;

andExpression : notExpression (SP rw_and SP notExpression)* ;

notExpression : (rw_not SP?)* comparisonExpression ;

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
    | (SP rw_is SP rw_null)
    | (SP rw_is SP rw_not SP rw_null)
    | ( ((SP rw_in) | (SP rw_starts SP rw_with) | (SP rw_ends SP rw_with) | (SP rw_contains)) SP? propertyOrLabelsExpression ) )* ;

propertyOrLabelsExpression : atom (SP? propertyLookup)* (SP? nodeLabels)? ;

propertyLookup : '.' SP? propertyKeyName ;

propertyKeyName : schemaName ;

nodeLabels : nodeLabel (SP? nodeLabel)* ;

nodeLabel : ':' SP? labelName ;

labelName : schemaName ;

// -----------------------------------------------------------------------------------------------------

atom : literal
    | parameter
    | caseExpression
    | listComprehension
    | patternComprehension
    | (w_count SP? '(' SP? '*' SP? ')')
    | (w_filter SP? '(' SP? filterExpression SP? ')')
    | (w_extract SP? '(' SP? filterExpression SP? (SP? '|' expression)? ')')
    | (rw_all SP? '(' SP? filterExpression SP? ')')
    | (w_any SP? '(' SP? filterExpression SP? ')')
    | (w_none SP? '(' SP? filterExpression SP? ')')
    | (w_single SP? '(' SP? filterExpression SP? ')')
    | relationshipsPattern
    | parenthesizedExpression
    | functionInvocation
    | variable
    ;

// CaseExpression

caseExpression : ((rw_case (SP? caseAlternatives)*) | (rw_case SP? expression (SP? caseAlternatives)*)) (SP? rw_else SP? expression)? SP? rw_end ;

caseAlternatives : rw_when SP? expression SP? rw_then SP? expression ;

// ListComprehension

listComprehension : '[' SP? filterExpression (SP? '|' SP? expression)? SP? ']' ;

// PatternComprehension

patternComprehension : '[' SP? (variable SP? '=' SP?)? relationshipsPattern SP? (rw_where SP? expression SP?)? '|' SP? expression SP? ']' ;

relationshipsPattern : nodePattern (SP? patternElementChain)* ;

// FilterExpression

filterExpression : idInColl (SP? where)? ;

idInColl : variable SP rw_in SP expression ;

// ParenthesizedExpression

parenthesizedExpression : '(' SP? expression SP? ')' ;

// FunctionInvocation

functionInvocation : functionName SP? '(' SP? (rw_distinct SP?)? (expression SP? (',' SP? expression SP?)*)? ')' ;

functionName : symbolicName | rw_exists ;

// RelationshipPattern

relationshipPattern : (leftArrowHead SP? dash SP? relationshipDetail? SP? dash SP? rightArrowHead)
    | (leftArrowHead SP? dash SP? relationshipDetail? SP? dash)
    | (dash SP? relationshipDetail? SP? dash SP? rightArrowHead)
    | (dash SP? relationshipDetail? SP? dash) ;

relationshipDetail : '[' SP? (variable SP?)? (relationshipTypes SP?)? rangeLiteral? (properties SP?)? ']' ;

relationshipTypes : ':' SP? relTypeName (SP? '|' ':'? SP? relTypeName)* ;

relTypeName : schemaName ;

rangeLiteral : '*' SP? (integerLiteral SP?)? ('..' SP? (integerLiteral SP?)?)? ;

leftArrowHead : '<' | '⟨' | '〈' | '﹤' | '＜' ;

rightArrowHead : '>' | '⟩' | '〉' | '﹥' | '＞' ;

dash : '-' | '­' | '‐' | '‑' | '‒' | '–' | '—' | '―' | '−' | '﹘' | '﹣' | '－' ;

// NodePattern

nodePattern : '(' SP? (variable SP?)? (nodeLabels SP?)? (properties SP?)? ')' ;

properties : mapLiteral | listLiteral ;

// Normal
variable : symbolicName ;

// -----------------------------------------------------------------------------------------------------

literal : numberLiteral | stringLiteral | booleanLiteral | rw_null | mapLiteral | listLiteral ;

parameter : '$' (symbolicName | decimalInteger) ;

mapLiteral : '{' SP? (propertyKeyName SP? ':' SP? expression SP? (',' SP? propertyKeyName SP? ':' SP? expression SP?)*)? '}' ;

listLiteral : '[' SP? (expression SP? (',' SP? expression SP?)*)? ']' ;

booleanLiteral : rw_true | rw_false ;

numberLiteral : integerLiteral | doubleLiteral ;

// IntegerLiteral

integerLiteral : hexInteger | octalInteger | decimalInteger ;

hexInteger : '0x' hexDigit* ;

hexDigit : digit | hexLetter ;

octalInteger : zeroDigit octDigit* ;

octDigit : zeroDigit | nonZeroDigit ;

decimalInteger : zeroDigit | (nonZeroDigit digit*) ;

// DoubleLiteral

doubleLiteral : exponentDecimalReal | regularDecimalReal ;

exponentDecimalReal : (digit* | (digit* '.' digit*) | ('.' digit*)) ('e' | 'E') '-'? digit? ;

regularDecimalReal : digit* '.' digit* ;

// StringLiteral

stringLiteral : ('"' (~('"' | '\\') | escapedChar)* '"')
    | ('\'' (~('\'' | '\\') | escapedChar)* '\'') ;

escapedChar : '\\' ('\\' | '\'' | '"' | ('b' | 'B') | ('f' | 'F') | ('n' | 'N') | ('r' | 'R') | ('t' | 'T') | (('u' | 'U') escapedHexDigit escapedHexDigit?) ) ;

escapedHexDigit : hexDigit hexDigit hexDigit hexDigit ;

// Digit

digit : zeroDigit | nonZeroDigit ;

zeroDigit : '0' ;

nonZeroDigit : nonZeroOctDigit | '8' | '9' ;

nonZeroOctDigit : '1' | '2' | '3' | '4' | '5' | '6' | '7' ;

// -----------------------------------------------------------------------------------------------------

// BaseName

schemaName : symbolicName | reserveWords ;

symbolicName : unescapedSymbolicName | escapedSymbolicName | hexLetter | w_count | w_filter | w_extract | w_any | w_none | w_single ;

unescapedSymbolicName : identifierStart (identifierStart | identifierExt)* ;

identifierStart : 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | 'h' | 'i' | 'j' | 'k' | 'l' | 'm' | 'n' | 'o' | 'p' | 'q' | 'r' | 's' | 't' | 'u' | 'v' | 'w' | 'x' | 'y' | 'z' | 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G' | 'H' | 'I' | 'J' | 'K' | 'L' | 'M' | 'N' | 'O' | 'P' | 'Q' | 'R' | 'S' | 'T' | 'U' | 'V' | 'W' | 'X' | 'Y' | 'Z' | '_' ;

identifierExt : '$' | '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' ;

escapedSymbolicName : '`' ~('`')* '`' ;

hexLetter : ('a' | 'A') | ('b' | 'B') | ('c' | 'C') | ('d' | 'D') | ('e' | 'E') | ('f' | 'F') ;

w_count : ('c' | 'C') ('o' | 'O') ('u' | 'U') ('n' | 'N') ('t' | 'T') ;

w_filter : ('f' | 'F') ('i' | 'I') ('l' | 'L') ('t' | 'T') ('e' | 'E') ('r' | 'R') ;

w_extract : ('e' | 'E') ('x' | 'X') ('t' | 'T') ('r' | 'R') ('a' | 'A') ('c' | 'C') ('t' | 'T') ;

w_any : ('a' | 'A') ('n' | 'N') ('y' | 'Y') ;

w_none : ('n' | 'N') ('o' | 'O') ('n' | 'N') ('e' | 'E') ;

w_single : ('s' | 'S') ('i' | 'I') ('n' | 'N') ('g' | 'G') ('l' | 'L') | ('e' | 'E') ;

w_call : ('c' | 'C') ('a' | 'A') ('l' | 'L') ('l' | 'L') ;

w_yield : ('y' | 'Y') ('i' | 'I') ('e' | 'E') ('l' | 'L') ('d' | 'D') ;

reserveWords : rw_all | rw_asc | rw_ascending | rw_by | rw_create | rw_delete | rw_desc | rw_descending | rw_detach | rw_exists | rw_limit | rw_match | rw_merge | rw_on | rw_optional | rw_order | rw_remove | rw_return | rw_set | rw_skip | rw_where | rw_with | rw_union | rw_unwind | rw_and | rw_as | rw_contains | rw_distinct | rw_ends | rw_in | rw_is | rw_not | rw_starts | rw_xor | rw_false | rw_true | rw_null | rw_constraint | rw_do | rw_for | rw_require | rw_unique | rw_case | rw_when | rw_then | rw_mandatory | rw_scalar | rw_of | rw_add | rw_drop ;

rw_all : ('a' | 'A') ('l' | 'L') ('l' | 'L') ;

rw_asc : ('a' | 'A') ('s' | 'S') ('c' | 'C') ;

rw_ascending : ('a' | 'A') ('s' | 'S') ('c' | 'C') ('e' | 'E') ('n' | 'N') ('d' | 'D') ('i' | 'I') ('n' | 'N') ('g' | 'G') ;

rw_by : ('b' | 'B') ('y' | 'Y') ;

rw_create : ('c' | 'C') ('r' | 'R') ('e' | 'E') ('a' | 'A') ('t' | 'T') ('e' | 'E') ;

rw_else : ('e' | 'E') ('l' | 'L') ('s' | 'S') ('e' | 'E') ;

rw_end : ('e' | 'E') ('n' | 'N') ('d' | 'D') ;

rw_delete : ('d' | 'D') ('e' | 'E') ('l' | 'L') ('e' | 'E') ('t' | 'T') ('e' | 'E') ;

rw_desc : ('d' | 'D') ('e' | 'E') ('s' | 'S') ('c' | 'C') ;

rw_descending : ('d' | 'D') ('e' | 'E') ('s' | 'S') ('c' | 'C') ('e' | 'E') ('n' | 'N') ('d' | 'D') ('i' | 'I') ('n' | 'N') ('g' | 'G') ;

rw_detach : ('d' | 'D') ('e' | 'E') ('t' | 'T') ('a' | 'A') ('c' | 'C') ('h' | 'H') ;

rw_exists : ('e' | 'E') ('x' | 'X') ('i' | 'I') ('s' | 'S') ('t' | 'T') ('s' | 'S') ;

rw_limit : ('l' | 'L') ('i' | 'I') ('m' | 'M') ('i' | 'I') ('t' | 'T') ;

rw_match : ('m' | 'M') ('a' | 'A') ('t' | 'T') ('c' | 'C') ('h' | 'H') ;

rw_merge : ('m' | 'M') ('e' | 'E') ('r' | 'R') ('g' | 'G') ('e' | 'E') ;

rw_on : ('o' | 'O') ('n' | 'N') ;

rw_optional : ('o' | 'O') ('p' | 'P') ('t' | 'T') ('i' | 'I') ('o' | 'O') ('n' | 'N') ('a' | 'A') ('l' | 'L') ;

rw_order : ('o' | 'O') ('r' | 'R') ('d' | 'D') ('e' | 'E') ('r' | 'R') ;

rw_remove : ('r' | 'R') ('e' | 'E') ('m' | 'M') ('o' | 'O') ('v' | 'V') ('e' | 'E') ;

rw_return : ('r' | 'R') ('e' | 'E') ('t' | 'T') ('u' | 'U') ('r' | 'R') ('n' | 'N') ;

rw_set : ('s' | 'S') ('e' | 'E') ('t' | 'T') ;

rw_skip : ('s' | 'S') ('k' | 'K') ('i' | 'I') ('p' | 'P') ;

rw_where : ('w' | 'W') ('h' | 'H') ('e' | 'E') ('r' | 'R') ('e' | 'E') ;

rw_with : ('w' | 'W') ('i' | 'I') ('t' | 'T') ('h' | 'H') ;

rw_union : ('u' | 'U') ('n' | 'N') ('i' | 'I') ('o' | 'O') ('n' | 'N') ;

rw_unwind : ('u' | 'U') ('n' | 'N') ('w' | 'W') ('i' | 'I') ('n' | 'N') ('d' | 'D') ;

rw_and : ('a' | 'A') ('n' | 'N') ('d' | 'D') ;

rw_as : ('a' | 'A') ('s' | 'S') ;

rw_contains : ('c' | 'C') ('o' | 'O') ('n' | 'N') ('t' | 'T') ('a' | 'A') ('i' | 'I') ('n' | 'N') ('s' | 'S') ;

rw_distinct : ('d' | 'D') ('i' | 'I') ('s' | 'S') ('t' | 'T') ('i' | 'I') ('n' | 'N') ('c' | 'C') ('t' | 'T') ;

rw_ends : ('e' | 'E') ('n' | 'N') ('d' | 'D') ('s' | 'S') ;

rw_in : ('i' | 'I') ('n' | 'N') ;

rw_is : ('i' | 'I') ('s' | 'S') ;

rw_not : ('n' | 'N') ('o' | 'O') ('t' | 'T') ;

rw_or : ('o' | 'O') ('r' | 'R') ;

rw_starts : ('s' | 'S') ('t' | 'T') ('a' | 'A') ('r' | 'R') ('t' | 'T') ('s' | 'S') ;

rw_xor : ('x' | 'X') ('o' | 'O') ('r' | 'R') ;

rw_false : ('f' | 'F') ('a' | 'A') ('l' | 'L') ('s' | 'S') ('e' | 'E') ;

rw_true : ('t' | 'T') ('r' | 'R') ('u' | 'U') ('e' | 'E') ;

rw_null : ('n' | 'N') ('u' | 'U') ('l' | 'L') ('l' | 'L') ;

rw_constraint : ('c' | 'C') ('o' | 'O') ('n' | 'N') ('s' | 'S') ('t' | 'T') ('r' | 'R') ('a' | 'A') ('i' | 'I') ('n' | 'N') ('t' | 'T') ;

rw_do : ('d' | 'D') ('o' | 'O') ;

rw_for : ('f' | 'F') ('o' | 'O') ('r' | 'R') ;

rw_require : ('r' | 'R') ('e' | 'E') ('q' | 'Q') ('u' | 'U') ('i' | 'I') ('r' | 'R') ('e' | 'E') ;

rw_unique : ('u' | 'U') ('n' | 'N') ('i' | 'I') ('q' | 'Q') ('e' | 'E') ;

rw_case : ('c' | 'C') ('a' | 'A') ('s' | 'S') ('e' | 'E') ;

rw_when : ('w' | 'W') ('h' | 'H') ('e' | 'E') ('n' | 'N') ;

rw_then : ('t' | 'T') ('h' | 'H') ('e' | 'E') ('n' | 'N') ;

rw_mandatory : ('m' | 'M') ('a' | 'A') ('n' | 'N') ('d' | 'D') ('a' | 'A') ('t' | 'T') ('o' | 'O') ('r' | 'R') ('y' | 'Y') ;

rw_scalar : ('s' | 'S') ('c' | 'C') ('a' | 'A') ('l' | 'L') ('a' | 'A') ('r' | 'R') ;

rw_of : ('o' | 'O') ('f' | 'F') ;

rw_add : ('a' | 'A') ('d' | 'D') ('d' | 'D') ;

rw_drop : ('d' | 'D') ('r' | 'R') ('o' | 'O') ('p' | 'P') ;

SP : ([\u0020] | [\u0009] | [\u000A] | [\u000B] | [\u000C] | [\u000D] | [\u001C] | [\u001D] | [\u001E] | [\u001F] | [\u1680] | [\u180E] | [\u2000] | [\u2001] | [\u2002] | [\u2003] | [\u2004] | [\u2005] | [\u2006] | [\u2008] | [\u2009] | [\u200A] | [\u2028] | [\u2029] | [\u205F] | [\u3000] | [\u00A0] | [\u2007] | [\u202F])+ ;
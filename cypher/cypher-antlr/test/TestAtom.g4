parser grammar TestAtom;

import TestLiteral, TestParameter, TestExpression;

atom : literal
    | parameter
    | caseExpression
    | listComprehension
    | patternComprehension
    | ((C O U N T) SP? '(' SP? '*' SP? ')')
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

// CaseExpression

caseExpression : (((C A S E) (SP? caseAlternatives)*) | ((C A S E) SP? expression (SP? caseAlternatives)*)) (SP? (E L S E) SP? expression) SP? (E N D) ;

caseAlternatives : (W H E N) SP? expression SP? (T H E N) SP? expression ;

// ListComprehension

listComprehension : '[' SP? filterExpression (SP? '|' SP? expression)? SP? ']' ;

// PatternComprehension

patternComprehension : '[' SP? (variable SP? '=' SP?)? relationshipsPattern SP? ((W H E R E) SP? expression SP?)? '|' SP? expression SP? ']' ;

relationshipsPattern : nodePattern (SP? patternElementChain)* ;

patternElementChain : relationshipPattern SP? nodePattern ;

// FilterExpression

filterExpression : idInColl (SP? where)? ;

idInColl : variable SP (I N) SP expression ;

where : (W H E R E) SP expression ;

// ParenthesizedExpression

parenthesizedExpression : '(' SP? expression SP? ')' ;

// FunctionInvocation

functionInvocation : functionName SP? '(' SP? ((D I S T I N C T) SP?)? (expression SP? (',' SP? expression SP?)*)? ')' ;

functionName : symbolicName | (E X I S T S) ;

// Normal
variable : symbolicName ;
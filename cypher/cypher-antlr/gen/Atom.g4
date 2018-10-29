parser grammar Atom;
import DataType;

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
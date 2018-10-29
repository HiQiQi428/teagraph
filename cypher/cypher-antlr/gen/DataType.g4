parser grammar DataType;

import BaseName;
import UnicodeSet;
import Expression;

literal : numberLiteral | stringLiteral | booleanLiteral | (N U L L) | mapLiteral | listLiteral ;

// NumberLiteral

numberLiteral : doubleLiteral | integerLiteral ;

// DoubleLiteral

doubleLiteral : exponentDecimalReal | regularDecimalReal ;

exponentDecimalReal : (digit* | (digit* '.' digit*) | ('.' digit*)) E '-'? digit? ;

regularDecimalReal : digit* '.' digit* ;

// IntegerLiteral

integerLiteral : hexInteger | octalInteger | decimalInteger ;

// HexInteger

hexInteger : '0x' hexDigit* ;

hexDigit : digit | hexLetter ;

hexLetter : A | B | C | D | E | F ;

// OctalInteger

octalInteger : zeroDigit octDigit* ;

octDigit : zeroDigit | nonZeroDigit ;

// DecimalInteger

decimalInteger : zeroDigit
    | (nonZeroDigit digit*) ;

digit : zeroDigit | nonZeroDigit ;

zeroDigit : '0' ;

nonZeroDigit : nonZeroOctDigit | '8' | '9' ;

nonZeroOctDigit : '1' | '2' | '3' | '4' | '5' | '6' | '7' ;

//StringLiteral

stringLiteral : ('"' (~('"' | '\\') | escapedChar)* '"')
    | ('\'' (~('\'' | '\\') | escapedChar)* '\'') ;

escapedChar : '\\' ('\\' | '\'' | '"' | B | F | N | R | T | (U escapedHexDigit escapedHexDigit?) ) ;

escapedHexDigit : hexDigit hexDigit hexDigit hexDigit ;

// BooleanLiteral

booleanLiteral : TRUE | FALSE ;

TRUE : T R U E;

FALSE : F A L S E;

// MapLiteral

mapLiteral : '{' SP? (propertyKeyName SP? ':' SP? expression SP? (',' SP? propertyKeyName SP? ':' SP? expression SP?)*)? '}' ;

// listLiteral

listLiteral : '[' SP? (expression SP? (',' SP? expression SP?)*)? ']' ;

// CaseExpression

caseExpression : (((C A S E) (SP? caseAlternatives)*) | ((C A S E) SP? expression (SP? caseAlternatives)*)) (SP? (E L S E) SP? expression)? SP? (E N D) ;

caseAlternatives : (W H E N) SP? expression SP? (T H E N) SP? expression ;

// FcuntionInvocation

functionInvocation : functionName SP? '(' SP? ((D I S T I N C T) SP?)? (expression SP? (',' SP? expression SP?)*)? ')' ;

functionName : symbolicName | (E X I S T S) ;
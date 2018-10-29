grammar TestStringLiteral;
import UnicodeSet;

stringLiteral : ('"' (~('"' | '\\') | escapedChar)* '"')
    | ('\'' (~('\'' | '\\') | escapedChar)* '\'') ;

escapedChar : '\\' ('\\' | '\'' | '"' | B | F | N | R | T | (U escapedHexDigit escapedHexDigit?) ) ;

escapedHexDigit : hexDigit hexDigit hexDigit hexDigit ;

hexDigit : digit | hexLetter ;

digit : zeroDigit | nonZeroDigit ;

zeroDigit : '0' ;

nonZeroDigit : nonZeroOctDigit | '8' | '9' ;

nonZeroOctDigit : '1' | '2' | '3' | '4' | '5' | '6' | '7' ;

hexLetter : A | B | C | D | E | F ;

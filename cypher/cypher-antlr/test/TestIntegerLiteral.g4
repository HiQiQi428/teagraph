grammar TestIntegerLiteral;

import UnicodeSet, TestDigit;

integerLiteral : hexInteger | octalInteger | decimalInteger ;

// HexInteger

hexInteger : '0x' hexDigit* ;

hexDigit : digit | hexLetter ;

hexLetter : A | B | C | D | E | F ;

// OctalInteger

octalInteger : zeroDigit octDigit* ;

octDigit : zeroDigit | nonZeroDigit ;

// DecimalInteger

decimalInteger : zeroDigit | (nonZeroDigit digit*) ;
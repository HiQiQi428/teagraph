grammar TestDoubleLiteral;

import UnicodeSet, TestDigit;

doubleLiteral : exponentDecimalReal | regularDecimalReal ;

exponentDecimalReal : (digit* | (digit* '.' digit*) | ('.' digit*)) E '-'? digit? ;

regularDecimalReal : digit* '.' digit* ;
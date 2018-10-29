grammar TestDigit;

digit : zeroDigit | nonZeroDigit ;

zeroDigit : '0' ;

nonZeroDigit : nonZeroOctDigit | '8' | '9' ;

nonZeroOctDigit : '1' | '2' | '3' | '4' | '5' | '6' | '7' ;
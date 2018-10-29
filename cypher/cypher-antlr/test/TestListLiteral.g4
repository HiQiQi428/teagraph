grammar TestListLiteral;

import UnicodeSet;

listLiteral : '[' SP? (expression SP? (',' SP? expression SP?)*)? ']' ;

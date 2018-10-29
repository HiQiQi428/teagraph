grammar TestMapLiteral;

import BaseName;

mapLiteral : '{' SP? (propertyKeyName SP? ':' SP? expression SP? (',' SP? propertyKeyName SP? ':' SP? expression SP?)*)? '}' ;
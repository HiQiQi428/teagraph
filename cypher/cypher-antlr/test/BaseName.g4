grammar BaseName;

import UnicodeSet;

schemaName : symbolicName | ReservedWord ;

symbolicName : unescapedSymbolicName
    | escapedSymbolicName
    | (A B C D E F)
    | (C O U N T)
    | (F I L T E R)
    | (E X T R A C T)
    | (A N Y)
    | (N O N E)
    | (S I N G L E)
    ;

unescapedSymbolicName : IDBase (IDBase | IDExtend)* ;

escapedSymbolicName : '`' ~('`')* '`' ;

IDBase : [A-Za-z_] ;

IDExtend : [$0-9] ;

ReservedWord : (A L L) | (A S C) | (A S C E N D I N G) | (B Y) | (C R E A T E) | (D E L E T E) | (D E S C) | (D E S C E N D I N G) | (D E T A C H) | (E X I S T S) | (L I M I T) | (M A T C H) | (M E R G E) | (O N) | (O P T I O N A L) | (O R D E R) | (R E M O V E) | (R E T U R N) | (S E T) | (S K I P) | (W H E R E) | (W I T H) | (U N I O N) | (U N W I N D) | (A N D) | (A S) | (C O N T A I N S) | (D I S T I N C T) | (E N D S) | (I N) | (I S) | (N O T) | (O R) | (S T A R T S) | (X O R) | (F A L S E) | (T R U E) | (N U L L) | (C O N S T R A I N T) | (D O) | (F O R) | (R E Q U I R E) | (U N I Q U E) | (C A S E) | (W H E N) | (T H E N) | (E L S E) | (E N D) | (M A N D A T O R Y) | (S C A L A R) | (O F) | (A D D) | (D R O P) ;

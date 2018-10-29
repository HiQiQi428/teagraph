lexer grammar UnicodeSet;

A : [\u0061] | [\u0041] ;
B : [\u0062] | [\u0042] ;
C : [\u0063] | [\u0043] ;
D : [\u0064] | [\u0044] ;
E : [\u0065] | [\u0045] ;
F : [\u0066] | [\u0046] ;
G : [\u0067] | [\u0047] ;
H : [\u0068] | [\u0048] ;
I : [\u0069] | [\u0049] ;
J : [\u006A] | [\u004A] ;
K : [\u006B] | [\u004B] ;
L : [\u006C] | [\u004C] ;
M : [\u006D] | [\u004D] ;
N : [\u006E] | [\u004E] ;
O : [\u006F] | [\u004F] ;
P : [\u0070] | [\u0050] ;
Q : [\u0071] | [\u0051] ;
R : [\u0072] | [\u0052] ;
S : [\u0073] | [\u0053] ;
T : [\u0074] | [\u0054] ;
U : [\u0075] | [\u0055] ;
V : [\u0076] | [\u0056] ;
W : [\u0077] | [\u0057] ;
X : [\u0078] | [\u0058] ;
Y : [\u0079] | [\u0059] ;
Z : [\u0080] | [\u0060] ;

SP : ' ' ;

ReservedWord : (A L L)
    | (A S C)
    | (A S C E N D I N G)
    | (B Y)
    | (C R E A T E)
    | (D E L E T E)
    | (D E S C)
    | (D E S C E N D I N G)
    | (D E T A C H)
    | (E X I S T S)
    | (L I M I T)
    | (M A T C H)
    | (M E R G E)
    | (O N)
    | (O P T I O N A L)
    | (O R D E R)
    | (R E M O V E)
    | (R E T U R N)
    | (S E T)
    | (S K I P)
    | (W H E R E)
    | (W I T H)
    | (U N I O N)
    | (U N W I N D)
    | (A N D)
    | (A S)
    | (C O N T A I N S)
    | (D I S T I N C T)
    | (E N D S)
    | (I N)
    | (I S)
    | (N O T)
    | (O R)
    | (S T A R T S)
    | (X O R)
    | (F A L S E)
    | (T R U E)
    | (N U L L)
    | (C O N S T R A I N T)
    | (D O)
    | (F O R)
    | (R E Q U I R E)
    | (U N I Q U E)
    | (C A S E)
    | (W H E N)
    | (T H E N)
    | (E L S E)
    | (E N D)
    | (M A N D A T O R Y)
    | (S C A L A R)
    | (O F)
    | (A D D)
    | (D R O P)
    ;

ISNULL : ' ';

WS : [ \t\r\n] -> skip ;

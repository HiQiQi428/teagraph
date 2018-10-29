lexer grammar UnicodeSet;

ID_Start : [A-Za-z] ;

ID_Continue : [0-9A-Za-z] ;

// SP

SP : WHITESPACE+ ;
WHITESPACE : SPACE | TAB | LF | VT | FF | CR | FS | GS | RS | US
    | [\u1680] | [\u180E] | [\u2000] | [\u2001] | [\u2002] | [\u2003] | [\u2004] | [\u2005] | [\u2006] | [\u2008] | [\u2009] | [\u200A] | [\u2028] | [\u2029] | [\u205F] | [\u3000] | [\u00A0] | [\u2007] | [\u202F] | COMMENT ;

SPACE : [\u0020] ;
TAB : [\u0009] ;
LF : [\u000A] ;
VT : [\u000B] ;
FF : [\u000C] ;
CR : [\u000D] ;
FS : [\u001C] ;
GS : [\u001D] ;
RS : [\u001E] ;
US : [\u001F] ;

COMMENT : ('/*' (ANY ('*') | ('*' ANY ('/')))* '*/')
    | ('//' (ANY (LF | CR))* CR? (LF | EOI)) ;

Pc : [\u005F] | [\u203F] | [\u2040] | [\u2054] | [\uFE33] | [\uFE34] | [\uFE4D] | [\uFE4F] | [\uFF3F] ;

Sc : [\u0024] | [\u00A2] | [\u00A5] | [\u058f] | [\u060B] | [\u07FE] | [\u07FF] | [\u09F2] | [\u09F3] | [\u09FB] | [\u0AF1] | [\u0BF9] | [\u0E3F] | [\u17DB] | [\u20A0] | [\u20BF] | [\uA838] | [\uFDFC] | [\uFE69] | [\uFF04] | [\uFFE0] | [\uFFE1] | [\uFFE5] | [\uFFE6];

EOI : EOF ;

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

ANY : . ;

WS : [ \t] -> skip ;
grammar Pattern;
import UnicodeSet;
import Expression;
import MapLiteral;
import BaseName;

pattern : patternPart (SP? ',' SP? patternPart)* ;

patternPart : (variable SP? '=' SP? anonymousPatternPart)
    | anonymousPatternPart ;

variable : symbolicName ;

anonymousPatternPart : patternElement ;

// PatternElement

patternElement : (nodePattern (SP? patternElementChain)*)
    | ('(' patternElement ')') ;

patternElementChain : relationshipPattern SP? nodePattern ;

// NodePattern

nodePattern : '(' SP? (variable SP?)? (nodeLabels SP?)? (properties SP?)? ')' ;

nodeLabels : nodeLabel (SP? nodeLabel)* ;

nodeLabel : ':' SP? labelName ;

labelName : schemaName ;

properties : mapLiteral | parameter ;

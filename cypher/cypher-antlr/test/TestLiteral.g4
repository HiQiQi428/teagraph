grammar TestLiteral;
import TestNumberLiteral, TestStringLiteral, TestBooleanLiteral, TestMapLiteral, TestListLiteral;

literal : numberLiteral | stringLiteral | booleanLiteral | (N U L L) | mapLiteral | listLiteral ;

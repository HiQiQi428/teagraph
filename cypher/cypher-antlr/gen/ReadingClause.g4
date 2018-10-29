parser grammar ReadingClause;
import Expression;
import UnicodeSet;

readingClause : match ; // | unwind | inQueryCall ;

// Match

match : ((O P T I O N A L) SP)? (M A T C H) SP? pattern (SP? where)? ;

// Where

where : (W H E R E) SP expression ;

package org.luncert.teagraph.cypher.cypherObject;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CExpression;
import org.luncert.teagraph.cypher.cypherObject.nonpiece.CVariable;

import lombok.Data;

@Data
public class CUnwind extends ReadingClause {

    CExpression expression;
    CVariable variable;
    
}
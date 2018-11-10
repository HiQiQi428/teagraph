package org.luncert.teagraph.cypher.cypherObject;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CExpression;

import lombok.Data;

@Data
public class CWhere {

    CExpression expression;
    
}
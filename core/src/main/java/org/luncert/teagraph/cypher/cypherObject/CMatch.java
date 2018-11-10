package org.luncert.teagraph.cypher.cypherObject;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CPattern;

import lombok.Data;

/**
 * MATCH (n:Actor)
 */
@Data
public class CMatch extends ReadingClause {

    boolean optional;
    CPattern pattern;
    CWhere where;

}
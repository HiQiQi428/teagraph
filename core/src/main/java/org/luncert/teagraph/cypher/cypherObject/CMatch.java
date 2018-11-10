package org.luncert.teagraph.cypher.cypherObject;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CPattern;

/**
 * MATCH (n:Actor)
 */
public class CMatch {

    boolean optional;
    CPattern pattern;
    CWhere where;

}
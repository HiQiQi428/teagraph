package org.luncert.teagraph.cypher.cypherObject;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CReturnBody;

public class CWith {

    /**
     * optional
     */
    boolean distinct = false;
    
    CReturnBody returnBody;

    /**
     * optional
     */
    CWhere where;

}
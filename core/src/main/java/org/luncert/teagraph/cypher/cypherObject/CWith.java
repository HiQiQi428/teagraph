package org.luncert.teagraph.cypher.cypherObject;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CReturnBody;

import lombok.Data;

@Data
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
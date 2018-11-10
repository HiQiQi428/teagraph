package org.luncert.teagraph.cypher.cypherObject;

import lombok.Data;

/**
 * UNION ALL
 */
@Data
public class CUnion {

    boolean all;
    CSingleQuery singleQuery;
    
}
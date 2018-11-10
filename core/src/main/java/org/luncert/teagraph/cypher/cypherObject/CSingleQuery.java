package org.luncert.teagraph.cypher.cypherObject;

import lombok.Data;

@Data
public class CSingleQuery {

    CSinglePartQuery singlePartQuery;
    CMultiPartQuery multiPartQuery;
    
}
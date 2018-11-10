package org.luncert.teagraph.cypher.cypherObject;

import lombok.Data;

@Data
public class CQuery {

    CRegularQuery regularQuery;
    CStandaloneCall standaloneCall;

}
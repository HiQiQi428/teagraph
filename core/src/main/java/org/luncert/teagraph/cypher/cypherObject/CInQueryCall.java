package org.luncert.teagraph.cypher.cypherObject;

import lombok.Data;

@Data
public class CInQueryCall extends ReadingClause {

    Object explicitProcedureInvocation;
    Object params;
    
}
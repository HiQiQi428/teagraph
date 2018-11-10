package org.luncert.teagraph.cypher.cypherObject;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CSinglePartQuery {

    List<ReadingClause> readingClauses;
    List<UpdatingClause> updatingClauses;
    CReturn creturn;

    public CSinglePartQuery() {
        readingClauses = new ArrayList<>();
        updatingClauses = new ArrayList<>();
    }

}
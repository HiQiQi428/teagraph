package org.luncert.teagraph.cypher.cypherObject;

import java.util.List;

import lombok.Data;

@Data
public class CMultiPartQuery {

    List<ReadingClause> readingClauses;
    List<UpdatingClause> updatingClauses;
    CWith cwith;
    CSinglePartQuery singlePartQuery;

}
package org.luncert.teagraph.cypher.cypherObject;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class CRegularQuery {

    CSingleQuery singleQuery;
    List<CUnion> unions;

    public CRegularQuery() {
        unions = new LinkedList<>();
    }

}
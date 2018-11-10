package org.luncert.teagraph.cypher.cypherObject.nonpiece;

import java.util.List;

public class COrder {

    public static class CSortItem {

        /**
         * asc | desc
         */
        boolean ascending;
        CExpression expression;
    }

    List<CSortItem> sortItems;
    
}
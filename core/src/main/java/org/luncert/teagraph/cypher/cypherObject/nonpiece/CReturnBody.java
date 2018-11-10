package org.luncert.teagraph.cypher.cypherObject.nonpiece;

import java.util.List;

public class CReturnBody {

    public static class CReturnItem {

        CExpression expression;
        CVariable variable;

    }

    List<CReturnItem> returnItems;
    COrder order;
    CSkip skip;
    CLimit limit;

}
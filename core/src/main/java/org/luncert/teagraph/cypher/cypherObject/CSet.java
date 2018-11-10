package org.luncert.teagraph.cypher.cypherObject;

import java.util.List;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CExpression;
import org.luncert.teagraph.cypher.cypherObject.nonpiece.CNodeLabels;
import org.luncert.teagraph.cypher.cypherObject.nonpiece.CPropertyExpression;
import org.luncert.teagraph.cypher.cypherObject.nonpiece.CVariable;

public class CSet {

    private interface CSetItem {}

    public static class CSetItem1 implements CSetItem {

        CPropertyExpression propertyExpression;
        CExpression expression;

    }

    public static class CSetItem2 implements CSetItem {

        CVariable variable;

        /**
         * mode 1
         */
        CExpression expression;

        /**
         * mode 2
         */
        CNodeLabels nodeLabels;
    }

    List<CSetItem> setItems;
    
}
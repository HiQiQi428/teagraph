package org.luncert.teagraph.cypher.cypherObject;

import java.util.List;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CNodeLabels;
import org.luncert.teagraph.cypher.cypherObject.nonpiece.CPropertyExpression;
import org.luncert.teagraph.cypher.cypherObject.nonpiece.CVariable;

import lombok.Data;

@Data
public class CRemove extends UpdatingClause {

    private interface CRemoveItem {}

    public static class CRemoveItem1 implements CRemoveItem {

        CVariable variable;
        CNodeLabels nodeLabels;
        
        public CRemoveItem1(CVariable variable, CNodeLabels nodeLabels) {
            this.variable = variable;
            this.nodeLabels = nodeLabels;
        }

    }

    public static class CRemoveItem2 implements CRemoveItem {

        CPropertyExpression propertyExpression;

        public CRemoveItem2(CPropertyExpression propertyExpression) {
            this.propertyExpression = propertyExpression;
        }
    }

    List<CRemoveItem> removeItems;

}
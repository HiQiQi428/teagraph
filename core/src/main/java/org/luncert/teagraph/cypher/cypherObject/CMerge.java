package org.luncert.teagraph.cypher.cypherObject;

import java.util.List;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CPatternPart;

public class CMerge {

    public static class CMergeAction {

        /**
         * on match set | on create set
         */
        boolean beMatch;
        CSet set;
        
    }

    CPatternPart patternPart;
    List<CMergeAction> mergeActions;
    
}
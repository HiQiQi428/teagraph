package org.luncert.teagraph.cypher.cypherVisitor;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

import org.antlr.v4.runtime.tree.ParseTree;

public class CVHelper {

    private List<CVStep> steps = new LinkedList<>();

    public CVHelper addStep(CVStep s) {
        steps.add(s);
        return this;
    }

    public boolean handle(ParseTree tree) {
        Iterator<CVStep> iterator = steps.iterator();
        CVStep s;
        int i = 0, limit = tree.getChildCount();
        while (iterator.hasNext() && i < limit) {
            s = iterator.next();
            i = s.handle(tree, i);
        }
        return i == limit;
    }

}
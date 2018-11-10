package org.luncert.teagraph.cypher.cypherVisitor;

import java.util.LinkedList;
import java.util.List;

public class SOption extends Step {

    List<Step> steps = new LinkedList<>();

    public SOption addOption(Step step) {
        steps.add(step);
        return this;
    }

    public <T> T handle(ParseTreeList list, T data) {
        for (Step step : steps)
            if (step.handle(list, data) != null)
                return data;
        return null;
    }

}
package org.luncert.teagraph.cypher.cypherVisitor;

import java.util.function.Function;
import java.util.function.Predicate;

import org.antlr.v4.runtime.tree.ParseTree;

public class CVPiece extends CVStep {

    private Predicate<ParseTree> condition;
    private Function<ParseTree, Object> handler;

    public CVPiece(Predicate<ParseTree> condition,
        Function<ParseTree, Object> handler)
    {
        this.condition = condition;
        this.handler = handler;
    }

    public boolean handle(ParseTree tree) {
        if (condition.test(tree)) {
            handler.apply(tree);
            return true;
        }
        return false;
    }

    public int handle(ParseTree tree, int i) {
        return handle(tree.getChild(i)) ? i + 1 : i;
	}

}
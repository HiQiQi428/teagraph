package org.luncert.teagraph.cypher.cypherVisitor;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

import org.antlr.v4.runtime.tree.ParseTree;

public class SPiece extends Step {

    private Predicate<ParseTree> condition;
    private BiConsumer<ParseTree, Object> handler;

    public SPiece(Predicate<ParseTree> condition,
    BiConsumer<ParseTree, Object> handler)
    {
        this.condition = condition;
        this.handler = handler;
    }

    @Override
    public <T> T handle(ParseTreeList list, T data) {
        ParseTree tree;
        while (true) {
            tree = list.current();
            if (tree == null)
                return null;
            if (condition.test(tree)) {
                handler.accept(tree, data);
                return data;
            }
            list.forward();
        }
    }

}
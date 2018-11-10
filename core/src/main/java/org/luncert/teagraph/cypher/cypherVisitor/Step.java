package org.luncert.teagraph.cypher.cypherVisitor;

import org.antlr.v4.runtime.tree.ParseTree;

public abstract class Step {

    /**
     * @return data
     */
    public <T> T handle(ParseTree tree, T data) {
        return handle(new ParseTreeList(tree), data);
    }

    public abstract <T> T handle(ParseTreeList list, T data);

}
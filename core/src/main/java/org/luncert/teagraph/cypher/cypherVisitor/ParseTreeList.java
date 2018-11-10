package org.luncert.teagraph.cypher.cypherVisitor;

import org.antlr.v4.runtime.tree.ParseTree;

public class ParseTreeList {

    private int cursor, limit;
    private ParseTree parent;

    public ParseTreeList(ParseTree parent) {
        cursor = 0;
        limit = parent.getChildCount();
        this.parent = parent;
    }

    public ParseTreeList backoff() {
        cursor--;
        return this;
    }

    public ParseTreeList forward() {
        cursor++;
        return this;
    }

    public boolean avaliable() {
        return cursor >= 0 && cursor < limit;
    }

    public ParseTree current() {
        return cursor < limit ? parent.getChild(cursor) : null;
    }

}
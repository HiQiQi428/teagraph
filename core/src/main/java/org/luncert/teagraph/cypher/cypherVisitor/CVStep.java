package org.luncert.teagraph.cypher.cypherVisitor;

import org.antlr.v4.runtime.tree.ParseTree;

public abstract class CVStep {

    public abstract int handle(ParseTree tree, int i);

}
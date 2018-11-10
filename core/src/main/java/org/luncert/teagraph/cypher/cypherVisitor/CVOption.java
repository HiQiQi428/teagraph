package org.luncert.teagraph.cypher.cypherVisitor;

import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

public class CVOption extends CVStep {

    List<CVPiece> pieces = new LinkedList<>();

    public CVOption addOption(CVPiece piece) {
        pieces.add(piece);
        return this;
    }

    public int handle(ParseTree tree, int i) {
        ParseTree t = tree.getChild(i);
        for (CVPiece piece : pieces) {
            if (piece.handle(t)) {
                i++;
                break;
            }
        }
        return i;
    }

}
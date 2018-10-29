package org.luncert.teagraph.cypher.cypherObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CPattern {

    public static enum Direction {
        Forward, Reverse, Undirected
    }

    public static abstract class Piece {
        String outputName;
        List<String> labels = new LinkedList<>();
        Map<String, String> properties = new HashMap<>();
        Piece pre, next;
    }

    public static class Node extends Piece {
    }

    public static class Rel extends Piece {
        Direction direction;
        int minDepth = 1, maxDepth = 1;
    }

    Piece first, tail;

    public void append(Piece piece) {
        if (first == null)
            tail = first = piece;
        else {
            tail.next = piece;
            piece.pre = tail;
            tail = piece;
        }
    }

}
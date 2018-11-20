package org.luncert.tkglb.cypher;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.luncert.tkglb.cypher.CPiece.PieceType;
import org.luncert.tkglb.cypher.CPiece.UnionType;

public class AstResolver {

    private static class ParseNode {

        final ParseTree tree;

        ParseNode(ParseTree tree) {
            this.tree = tree;
        }
        
        private String[] pathPrehandle(String src) {
            if (src.startsWith("/"))
                src = src.substring(1);
            if (src.endsWith("/"))
                src = src.substring(0, src.length() - 1);
            return src.split("/");
        }

        private String getNodeName(ParseTree node) {
            String className = node.getClass().getName();
            int i = className.indexOf("$");
            className = className.substring(i + 1).replace("Context", "");
            return className.substring(0, 1).toLowerCase() + className.substring(1);
        }

        private ParseTree matchChildren(ParseTree parent, String name) {
            for (int i = 0, limit = parent.getChildCount(); i < limit; i++) {
                ParseTree child = parent.getChild(i);
                if (getNodeName(child).equals(name))
                    return child;
            }
            return null;
        }

        public ParseNode matchPath(String path) {
            String[] p = pathPrehandle(path);
            ParseTree node = tree;
            // 尝试找到目标节点
            if (!getNodeName(tree).equals(p[0]))
                return null;
            for (int i = 1; node != null && i < p.length; i++)
                node = matchChildren(node, p[i]);
            return node == null ? null : new ParseNode(node);
        }

        public ParseNode match(String name) {
            ParseTree node = matchChildren(tree, name);
            return node == null ? null : new ParseNode(node);
        }

        public List<ParseNode> matchAll(String name) {
            List<ParseNode> list = new ArrayList<>();
            for (int i = 0, limit = tree.getChildCount(); i < limit; i++) {
                ParseTree child = tree.getChild(i);
                if (getNodeName(child).equals(name))
                    list.add(new ParseNode(child));
            }
            return list;
        }

        public int getChildCount() {
            int count = 0;
            for (int i = 0, limit = tree.getChildCount(); i < limit; i++) {
                ParseTree child = tree.getChild(i);
                if (!getNodeName(child).equals("TerminalNodeImpl"))
                    count++;
            }
            return count;
        }

        public ParseTree getParseTree() {
            return tree;
        }

    }

    private CPiece resolveSingleQuery(ParseNode singleQuery) {
        CPiece piece = new CPiece();
        ParseNode node;
        if ((node = singleQuery.match("singlePartQuery")) == null)
            if ((node = singleQuery.match("multiPartQuery")) == null)
                throw new AstError("resolve singleQuery failed");
        if (node.match("updatingClause") != null)
            piece.setPieceType(PieceType.Update);
        piece.setContent(node.getParseTree().getText());
        return piece;
    }

    public List<CPiece> resolve(ParseTree tree) {
        List<CPiece> list = new ArrayList<>();
        ParseNode root = new ParseNode(tree);
        ParseNode reqularQuery = root.matchPath("cypher/statement/query/regularQuery");
        list.add(resolveSingleQuery(reqularQuery.match("singleQuery")));

        assert(reqularQuery.getChildCount() % 2 == 1);
        for (ParseNode union : reqularQuery.matchAll("union")) {
            CPiece piece = resolveSingleQuery(union.match("singleQuery"));
            if (union.match("rw_all") != null)
                piece.setUnionType(UnionType.UnionAll);
            else
                piece.setUnionType(UnionType.Union);
            list.add(piece);
        }
        return list;
    }

}
package org.luncert.tkglb.cypher;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.luncert.tkglb.cypher.CPiece.PieceType;
import org.luncert.tkglb.cypher.CPiece.UnionType;

/**
 * 处理Cypher AST
 */
public class AstResolver {

    private static class ParseNode {

        final ParseTree tree;

        ParseNode(ParseTree tree) {
            this.tree = tree;
        }
        
        /**
         * 路径字符串预处理
         * @param src
         * @return String[] 合法的路径字符串
         */
        private String[] pathPrehandle(String src) {
            if (src.startsWith("/"))
                src = src.substring(1);
            if (src.endsWith("/"))
                src = src.substring(0, src.length() - 1);
            return src.split("/");
        }

        /**
         * 从ParseTree的类名解析出小写字母开头的节点名，
         * 例如{@code CypherParser$CypherContext}
         * 解析为{@code cypher}
         * @param node
         * @return
         */
        private String getNodeName(ParseTree node) {
            String className = node.getClass().getName();
            int i = className.indexOf("$");
            className = className.substring(i + 1).replace("Context", "");
            return className.substring(0, 1).toLowerCase() + className.substring(1);
        }

        /**
         * 遍历AST树，返回匹配路径的节点
         * @param path 例如{@code cypher/statement/query/regularQuery}
         * @return ParseNode or null
         */
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

        /**
         * 从parent的字节点中寻找匹配name的节点
         * @param parent
         * @param name
         * @return ParseTree or null
         */
        private ParseTree matchChildren(ParseTree parent, String name) {
            for (int i = 0, limit = parent.getChildCount(); i < limit; i++) {
                ParseTree child = parent.getChild(i);
                if (getNodeName(child).equals(name))
                    return child;
            }
            return null;
        }

        /**
         * 在子节点中查找指定名称的节点，只查找一层
         * @param name
         * @return ParseNode or null
         */
        public ParseNode match(String name) {
            ParseTree node = matchChildren(tree, name);
            return node == null ? null : new ParseNode(node);
        }

        /**
         * 在字节点中查找所有name匹配的节点，只查找一层
         * @param name
         * @return List<ParseNode>
         */
        public List<ParseNode> matchAll(String name) {
            List<ParseNode> list = new ArrayList<>();
            for (int i = 0, limit = tree.getChildCount(); i < limit; i++) {
                ParseTree child = tree.getChild(i);
                if (getNodeName(child).equals(name))
                    list.add(new ParseNode(child));
            }
            return list;
        }

        /**
         * 获取子节点数目，会过滤掉{@code TermialNodeImpl}类型的节点
         * @return
         */
        public int getChildCount() {
            int count = 0;
            for (int i = 0, limit = tree.getChildCount(); i < limit; i++) {
                ParseTree child = tree.getChild(i);
                /*
                这里我之前写的是 TerminalNodeImpl，但是getNodeName返回的是
                小写字母开头的节点名。。。我不清楚了，不确定这里的过滤是不是能其作用
                */
                if (!getNodeName(child).equals("terminalNodeImpl"))
                    count++;
            }
            return count;
        }

        public ParseTree getParseTree() {
            return tree;
        }

    }

    /**
     * 解析SingleQuery为CPiece
     * @param singleQuery
     * @return {@Code CPiece}
     */
    private CPiece resolveSingleQuery(ParseNode singleQuery) {
        CPiece.CPieceBuilder builder = CPiece.builder();
        ParseNode node;

        if ((node = singleQuery.match("singlePartQuery")) == null)
            if ((node = singleQuery.match("multiPartQuery")) == null)
                throw new AstError("resolve singleQuery failed");

        if (node.match("updatingClause") == null) {
            builder.pieceType(PieceType.Read);
        }
        else {
            builder.pieceType(PieceType.Update);
        }

        builder.content(node.getParseTree().getText());
        return builder.build();
    }

    /**
     * 解析AST
     * @param tree
     * @return List&lt;CPiece&gt;
     */
    public List<CPiece> resolve(ParseTree tree) {
        List<CPiece> list = new ArrayList<>();
        ParseNode root = new ParseNode(tree);
        
        // 暂不支持解析standaloneCall

        ParseNode reqularQuery = root.matchPath("cypher/statement/query/regularQuery");
        list.add(resolveSingleQuery(reqularQuery.match("singleQuery")));

        assert(reqularQuery.getChildCount() % 2 == 1);

        for (ParseNode union : reqularQuery.matchAll("union")) {
            CPiece piece = resolveSingleQuery(union.match("singleQuery"));

            if (union.match("rw_all") != null) {
                piece.setUnionType(UnionType.UnionAll);
            }
            else {
                piece.setUnionType(UnionType.Union);
            }

            list.add(piece);
        }
        return list;
    }

}
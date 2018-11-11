package org.luncert.teagraph.cypher.astResolver;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.antlr.v4.runtime.tree.ParseTree;
import org.luncert.teagraph.cypher.astResolver.xmlHandler.CypherHandler;
import org.luncert.teagraph.cypher.astResolver.xmlHandler.DynamicNode;
import org.luncert.teagraph.cypher.astResolver.xmlHandler.DynamicTree;

public class AstResolver {

    private DynamicTree tree;
    private List<DynamicNode> vars;
    private DynamicNode ignore;
    private String classpath;
    private DynamicNode root; // solution

    public void init(InputStream xmlInput) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        CypherHandler handler = new CypherHandler();
        parser.parse(xmlInput, handler);

        tree = handler.getTree();
        vars = tree.matchAll("resolver/var/element");
        ignore = tree.match("resolver/ignore");
        DynamicNode solution = tree.match("resolver/solution");
        // 获取classpath和root
        assert(solution.getChildCount() == 2);
        for (DynamicNode node : solution.getChildren()) {
            if ("classpath".equals(node.getName()))
                classpath = (String) node.getAttr("value");
            else root = node;
        }
    }

    private DynamicNode getRefNode(String refName) {
        DynamicNode node;
        for (int i = 0, limit = vars.size(); i < limit; i++) {
            node = vars.get(i);
            if (refName.equals(node.getName()))
                return node;
        }
        return null;
    }

    private String getTypeName(String tagName) {
        return classpath + "$" + tagName.substring(0, 1).toUpperCase() + tagName.substring(1) + "Context";
    }

    private boolean condition(ParseTree tree, String tagName) {
        return tree.getClass().getName().equals(getTypeName(tagName));
    }

    public void resolve(ParseTree ast) {
        assert(tree != null);
        // DFT
        ParseTree tmp = ast;
        DynamicNode node = root;
        List<DynamicNode> children;
        LinkedQueue<DynamicNode> q = new LinkedQueue<>();

        assert(condition(tmp, node.getName()));

        q.enQueue(node);
        // 加入队列的是已经处理了的.每次循环都从队列里取出节点,然后处理其孩子们
        while (!q.isEmpty()) {
            node = q.deQueue();
            children = node.getChildren();

            for (int i = 0, limit = children.size(); i < limit; i++) {
                node = children.get(i);
                if ("element".equals(node.getName())) {
                    String refName = (String) node.getAttr("ref");
                    if (refName != null)
                        node = getRefNode(refName); // 将node指向refNode,实现refNode替换
                    else {
                        
                    }
                }
                if ("true".equals(node.getAttr("ignore")))
                    continue;
                else if ("true".equals(node.getAttr("bool"))) {
                    boolean value = condition(tmp, node.getName());
                }
                else if ("true".equals(node.getAttr("text"))) {
                    String value = tmp.getText();
                }
                q.enQueue(node.getChildren());
            }
        }
    }

}
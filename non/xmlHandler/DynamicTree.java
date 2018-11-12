package org.luncert.teagraph.cypher.astResolver.xmlHandler;

import java.util.ArrayList;
import java.util.List;

public class DynamicTree {

    private DynamicNode root = new DynamicNode(null);

    private String[] pathPrehandle(String src) {
        if (src.startsWith("/"))
            src = src.substring(1);
        if (src.endsWith("/"))
            src = src.substring(0, src.length() - 1);
        return src.split("/");
    }

    /**
     * 不会返回null
     */
    public DynamicNode matchOrCreate(String path) {
        String[] p = pathPrehandle(path);
        if (p.length == 0)
            return null;

        DynamicNode node = root, pre = root;
        int i = 0;
        // 尝试找到目标节点
        for (; node != null && i < p.length; i++) {
            pre = node;
            node = node.match(p[i]);
        }
        if (node == null) {
            i--;
            node = pre;
        }
        for (; i < p.length; i++) {
            pre = node;
            node = new DynamicNode(p[i]);
            pre.addChild(node);
            node.setParent(pre);
        }
        return node;
    }

    public DynamicNode match(String path) {
        String[] p = pathPrehandle(path);
        if (p.length == 0)
            return null;

        DynamicNode node = root;
        for (int i = 0; node != null && i < p.length; i++)
            node = node.match(p[i]);
        return node;
    }

    public List<DynamicNode> matchAll(String path) {
        String[] p = pathPrehandle(path);
        if (p.length == 0)
            return null;

        DynamicNode node = root;
        // 尝试找到目标节点的父亲
        int i = 0;
        for (int limit = p.length - 1; node != null && i < limit; i++)
            node = node.match(p[i]);
        if (node != null)
            return node.matchAll(p[i]);
        return new ArrayList<>();
    }

    public String toString() {
        return root.toString();
    }

}
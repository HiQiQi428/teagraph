package org.luncert.teagraph.cypher.astResolver.xmlHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicNode {

    private String name;
    private DynamicNode parent;
    private Map<String, Object> attrs = new HashMap<>();
    private List<DynamicNode> children = new ArrayList<>();

    public DynamicNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParent(DynamicNode parent) {
        this.parent = parent;
    }

    public DynamicNode getParent() {
        return parent;
    }

    public void setAttr(String name, Object value) {
        attrs.put(name, value);
    }

    public Object getAttr(String name) {
        return attrs.get(name);
    }

    public void addChild(DynamicNode node) {
        children.add(node);
    }

    public int getChildCount() {
        return children.size();
    }

    public DynamicNode getFirstChild() {
        return children.get(0);
    }

    public List<DynamicNode> getChildren() {
        return children;
    }

    public DynamicNode match(String name) {
        for (DynamicNode child : children)
            if (child.name.equals(name))
                return child;
        return null;
    }

    public List<DynamicNode> matchAll(String name) {
        List<DynamicNode> result = new ArrayList<>();
        for (DynamicNode child : children)
            if (child.name.equals(name))
                result.add(child);
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("DynamicNode(")
                .append("name=").append(name)
                .append(",attrs=").append(attrs)
                .append(",childCount=").append(children.size())
                .append(")");
        return builder.toString();
    }

}
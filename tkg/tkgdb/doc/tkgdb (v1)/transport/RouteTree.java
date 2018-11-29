package org.luncert.tkgdb.transport;

import java.util.LinkedList;
import java.util.List;

public class RouteTree<T> {

    public static class RouteNode<T> {
        private String name;
        private T value;
        private List<RouteNode<T>> children;

        RouteNode(String name, T value) {
            this.name = name;
            this.value = value;
            children = new LinkedList<>();
        }

        RouteNode<T> match(String name) {
            for (RouteNode<T> child : children)
                if (child.name.equals(name))
                    return child;
            return null;
        }
    }

    private RouteNode<T> root;

    public RouteTree() {
        root = new RouteNode<>(null, null);
    }

    private String[] pathPreHandle(String path) {
        if (path.startsWith("/"))
            path = path.substring(1);
        if (path.endsWith("/"));
            path = path.substring(0, path.length() - 1);
        return path.split("/");
    }

    public void add(String path, T value) {
        String[] p = pathPreHandle(path);
        if (p.length == 0)
            return;

        RouteNode<T> node = root, pre = root;
        int i = 0, limit = p.length - 1;
        for (; node != null && i < limit; i++) {
            pre = node;
            node = node.match(p[i]);
        }
        if (node == null) {
            i--;
            for (; i < p.length; i++) {
                node = new RouteNode<>(p[i], null);
                pre.children.add(node);
                pre = node;
            }
        }
        else if (node.match(p[limit]) != null) {
            node.children.add(new RouteNode<>(p[limit], value));
        }
    }

    public T match(String path) {
        String[] p = pathPreHandle(path);
        if (p.length == 0)
            return null;
        
        int i = 0;
        RouteNode<T> node = root;
        for (; node != null && i < p.length; i++) {
            node = node.match(p[i]);
        }
        return (i == p.length) ? node.value : null;
    }

}
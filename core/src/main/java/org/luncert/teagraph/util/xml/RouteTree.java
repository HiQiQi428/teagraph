package org.luncert.teagraph.util.xml;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.luncert.teagraph.util.xml.annotations.AccessType;
import org.luncert.teagraph.util.xml.annotations.Characters;
import org.luncert.teagraph.util.xml.annotations.EndDocument;
import org.luncert.teagraph.util.xml.annotations.EndElement;
import org.luncert.teagraph.util.xml.annotations.StartDocument;
import org.luncert.teagraph.util.xml.annotations.StartElement;

public class RouteTree {

    public static class Node {

        public class NodeContext {
    
            private class Pair {
                String key;
                Object value;
                Pair(String key, Object value) {
                    this.key = key;
                    this.value = value;
                }
            }

            NodeContext() {}
        
            private List<Pair> data;

            public void put(String key, Object value) {
                if (data == null)
                    data = new LinkedList<>();
                data.add(new Pair(key, value));
            }
        
            public Object get(String key) {
                if (data != null) {
                    Pair pair;
                    for (int i = 0, limit = data.size(); i < limit; i++) {
                        pair = data.get(i);
                        if (data.get(i).key.equals(key))
                            return pair.value;
                    }
                }
                return null;
            }
        
            public void remove(String key) {
                if (data != null) {
                    for (int i = 0, limit = data.size(); i < limit; i++)
                        if (data.get(i).key.equals(key)) {
                            data.remove(i);
                            break;
                        }
                }
            }
    
        }

        private String name;
        private NodeContext context;
        private Map<AccessType, Method> map;
        private Node parent;
        private List<Node> children;

        private Node(String name) {
            this.name = name;
            context = new NodeContext();
            map = new HashMap<>();
            children = new ArrayList<>();
        }

        void bind(AccessType accessType, Method method) {
            map.put(accessType, method);
        }

        void addChild(Node child) {
            children.add(child);
        }

        Node match(String name) {
            for (Node node : children) {
                if (node.name.equals(name))
                    return node;
            }
            return null;
        }

        public Node getParent() {
            return parent;
        }

        public NodeContext getContext() {
            return context;
        }

        public Method getHandler(AccessType accessType) {
            return map.get(accessType);
        }

    }

    private Class<?> handlerClass;
    private Node root; // empty node

    private String[] pathPrehandle(String src) {
        if (src.startsWith("/"))
            src = src.substring(1);
        if (src.endsWith("/"))
            src = src.substring(0, src.length() - 1);
        return src.split("/");
    }

    public RouteTree(Class<?> handlerClass) {
        this.handlerClass = handlerClass;
        root = new Node(null);
        initWithHandler();
    }

    private void initWithHandler() {
        Class<?> clazz = this.handlerClass;
        for (Method method : clazz.getMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                if (annotation instanceof StartDocument) {
                    root.bind(AccessType.StartDocument, method);
                }
                else if (annotation instanceof StartElement) {
                    StartElement anno = ((StartElement)annotation);
                    addNode(anno.path(), AccessType.StartElement, method);
                }
                else if (annotation instanceof Characters) {
                    Characters anno = ((Characters)annotation);
                    addNode(anno.path(), AccessType.Characters, method);
                }
                else if (annotation instanceof EndElement) {
                    EndElement anno = ((EndElement)annotation);
                    addNode(anno.path(), AccessType.Characters, method);
                }
                else if (annotation instanceof EndDocument) {
                    root.bind(AccessType.EndDocuemnt, method);
                }
                
            }
        }
    }

    private Node addNode(String path, AccessType accessType, Method method) {
        String[] p = pathPrehandle(path);
        if (p.length == 0)
            return null;

        Node node = root, pre = root;
        int i = 0, limit = p.length - 1;
        for (; node != null && i < limit; i++) {
            pre = node;
            node = node.match(p[i]);
        }
        if (node != null) {
            pre = node;
            node = node.match(p[i]);
            if (node != null) {
                node.bind(accessType, method);
                return node;
            }
        }
        else {
            i--;
            node = pre;
            for (; i < limit; i++) {
                pre = node;
                node = new Node(p[i]);
                pre.addChild(node);
                node.parent = pre;
            }
            pre = node;
        }
        node = new Node(p[i]);
        node.bind(accessType, method);
        pre.addChild(node);
        node.parent = pre;
        return node;
    }

    public Optional<Node> match(String path) {
        Node node = root;
        if (path != null) {
            String[] p = pathPrehandle(path);
            if (p.length == 0)
                node = null;
            else {
                for (int i = 0; node != null && i < p.length; i++)
                    node = node.match(p[i]);
            }
        }
        return Optional.ofNullable(node);
    }

}
package org.luncert.teagraph.configurer;

import java.util.HashMap;
import java.util.Map;

import org.luncert.teagraph.configurer.Configuration.AuthPair;
import org.luncert.teagraph.configurer.RouteTree.Node;
import org.luncert.teagraph.configurer.RouteTree.Node.NodeContext;
import org.luncert.teagraph.configurer.annotations.Characters;
import org.luncert.teagraph.configurer.annotations.StartDocument;
import org.luncert.teagraph.configurer.annotations.StartElement;
import org.xml.sax.Attributes;

public class ConfigHandler {

    private static Configuration config;

    @StartDocument
    public static void init() {
        config = new Configuration();
    }

    @Characters(path = "config/clientManage/port")
    public static void cmport(Node node, String value) {
        config.setCmPort(Integer.valueOf(value));
    }

    @Characters(path = "config/nodeManage/port")
    public static void nmport(Node node, String value) {
        config.setNmPort(Integer.valueOf(value));
    }

    @StartElement(path = "config/nodeManage/nodes")
    public static void nodes(Node node, Attributes attrs) {
        NodeContext ctx = node.getContext();
        Map<String, AuthPair> nodes = new HashMap<>();
        config.setNodes(nodes);
        ctx.put("nodes", nodes);
    }

    @SuppressWarnings("unchecked")
    @StartElement(path = "config/nodeManage/nodes/node")
    public static void node(Node node, Attributes attrs) {
        Map<String, AuthPair> nodes = (Map<String, AuthPair>)node.getParent().getContext().get("nodes");
        AuthPair authPair = new AuthPair(attrs.getValue("username"),
                                        attrs.getValue("password"));
        nodes.put(attrs.getValue("ip"), authPair);
    }

    public static Configuration getConfiguration() {
        return config;
    }

}
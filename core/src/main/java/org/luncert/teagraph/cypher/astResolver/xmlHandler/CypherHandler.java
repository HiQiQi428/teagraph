package org.luncert.teagraph.cypher.astResolver.xmlHandler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.luncert.teagraph.cypher.astResolver.xmlHandler.DynamicNode;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CypherHandler extends DefaultHandler {

    private List<String> path = new LinkedList<>();
    private DynamicTree tree = new DynamicTree();
    private DynamicNode node;

    private String stringifyPath() {
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = path.iterator();
        if (iterator.hasNext()) {
            while (true) {
                builder.append(iterator.next());
                if (!iterator.hasNext()) break;
                builder.append('/');
            }
        }
        return builder.toString();
    }

    public DynamicTree getTree() {
        return tree;
    }

    public CypherHandler() {
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        path.add(qName);
        node = tree.matchOrCreate(stringifyPath());
        for (int i = 0, limit = attributes.getLength(); i < limit; i++) {
            String name = attributes.getQName(i);
            String value = attributes.getValue(i);
            node.setAttr(name, value);
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        path.remove(path.size() - 1);
    }

}
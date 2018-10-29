package org.luncert.teagraph.configurer;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.luncert.teagraph.configurer.annotations.AccessType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParserHandler extends DefaultHandler {

    private RouteTree rt;
    private List<String> path = new LinkedList<>();

    private String stringifyPath() {
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = path.iterator();
        if (iterator.hasNext()) {
            while (true) {
                builder.append(iterator.next());
                if (iterator.hasNext())
                    builder.append('/');
                else
                    break;
            }
        }
        return builder.toString();
    }

    public XmlParserHandler(RouteTree rt) {
        this.rt = rt;
    }

    @Override
    public void startDocument() throws SAXException {
        rt.match(null).ifPresent((node) -> {
            Method handler = node.getHandler(AccessType.StartDocument);
            if (handler != null) {
                try {
                    handler.invoke(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        path.add(qName);
        rt.match(stringifyPath()).ifPresent((node) -> {
            Method handler = node.getHandler(AccessType.StartElement);
            if (handler != null) {
                try {
                    handler.invoke(null, node, attributes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        rt.match(stringifyPath()).ifPresent((node) -> {
            Method handler = node.getHandler(AccessType.Characters);
            if (handler != null) {
                try {
                    handler.invoke(null, node, String.valueOf(ch, start, length));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        path.remove(path.size() - 1);
        rt.match(stringifyPath()).ifPresent((node) -> {
            Method handler = node.getHandler(AccessType.EndElement);
            if (handler != null) {
                try {
                    handler.invoke(null, node);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void endDocument() throws SAXException {
        rt.match(null).ifPresent((node) -> {
            Method handler = node.getHandler(AccessType.EndDocuemnt);
            if (handler != null) {
                try {
                    handler.invoke(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
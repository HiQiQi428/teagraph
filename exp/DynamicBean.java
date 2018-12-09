package org.luncert.teagraph.cypher.astResolver;

import java.util.HashMap;
import java.util.Map;

public class DynamicBean {

    private Map<String, Object> map = new HashMap<>();

    public void setAttr(String name, Object value) {
        map.put(name, value);
    }

    public Object getAttr(String name) {
        return map.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttr(String name, Class<T> clazz) {
        return (T) map.get(name);
    }

    public String toString() {
        return map.toString();
    }

}
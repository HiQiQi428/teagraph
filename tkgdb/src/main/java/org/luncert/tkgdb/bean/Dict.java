package org.luncert.tkgdb.bean;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Dict {

    private Map<String, Object> attrs = new HashMap<>();

    public void addAttr(String name, Object value) {
        attrs.put(name, value);
    }

    public Object getAttr(String name) {
        return attrs.get(name);
    }

    public <T> T getAttr(String name, Class<T> clazz) {
        Object obj = attrs.get(name);
        return clazz.isInstance(obj) ? clazz.cast(obj) : null;
    }

    public String toJSONString() {
        return JSON.toJSONString(attrs);
    }

    public static Dict fromJSONString(String raw) {
        Dict rep = new Dict();
        JSONObject json = JSON.parseObject(raw);
        for (String name : json.keySet()) {
            rep.addAttr(name, json.get(name));
        }
        return rep;
    }

}
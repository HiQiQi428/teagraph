package org.luncert.teagraph.cypher.astResolver;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

public class BeanBuilder {

    public static class DynamicBean {
        
        private Object bean;      // 生成的类
        private BeanMap beanMap;    // 存放属性名称和类型

        public void setAttribute(String name, Object value) {
            beanMap.put(name, value);
        }

        public Object getAttribute(String name) {
            return beanMap.get(name);
        }

    }

    private DynamicBean dynamicBean = new DynamicBean();
    private Map<String, Object> attrs = new HashMap<>();
    private boolean used = false;
    // private Map

    public BeanBuilder setAttribute(String name, Object value) {
        attrs.put(name, value);
        return this;
    }

    public DynamicBean buildBean() {
        assert(used == false);
        BeanGenerator generator = new BeanGenerator();
        for (Map.Entry<String, Object> entry : attrs.entrySet())
            generator.addProperty(entry.getKey(), entry.getValue().getClass());
            dynamicBean.bean = generator.create();
        dynamicBean.beanMap = BeanMap.create(dynamicBean.bean);
        for (Map.Entry<String, Object> entry : attrs.entrySet())
            dynamicBean.beanMap.put(entry.getKey(), entry.getValue());
        used = true;
        return dynamicBean;
    }

}
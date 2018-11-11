package org.luncert.teagraph.cypher.astResolver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.teagraph.cypher.astResolver.BeanBuilder.DynamicBean;

@RunWith(JUnit4.class)
public class TestBeanBuilder {

    @Test
    public void test() {
        DynamicBean bean = new BeanBuilder()
            .setAttribute("name", "Tom")
            .setAttribute("age", 3)
            .setAttribute("school", "UESTC")
            .buildBean();
        System.out.println(bean.getAttribute("name"));
        System.out.println(bean.getAttribute("age"));
        System.out.println(bean.getAttribute("school"));
    }

}
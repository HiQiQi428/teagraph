package org.luncert.tkgdb.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestResponse {

    @Test
    public void test() {
        Dict dict = new Dict();
        dict.addAttr("taskId", 1);
        dict.addAttr("groupId", 1);
        dict.addAttr("result", null);
        String json = dict.toJSONString();
        System.out.println(json);
        System.out.println(Dict.fromJSONString(json).getAttr("groupId", Integer.class));
    }

}
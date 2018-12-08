package org.luncert.tkglb;

import com.alibaba.fastjson.JSON;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestJSON {

    static class Student {
        int id;
        String name;
        Student() {}
        public void setId(int id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public int getId() { return id; }
        public String getName() { return name; }
        Student(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public String toString() {
            return "Student(id = " + id + ", name = " + name + ")";
        }
    }

    @Test
    public void test() {
        Student s = new Student(10, "Luncert");
        String json = JSON.toJSONString(s);
        System.out.println(json);
        Student copy = JSON.parseObject(json, Student.class);
        System.out.println(copy);        
    }

}
package org.luncert.tkglb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ATest {

    static class A {
        void say() {
            System.out.println("a");
        }
    }

    static class B extends A {
        void say() {
            System.out.println("b");
        }
    }
    
    @Test
    public void test() {
        A a = new B();
        a.say();
    }

}
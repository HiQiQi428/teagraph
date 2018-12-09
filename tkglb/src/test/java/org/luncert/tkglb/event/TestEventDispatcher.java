package org.luncert.tkglb.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestEventDispatcher {

    interface EventHandler {
        
        Object peopleIn(String name);
        Object peopleOut(String name);

    }

    @Test
    public void test() {
        EventDispatcher dispatcher = new EventDispatcher();
        EventHandler handler = (EventHandler) dispatcher.addEventSource(EventHandler.class);
        dispatcher.registerListener("peopleIn", (params) -> {
            System.out.println("people in: " + params.get("name"));
            return "test return";
        });
        Object ret = handler.peopleIn("Luncert");
        System.out.println(ret);
    }

}
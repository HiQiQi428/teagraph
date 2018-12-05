package org.luncert.tkglb.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestEventDispatcher {

    interface EventHandler {
        
        @EventSource
        public void peopleIn(String name);

        @EventSource
        public void peopleOut(String name);

    }

    @Test
    public void test() {
        EventDispatcher dispatcher = new EventDispatcher();
        EventHandler handler = (EventHandler) dispatcher.addEventSource(EventHandler.class);
        dispatcher.registerListener("peopleIn", (args) -> {
            System.out.println("people in: " + args[0]);
            return null;
        });
        handler.peopleIn("Luncert");
    }

}
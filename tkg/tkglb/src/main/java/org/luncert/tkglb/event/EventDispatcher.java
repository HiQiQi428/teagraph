package org.luncert.tkglb.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class EventDispatcher {

    private class EventInterceptor implements MethodInterceptor {

        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            EventSource eventSource = method.getAnnotation(EventSource.class);
            if (eventSource != null) {
                String eventName = eventSource.name();
                if (eventName == null || eventName.length() == 0)
                    eventName = method.getName();
                return triggerEvent(eventName, args);
            }
			else return null;
		}

    }

    private EventInterceptor eventInterceptor = new EventInterceptor();
    private Map<String, Function<Object[], Object>> handlers = new HashMap<>();

    public <E> E addEventSource(Class<E> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{ clazz });
        enhancer.setCallback(eventInterceptor);
        return clazz.cast(enhancer.create());
    }

    private Object triggerEvent(String eventName, Object[] args) {
        Function<Object[], Object> handler = handlers.get(eventName);
        if (handler != null)
            return handler.apply(args);
        else return null;
    }

    public void registerListener(String eventName, Function<Object[], Object> handler) {
        handlers.put(eventName, handler);
    }

}
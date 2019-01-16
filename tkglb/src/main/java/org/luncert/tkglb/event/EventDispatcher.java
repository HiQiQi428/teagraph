package org.luncert.tkglb.event;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 没有用到
 */
public class EventDispatcher {

    public static class Params {

        Parameter[] params;
        Object[] args;
        private Params(Method method, Object[] args) {
            params = method.getParameters();
            this.args = args;
        }

        /**
         * 根据参数位置获取参数
         * @param i
         * @return
         */
        public Object get(int i) {
            if (i < 0 || i >= args.length)
                throw new IndexOutOfBoundsException("index: " + i);
            return args[i];
        }

        /**
         * 获取第一个类型匹配的参数
         * @param clazz
         * @return
         */
        public <T> T get(Class<T> clazz) {
            for (int i = 0; i < params.length; i++) {
                if (params[i].getType().equals(clazz))
                    return clazz.cast(params[i]);
            }
            return null;
        }

        /**
         * 不稳定的方法,有的方法可能因为编译方式没有形参名,而变成这种: arg0, arg1, arg2 ...
         * @param name
         * @return
         */
        public Object get(String name) {
            for (int i = 0; i < params.length; i++) {
                if (params[i].getName().equals(name))
                    return args[i];
            }
            return null;
        }
    }

    private class EventInterceptor implements MethodInterceptor {

        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            return triggerEvent(method.getName(), new Params(method, args));
		}

    }

    private EventInterceptor eventInterceptor = new EventInterceptor();
    private Map<String, Function<Params, Object>> handlers = new HashMap<>();

    /**
     * 添加接口作为事件源
     * @param clazz
     * @return
     */
    public <E> E addEventSource(Class<E> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{ clazz });
        enhancer.setCallback(eventInterceptor);
        return clazz.cast(enhancer.create());
    }

    private Object triggerEvent(String eventName, Params params) {
        Function<Params, Object> handler = handlers.get(eventName);
        if (handler != null)
            return handler.apply(params);
        else return null;
    }

    public void registerListener(String eventName, Function<Params, Object> handler) {
        handlers.put(eventName, handler);
    }

}
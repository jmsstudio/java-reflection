package br.com.jmsstudio.processor.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodHandler {
    private final Object instance;
    private final Method method;

    public MethodHandler(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    public Object invoke() {
        try {
            return method.invoke(this.instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Error invoking method. ", e.getTargetException());
        }
    }
}

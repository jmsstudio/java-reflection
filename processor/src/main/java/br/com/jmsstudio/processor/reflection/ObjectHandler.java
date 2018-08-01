package br.com.jmsstudio.processor.reflection;

import java.lang.reflect.Method;

public class ObjectHandler {
    private Object instance;

    public ObjectHandler(Object instance) {
        this.instance = instance;
    }

    public MethodHandler getMethod(String methodName) {
        final Method method;
        try {
            method = this.instance.getClass().getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return new MethodHandler(this.instance, method);
    }
}

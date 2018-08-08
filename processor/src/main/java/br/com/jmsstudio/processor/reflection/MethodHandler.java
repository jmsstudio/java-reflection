package br.com.jmsstudio.processor.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MethodHandler {
    private final Object instance;
    private final Method method;
    private List<Object> params;

    public MethodHandler(Object instance, Method method, List<Object> params) {
        this.instance = instance;
        this.method = method;
        this.params = params;
    }

    public Object invoke() {
        try {
            return method.invoke(this.instance, this.params.toArray());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Error invoking method. ", e.getTargetException());
        }
    }
}

package br.com.jmsstudio.processor.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiFunction;

public class MethodHandler {
    private final Object instance;
    private final Method method;
    private List<Object> params;
    private BiFunction<Method, InvocationTargetException, Object> exceptionHandling;

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
            if (exceptionHandling != null) {
                return exceptionHandling.apply(method, e);
            } else {
                throw new RuntimeException("Error invoking method. ", e.getTargetException());
            }
        }
    }

    public MethodHandler withExceptionHandling(BiFunction<Method, InvocationTargetException, Object> exceptionHandling) {
        this.exceptionHandling = exceptionHandling;
        return this;
    }
}

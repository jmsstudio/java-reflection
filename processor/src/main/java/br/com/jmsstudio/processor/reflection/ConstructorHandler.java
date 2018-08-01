package br.com.jmsstudio.processor.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorHandler {
    private Constructor<?> constructor;

    public ConstructorHandler(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    public Object invoke() {
        try {
            return this.constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Error invoking constructor. ", e.getTargetException());
        }
    }
}

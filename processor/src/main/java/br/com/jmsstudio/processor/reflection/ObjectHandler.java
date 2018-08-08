package br.com.jmsstudio.processor.reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ObjectHandler {
    private Object instance;

    public ObjectHandler(Object instance) {
        this.instance = instance;
    }

    public MethodHandler getMethod(String methodName, Map<String, Object> parameters) {
        final Method method = Stream.of(this.instance.getClass().getDeclaredMethods())
                .filter(m -> m.getName().equals(methodName)
                        && m.getParameterCount() == parameters.values().size()
                        && Stream.of(m.getParameters())
                                .allMatch(p ->
                                        (!p.isNamePresent() || parameters.keySet().contains(p.getName()))
                                            && parameters.get(p.getName()).getClass().equals(p.getType())
                                )
                )
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Method not found"));

        List<Object> params = new ArrayList<>();

        Stream.of(method.getParameters()).forEach(p -> params.add(parameters.get(p.getName())));

        return new MethodHandler(this.instance, method, params);
    }
}

package br.com.jmsstudio.processor.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class IoCContainer {

    private Map<Class<?>, Class<?>> typeMap = new HashMap<>();

    public Object getInstance(Class<?> sourceClass) {
        Object instance = null;

        Class<?> destinyClass = typeMap.get(sourceClass);

        if (destinyClass != null) {

            instance = getInstance(destinyClass);

        } else {

            final Stream<Constructor<?>> constructors = Stream.of(sourceClass.getDeclaredConstructors());

            final Optional<Constructor<?>> defaultConstructor = constructors.filter(c -> c.getParameterCount() == 0).findFirst();

            try {
                if (defaultConstructor.isPresent()) {
                    instance = defaultConstructor.get().newInstance();
                } else {
                    final Constructor<?> constructor = sourceClass.getDeclaredConstructors()[0];

                    final ArrayList<Object> parameters = new ArrayList<>();

                    for (Parameter parameter : constructor.getParameters()) {
                        final Class<?> parameterType = parameter.getType();
                        parameters.add(getInstance(parameterType));
                    }

                    instance = constructor.newInstance(parameters.toArray());

                }

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Error when instantiating class", e);
            }
        }

        return instance;
    }

    public <T> void register(Class<T> sourceClass, Class<? extends T> destinyClass) {
        this.typeMap.put(sourceClass, destinyClass);
    }
}

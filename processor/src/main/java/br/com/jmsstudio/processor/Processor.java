package br.com.jmsstudio.processor;

import br.com.jmsstudio.processor.converter.XmlConverter;
import br.com.jmsstudio.processor.ioc.IoCContainer;
import br.com.jmsstudio.processor.protocol.Request;
import br.com.jmsstudio.processor.reflection.ObjectHandler;
import br.com.jmsstudio.processor.reflection.ReflectionUtils;

import java.util.Map;

public class Processor {

    private String basePackage;
    private final IoCContainer ioCContainer;

    public Processor(final String basePackage) {
        String packagePath = basePackage;
        if (!basePackage.endsWith(".")) {
            packagePath = basePackage + ".";
        }
        this.basePackage = packagePath;
        this.ioCContainer = new IoCContainer();
    }

    public Object execute(final String url) {
        final Request request = new Request(url);
        final String controllerName = request.getControllerName();
        final String methodName = request.getMethodName();
        final Map<String, Object> parameters = request.getParameters();

        final Class<?> targetClass = new ReflectionUtils().getClass(this.basePackage + controllerName);
        final Object controllerInstance = this.ioCContainer.getInstance(targetClass);

        Object instance = new ObjectHandler(controllerInstance)
                .getMethod(methodName, parameters)
                .withExceptionHandling((method, exception) -> {
                    System.out.println("Error when executing method " + method.getName() + " from class " + method.getDeclaringClass().getName());
                    throw new RuntimeException("Exception: ", exception);
                })
                .invoke();

        instance = new XmlConverter().convert(instance);

        return instance;
    }

    public <T> void register(Class<T> sourceClass, Class<? extends T> destinyClass) {
        this.ioCContainer.register(sourceClass, destinyClass);
    }
}

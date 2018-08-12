package br.com.jmsstudio.processor;

import br.com.jmsstudio.processor.protocol.Request;
import br.com.jmsstudio.processor.reflection.ReflectionUtils;

import java.util.Map;

public class Processor {

    private String basePackage;

    public Processor(final String basePackage) {
        String packagePath = basePackage;
        if (!basePackage.endsWith(".")) {
            packagePath = basePackage + ".";
        }
        this.basePackage = packagePath;
    }

    public Object execute(final String url) {
        final Request request = new Request(url);
        final String controllerName = request.getControllerName();
        final String methodName = request.getMethodName();
        final Map<String, Object> parameters = request.getParameters();

        return new ReflectionUtils()
                .getClass(this.basePackage + controllerName)
                .createInstance()
                .getMethod(methodName, parameters)
                .withExceptionHandling((method, exception) -> {
                    System.out.println("Error when executing method " + method.getName() + " from class " + method.getDeclaringClass().getName());
                    throw new RuntimeException("Exception: ", exception);
                })
                .invoke();
    }

}

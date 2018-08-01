package br.com.jmsstudio.processor.reflection;

public class ReflectionUtils {

    public ClassHandler getClass(String classFullyQualifiedName) {
        try {
            return new ClassHandler(Class.forName(classFullyQualifiedName));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

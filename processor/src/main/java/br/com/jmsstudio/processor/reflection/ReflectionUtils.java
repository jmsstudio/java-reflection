package br.com.jmsstudio.processor.reflection;

public class ReflectionUtils {

    public ClassHandler getClassHandler(String classFullyQualifiedName) {
        return new ClassHandler(this.getClass(classFullyQualifiedName));
    }

    public Class<?> getClass(String classFullyQualifiedName) {
        try {
            return Class.forName(classFullyQualifiedName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

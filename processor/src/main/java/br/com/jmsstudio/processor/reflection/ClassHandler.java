package br.com.jmsstudio.processor.reflection;

public class ClassHandler {
    private Class<?> classInstance;

    public ClassHandler(Class<?> classInstance) {
        this.classInstance = classInstance;
    }

    public ConstructorHandler getDefaultConstructor() {
        try {
            return new ConstructorHandler(this.classInstance.getConstructor());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectHandler createInstance() {
        return new ObjectHandler(getDefaultConstructor().invoke());
    }
}

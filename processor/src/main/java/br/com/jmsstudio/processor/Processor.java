package br.com.jmsstudio.processor;

import java.util.Arrays;
import java.util.List;

public class Processor {

    private String basePackage;

    public Processor(String basePackage) {
        this.basePackage = basePackage;
    }

    public Object execute(String url) {
        final String CONTROLLER_SUFIX = "Controller";

        String urlToBeProcessed = url;
        if (urlToBeProcessed.startsWith("/")) {
            urlToBeProcessed = urlToBeProcessed.replaceFirst("/", "");
        }

        final List<String> urlParts = Arrays.asList(urlToBeProcessed.split("/"));

        final String className = urlParts.stream().findFirst().map(String::toLowerCase).map(s -> {
            final String firstLetter = String.valueOf(s.charAt(0));
            return s.replaceFirst(firstLetter, firstLetter.toUpperCase());
        }).get() + CONTROLLER_SUFIX;

        Object instance = null;

        try {
            final Class<?> controllerClass = Class.forName(this.basePackage + "." + className);
            instance = controllerClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


        return instance;
    }

}

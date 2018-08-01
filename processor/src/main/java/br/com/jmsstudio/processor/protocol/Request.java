package br.com.jmsstudio.processor.protocol;

import java.util.Arrays;
import java.util.List;

public class Request {

    private String controllerName;
    private String methodName;

    public Request(String url) {
		final String CONTROLLER_SUFIX = "Controller";

		String urlToBeProcessed = url;
		if (urlToBeProcessed.startsWith("/")) {
			urlToBeProcessed = urlToBeProcessed.replaceFirst("/", "");
		}

		final List<String> urlParts = Arrays.asList(urlToBeProcessed.split("/"));

		this.controllerName = urlParts.stream().findFirst().map(String::toLowerCase).map(s -> {
			final String firstLetter = String.valueOf(s.charAt(0));
			return s.replaceFirst(firstLetter, firstLetter.toUpperCase());
		}).get() + CONTROLLER_SUFIX;

		this.methodName = urlParts.size() > 1 ? urlParts.get(urlParts.size() - 1) : "";

	}

    public String getControllerName() {
        return controllerName;
    }

    public String getMethodName() {
        return methodName;
    }
}

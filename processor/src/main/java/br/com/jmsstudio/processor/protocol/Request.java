package br.com.jmsstudio.processor.protocol;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Request {

	private String controllerName;
    private String methodName;
	private final Map<String, Object> parameters;

    public Request(String url) {
		final String CONTROLLER_SUFIX = "Controller";

		String urlToBeProcessed = url;
		if (urlToBeProcessed.startsWith("/")) {
			urlToBeProcessed = urlToBeProcessed.replaceFirst("/", "");
		}

		final String[] urlAndParams = urlToBeProcessed.split("\\?");

		final String processedUrl = urlAndParams[0];
		final String params = urlAndParams.length > 1 ? urlAndParams[1] : "";

		final List<String> urlParts = Arrays.asList(processedUrl.split("/"));

		this.controllerName = urlParts.stream().findFirst().map(String::toLowerCase).map(s -> {
			final String firstLetter = String.valueOf(s.charAt(0));
			return s.replaceFirst(firstLetter, firstLetter.toUpperCase());
		}).get() + CONTROLLER_SUFIX;

		this.methodName = urlParts.size() > 1 ? urlParts.get(urlParts.size() - 1) : "";
		this.parameters = new QueryParamsBuilder().withParams(params).build();

	}

    public String getControllerName() {
        return controllerName;
    }

    public String getMethodName() {
        return methodName;
    }

	public Map<String, Object> getParameters() {
		return parameters;
	}
}

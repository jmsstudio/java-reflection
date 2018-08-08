package br.com.jmsstudio.processor.protocol;

import java.util.HashMap;
import java.util.Map;

public class QueryParamsBuilder {
	private Map<String, Object> queryParams = new HashMap<>();

	public QueryParamsBuilder withParams(String stringQueryParams) {
		if (stringQueryParams != null && stringQueryParams.trim().length() > 0) {
			String[] stringParams = stringQueryParams.split("&");

			for (String stringParam : stringParams) {
				String[] keyAndValue = stringParam.split("=");

				String key = keyAndValue[0];
				Object value = keyAndValue[1];

				queryParams.put(key, value);
			}
		}
		
		return this;
	}
	
	public Map<String, Object> build() {
		return this.queryParams;
	}

}

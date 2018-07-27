package br.com.jmsstudio.processor.protocol;

import java.util.HashMap;
import java.util.Map;

public class QueryParamsBuilder {
	private Map<String, Object> queryParams = new HashMap<>();

	public QueryParamsBuilder withParams(String stringQueryParams) {
		String[] stringParams = stringQueryParams.split("&");
		
		for (String stringParam : stringParams) {
			String[] chaveEValor = stringParam.split("=");
			
			String key = chaveEValor[0];
			Object value = chaveEValor[1];
			
			queryParams.put(key, value);
		}
		
		return this;
	}
	
	public Map<String, Object> build() {
		return this.queryParams;
	}

}

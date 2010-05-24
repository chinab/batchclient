package com.vicutu.bw.http.factory;

import java.util.List;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class AbstractHttpClientFactory implements HttpClientFactory {

	protected List<HttpRequestInterceptor> httpRequestInterceptors;

	protected List<HttpResponseInterceptor> httpResponseInterceptors;

	public void setHttpRequestInterceptors(List<HttpRequestInterceptor> httpRequestInterceptors) {
		this.httpRequestInterceptors = httpRequestInterceptors;
	}

	public void setHttpResponseInterceptors(List<HttpResponseInterceptor> httpResponseInterceptors) {
		this.httpResponseInterceptors = httpResponseInterceptors;
	}

	protected abstract DefaultHttpClient createInner();

	@Override
	public HttpClient createHttpClientInstance() {
		DefaultHttpClient httpClient = this.createInner();
		if (httpRequestInterceptors != null) {
			for (int i = 0; i < httpRequestInterceptors.size(); i++) {
				httpClient.addRequestInterceptor(httpRequestInterceptors.get(i), i);
			}
		}
		if (httpResponseInterceptors != null) {
			for (int i = 0; i < httpResponseInterceptors.size(); i++) {
				httpClient.addResponseInterceptor(httpResponseInterceptors.get(i), i);
			}
		}
		return httpClient;
	}
}

package com.vicutu.http.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import com.vicutu.commons.exception.BaseRuntimeException;

public abstract class AbstractHttpClientFactory implements HttpClientFactory {

	protected List<HttpRequestInterceptor> httpRequestInterceptors;

	protected List<HttpResponseInterceptor> httpResponseInterceptors;

	protected Map<Integer, String> schemes;

	public void setSchemes(Map<Integer, String> schemes) {
		this.schemes = schemes;
	}

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

	protected void initScheme(SchemeRegistry schemeRegistry) {
		if (schemes != null && !schemes.isEmpty()) {
			Set<Entry<Integer, String>> entrySet = schemes.entrySet();
			for (Entry<Integer, String> entry : entrySet) {
				String protocol = entry.getValue();
				int port = entry.getKey().intValue();
				if ("https".equalsIgnoreCase(protocol)) {
					schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), port));
				} else if ("http".equalsIgnoreCase(protocol)) {
					schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), port));
				} else {
					throw new BaseRuntimeException("unknown protocol : " + protocol);
				}
			}
		} else {
			schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
		}
	}
}

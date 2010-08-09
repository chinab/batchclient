package com.vicutu.bw.http.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

import com.vicutu.bw.http.params.HttpParamConfiguration;
import com.vicutu.bw.http.params.HttpParamUtils;
import com.vicutu.commons.exception.BaseRuntimeException;

public abstract class AbstractHttpClientFactory implements HttpClientFactory {

	private List<HttpRequestInterceptor> httpRequestInterceptors;

	private List<HttpResponseInterceptor> httpResponseInterceptors;

	private Map<Integer, String> schemes;

	private List<DefaultHttpClient> httpClients = new ArrayList<DefaultHttpClient>();

	private HttpParamConfiguration httpParamConfiguration;

	public void setHttpParamConfiguration(HttpParamConfiguration httpParamConfiguration) {
		this.httpParamConfiguration = httpParamConfiguration;
	}

	public void setSchemes(Map<Integer, String> schemes) {
		this.schemes = schemes;
	}

	public void setHttpRequestInterceptors(List<HttpRequestInterceptor> httpRequestInterceptors) {
		this.httpRequestInterceptors = httpRequestInterceptors;
	}

	public void setHttpResponseInterceptors(List<HttpResponseInterceptor> httpResponseInterceptors) {
		this.httpResponseInterceptors = httpResponseInterceptors;
	}

	protected abstract ClientConnectionManager getClientConnectionManager(HttpParams params,SchemeRegistry schemeRegistry);

	@Override
	public HttpClient createHttpClientInstance() {
		SchemeRegistry schemeRegistry = initScheme(schemes);
		HttpParams params = httpParamConfiguration != null ? httpParamConfiguration.getHttpParam() : HttpParamUtils
				.getDefaultHttpParam();
		ClientConnectionManager clientConnectionManager = getClientConnectionManager(params,schemeRegistry);

		DefaultHttpClient httpClient = new DefaultHttpClient(clientConnectionManager, params);
		initInterceptor(httpClient, httpRequestInterceptors, httpResponseInterceptors);
		httpClients.add(httpClient);
		return httpClient;
	}

	protected void initInterceptor(DefaultHttpClient httpClient, List<HttpRequestInterceptor> httpRequestInterceptors,
			List<HttpResponseInterceptor> httpResponseInterceptors) {
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
	}

	protected SchemeRegistry initScheme(Map<Integer, String> schemes) {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
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
		return schemeRegistry;
	}

	public void init() throws Throwable {
	}

	public void cleanup() throws Throwable {
		for (DefaultHttpClient httpClient : httpClients) {
			httpClient.getConnectionManager().shutdown();
		}
	}
}

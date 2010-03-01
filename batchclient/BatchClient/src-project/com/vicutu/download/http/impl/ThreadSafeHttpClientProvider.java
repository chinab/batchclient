package com.vicutu.download.http.impl;

import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.params.CookieSpecParamBean;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import com.vicutu.download.descriptor.ProxyDescriptor;
import com.vicutu.download.http.HttpClientProvider;
import com.vicutu.download.login.Loginer;

public class ThreadSafeHttpClientProvider implements HttpClientProvider {

	private ProxyDescriptor proxyDescriptor;

	private DefaultHttpClient httpClient;

	private Loginer loginer;

	public void setLoginer(Loginer loginer) {
		this.loginer = loginer;
	}

	public void setProxyDescriptor(ProxyDescriptor proxyDescriptor) {
		this.proxyDescriptor = proxyDescriptor;
	}

	public void initialize() {
		HttpParams params = new BasicHttpParams();
		ConnManagerParams.setMaxTotalConnections(params, 100);
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

		// Create and initialize scheme registry
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

		// Create an HttpClient with the ThreadSafeClientConnManager.
		// This connection manager must be used if more than one thread will
		// be using the HttpClient.

		CookieSpecParamBean cookieParams = new CookieSpecParamBean(params);
		cookieParams.setSingleHeader(true);

		ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
		httpClient = new DefaultHttpClient(cm, params);

		if (proxyDescriptor != null && proxyDescriptor.isUseProxy()) {
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(proxyDescriptor.getProxyHost(), proxyDescriptor.getPorxyPort()),
					new UsernamePasswordCredentials(proxyDescriptor.getProxyUserName(), proxyDescriptor
							.getProxyPassword()));
			HttpHost proxyHttpHost = new HttpHost(proxyDescriptor.getProxyHost(), proxyDescriptor.getPorxyPort());
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHttpHost);
		}

		if (loginer != null) {
			httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
			loginer.login(httpClient);
		}
	}

	@Override
	public HttpClient getHttpClient() {
		return httpClient;
	}
}
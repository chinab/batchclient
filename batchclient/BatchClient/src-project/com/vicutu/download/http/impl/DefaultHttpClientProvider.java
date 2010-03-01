package com.vicutu.download.http.impl;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import com.vicutu.download.descriptor.ProxyDescriptor;
import com.vicutu.download.http.HttpClientProvider;
import com.vicutu.download.login.Loginer;

public class DefaultHttpClientProvider implements HttpClientProvider {

	private ProxyDescriptor proxyDescriptor;

	private Loginer loginer;

	public void setLoginer(Loginer loginer) {
		this.loginer = loginer;
	}

	public void setProxyDescriptor(ProxyDescriptor proxyDescriptor) {
		this.proxyDescriptor = proxyDescriptor;
	}

	public void initialize() {
	}

	public HttpClient getHttpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		if (proxyDescriptor != null && proxyDescriptor.isUseProxy()) {
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(proxyDescriptor.getProxyHost(), proxyDescriptor.getPorxyPort()),
					new UsernamePasswordCredentials(proxyDescriptor.getProxyUserName(), proxyDescriptor
							.getProxyPassword()));
			HttpHost proxyHttpHost = new HttpHost(proxyDescriptor.getProxyHost(), proxyDescriptor.getPorxyPort());
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHttpHost);
		}

		if (loginer != null) {
			loginer.login(httpClient);
		}

		return httpClient;
	}
}
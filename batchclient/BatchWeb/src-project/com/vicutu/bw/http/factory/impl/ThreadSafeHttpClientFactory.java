package com.vicutu.bw.http.factory.impl;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import com.vicutu.bw.http.factory.AbstractHttpClientFactory;
import com.vicutu.bw.http.factory.HttpClientFactory;

public class ThreadSafeHttpClientFactory extends AbstractHttpClientFactory implements HttpClientFactory {

	private int maxTotalConnections = -1;

	private int maxConnectionsPerRoute = -1;
	
	private long timeout = -1;

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public void setMaxTotalConnections(int maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

	public void setMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
		this.maxConnectionsPerRoute = maxConnectionsPerRoute;
	}

	@Override
	protected DefaultHttpClient createInner() {
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		if (maxTotalConnections > 0) {
			ConnManagerParams.setMaxTotalConnections(params, maxTotalConnections);
		}
		if (maxConnectionsPerRoute > 0) {
			ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRouteBean(maxConnectionsPerRoute));
		}
		if(timeout>0){
			ConnManagerParams.setTimeout(params, timeout);
		}
		

		// Create and initialize scheme registry 
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", PlainSocketFactory.getSocketFactory(), 443));
		// Create an HttpClient with the ThreadSafeClientConnManager.
		// This connection manager must be used if more than one thread will
		// be using the HttpClient.
		ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
		DefaultHttpClient httpClient = new DefaultHttpClient(cm, params);
		return httpClient;
	}
}

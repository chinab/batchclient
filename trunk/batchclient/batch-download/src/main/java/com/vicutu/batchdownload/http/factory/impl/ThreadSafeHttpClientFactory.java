package com.vicutu.batchdownload.http.factory.impl;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

import com.vicutu.batchdownload.http.factory.AbstractHttpClientFactory;
import com.vicutu.batchdownload.http.factory.HttpClientFactory;

public class ThreadSafeHttpClientFactory extends AbstractHttpClientFactory implements HttpClientFactory {

	@Override
	protected ClientConnectionManager getClientConnectionManager(SchemeRegistry schemeRegistry,
			int maxConnectionsPerRoute, int maxTotalConnections) {
		ThreadSafeClientConnManager manager = null;
		if (schemeRegistry != null) {
			manager = new ThreadSafeClientConnManager(schemeRegistry);
		} else {
			manager = new ThreadSafeClientConnManager();
		}
		if (maxConnectionsPerRoute > 0) {
			manager.setDefaultMaxPerRoute(maxConnectionsPerRoute);
		}
		if (maxTotalConnections > 0) {
			manager.setMaxTotal(maxTotalConnections);
		}
		return manager;
	}
}

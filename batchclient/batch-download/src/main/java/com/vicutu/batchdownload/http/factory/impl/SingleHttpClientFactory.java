package com.vicutu.batchdownload.http.factory.impl;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.SingleClientConnManager;

import com.vicutu.batchdownload.http.factory.AbstractHttpClientFactory;
import com.vicutu.batchdownload.http.factory.HttpClientFactory;

public class SingleHttpClientFactory extends AbstractHttpClientFactory implements HttpClientFactory {

	@Override
	protected ClientConnectionManager getClientConnectionManager(SchemeRegistry schemeRegistry,
			int maxConnectionsPerRoute, int maxTotalConnections) {
		if (schemeRegistry != null) {
			return new SingleClientConnManager(schemeRegistry);
		} else {
			return new SingleClientConnManager();
		}
	}
}

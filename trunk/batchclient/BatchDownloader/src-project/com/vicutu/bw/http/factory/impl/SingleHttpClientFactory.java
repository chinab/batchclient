package com.vicutu.bw.http.factory.impl;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.HttpParams;

import com.vicutu.bw.http.factory.AbstractHttpClientFactory;
import com.vicutu.bw.http.factory.HttpClientFactory;

public class SingleHttpClientFactory extends AbstractHttpClientFactory implements HttpClientFactory {

	@Override
	protected ClientConnectionManager getClientConnectionManager(HttpParams params, SchemeRegistry schemeRegistry) {
		return new SingleClientConnManager(params, schemeRegistry);
	}

}

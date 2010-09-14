package com.vicutu.bw.http.factory;

import org.apache.http.client.HttpClient;

public interface HttpClientFactory {
	HttpClient createHttpClientInstance();
}

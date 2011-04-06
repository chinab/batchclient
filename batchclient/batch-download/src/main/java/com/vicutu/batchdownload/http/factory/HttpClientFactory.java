package com.vicutu.batchdownload.http.factory;

import org.apache.http.client.HttpClient;

public interface HttpClientFactory {
	HttpClient createHttpClientInstance();
}

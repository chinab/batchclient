package com.vicutu.download.http;

import org.apache.http.client.HttpClient;

public interface HttpClientProvider
{
	HttpClient getHttpClient();
}

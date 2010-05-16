package com.vicutu.bw.service;

import org.apache.http.client.HttpClient;

public interface HttpClientService {
	HttpClient getHttpClient(String accessDetailName,boolean threadSafe) throws Exception;
}

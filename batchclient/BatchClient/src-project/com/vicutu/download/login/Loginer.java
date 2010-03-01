package com.vicutu.download.login;

import org.apache.http.client.HttpClient;

public interface Loginer
{
	boolean login(HttpClient httpClient);
}

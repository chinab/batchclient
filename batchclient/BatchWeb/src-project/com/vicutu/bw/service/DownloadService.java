package com.vicutu.bw.service;

import java.io.OutputStream;

import org.apache.http.client.HttpClient;

public interface DownloadService {
	String downloadHtml(HttpClient httpClient, String linkUrl, String authorizationUsername,
			String authorizationPassword) throws Exception;

	long download(HttpClient httpClient, String linkUrl, OutputStream outputStream, String authorizationUsername,
			String authorizationPassword) throws Exception;
}

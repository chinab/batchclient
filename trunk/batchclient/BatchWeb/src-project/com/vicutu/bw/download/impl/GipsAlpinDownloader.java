package com.vicutu.bw.download.impl;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.vicutu.bw.download.AbstractDownloader;
import com.vicutu.bw.download.Downloader;
import com.vicutu.bw.engine.DownloadItem;

@Component
public class GipsAlpinDownloader extends AbstractDownloader implements Downloader {

	@Override
	@Autowired
	@Qualifier("gipsAlpinHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	@Async
	@Override
	public void download(DownloadItem downloadItem){
		super.download(downloadItem);
	}
}

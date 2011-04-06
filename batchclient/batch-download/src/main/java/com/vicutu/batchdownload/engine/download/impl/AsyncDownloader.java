package com.vicutu.batchdownload.engine.download.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.vicutu.batchdownload.engine.DownloadItem;
import com.vicutu.batchdownload.engine.download.AbstractDownloader;
import com.vicutu.batchdownload.engine.download.Downloader;

@Component
public class AsyncDownloader extends AbstractDownloader implements Downloader {

	@Async
	@Override
	public void download(DownloadItem downloadItem) {
		super.download(downloadItem);
	}
}

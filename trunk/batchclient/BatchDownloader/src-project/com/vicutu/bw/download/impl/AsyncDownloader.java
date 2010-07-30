package com.vicutu.bw.download.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.vicutu.bw.download.AbstractDownloader;
import com.vicutu.bw.download.Downloader;
import com.vicutu.bw.engine.DownloadItem;

@Component
public class AsyncDownloader extends AbstractDownloader implements Downloader {

	@Async
	@Override
	public void download(DownloadItem downloadItem) {
		super.download(downloadItem);
	}
}

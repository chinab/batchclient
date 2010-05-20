package com.vicutu.bw.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.bw.download.Downloader;
import com.vicutu.bw.engine.DownloadItem;

@Component
public class AddDownloadItemListener implements ApplicationListener<AddDownloadItemEvent> {

	private Downloader downloader;

	@Autowired
	public void setDownloader(Downloader downloader) {
		this.downloader = downloader;
	}

	@Override
	public void onApplicationEvent(AddDownloadItemEvent event) {
		DownloadItem downloadItem = event.getDownloadItem();
		downloader.download(downloadItem);
	}
}

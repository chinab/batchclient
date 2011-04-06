package com.vicutu.batchdownload.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.batchdownload.engine.download.Downloader;
import com.vicutu.batchdownload.engine.event.AddDownloadItemEvent;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

@Component
public class AddDownloadItemListener implements ApplicationListener<AddDownloadItemEvent> {

	protected final Logger logger = LoggerFactory.getLogger(AddDownloadItemListener.class);

	private Downloader downloader;

	@Autowired
	public void setDownloader(Downloader downloader) {
		this.downloader = downloader;
	}

	@Override
	public void onApplicationEvent(AddDownloadItemEvent event) {
		downloader.download(event.getDownloadItem());
	}
}
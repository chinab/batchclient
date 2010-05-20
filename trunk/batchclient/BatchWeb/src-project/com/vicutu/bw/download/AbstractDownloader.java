package com.vicutu.bw.download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vicutu.bw.service.DownloadService;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

public abstract class AbstractDownloader implements Downloader {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected DownloadService downloadService;

	protected ApplicationContext applicationContext;

	@Autowired
	public void setDownloadService(DownloadService downloadService) {
		this.downloadService = downloadService;
	}

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}

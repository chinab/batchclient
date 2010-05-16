package com.vicutu.bw.engine.impl;

import java.io.File;

import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;

import com.vicutu.bw.engine.Engine;
import com.vicutu.bw.service.DownloadService;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.DownloadDetail;
import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.event.Event;

public class DefaultDownloader implements Engine {

	private final Logger logger = LoggerFactory.getLogger(DefaultDownloader.class);

	private AccessDetail accessDetail;

	private DownloadDetail downloadDetail;

	private DefaultHttpClient httpClient;

	private DownloadService downloadService;

	private ApplicationContext applicationContext;

	public DefaultDownloader(AccessDetail accessDetail, DownloadDetail downloadDetail, DefaultHttpClient httpClient,
			DownloadService downloadService, ApplicationContext applicationContext) {
		this.accessDetail = accessDetail;
		this.downloadDetail = downloadDetail;
		this.httpClient = httpClient;
		this.downloadService = downloadService;
		this.applicationContext = applicationContext;
	}

	@Async
	@Override
	public void run() {
		try {
			File savePath = new File(downloadDetail.getRealPath());
			if (savePath.exists()) {
				if (accessDetail.isReplaceExist()) {
					long fileLength = downloadService.download(httpClient, downloadDetail.getRealUrl(), FileUtils
							.openOutputStream(savePath), accessDetail.getAuthorizationUsername(), accessDetail
							.getAuthorizationPassword());
					downloadDetail.setFileLength(fileLength);
					String lengthInfo = FileUtils.byteCountToDisplaySize(fileLength);
					downloadDetail.setLenghtInfo(lengthInfo);
					if (fileLength > 0) {
						downloadDetail.setLastState(DownloadDetail.LAST_STATE_RELOADED);
						logger.info("reload success : {}\t{}", downloadDetail.getRealUrl(), lengthInfo);
					} else {
						downloadDetail.setLastState(DownloadDetail.LAST_STATE_FAILED);
						logger.info("reload failed : {}", downloadDetail.getRealUrl());
					}
				} else {
					downloadDetail.setLastState(DownloadDetail.LAST_STATE_IGNORED);
					logger.info("ignore exist : {}", downloadDetail.getRealUrl());
				}
			} else {
				long fileLength = downloadService.download(httpClient, downloadDetail.getRealUrl(), FileUtils
						.openOutputStream(savePath), accessDetail.getAuthorizationUsername(), accessDetail
						.getAuthorizationPassword());
				downloadDetail.setFileLength(fileLength);
				String lengthInfo = FileUtils.byteCountToDisplaySize(fileLength);
				downloadDetail.setLenghtInfo(lengthInfo);
				if (fileLength > 0) {
					downloadDetail.setLastState(DownloadDetail.LAST_STATE_LOADED);
					logger.info("load success : {}\t{}", downloadDetail.getRealUrl(), lengthInfo);
				} else {
					downloadDetail.setLastState(DownloadDetail.LAST_STATE_FAILED);
					logger.info("load failed : {}", downloadDetail.getRealUrl());
				}
			}
		} catch (Exception e) {
			downloadDetail.setLastState(DownloadDetail.LAST_STATE_FAILED);
			logger.info("occur error when downloading {}", downloadDetail.getRealUrl(), e);
		} finally {
			Event event = new Event(this.getClass(), EVENT_TYPE_DOWNLOAD_DETAIL, downloadDetail);
			applicationContext.publishEvent(event);
		}
	}
}

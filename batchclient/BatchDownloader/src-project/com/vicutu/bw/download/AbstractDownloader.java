package com.vicutu.bw.download;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vicutu.bw.engine.DownloadItem;
import com.vicutu.bw.event.UpdateDownloadDetailEvent;
import com.vicutu.bw.utils.HttpUtils;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.DownloadDetail;
import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

public abstract class AbstractDownloader implements Downloader {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected ApplicationContext applicationContext;

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void download(DownloadItem downloadItem) {
		DownloadDetail downloadDetail = null;
		OutputStream os = null;
		HttpClient httpClient = null;
		try {
			downloadDetail = downloadItem.getDownloadDetail();
			httpClient = downloadItem.getHttpClient();
			AccessDetail accessDetail = downloadItem.getAccessDetail();
			File savePath = new File(downloadDetail.getRealPath());
			if (savePath.exists()) {
				if (accessDetail.isReplaceExist()) {
					os = FileUtils.openOutputStream(savePath);
					long fileLength = HttpUtils.download(httpClient, downloadDetail.getRealUrl(), os);
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
				os = FileUtils.openOutputStream(savePath);
				long fileLength = HttpUtils.download(httpClient, downloadDetail.getRealUrl(), os);
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
			logger.info("occur error when downloading [{}]", downloadDetail.getRealUrl(), e);
		} finally {
			IOUtils.closeQuietly(os);
			downloadDetail.setUpdateTime(new Date(System.currentTimeMillis()));
			applicationContext.publishEvent(new UpdateDownloadDetailEvent(this, downloadDetail));
		}
	}
}
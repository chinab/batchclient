package com.vicutu.batchdownload.engine.download;

import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.domain.DownloadDetail;
import com.vicutu.batchdownload.engine.DownloadItem;
import com.vicutu.batchdownload.engine.event.UpdateDownloadDetailEvent;
import com.vicutu.batchdownload.engine.io.FileHandler;
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
		HttpClient httpClient = null;
		try {
			downloadDetail = downloadItem.getDownloadDetail();
			httpClient = downloadItem.getHttpClient();
			AccessDetail accessDetail = downloadItem.getAccessDetail();
			FileHandler fileHandler = downloadItem.getFileHandler();
			String folder = downloadDetail.getFolder();
			String fileName = downloadDetail.getFileName();
			if (fileHandler.exists(folder, fileName)) {
				if (accessDetail.isReplaceExist()) {
					long fileLength = download(accessDetail, httpClient, fileHandler, downloadDetail.getRealUrl(),
							folder, fileName);
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
				long fileLength = download(accessDetail, httpClient, fileHandler, downloadDetail.getRealUrl(), folder,
						fileName);
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
			downloadDetail.setUpdateTime(new Date(System.currentTimeMillis()));
			applicationContext.publishEvent(new UpdateDownloadDetailEvent(this, downloadItem.getAccessDetail(),
					downloadDetail));
		}
	}

	private long download(AccessDetail accessDetail, HttpClient httpClient, FileHandler fileHandler, String linkUrl,
			String folder, String fileName) throws Exception {
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet(linkUrl);
		HttpResponse response = httpClient.execute(httpget, localContext);
		return fileHandler.save(accessDetail, response.getEntity().getContent(), folder, fileName);
	}
}
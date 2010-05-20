package com.vicutu.bw.download.impl;

import java.io.File;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.vicutu.bw.download.AbstractDownloader;
import com.vicutu.bw.download.Downloader;
import com.vicutu.bw.engine.DownloadItem;
import com.vicutu.bw.event.UpdateDownloadDetailEvent;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.DownloadDetail;
import com.vicutu.commons.lang.FileUtils;

@Component
public class DefaultDownloader extends AbstractDownloader implements Downloader {

	@Async
	@Override
	public void download(DownloadItem downloadItem) {
		DownloadDetail downloadDetail = null;
		OutputStream os = null;
		DefaultHttpClient httpClient = null;
		try {

			downloadDetail = downloadItem.getDownloadDetail();
			AccessDetail accessDetail = downloadItem.getAccessDetail();
			httpClient = downloadItem.getHttpClient();
			File savePath = new File(downloadDetail.getRealPath());
			if (savePath.exists()) {
				if (accessDetail.isReplaceExist()) {
					os = FileUtils.openOutputStream(savePath);
					long fileLength = downloadService.download(httpClient, downloadDetail.getRealUrl(), os,
							accessDetail.getAuthorizationUsername(), accessDetail.getAuthorizationPassword());
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
				long fileLength = downloadService.download(httpClient, downloadDetail.getRealUrl(), os, accessDetail
						.getAuthorizationUsername(), accessDetail.getAuthorizationPassword());
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
			UpdateDownloadDetailEvent updateDownloadDetailEvent = new UpdateDownloadDetailEvent(this, downloadDetail);
			applicationContext.publishEvent(updateDownloadDetailEvent);
		}
	}
}

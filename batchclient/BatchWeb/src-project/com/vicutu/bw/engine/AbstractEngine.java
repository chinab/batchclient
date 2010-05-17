package com.vicutu.bw.engine;

import java.io.File;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.io.IOUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.vicutu.bw.service.AccessDetailService;
import com.vicutu.bw.service.DownloadService;
import com.vicutu.bw.service.HttpClientService;
import com.vicutu.bw.service.SearchStatusService;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.DownloadDetail;
import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.event.Event;

@Component
public abstract class AbstractEngine implements Engine {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpClientService httpClientService;

	protected AccessDetailService accessDetailService;

	protected DownloadService downloadService;

	protected SearchStatusService searchStatusService;

	protected ApplicationContext applicationContext;

	protected BlockingQueue<DownloadItem> queue;

	private DefaultHttpClient httpClient;

	@Autowired
	public void setSearchStatusService(SearchStatusService searchStatusService) {
		this.searchStatusService = searchStatusService;
	}

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Autowired
	public void setDownloadService(DownloadService downloadService) {
		this.downloadService = downloadService;
	}

	@Autowired
	public void setAccessDetailService(AccessDetailService accessDetailService) {
		this.accessDetailService = accessDetailService;
	}

	@Autowired
	@Qualifier("gipsAlpinHttpClientService")
	public void setHttpClientService(HttpClientService httpClientService) {
		this.httpClientService = httpClientService;
	}

	protected synchronized AccessDetail refresh() throws Exception {
		AccessDetail accessDetail = accessDetailService.findAccessDetailByName(this.getAccessDetailName());
		if (queue == null) {
			queue = new ArrayBlockingQueue<DownloadItem>(accessDetail.getQueueLength());
		}
		if (httpClient == null) {
			httpClient = (DefaultHttpClient) httpClientService.getHttpClient(accessDetail.getName(), accessDetail
					.isSingleHttpClient());
		}
		return accessDetail;
	}

	protected DefaultHttpClient currentHttpClient() {
		return httpClient;
	}

	protected abstract String getAccessDetailName();

	@Async
	@Override
	public void download() {
		DownloadDetail downloadDetail = null;
		OutputStream os = null;
		DefaultHttpClient httpClient = null;
		try {
			DownloadItem downloadItem = queue.take();
			downloadDetail = downloadItem.getDownloadDetail();
			AccessDetail accessDetail = downloadItem.getAccessDetail();
			if (accessDetail.isSingleHttpClient()) {
				httpClient = this.currentHttpClient();
			} else {
				httpClient = (DefaultHttpClient) httpClientService.getHttpClient(accessDetail.getName(), false);
			}
			File savePath = new File(downloadDetail.getRealPath());
			os = FileUtils.openOutputStream(savePath);
			if (savePath.exists()) {
				if (accessDetail.isReplaceExist()) {
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
			Event event = new Event(this.getClass(), EVENT_TYPE_DOWNLOAD_DETAIL, downloadDetail);
			applicationContext.publishEvent(event);
		}
	}
}
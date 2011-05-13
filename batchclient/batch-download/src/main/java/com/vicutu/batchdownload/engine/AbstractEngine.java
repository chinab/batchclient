package com.vicutu.batchdownload.engine;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.domain.DownloadDetail;
import com.vicutu.batchdownload.domain.SearchStatus;
import com.vicutu.batchdownload.engine.event.AddDownloadItemEvent;
import com.vicutu.batchdownload.engine.event.SearchBeginEvent;
import com.vicutu.batchdownload.engine.event.SearchEndEvent;
import com.vicutu.batchdownload.engine.event.UpdateDownloadDetailEvent;
import com.vicutu.batchdownload.engine.event.UpdateSearchStatusEvent;
import com.vicutu.batchdownload.engine.io.FileHandler;
import com.vicutu.batchdownload.service.AccessDetailService;
import com.vicutu.batchdownload.service.SearchStatusService;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

public abstract class AbstractEngine implements Engine {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpClient httpClient;

	protected AtomicInteger counter = new AtomicInteger();

	protected AccessDetailService accessDetailService;

	protected SearchStatusService searchStatusService;

	protected ApplicationContext applicationContext;

	protected FileHandler fileHandler;

	@Autowired
	public void setSearchStatusService(SearchStatusService searchStatusService) {
		this.searchStatusService = searchStatusService;
	}

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Autowired
	public void setAccessDetailService(AccessDetailService accessDetailService) {
		this.accessDetailService = accessDetailService;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}

	@PreDestroy
	public void cleanUp() {
		if (httpClient != null) {
			httpClient.getConnectionManager().shutdown();
		}
	}

	protected void fireDownloadEvent(AccessDetail accessDetail, SearchStatus searchStatus, String url, String folder,
			String fileName) {
		DownloadDetail downloadDetail = new DownloadDetail();
		downloadDetail.setRealUrl(url);
		downloadDetail.setFolder(folder);
		downloadDetail.setFileName(fileName);
		downloadDetail.setUpdateTime(new Date(System.currentTimeMillis()));
		if (StringUtils.endsWithAny(folder, new String[] { "/", "\\" })) {
			downloadDetail.setRealPath((new StringBuilder()).append(folder).append(fileName).toString());
		} else {
			downloadDetail.setRealPath((new StringBuilder()).append(folder).append("/").append(fileName).toString());
		}
		logger.info("DownloadDetail-Url : {}", url);
		publishEvent(new UpdateDownloadDetailEvent(this, accessDetail, downloadDetail));
		DownloadItem downloadItem = new DownloadItem(accessDetail, downloadDetail, searchStatus, httpClient,
				fileHandler);
		publishEvent(new AddDownloadItemEvent(this, downloadItem));
	}

	protected void fireUpdateSearchStatusEvent(SearchStatus searchStatus) {
		searchStatus.setLastSearchTime(new Date(System.currentTimeMillis()));
		publishEvent(new UpdateSearchStatusEvent(this, searchStatus));
	}

	protected void fireSearchBeginEvent() {
		publishEvent(new SearchBeginEvent(this, getAccessDetailName(), new Date(System.currentTimeMillis())));
	}

	protected void fireSearchEndEvent() {
		publishEvent(new SearchEndEvent(this, getAccessDetailName(), new Date(System.currentTimeMillis())));
	}

	protected AccessDetail queryAccessDetail() {
		return accessDetailService.findAccessDetailByName(getAccessDetailName());
	}

	protected void publishEvent(ApplicationEvent event) {
		applicationContext.publishEvent(event);
	}

	protected boolean urlExists(String url) {
		return searchStatusService.urlExists(url);
	}

	protected abstract String getAccessDetailName();
}

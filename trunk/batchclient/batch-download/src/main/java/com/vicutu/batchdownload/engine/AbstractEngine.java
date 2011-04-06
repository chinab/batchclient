package com.vicutu.batchdownload.engine;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PreDestroy;

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

	@PreDestroy
	public void cleanUp() {
		if (httpClient != null) {
			httpClient.getConnectionManager().shutdown();
		}
	}

	protected void fireDownloadEvent(AccessDetail accessDetail, SearchStatus searchStatus, String fileName,
			String realPath, String imageUrl0) {
		DownloadDetail downloadDetail = new DownloadDetail();
		downloadDetail.setRealUrl(imageUrl0);
		downloadDetail.setRealPath(realPath);
		downloadDetail.setFileName(fileName);
		downloadDetail.setUpdateTime(new Date(System.currentTimeMillis()));
		logger.info("DownloadDetail-Url : {}", imageUrl0);
		publishEvent(new UpdateDownloadDetailEvent(this, accessDetail, downloadDetail));
		DownloadItem downloadItem = new DownloadItem(accessDetail, downloadDetail, searchStatus, httpClient);
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

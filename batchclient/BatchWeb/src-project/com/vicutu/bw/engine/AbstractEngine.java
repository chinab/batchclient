package com.vicutu.bw.engine;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vicutu.bw.service.AccessDetailService;
import com.vicutu.bw.service.DownloadService;
import com.vicutu.bw.service.SearchStatusService;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

public abstract class AbstractEngine implements Engine {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpClient httpClient;

	protected AccessDetailService accessDetailService;

	protected DownloadService downloadService;

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
	public void setDownloadService(DownloadService downloadService) {
		this.downloadService = downloadService;
	}

	@Autowired
	public void setAccessDetailService(AccessDetailService accessDetailService) {
		this.accessDetailService = accessDetailService;
	}
	
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	protected abstract String getAccessDetailName();
}
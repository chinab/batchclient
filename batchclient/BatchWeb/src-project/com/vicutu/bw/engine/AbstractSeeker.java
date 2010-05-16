package com.vicutu.bw.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.vicutu.bw.service.AccessDetailService;
import com.vicutu.bw.service.DownloadService;
import com.vicutu.bw.service.HttpClientService;
import com.vicutu.bw.service.SearchStatusService;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

@Component
public abstract class AbstractSeeker implements Engine {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpClientService httpClientService;

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

	@Autowired
	@Qualifier("gipsAlpinHttpClientService")
	public void setHttpClientService(HttpClientService httpClientService) {
		this.httpClientService = httpClientService;
	}

	protected abstract String getAccessDetailName();

}
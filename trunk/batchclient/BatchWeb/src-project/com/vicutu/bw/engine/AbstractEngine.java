package com.vicutu.bw.engine;

import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vicutu.bw.service.AccessDetailService;
import com.vicutu.bw.service.DownloadService;
import com.vicutu.bw.service.HttpClientService;
import com.vicutu.bw.service.SearchStatusService;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

public abstract class AbstractEngine implements Engine {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpClientService httpClientService;

	protected AccessDetailService accessDetailService;

	protected DownloadService downloadService;

	protected SearchStatusService searchStatusService;

	protected ApplicationContext applicationContext;

	protected DefaultHttpClient httpClient;

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

	public void setHttpClientService(HttpClientService httpClientService) {
		this.httpClientService = httpClientService;
	}

	protected synchronized AccessDetail refresh() throws Exception {
		AccessDetail accessDetail = accessDetailService.findAccessDetailByName(this.getAccessDetailName());
		if (httpClient == null) {
			httpClient = (DefaultHttpClient) httpClientService.getHttpClient(accessDetail.getName(), accessDetail
					.isSingleHttpClient());
		}
		return accessDetail;
	}

	protected abstract String getAccessDetailName();
}
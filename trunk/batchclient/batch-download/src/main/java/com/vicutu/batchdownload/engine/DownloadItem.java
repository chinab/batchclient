package com.vicutu.batchdownload.engine;

import org.apache.http.client.HttpClient;

import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.domain.DownloadDetail;
import com.vicutu.batchdownload.domain.SearchStatus;
import com.vicutu.batchdownload.engine.io.FileHandler;

public class DownloadItem {

	private AccessDetail accessDetail;

	private DownloadDetail downloadDetail;

	private SearchStatus searchStatus;

	private HttpClient httpClient;

	private FileHandler fileHandler;

	public DownloadItem(AccessDetail accessDetail, DownloadDetail downloadDetail, SearchStatus searchStatus,
			HttpClient httpClient, FileHandler fileHandler) {
		this.accessDetail = accessDetail;
		this.downloadDetail = downloadDetail;
		this.searchStatus = searchStatus;
		this.httpClient = httpClient;
		this.fileHandler = fileHandler;
	}

	public DownloadItem() {
	}

	public AccessDetail getAccessDetail() {
		return accessDetail;
	}

	public void setAccessDetail(AccessDetail accessDetail) {
		this.accessDetail = accessDetail;
	}

	public DownloadDetail getDownloadDetail() {
		return downloadDetail;
	}

	public void setDownloadDetail(DownloadDetail downloadDetail) {
		this.downloadDetail = downloadDetail;
	}

	public SearchStatus getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(SearchStatus searchStatus) {
		this.searchStatus = searchStatus;
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public FileHandler getFileHandler() {
		return fileHandler;
	}

	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}
}

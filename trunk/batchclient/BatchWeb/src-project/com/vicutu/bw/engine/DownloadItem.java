package com.vicutu.bw.engine;

import org.apache.http.impl.client.DefaultHttpClient;

import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.DownloadDetail;
import com.vicutu.bw.vo.SearchStatus;

public class DownloadItem {

	private AccessDetail accessDetail;

	private DownloadDetail downloadDetail;

	private SearchStatus searchStatus;

	private DefaultHttpClient httpClient;

	public DownloadItem(AccessDetail accessDetail, DownloadDetail downloadDetail, SearchStatus searchStatus,
			DefaultHttpClient httpClient) {
		this.accessDetail = accessDetail;
		this.downloadDetail = downloadDetail;
		this.searchStatus = searchStatus;
		this.httpClient = httpClient;
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

	public DefaultHttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(DefaultHttpClient httpClient) {
		this.httpClient = httpClient;
	}
}

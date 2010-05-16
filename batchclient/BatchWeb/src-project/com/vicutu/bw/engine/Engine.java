package com.vicutu.bw.engine;

import org.apache.http.client.HttpClient;

import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.bw.vo.DownloadDetail;

public interface Engine {

	public static final String EVENT_TYPE_DOWNLOAD_DETAIL = "event_type_download_detail";

	public static final String EVENT_TYPE_SEARCH_STATUS = "event_type_search_status";

	void search();

	void download(AccessDetail accessDetail,DownloadDetail downloadDetail, HttpClient httpClient);
}

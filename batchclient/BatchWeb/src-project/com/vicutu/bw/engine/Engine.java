package com.vicutu.bw.engine;


public interface Engine {

	public static final String EVENT_TYPE_DOWNLOAD_DETAIL = "event_type_download_detail";

	public static final String EVENT_TYPE_SEARCH_STATUS = "event_type_search_status";

	void search();
	
	void download();
}

package com.vicutu.batchdownload.service;

import com.vicutu.batchdownload.domain.SearchStatus;

public interface SearchStatusService {

	void saveOrUpdateSearchStatus(SearchStatus searchStatus);

	boolean urlExists(String url);

	SearchStatus findSearchStatusByUrl(String url);
}

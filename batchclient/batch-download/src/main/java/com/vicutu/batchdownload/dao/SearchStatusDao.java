package com.vicutu.batchdownload.dao;

import com.vicutu.batchdownload.domain.SearchStatus;

public interface SearchStatusDao {

	void saveOrUpdateSearchStatus(SearchStatus searchStatus);

	boolean urlExists(String url);

	SearchStatus findSearchStatusByUrl(String url);
}

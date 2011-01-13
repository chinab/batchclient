package com.vicutu.bw.service;

import com.vicutu.bw.vo.SearchStatus;

public interface SearchStatusService {

	void saveOrUpdateSearchStatus(SearchStatus searchStatus);

	boolean urlExists(String url);

	SearchStatus findSearchStatusByUrl(String url);
}

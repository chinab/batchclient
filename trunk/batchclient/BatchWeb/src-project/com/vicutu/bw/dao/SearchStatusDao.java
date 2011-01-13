package com.vicutu.bw.dao;

import com.vicutu.bw.vo.SearchStatus;

public interface SearchStatusDao {

	void saveOrUpdateSearchStatus(SearchStatus searchStatus);

	boolean urlExists(String url);

	SearchStatus findSearchStatusByUrl(String url);
}

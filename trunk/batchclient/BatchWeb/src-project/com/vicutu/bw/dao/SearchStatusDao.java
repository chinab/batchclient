package com.vicutu.bw.dao;

import com.vicutu.bw.vo.SearchStatus;

public interface SearchStatusDao {

	SearchStatus findSearchStatusByName(String accessName);

	void saveOrUpdateSearchStatus(SearchStatus searchStatus);
}

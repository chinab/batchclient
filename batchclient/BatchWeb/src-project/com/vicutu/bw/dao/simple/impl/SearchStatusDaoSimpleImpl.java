package com.vicutu.bw.dao.simple.impl;

import org.springframework.stereotype.Repository;

import com.vicutu.bw.dao.SearchStatusDao;
import com.vicutu.bw.vo.SearchStatus;

@Repository
public class SearchStatusDaoSimpleImpl implements SearchStatusDao {

	@Override
	public SearchStatus findSearchStatusByUrl(String url) {
		return null;
	}

	@Override
	public void saveOrUpdateSearchStatus(SearchStatus searchStatus) {
	}

	@Override
	public boolean urlExists(String url) {
		return false;
	}
}

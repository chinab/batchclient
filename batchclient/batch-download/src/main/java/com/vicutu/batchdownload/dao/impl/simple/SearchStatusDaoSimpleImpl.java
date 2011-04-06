package com.vicutu.batchdownload.dao.impl.simple;

import org.springframework.stereotype.Repository;

import com.vicutu.batchdownload.dao.SearchStatusDao;
import com.vicutu.batchdownload.domain.SearchStatus;

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

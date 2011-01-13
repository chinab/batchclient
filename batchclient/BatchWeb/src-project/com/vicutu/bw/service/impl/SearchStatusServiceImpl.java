package com.vicutu.bw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicutu.bw.dao.SearchStatusDao;
import com.vicutu.bw.service.SearchStatusService;
import com.vicutu.bw.vo.SearchStatus;

@Service
public class SearchStatusServiceImpl implements SearchStatusService {

	private SearchStatusDao searchStatusDao;

	@Autowired
	public void setSearchStatusDao(SearchStatusDao searchStatusDao) {
		this.searchStatusDao = searchStatusDao;
	}

	@Override
	public void saveOrUpdateSearchStatus(SearchStatus searchStatus) {
		searchStatusDao.saveOrUpdateSearchStatus(searchStatus);
	}

	@Override
	public boolean urlExists(String url) {
		return searchStatusDao.urlExists(url);
	}

	@Override
	public SearchStatus findSearchStatusByUrl(String url) {
		return searchStatusDao.findSearchStatusByUrl(url);
	}
}

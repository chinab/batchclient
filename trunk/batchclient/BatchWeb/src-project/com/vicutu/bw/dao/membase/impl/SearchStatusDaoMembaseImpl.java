package com.vicutu.bw.dao.membase.impl;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vicutu.bw.dao.membase.SearchStatusMembaseDao;
import com.vicutu.bw.vo.SearchStatus;

@Repository
public class SearchStatusDaoMembaseImpl implements SearchStatusMembaseDao {

	private MemcachedClient memcachedClient;

	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	@Override
	public void saveOrUpdateSearchStatus(SearchStatus searchStatus) {
		try {
			memcachedClient.set(PREFIX + searchStatus.getLastSearchUrl(), 0, searchStatus);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean urlExists(String url) {
		return findSearchStatusByUrl(url) != null;
	}

	@Override
	public SearchStatus findSearchStatusByUrl(String url) {
		try {
			return memcachedClient.get(PREFIX + url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

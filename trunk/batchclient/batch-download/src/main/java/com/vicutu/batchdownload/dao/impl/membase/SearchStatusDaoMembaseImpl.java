package com.vicutu.batchdownload.dao.impl.membase;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vicutu.batchdownload.dao.SearchStatusDao;
import com.vicutu.batchdownload.domain.SearchStatus;

@Repository
public class SearchStatusDaoMembaseImpl implements SearchStatusDao {

	private MemcachedClient memcachedClient;

	private static final String PREFIX = "SearchStatus$";

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

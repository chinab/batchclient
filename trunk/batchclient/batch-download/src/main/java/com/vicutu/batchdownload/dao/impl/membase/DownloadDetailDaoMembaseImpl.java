package com.vicutu.batchdownload.dao.impl.membase;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vicutu.batchdownload.dao.DownloadDetailDao;
import com.vicutu.batchdownload.domain.DownloadDetail;

@Repository
public class DownloadDetailDaoMembaseImpl implements DownloadDetailDao {

	private MemcachedClient memcachedClient;

	private static final String PREFIX = "DownloadDetail$";

	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	@Override
	public void saveOrUpdateAccessDetail(DownloadDetail downloadDetail) {
		try {
			memcachedClient.set(PREFIX + downloadDetail.getRealUrl(), 0, downloadDetail);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

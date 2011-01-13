package com.vicutu.bw.dao.membase.impl;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vicutu.bw.dao.membase.DownloadDetailMembaseDao;
import com.vicutu.bw.vo.DownloadDetail;

@Repository
public class DownloadDetailDaoMembaseImpl implements DownloadDetailMembaseDao {

	private MemcachedClient memcachedClient;

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

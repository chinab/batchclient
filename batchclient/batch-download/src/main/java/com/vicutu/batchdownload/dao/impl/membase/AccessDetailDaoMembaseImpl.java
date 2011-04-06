package com.vicutu.batchdownload.dao.impl.membase;

import java.util.List;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vicutu.batchdownload.dao.AccessDetailDao;
import com.vicutu.batchdownload.domain.AccessDetail;

@Repository
public class AccessDetailDaoMembaseImpl implements AccessDetailDao {

	private static final String PREFIX = "AccessDetail$";

	private MemcachedClient memcachedClient;

	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	@Override
	public List<AccessDetail> findAllAccessDetail() {
		return null;
	}

	@Override
	public AccessDetail findAccessDetailByName(String name) {
		try {
			return memcachedClient.get(PREFIX + name);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void saveOrUpdateAccessDetail(AccessDetail accessDetail) {
		try {
			memcachedClient.set(PREFIX + accessDetail.getName(), 0, accessDetail);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

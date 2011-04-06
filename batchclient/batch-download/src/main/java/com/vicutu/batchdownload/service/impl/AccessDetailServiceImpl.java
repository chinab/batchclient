package com.vicutu.batchdownload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicutu.batchdownload.dao.AccessDetailDao;
import com.vicutu.batchdownload.domain.AccessDetail;
import com.vicutu.batchdownload.service.AccessDetailService;

@Service
public class AccessDetailServiceImpl implements AccessDetailService {

	private AccessDetailDao accessDetailDao;

	@Autowired
	public void setAccessDetailDao(AccessDetailDao accessDetailDao) {
		this.accessDetailDao = accessDetailDao;
	}

	@Override
	public AccessDetail findAccessDetailByName(String name) {
		return accessDetailDao.findAccessDetailByName(name);
	}
}

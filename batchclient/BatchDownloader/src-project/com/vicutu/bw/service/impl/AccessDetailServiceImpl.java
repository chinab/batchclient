package com.vicutu.bw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicutu.bw.dao.AccessDetailDao;
import com.vicutu.bw.service.AccessDetailService;
import com.vicutu.bw.vo.AccessDetail;

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

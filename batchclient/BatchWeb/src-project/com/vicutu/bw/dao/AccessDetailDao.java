package com.vicutu.bw.dao;

import java.util.List;

import com.vicutu.bw.vo.AccessDetail;

public interface AccessDetailDao {

	List<AccessDetail> findAllAccessDetail();

	AccessDetail findAccessDetailByName(String name);
	
	void saveOrUpdateAccessDetail(AccessDetail accessDetail);
}

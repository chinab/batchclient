package com.vicutu.batchdownload.dao;

import java.util.List;

import com.vicutu.batchdownload.domain.AccessDetail;

public interface AccessDetailDao {

	List<AccessDetail> findAllAccessDetail();

	AccessDetail findAccessDetailByName(String name);

	void saveOrUpdateAccessDetail(AccessDetail accessDetail);
}

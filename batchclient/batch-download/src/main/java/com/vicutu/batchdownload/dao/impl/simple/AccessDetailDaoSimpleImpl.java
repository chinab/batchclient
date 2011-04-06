package com.vicutu.batchdownload.dao.impl.simple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vicutu.batchdownload.dao.AccessDetailDao;
import com.vicutu.batchdownload.domain.AccessDetail;

@Repository
public class AccessDetailDaoSimpleImpl implements AccessDetailDao {

	private List<AccessDetail> accessDetails;

	@Autowired
	public void setAccessDetails(List<AccessDetail> accessDetails) {
		this.accessDetails = accessDetails;
	}

	@Override
	public List<AccessDetail> findAllAccessDetail() {
		return accessDetails;
	}

	@Override
	public AccessDetail findAccessDetailByName(String name) {
		for (AccessDetail accessDetail : accessDetails) {
			if (accessDetail.getName().equals(name)) {
				return accessDetail;
			}
		}
		return null;
	}

	@Override
	public void saveOrUpdateAccessDetail(AccessDetail accessDetail) {
	}
}

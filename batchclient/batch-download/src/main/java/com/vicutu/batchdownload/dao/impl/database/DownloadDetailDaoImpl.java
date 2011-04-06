package com.vicutu.batchdownload.dao.impl.database;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vicutu.batchdownload.dao.DownloadDetailDao;
import com.vicutu.batchdownload.domain.DownloadDetail;
import com.vicutu.persistence.dao.impl.HibernateGenericDaoSupport;

@Repository
@Transactional
public class DownloadDetailDaoImpl extends HibernateGenericDaoSupport<DownloadDetail> implements DownloadDetailDao {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdateAccessDetail(DownloadDetail downloadDetail) {
		this.saveOrUpdate(downloadDetail);
	}
}

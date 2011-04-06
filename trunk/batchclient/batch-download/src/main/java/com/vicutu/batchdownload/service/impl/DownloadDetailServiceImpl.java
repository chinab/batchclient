package com.vicutu.batchdownload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicutu.batchdownload.dao.DownloadDetailDao;
import com.vicutu.batchdownload.domain.DownloadDetail;
import com.vicutu.batchdownload.service.DownloadDetailService;

@Service
public class DownloadDetailServiceImpl implements DownloadDetailService {

	private DownloadDetailDao downloadDetailDao;

	@Autowired
	public void setDownloadDetailDao(DownloadDetailDao downloadDetailDao) {
		this.downloadDetailDao = downloadDetailDao;
	}

	@Override
	public void saveOrUpdateDownloadDetail(DownloadDetail downloadDetail) {
		downloadDetailDao.saveOrUpdateAccessDetail(downloadDetail);
	}
}

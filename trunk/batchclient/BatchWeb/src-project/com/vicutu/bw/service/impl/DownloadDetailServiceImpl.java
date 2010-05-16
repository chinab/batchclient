package com.vicutu.bw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicutu.bw.dao.DownloadDetailDao;
import com.vicutu.bw.service.DownloadDetailService;
import com.vicutu.bw.vo.DownloadDetail;

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

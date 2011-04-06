package com.vicutu.batchdownload.dao;

import com.vicutu.batchdownload.domain.DownloadDetail;

public interface DownloadDetailDao {

	void saveOrUpdateAccessDetail(DownloadDetail downloadDetail);

}

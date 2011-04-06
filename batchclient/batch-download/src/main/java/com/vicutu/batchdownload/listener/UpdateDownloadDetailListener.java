package com.vicutu.batchdownload.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.batchdownload.domain.DownloadDetail;
import com.vicutu.batchdownload.engine.event.UpdateDownloadDetailEvent;
import com.vicutu.batchdownload.service.DownloadDetailService;

@Component
public class UpdateDownloadDetailListener implements ApplicationListener<UpdateDownloadDetailEvent> {

	private DownloadDetailService downloadDetailService;

	@Autowired
	public void setDownloadDetailService(DownloadDetailService downloadDetailService) {
		this.downloadDetailService = downloadDetailService;
	}

	@Override
	public void onApplicationEvent(UpdateDownloadDetailEvent event) {
		DownloadDetail downloadDetail = event.getDownloadDetail();
		downloadDetailService.saveOrUpdateDownloadDetail(downloadDetail);
	}
}

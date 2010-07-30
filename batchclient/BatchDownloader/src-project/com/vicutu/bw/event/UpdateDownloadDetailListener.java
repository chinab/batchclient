package com.vicutu.bw.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.bw.service.DownloadDetailService;
import com.vicutu.bw.vo.DownloadDetail;

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

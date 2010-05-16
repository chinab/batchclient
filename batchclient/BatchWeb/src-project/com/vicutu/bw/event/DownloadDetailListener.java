package com.vicutu.bw.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.bw.engine.Engine;
import com.vicutu.bw.service.DownloadDetailService;
import com.vicutu.bw.vo.DownloadDetail;
import com.vicutu.event.Event;

@Component
public class DownloadDetailListener implements ApplicationListener<Event> {

	private DownloadDetailService downloadDetailService;

	@Autowired
	public void setDownloadDetailService(DownloadDetailService downloadDetailService) {
		this.downloadDetailService = downloadDetailService;
	}

	@Override
	public void onApplicationEvent(Event event) {
		if (Engine.EVENT_TYPE_DOWNLOAD_DETAIL.equals(event.getType())) {
			DownloadDetail downloadDetail = (DownloadDetail) event.getData();
			downloadDetailService.saveOrUpdateDownloadDetail(downloadDetail);
		}
	}
}

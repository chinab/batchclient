package com.vicutu.batchdownload.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.batchdownload.domain.SearchStatus;
import com.vicutu.batchdownload.engine.event.UpdateSearchStatusEvent;
import com.vicutu.batchdownload.service.SearchStatusService;

@Component
public class UpdateSearchStatusListener implements ApplicationListener<UpdateSearchStatusEvent> {

	private SearchStatusService searchStatusService;

	@Autowired
	public void setSearchStatusService(SearchStatusService searchStatusService) {
		this.searchStatusService = searchStatusService;
	}

	@Override
	public void onApplicationEvent(UpdateSearchStatusEvent event) {
		SearchStatus searchStatus = event.getSearchStatus();
		searchStatusService.saveOrUpdateSearchStatus(searchStatus);
	}
}

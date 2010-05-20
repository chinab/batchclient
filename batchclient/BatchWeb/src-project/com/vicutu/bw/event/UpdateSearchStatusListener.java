package com.vicutu.bw.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.bw.service.SearchStatusService;
import com.vicutu.bw.vo.SearchStatus;

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

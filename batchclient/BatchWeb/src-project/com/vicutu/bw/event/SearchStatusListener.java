package com.vicutu.bw.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.bw.engine.Engine;
import com.vicutu.bw.service.SearchStatusService;
import com.vicutu.bw.vo.SearchStatus;
import com.vicutu.event.Event;

@Component
public class SearchStatusListener implements ApplicationListener<Event> {

	private SearchStatusService searchStatusService;

	@Autowired
	public void setSearchStatusService(SearchStatusService searchStatusService) {
		this.searchStatusService = searchStatusService;
	}

	@Override
	public void onApplicationEvent(Event event) {
		if (Engine.EVENT_TYPE_SEARCH_STATUS.equals(event.getType())) {
			SearchStatus searchStatus = (SearchStatus) event.getData();
			searchStatusService.saveOrUpdateSearchStatus(searchStatus);
		}
	}
}

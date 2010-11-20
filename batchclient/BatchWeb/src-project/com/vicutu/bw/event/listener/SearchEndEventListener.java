package com.vicutu.bw.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.bw.event.SearchEndEvent;
import com.vicutu.bw.support.SpeedLogger;

@Component
public class SearchEndEventListener implements ApplicationListener<SearchEndEvent> {

	private SpeedLogger speedLogger;

	@Autowired
	public void setSpeedLogger(SpeedLogger speedLogger) {
		this.speedLogger = speedLogger;
	}

	@Override
	public void onApplicationEvent(SearchEndEvent event) {
		speedLogger.endLog(event.getAccessDetailName());
	}
}

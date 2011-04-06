package com.vicutu.batchdownload.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.batchdownload.engine.event.SearchEndEvent;
import com.vicutu.batchdownload.support.speed.SpeedLogger;

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

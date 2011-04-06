package com.vicutu.batchdownload.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.batchdownload.engine.event.SearchBeginEvent;
import com.vicutu.batchdownload.support.speed.SpeedLogger;

@Component
public class SearchBeginEventListener implements ApplicationListener<SearchBeginEvent> {

	private SpeedLogger speedLogger;

	@Autowired
	public void setSpeedLogger(SpeedLogger speedLogger) {
		this.speedLogger = speedLogger;
	}

	@Override
	public void onApplicationEvent(SearchBeginEvent event) {
		speedLogger.beginLog(event.getAccessDetailName());
	}
}

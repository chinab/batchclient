package com.vicutu.batchdownload.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.batchdownload.engine.event.SpeedRecorderEvent;
import com.vicutu.batchdownload.support.speed.SpeedLogger;

@Component
public class SpeedRecorderListener implements ApplicationListener<SpeedRecorderEvent> {

	private SpeedLogger speedLogger;

	@Autowired
	public void setSpeedLogger(SpeedLogger speedLogger) {
		this.speedLogger = speedLogger;
	}

	@Override
	public void onApplicationEvent(SpeedRecorderEvent event) {
		speedLogger.update(event.getAccessDetail().getName(), event.getBufferLength());
	}
}

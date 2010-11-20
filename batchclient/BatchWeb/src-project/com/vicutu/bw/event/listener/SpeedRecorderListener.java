package com.vicutu.bw.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.bw.event.UpdateDownloadDetailEvent;
import com.vicutu.bw.support.SpeedLogger;

@Component
public class SpeedRecorderListener implements ApplicationListener<UpdateDownloadDetailEvent> {

	private SpeedLogger speedLogger;

	@Autowired
	public void setSpeedLogger(SpeedLogger speedLogger) {
		this.speedLogger = speedLogger;
	}

	@Override
	public void onApplicationEvent(UpdateDownloadDetailEvent event) {
		speedLogger.update(event.getAccessDetail().getName(), event.getDownloadDetail().getFileLength());
	}
}

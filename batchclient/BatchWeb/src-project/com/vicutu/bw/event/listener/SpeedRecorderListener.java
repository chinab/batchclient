package com.vicutu.bw.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vicutu.bw.event.UpdateDownloadDetailEvent;
import com.vicutu.bw.support.SpeedRecorder;

@Component
public class SpeedRecorderListener  implements ApplicationListener<UpdateDownloadDetailEvent> {

	private SpeedRecorder speedRecorder;
	
	@Autowired
	public void setSpeedRecorder(SpeedRecorder speedRecorder) {
		this.speedRecorder = speedRecorder;
	}

	@Override
	public void onApplicationEvent(UpdateDownloadDetailEvent event) {
		speedRecorder.record(event.getDownloadDetail().getFileLength());
	}
}

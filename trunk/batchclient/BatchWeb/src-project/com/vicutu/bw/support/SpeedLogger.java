package com.vicutu.bw.support;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

@Component
public class SpeedLogger {

	private final Logger logger = LoggerFactory.getLogger(SpeedLogger.class);

	ConcurrentMap<String, SpeedRecorder> recorders = new ConcurrentHashMap<String, SpeedRecorder>();

	public void beginLog(String name) {
		recorders.putIfAbsent(name, new SpeedRecorder(name));
	}

	public void endLog(String name) {
		recorders.remove(name);
	}

	public void update(String name, long size) {
		recorders.get(name).record(size);
	}

	@Scheduled(fixedDelay = 10000)
	public void log() {
		for (SpeedRecorder recorder : recorders.values()) {
			String[] texts = new String[] { recorder.getName(),
					FileUtils.byteCountToDisplaySize(recorder.getCurrentSpeed()),
					FileUtils.byteCountToDisplaySize(recorder.getAverageSpeed()),
					FileUtils.byteCountToDisplaySize(recorder.getTotalSize()) };
			logger.info("Engine : [{}]\tCurrentSpeed : [{}]\tAverageSpeed : [{}]\tTotalSize : [{}]", texts);
		}
	}
}

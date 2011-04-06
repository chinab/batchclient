package com.vicutu.batchdownload.support.speed;

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

	private ConcurrentMap<String, SpeedRecorder> recorders = new ConcurrentHashMap<String, SpeedRecorder>();

	private long firstDisplayTimestamp;

	private long lastDisplayTimestamp;

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
		if (lastDisplayTimestamp > 0) {
			for (SpeedRecorder recorder : recorders.values()) {
				long currentTimestamp = System.currentTimeMillis();
				long currentCost = currentTimestamp - lastDisplayTimestamp;
				long totalCost = currentTimestamp - firstDisplayTimestamp;
				long totalBytes = recorder.getTotalTransferredBytes();
				long currentBytes = recorder.getCurrentTransferredBytes();
				String currentSpeed = FileUtils.byteCountToDisplaySize(currentBytes
						/ ((currentCost / 1000) == 0 ? 1 : (currentCost / 1000)));
				String averageSpeed = FileUtils.byteCountToDisplaySize(totalBytes / (totalCost / 1000));
				String totalSize = FileUtils.byteCountToDisplaySize(totalBytes);
				String[] texts = new String[] { recorder.getName(), currentSpeed, averageSpeed, totalSize };
				logger.info("Engine : [{}]\tCurrentSpeed : [{}]\tAverageSpeed : [{}]\tTotalSize : [{}]", texts, null);

				lastDisplayTimestamp = currentTimestamp;
				recorder.reset();
			}
		} else {
			lastDisplayTimestamp = System.currentTimeMillis();
			firstDisplayTimestamp = lastDisplayTimestamp;
		}
	}
}

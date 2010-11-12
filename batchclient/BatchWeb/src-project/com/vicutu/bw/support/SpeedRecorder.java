package com.vicutu.bw.support;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

@Component
public class SpeedRecorder {

	private final AtomicLong totalTransferredBytes = new AtomicLong();

	private final AtomicLong currentTransferredBytes = new AtomicLong();

	private long lastDisplayTimestamp;

	private long firstDisplayTimestamp;

	private final Logger logger = LoggerFactory.getLogger(SpeedRecorder.class);

	public void record(long size) {
		totalTransferredBytes.addAndGet(size);
		currentTransferredBytes.addAndGet(size);
	}

	@Scheduled(fixedDelay = 10000)
	public void dispaly() {
		if (lastDisplayTimestamp > 0) {
			long currentTimestamp = System.currentTimeMillis();
			long currentCost = currentTimestamp - lastDisplayTimestamp;
			long totalCost = currentTimestamp - firstDisplayTimestamp;
			long totalBytes = totalTransferredBytes.get();
			long currentBytes = currentTransferredBytes.get();
			String currentSpeed = FileUtils.byteCountToDisplaySize(currentBytes / (currentCost / 1000));
			String averageSpeed = FileUtils.byteCountToDisplaySize(totalBytes / (totalCost / 1000));
			String totalSize = FileUtils.byteCountToDisplaySize(totalBytes);
			logger.info("CurrentSpeed : [{}]\tAverageSpeed : [{}]\tTotalSize : [{}]", currentSpeed, averageSpeed,
					totalSize);

			lastDisplayTimestamp = currentTimestamp;
			currentTransferredBytes.set(0L);
		} else {
			lastDisplayTimestamp = System.currentTimeMillis();
			firstDisplayTimestamp = lastDisplayTimestamp;
		}
	}
}

package com.vicutu.bw.support;

import java.util.concurrent.atomic.AtomicLong;

public class SpeedRecorder {

	private final AtomicLong totalTransferredBytes = new AtomicLong();

	private final AtomicLong currentTransferredBytes = new AtomicLong();

	private long lastDisplayTimestamp;

	private long firstDisplayTimestamp;

	private volatile long currentSpeed;

	private volatile long averageSpeed;

	private volatile long totalSize;

	private String name;

	public SpeedRecorder(String name) {
		this.name = name;
		lastDisplayTimestamp = System.currentTimeMillis();
		firstDisplayTimestamp = lastDisplayTimestamp;
	}

	public void record(long size) {
		if (lastDisplayTimestamp > 0) {
			long currentTimestamp = System.currentTimeMillis();
			long currentCost = currentTimestamp - lastDisplayTimestamp;
			long totalCost = currentTimestamp - firstDisplayTimestamp;
			long totalBytes = totalTransferredBytes.addAndGet(size);
			long currentBytes = currentTransferredBytes.addAndGet(size);
			currentSpeed = currentBytes / (currentCost / 1000);
			averageSpeed = totalBytes / (totalCost / 1000);
			totalSize = totalBytes;

			lastDisplayTimestamp = currentTimestamp;
			currentTransferredBytes.set(0L);
		} else {
			throw new IllegalStateException("SpeedRecorder is not completely initialized");
		}
	}

	public long getCurrentSpeed() {
		return currentSpeed;
	}

	public long getAverageSpeed() {
		return averageSpeed;
	}

	public long getTotalSize() {
		return totalSize;
	}
	
	public String getName() {
		return name;
	}
}

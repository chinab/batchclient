package com.vicutu.batchdownload.support.speed;

import java.util.concurrent.atomic.AtomicLong;

public class SpeedRecorder {

	private final AtomicLong totalTransferredBytes = new AtomicLong();

	private final AtomicLong currentTransferredBytes = new AtomicLong();

	private String name;

	public SpeedRecorder(String name) {
		this.name = name;
	}

	public void record(long size) {
		totalTransferredBytes.addAndGet(size);
		currentTransferredBytes.addAndGet(size);
	}

	public long getTotalTransferredBytes() {
		return totalTransferredBytes.get();
	}

	public long getCurrentTransferredBytes() {
		return currentTransferredBytes.get();
	}

	public void reset() {
		currentTransferredBytes.set(0L);
	}

	public void resetAll() {
		currentTransferredBytes.set(0L);
		totalTransferredBytes.set(0L);
	}

	public String getName() {
		return name;
	}
}

package com.vicutu.download.task;

import java.util.Queue;

public interface TaskQueueProvider {
	Queue<AtomicTask> getTaskQueue();
}
package com.vicutu.bw.engine.support;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

public class BlockPolicy implements RejectedExecutionHandler {

	protected final Logger logger = LoggerFactory.getLogger(BlockPolicy.class);
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		if (!executor.isShutdown()) {
			try {
				logger.debug("engine is being blocked cause the queue is full...");
				executor.getQueue().put(r);
				logger.debug("task has been put into the queue...");
			} catch (InterruptedException e) {
			}
		}
	}
}

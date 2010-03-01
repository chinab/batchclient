package com.vicutu.download.engine.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.client.HttpClient;

import com.vicutu.download.descriptor.AuthenticationDescriptor;
import com.vicutu.download.engine.AbstractDownloader;
import com.vicutu.download.seek.Seeker;
import com.vicutu.download.task.AtomicTask;

public class AutoDownloader extends AbstractDownloader
{
	protected Seeker<Integer> seeker;

	protected int timeout;

	private int interval;

	public AutoDownloader(HttpClient httpClient, AuthenticationDescriptor authenticationDescriptor, Seeker<Integer> seeker, int timeout, int interval)
	{
		this.httpClient = httpClient;
		this.authenticationDescriptor = authenticationDescriptor;
		this.taskQueue = seeker.getTaskQueue();
		this.contentTypes = seeker.getContentTypes();
		this.seeker = seeker;
		this.timeout = timeout;
		this.interval = interval;
	}

	public Integer call() throws Exception
	{
		AtomicTask atomicTask = null;
		name = Thread.currentThread().getName();
		while (true)
		{
			atomicTask = ((BlockingQueue<AtomicTask>) taskQueue).poll(timeout, TimeUnit.SECONDS);
			if (atomicTask == null)
			{
				if (seeker.isDone())
				{
					logger.info("download over... success Count : {}", Integer.valueOf(successCount));
					break;
				}
			}
			else
			{
				try
				{
					downloadImange(atomicTask);
					logger.info("current task queue : {}", Integer.valueOf(taskQueue.size()));
				}
				catch (Exception e)
				{
					logger.error("occur error when downloading", e);
				}
			}
			if (interval > 0)
			{
				try
				{
					int realSleep;
					if (RandomUtils.nextBoolean())
					{
						realSleep = interval - RandomUtils.nextInt(interval / 10);
					}
					else
					{
						realSleep = interval + RandomUtils.nextInt(interval / 10);
					}
					logger.info("sleep : {}", Integer.valueOf(realSleep));
					Thread.sleep(realSleep);
				}
				catch (InterruptedException ie)
				{
				}
			}
		}
		return Integer.valueOf(successCount);
	}
}
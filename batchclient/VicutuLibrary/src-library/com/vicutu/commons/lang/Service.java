package com.vicutu.commons.lang;

import com.vicutu.commons.exception.StartServiceException;
import com.vicutu.commons.exception.StopServiceException;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

public abstract class Service implements IServiceable
{
	public static final String OPERATION_RESTART = "restart";

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private int status = STOPPED;

	protected String serviceName;

	public Service()
	{
	}

	public void start()
	{
		status = STARTING;

		try
		{
			doStart();
			logger.info("start service " + getServiceName());
		}
		catch (Throwable ex)
		{
			logger.error("start service error:" + getServiceName(), ex);
			throw new StartServiceException(ex, this.getClass().getName());
		}

		status = RUNNING;
	}

	public void stop()
	{
		status = STOPPING;

		try
		{
			doStop();
			logger.info("stop service " + getServiceName());
		}
		catch (Throwable ex)
		{
			throw new StopServiceException(ex, this.getClass().getName());
		}

		status = STOPPED;
	}

	public void restart()
	{
		if (isRunning())
		{
			stop();
		}

		start();
	}

	public int getStatus()
	{
		return status;
	}

	public synchronized boolean isRunning()
	{
		return status == RUNNING;
	}

	public synchronized boolean isStarting()
	{
		return status == STARTING;
	}

	public synchronized boolean isStopped()
	{
		return status == STOPPED;
	}

	public synchronized boolean isStopping()
	{
		return status == STOPPING;
	}

	public String getServiceName()
	{
		return serviceName != null ? serviceName : getClass().getName();
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	protected abstract void doStart() throws Throwable;

	protected abstract void doStop() throws Throwable;
}
package com.vicutu.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vicutu.commons.config.support.NoServiceException;
import com.vicutu.commons.lang.IServiceable;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

public class ServiceManager
{
	private static Logger logger = LoggerFactory.getLogger(ServiceManager.class);

	protected List<IServiceable> services;

	private Map<String, IServiceable> serviceMap = new HashMap<String, IServiceable>();

	public List<IServiceable> getServices()
	{
		return services;
	}

	public void setServices(List<IServiceable> services)
	{
		this.services = services;
	}

	public IServiceable getService(String servcieName)
	{
		return serviceMap.get(servcieName);
	}

	public void initialize()
	{
		if (services != null)
		{
			for (int i = 0; i < services.size(); i++)
			{
				IServiceable service = services.get(i);
				serviceMap.put(service.getServiceName(), service);
			}
		}
	}

	public void startAll()
	{
		if (services != null)
		{
			for (int i = 0; i < services.size(); i++)
			{
				IServiceable service = services.get(i);
				try
				{
					service.start();
				}
				catch (Throwable ex)
				{
					logger.error("occur error when start service {}", service.getServiceName(), ex);
				}
			}
		}
	}

	public void stopAll()
	{
		if (services != null)
		{
			for (int i = services.size() - 1; i >= 0; i--)
			{
				IServiceable service = services.get(i);
				try
				{
					service.stop();
				}
				catch (Throwable ex)
				{
					logger.error("occur error when stop service {}", service.getServiceName(), ex);
				}
			}
		}
	}

	public void start(String servcieName) throws Throwable
	{
		IServiceable service = this.getService(servcieName);
		if (service != null)
		{
			try
			{
				service.start();
			}
			catch (Throwable ex)
			{
				logger.error("occur error when start service {}", service.getServiceName(), ex);
			}
		}
		else
		{
			throw new NoServiceException(servcieName);
		}
	}

	public void stop(String servcieName) throws Throwable
	{
		IServiceable service = this.getService(servcieName);
		if (service != null)
		{
			try
			{
				service.stop();
			}
			catch (Throwable ex)
			{
				logger.error("occur error when stop service {}", service.getServiceName(), ex);
			}
		}
		else
		{
			throw new NoServiceException(servcieName);
		}
	}
}

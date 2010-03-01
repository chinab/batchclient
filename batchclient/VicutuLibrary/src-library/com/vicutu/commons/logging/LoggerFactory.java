package com.vicutu.commons.logging;

import com.vicutu.commons.exception.CreateObjectException;
import com.vicutu.commons.lang.ObjectUtils;
import com.vicutu.commons.logging.support.Log4jFactory;

@SuppressWarnings("unchecked")
public final class LoggerFactory
{
	private static ILoggerFactory factory;

	public static final String LOGGER_FACTORY = LoggerFactory.class.getName();

	static
	{
		String clazz = System.getProperty(LOGGER_FACTORY, Log4jFactory.class.getName());
		try
		{
			factory = (ILoggerFactory) ObjectUtils.create(clazz);
		}
		catch (Throwable ex)
		{
			throw new CreateObjectException(ex, clazz);
		}
	}

	public static Logger getLogger(String name)
	{
		return factory.getLogger(name);
	}

	public static Logger getLogger(Class clazz)
	{
		return factory.getLogger(clazz);
	}
}
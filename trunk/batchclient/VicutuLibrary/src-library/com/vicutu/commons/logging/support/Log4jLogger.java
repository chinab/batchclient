package com.vicutu.commons.logging.support;

import org.apache.commons.logging.Log;

import com.vicutu.commons.lang.StringUtils;
import com.vicutu.commons.logging.Logger;

public class Log4jLogger extends Logger
{
	private Log logger;

	public Log4jLogger(Log logger)
	{
		this.logger = logger;
	}

	public void debug(String message, Object[] args, Throwable t)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug(StringUtils.formatMessage(message, args), t);
		}
	}

	public void error(String message, Object[] args, Throwable t)
	{
		if (logger.isErrorEnabled())
		{
			logger.error(StringUtils.formatMessage(message, args), t);
		}
	}

	public void fatal(String message, Object[] args, Throwable t)
	{
		if (logger.isFatalEnabled())
		{
			logger.fatal(StringUtils.formatMessage(message, args), t);
		}
	}

	public void info(String message, Object[] args, Throwable t)
	{
		if (logger.isInfoEnabled())
		{
			logger.info(StringUtils.formatMessage(message, args), t);
		}
	}

	public void trace(String message, Object[] args, Throwable t)
	{
		if (logger.isTraceEnabled())
		{
			logger.trace(StringUtils.formatMessage(message, args), t);
		}
	}

	public void warn(String message, Object[] args, Throwable t)
	{
		if (logger.isWarnEnabled())
		{
			logger.warn(StringUtils.formatMessage(message, args), t);
		}
	}
}
package com.vicutu.commons.logging.slf4j;

import com.vicutu.commons.lang.StringUtils;

public class Slf4jLogger extends com.vicutu.commons.logging.Logger {
	private org.slf4j.Logger logger;

	public Slf4jLogger(org.slf4j.Logger logger) {
		this.logger = logger;
	}

	@Override
	public void debug(String message, Object[] args, Throwable t) {
		if (logger.isDebugEnabled()) {
			logger.debug(StringUtils.formatMessage(message, args), t);
		}
	}

	@Override
	public void error(String message, Object[] args, Throwable t) {
		if (logger.isErrorEnabled()) {
			logger.error(StringUtils.formatMessage(message, args), t);
		}
	}

	@Override
	public void fatal(String message, Object[] args, Throwable t) {
		throw new UnsupportedOperationException("slf4j is not supported fatal log level");
	}

	@Override
	public void info(String message, Object[] args, Throwable t) {
		if (logger.isInfoEnabled()) {
			logger.info(StringUtils.formatMessage(message, args), t);
		}
	}

	@Override
	public void trace(String message, Object[] args, Throwable t) {
		if (logger.isTraceEnabled()) {
			logger.trace(StringUtils.formatMessage(message, args), t);
		}
	}

	@Override
	public void warn(String message, Object[] args, Throwable t) {
		if (logger.isWarnEnabled()) {
			logger.warn(StringUtils.formatMessage(message, args), t);
		}
	}
}
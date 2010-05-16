package com.vicutu.commons.logging.slf4j;

public class Slf4jLogger extends com.vicutu.commons.logging.Logger {
	private org.slf4j.Logger logger;

	public Slf4jLogger(org.slf4j.Logger logger) {
		this.logger = logger;
	}

	public void debug(String message, Object[] args, Throwable t) {
		if (logger.isDebugEnabled()) {
			logger.debug(message, args, t);
		}
	}

	public void error(String message, Object[] args, Throwable t) {
		if (logger.isErrorEnabled()) {
			logger.error(message, args, t);
		}
	}

	public void fatal(String message, Object[] args, Throwable t) {
		throw new UnsupportedOperationException("slf4j is not supported fatal log level");
	}

	public void info(String message, Object[] args, Throwable t) {
		if (logger.isInfoEnabled()) {
			logger.info(message, args, t);
		}
	}

	public void trace(String message, Object[] args, Throwable t) {
		if (logger.isTraceEnabled()) {
			logger.trace(message, args, t);
		}
	}

	public void warn(String message, Object[] args, Throwable t) {
		if (logger.isWarnEnabled()) {
			logger.warn(message, args, t);
		}
	}
}
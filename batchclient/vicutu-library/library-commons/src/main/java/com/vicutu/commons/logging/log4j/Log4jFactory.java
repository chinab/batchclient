package com.vicutu.commons.logging.log4j;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;

import com.vicutu.commons.logging.ILoggerFactory;
import com.vicutu.commons.logging.Logger;

public class Log4jFactory implements ILoggerFactory {
	private Map<String, Logger> loggers = new HashMap<String, Logger>();

	public Logger getLogger(String name) {
		Logger logger = (Logger) loggers.get(name);

		if (logger == null) {
			synchronized (loggers) {
				logger = (Logger) loggers.get(name);
				if (logger == null) {
					logger = new Log4jLogger(LogFactory.getLog(name));
					loggers.put(name, logger);
				}
			}
		}
		return logger;
	}

	public Logger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}
}
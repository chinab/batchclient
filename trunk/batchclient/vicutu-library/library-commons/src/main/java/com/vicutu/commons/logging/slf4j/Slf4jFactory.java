package com.vicutu.commons.logging.slf4j;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.vicutu.commons.logging.ILoggerFactory;
import com.vicutu.commons.logging.Logger;

public class Slf4jFactory implements ILoggerFactory {
	private Map<String, Logger> loggers = new HashMap<String, Logger>();

	public Logger getLogger(String name) {
		Logger logger = (Logger) loggers.get(name);

		if (logger == null) {
			synchronized (loggers) {
				logger = (Logger) loggers.get(name);
				if (logger == null) {
					logger = new Slf4jLogger(LoggerFactory.getLogger(name));
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
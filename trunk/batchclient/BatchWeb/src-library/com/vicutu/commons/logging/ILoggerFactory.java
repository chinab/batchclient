package com.vicutu.commons.logging;

public interface ILoggerFactory {
	
	Logger getLogger(String name);

	Logger getLogger(Class<?> clazz);
}
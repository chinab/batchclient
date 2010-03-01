package com.vicutu.commons.logging;

@SuppressWarnings("unchecked")
public interface ILoggerFactory
{
	Logger getLogger(String name);

	Logger getLogger(Class clazz);
}
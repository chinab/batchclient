package com.vicutu.persistence.query;

public interface QueryTemplateManager
{
	QueryTemplate getQueryTemplate(String name);
	
	QueryTemplate getQueryTemplate(Class<?> clazz);
}

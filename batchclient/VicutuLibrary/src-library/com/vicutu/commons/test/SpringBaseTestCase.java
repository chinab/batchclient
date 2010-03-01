package com.vicutu.commons.test;

import com.vicutu.commons.config.Application;

public class SpringBaseTestCase extends LoggerTestCase
{

	protected void setUp() throws Exception
	{
		try
		{
			Application.initialize();
			Application.bootstrap();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	protected void tearDown() throws Exception
	{
		Application.shutdown();
	}

	public Object getBean(String beanId)
	{
		return Application.getBean(beanId);
	}

	@SuppressWarnings("unchecked")
	public static Object getBean(Class clazz)
	{
		return Application.getBean(clazz);
	}
}

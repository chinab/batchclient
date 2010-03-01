package com.vicutu.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vicutu.commons.config.Application;

public class ApplicationListener implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent event)
	{

		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		Application.initialize();
		Application.bootstrap(context, false, false, true);
	}

	public void contextDestroyed(ServletContextEvent event)
	{
		Application.shutdown();
	}
}

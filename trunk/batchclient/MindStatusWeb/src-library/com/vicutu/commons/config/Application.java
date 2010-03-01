package com.vicutu.commons.config;

import org.apache.log4j.xml.DOMConfigurator;

import com.vicutu.commons.lang.PathUtils;

public class Application {


	public static final String CONFIG_LOG_PATH = "config/log4j-config.xml";

	public static String WEB_ROOT_PROPERTY = "webapp.root";

	public static void bootstrap() {
		System.setProperty(WEB_ROOT_PROPERTY, PathUtils.getSystemPath());
		DOMConfigurator.configure(PathUtils.getPath(CONFIG_LOG_PATH));
	}

	public static void shutdown() {

	}
	
	public static String getSystemPathUri(){
		return PathUtils.getFile("").toURI().toString();
	}

}

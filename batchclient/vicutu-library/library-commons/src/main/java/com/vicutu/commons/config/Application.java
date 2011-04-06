package com.vicutu.commons.config;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;

import com.vicutu.commons.config.support.NoSystemPathException;
import com.vicutu.commons.config.support.Path;

public class Application {

	private static String SYSTEM_PATH;

	public static String SYSTEM_PROPERTY = "webapp.root";

	private Application() {
	}

	static {
		initialize();
	}

	private static void initialize() {
		/* load system path */
		if (SYSTEM_PATH == null) {
			SYSTEM_PATH = System.getProperty(SYSTEM_PROPERTY);
		}

		if (SYSTEM_PATH == null) {
			SYSTEM_PATH = Path.getSystemPath();
			System.setProperty(SYSTEM_PROPERTY, SYSTEM_PATH);
		} else {
			Path.setSystemPath(SYSTEM_PATH);
		}

		if (SYSTEM_PATH == null) {
			throw new NoSystemPathException();
		}
	}

	public static void initLog4j() {
		DOMConfigurator.configure(getConfigPath("log4j-config.xml"));
	}

	public static String getSystemPath() {
		return SYSTEM_PATH;
	}

	public static void setSystemPath(String systemPath) {
		Application.SYSTEM_PATH = systemPath;
		System.setProperty(SYSTEM_PROPERTY, systemPath);
	}

	public static String getPath(String path) {
		return Path.getPath(path);
	}

	public static File getFile() {
		return new File(getSystemPath());
	}

	public static File getFile(String path) {
		if (path == null || path.equals("") || path.equals("/")) {
			return getFile();
		} else {
			return Path.getFile(path);
		}
	}

	public static String getConfigPath(String name) {
		if (name == null || name.equals("") || name.equals("/")) {
			return getPath("config");
		} else {
			return getPath("config/" + name);
		}
	}

	public static File getConfigFile(String name) {
		if (name == null || name.equals("") || name.equals("/")) {
			return getFile("config");
		} else {
			return getFile("config/" + name);
		}
	}
}

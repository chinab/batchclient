package com.vicutu.commons.config.support;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.commons.io.filefilter.FileFilterUtils;

import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.lang.StringUtils;

public final class Path {
	private static String systemPath;

	private static final String FILE_WEB_XML = "/WEB-INF/web.xml";

	private static final String FILE_CONFIG = "/config/application-default.xml";

	private Path() {
	}

	public static String getSystemPath() {
		if (systemPath == null) {
			initializeSystemPath();
		}

		return systemPath;
	}

	public static void setSystemPath(String systemPath) {
		Path.systemPath = systemPath;
	}

	public static String getPath(String path) {
		return FileUtils.concatPath(getSystemPath(), path);
	}

	public static File getFile(String path) {
		if (path.indexOf("..") >= 0) {
			path = replaceFileSeparator(path);
			String[] paths = StringUtils.split(path, '/');

			File file = new File(getSystemPath());
			for (int i = 0; i < paths.length; i++) {
				if (paths[i].equals("..")) {
					file = file.getParentFile();
				} else {
					file = new File(file, paths[i]);
				}
			}

			return file;
		} else {
			return new File(getPath(path));
		}
	}

	// ------------------------------------------------------------------------//
	// --private method
	// ------------------------------------------------------------------------//
	private static void initializeSystemPath() {
		if (systemPath == null) {
			try {
				systemPath = findSystemPath();
			} catch (Throwable ex) {
				throw new FindSystemPathException(ex);
			}
		}
	}

	private static String findSystemPath() throws Exception {
		String path = getLocation(Path.class);
		if (path != null) {
			path = replaceFileSeparator(path);
			path = trimFilePath(path);
			while (path != null) {
				File folder = new File(path);
				if (detectContext(folder)) {
					if (!path.endsWith("/")) {
						path = path + "/";
					}
					return path;
				} else {
					File[] files = folder.listFiles((FileFilter) FileFilterUtils.directoryFileFilter());
					if (files != null && files.length > 0) {
						for (int i = 0; i < files.length; i++) {
							if (detectContext(files[i])) {
								return path + "/" + files[i].getName() + "/";
							}
						}
					}
				}
				path = trimFilePath(path);
			}
			return null;
		} else {
			return null;
		}
	}

	private static boolean detectContext(File folder) {
		return (new File(folder + FILE_WEB_XML)).exists() || (new File(folder, FILE_CONFIG).exists());
	}

	private static String trimFilePath(String path) {
		int pos = path.lastIndexOf('/');
		if (pos > 0) {
			return path.substring(0, pos);
		} else {
			return null;
		}
	}

	private static String replaceFileSeparator(String path) {
		String separator = System.getProperty("file.separator");
		if (separator.length() == 1) {
			char chr = separator.charAt(0);
			if (chr != '/') {
				path = path.replace(chr, '/');
			}
		}
		return path;
	}

	private static String getLocation(Class<?> clazz) throws Exception {
		String className = clazz.getName();
		int pos = className.lastIndexOf(".");
		if (pos > 0) {
			className = className.substring(pos + 1);
		}
		URL url = clazz.getResource(className + ".class");
		String location = URLDecoder.decode(url.toString(), "utf-8");
		if (location.startsWith("jar") || location.startsWith("zip")) {
			url = ((JarURLConnection) url.openConnection()).getJarFileURL();
			location = url.toString();
		}
		if (location.startsWith("file")) {
			return new File(url.getFile()).getAbsolutePath();
		} else {
			return location;
		}
	}
}
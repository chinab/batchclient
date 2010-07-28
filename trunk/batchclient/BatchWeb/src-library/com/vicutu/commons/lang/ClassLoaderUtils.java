package com.vicutu.commons.lang;

import java.util.HashMap;
import java.util.Map;

public final class ClassLoaderUtils {

	private static Map<String,Class<?>> primitiveMapping = new HashMap<String,Class<?>>();

	static {
		primitiveMapping.put(boolean.class.getName(), boolean.class);
		primitiveMapping.put(char.class.getName(), char.class);
		primitiveMapping.put(byte.class.getName(), byte.class);
		primitiveMapping.put(short.class.getName(), short.class);
		primitiveMapping.put(int.class.getName(), int.class);
		primitiveMapping.put(long.class.getName(), long.class);
		primitiveMapping.put(float.class.getName(), float.class);
		primitiveMapping.put(double.class.getName(), double.class);
		primitiveMapping.put(void.class.getName(), void.class);

		primitiveMapping.put(boolean[].class.getName(), boolean[].class);
		primitiveMapping.put(char[].class.getName(), char[].class);
		primitiveMapping.put(byte[].class.getName(), byte[].class);
		primitiveMapping.put(short[].class.getName(), short[].class);
		primitiveMapping.put(int[].class.getName(), int[].class);
		primitiveMapping.put(long[].class.getName(), long[].class);
		primitiveMapping.put(float[].class.getName(), float[].class);
		primitiveMapping.put(double[].class.getName(), double[].class);
		primitiveMapping.put(String[].class.getName(), String[].class);

	}

	private ClassLoaderUtils() {
	}

	public static java.lang.ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public static Class<?> loadClass(String className) throws Exception {
		return loadClass(className, null);
	}

	public static Class<?> loadClass(String className, Class<?> defaultClass) throws Exception {
		if (className == null || className.equals("")) {
			return defaultClass;
		} else {
			if (primitiveMapping.containsKey(className)) {
				return (Class<?>) primitiveMapping.get(className);
			} else {
				return getClassLoader().loadClass(className);
			}
		}
	}
}
package com.vicutu.commons.lang;

import java.lang.reflect.Field;

public final class ClassUtils {
	private static final String ENHANCER_FLAG = "$$EnhancerByCGLIB$$";

	public static boolean isIntercept(Class<?> clazz) {
		return clazz.getName().indexOf(ENHANCER_FLAG) > 0;
	}

	public static Class<?> getRealClass(Class<?> clazz) {
		if (isIntercept(clazz)) {
			return clazz.getSuperclass();
		} else {
			return clazz;
		}
	}

	public static Class<?> getRealClass(Object object) {
		return getRealClass(object.getClass());
	}

	public static Field getField(Class<?> clazz, String name) {
		if (clazz != Object.class) {
			try {
				return clazz.getDeclaredField(name);
			} catch (NoSuchFieldException ex) {
				return getField(clazz.getSuperclass(), name);
			}
		} else {
			return null;
		}
	}
}
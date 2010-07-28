package com.vicutu.commons.lang;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

@SuppressWarnings("unchecked")
public final class ObjectUtils {
	private ObjectUtils() {
	}

	public static Object create(String className, String factoryMethod, Class<?>[] paramTypes, Object[] paramValues)
			throws Exception {
		Class<?> clazz = ClassLoaderUtils.loadClass(className);
		Method method = clazz.getMethod(factoryMethod, paramTypes);
		return method.invoke(null, paramValues);
	}

	public static Object create(Object bean, String factoryMethod, Class<?>[] paramTypes, Object[] paramValues)
			throws Exception {
		Class<?> clazz = bean.getClass();
		Method method = clazz.getMethod(factoryMethod, paramTypes);
		return method.invoke(bean, paramValues);
	}

	public static Object create(String className) throws Exception {
		Class<?> clazz = ClassLoaderUtils.loadClass(className);
		return clazz.newInstance();
	}

	public static Object create(String className, Class<?>[] paramTypes, Object[] paramValues) throws Exception {
		Class<?> clazz = ClassLoaderUtils.loadClass(className);
		Constructor<?> constructor = clazz.getConstructor(paramTypes);
		return constructor.newInstance(paramValues);
	}

	public static byte[] serialize(Object object) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bytestream);
		oos.writeObject(object);
		return bytestream.toByteArray();
	}

	public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		if (bytes == null) {
			return null;
		} else {
			ByteArrayInputStream bytestream = new ByteArrayInputStream(bytes);
			ObjectInputStream oos = new ObjectInputStream(bytestream);
			return oos.readObject();
		}
	}

	public static Object clone(Object object) {
		if (object == null) {
			return null;
		}

		if (object instanceof String[]) {
			String[] source = (String[]) object;
			String[] value = new String[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof Long[]) {
			Long[] source = (Long[]) object;
			Long[] value = new Long[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof Integer[]) {
			Integer[] source = (Integer[]) object;
			Integer[] value = new Integer[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof Short[]) {
			Short[] source = (Short[]) object;
			Short[] value = new Short[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof Character[]) {
			Character[] source = (Character[]) object;
			Character[] value = new Character[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof Byte[]) {
			Byte[] source = (Byte[]) object;
			Byte[] value = new Byte[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof Boolean[]) {
			Boolean[] source = (Boolean[]) object;
			Boolean[] value = new Boolean[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof Double[]) {
			Double[] source = (Double[]) object;
			Double[] value = new Double[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof Float[]) {
			Float[] source = (Float[]) object;
			Float[] value = new Float[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof BigDecimal[]) {
			BigDecimal[] source = (BigDecimal[]) object;
			BigDecimal[] value = new BigDecimal[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof Date[]) {
			Date[] source = (Date[]) object;
			Date[] value = new Date[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = source[i];
			}

			return value;
		}

		if (object instanceof Object[]) {
			Object[] source = (Object[]) object;
			Object[] value = new Object[source.length];
			for (int i = 0; i < source.length; i++) {
				value[i] = clone(source[i]);
			}

			return value;
		}

		if (object instanceof ListOrderedMap) {
			Map<?, ?> source = (Map<?, ?>) object;
			Map<Object, Object> value = new ListOrderedMap();
			Iterator<?> iter = source.keySet().iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				value.put(key, clone(source.get(key)));
			}

			return value;
		}

		if (object instanceof List) {
			List<?> source = (List<?>) object;
			List<Object> value = new ArrayList<Object>(source.size());
			for (int i = 0, size = source.size(); i < size; i++) {
				value.add(clone(source.get(i)));
			}

			return value;
		}

		if (object instanceof Map) {
			Map<?, ?> source = (Map<?, ?>) object;
			Map<Object, Object> value = new HashMap<Object, Object>();
			Iterator<?> iter = source.keySet().iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				value.put(key, clone(source.get(key)));
			}

			return value;
		}

		return object;
	}
}
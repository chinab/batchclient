package com.vicutu.commons.lang;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class ObjectUtils
{
	private ObjectUtils()
	{
	}

	public static Object create(String className, String factoryMethod, Class<?>[] paramTypes, Object[] paramValues) throws Exception
	{
		Class<?> clazz = ClassLoaderUtils.loadClass(className);
		Method method = clazz.getMethod(factoryMethod, paramTypes);
		return method.invoke(null, paramValues);
	}

	public static Object create(Object bean, String factoryMethod, Class<?>[] paramTypes, Object[] paramValues) throws Exception
	{
		Class<?> clazz = bean.getClass();
		Method method = clazz.getMethod(factoryMethod, paramTypes);
		return method.invoke(bean, paramValues);
	}

	public static Object create(String className) throws Exception
	{
		Class<?> clazz = ClassLoaderUtils.loadClass(className);
		return clazz.newInstance();
	}

	public static Object create(String className, Class<?>[] paramTypes, Object[] paramValues) throws Exception
	{
		Class<?> clazz = ClassLoaderUtils.loadClass(className);
		Constructor<?> constructor = clazz.getConstructor(paramTypes);
		return constructor.newInstance(paramValues);
	}

	public static byte[] serialize(Object object) throws IOException
	{
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bytestream);
		oos.writeObject(object);
		return bytestream.toByteArray();
	}

	public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException
	{
		if (bytes == null)
		{
			return null;
		}
		else
		{
			ByteArrayInputStream bytestream = new ByteArrayInputStream(bytes);
			ObjectInputStream oos = new ObjectInputStream(bytestream);
			return oos.readObject();
		}
	}
}
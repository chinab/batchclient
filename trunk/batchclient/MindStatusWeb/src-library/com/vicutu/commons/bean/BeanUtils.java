package com.vicutu.commons.bean;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import com.vicutu.commons.lang.ClassLoaderUtils;



public final class BeanUtils
{
	

	private BeanUtils()
	{
	}

	public static Object convert(Object value, Class<?> clazz)
	{
		if (value == null)
		{
			return null;
		}

		if (value.getClass() == clazz)
		{
			return value;
		}

		Converter conveter = (Converter) ConvertUtils.lookup(clazz);
		if (conveter == null)
		{
			return value;
		}
		else
		{
			return conveter.convert(clazz, value);
		}
	}

	public static Object convert(Object value, String className) throws Exception
	{
		if (className == null || className.equals(""))
		{
			return value;
		}
		else
		{
			return convert(value, ClassLoaderUtils.loadClass(className));
		}
	}

	public static Object convert(Object value, int type)
	{
		switch (type)
		{
			case Types.TIME:
			{
				return convert(value, Time.class);
			}
			case Types.TIMESTAMP:
			{
				return convert(value, Timestamp.class);
			}
			case Types.DATE:
			{
				return convert(value, java.util.Date.class);
			}
			case Types.CLOB:
			{
				return convert(value, java.sql.Clob.class);
			}
			case Types.BLOB:
			{
				return convert(value, java.sql.Blob.class);
			}
			case Types.BIT:
			{
				return convert(value, Boolean.class);
			}
			case Types.TINYINT:
			{
				return convert(value, Byte.class);
			}
			case Types.SMALLINT:
			{
				return convert(value, Short.class);
			}
			case Types.INTEGER:
			{
				return convert(value, Integer.class);
			}
			case Types.BIGINT:
			{
				return convert(value, Long.class);
			}
			case Types.DECIMAL:
			case Types.NUMERIC:
			{
				return convert(value, BigDecimal.class);
			}
			case Types.REAL:
			case Types.FLOAT:
			{
				return convert(value, Float.class);
			}
			case Types.DOUBLE:
			{
				return convert(value, Double.class);
			}
			default:
			{
				return value;
			}
		}
	}

	public static void setProperty(Object bean, String name, Object value) throws Exception
	{
		if (value != null)
		{
			if (name.equals("charValue"))
			{
				System.out.println();
			}

			if (name.length() > 2 && Character.isUpperCase(name.charAt(1)))
			{
				char chars[] = name.toCharArray();
				chars[0] = Character.toUpperCase(chars[0]);
				name = new String(chars);
			}

			PropertyDescriptor property = PropertyUtils.getPropertyDescriptor(bean, name);
			if (property != null)
			{
				Class<?> clazz = property.getPropertyType();
				value = convert(value, clazz);
				if (PropertyUtils.isWriteable(bean, name))
				{
					PropertyUtils.setProperty(bean, name, value);
				}
			}
		}
	}

	public static Object getProperty(Object bean, String name) throws Exception
	{
		return PropertyUtils.getProperty(bean, name);
	}
}
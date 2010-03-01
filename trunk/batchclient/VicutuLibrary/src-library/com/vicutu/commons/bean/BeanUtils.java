package com.vicutu.commons.bean;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;

import com.vicutu.commons.bean.convert.BigDecimalConverter;
import com.vicutu.commons.bean.convert.BigIntegerConverter;
import com.vicutu.commons.bean.convert.BlobConveter;
import com.vicutu.commons.bean.convert.BooleanArrayConveter;
import com.vicutu.commons.bean.convert.BooleanConveter;
import com.vicutu.commons.bean.convert.ByteArrayConveter;
import com.vicutu.commons.bean.convert.ByteConverter;
import com.vicutu.commons.bean.convert.CharArrayConveter;
import com.vicutu.commons.bean.convert.CharConverter;
import com.vicutu.commons.bean.convert.ClobConveter;
import com.vicutu.commons.bean.convert.DateConveter;
import com.vicutu.commons.bean.convert.DoubleArrayConveter;
import com.vicutu.commons.bean.convert.DoubleConverter;
import com.vicutu.commons.bean.convert.FloatArrayConveter;
import com.vicutu.commons.bean.convert.FloatConverter;
import com.vicutu.commons.bean.convert.IntegerArrayConveter;
import com.vicutu.commons.bean.convert.IntegerConverter;
import com.vicutu.commons.bean.convert.LongArrayConveter;
import com.vicutu.commons.bean.convert.LongConverter;
import com.vicutu.commons.bean.convert.ShortArrayConveter;
import com.vicutu.commons.bean.convert.ShortConverter;
import com.vicutu.commons.bean.convert.StringArrayConveter;
import com.vicutu.commons.bean.convert.StringConveter;
import com.vicutu.commons.bean.convert.TimeConveter;
import com.vicutu.commons.bean.convert.TimestampConverter;
import com.vicutu.commons.lang.ClassLoaderUtils;

@SuppressWarnings("unchecked")
public final class BeanUtils
{
	private static Converter booleanConveter = new BooleanConveter();

	private static Converter dateConveter = new DateConveter();

	private static Converter timeConveter = new TimeConveter();

	private static Converter timestampConveter = new TimestampConverter();

	private static Converter stringConveter = new StringConveter();

	private static Converter clobConveter = new ClobConveter();

	private static Converter blobConveter = new BlobConveter();

	private static Converter bigDecimalConveter = new BigDecimalConverter();

	private static Converter bigIntegerConveter = new BigIntegerConverter();

	private static Converter longConveter = new LongConverter();

	private static Converter integerConveter = new IntegerConverter();

	private static Converter shortConveter = new ShortConverter();

	private static Converter byteConveter = new ByteConverter();

	private static Converter charConveter = new CharConverter();

	private static Converter doubleConveter = new DoubleConverter();

	private static Converter floatConveter = new FloatConverter();

	private static Converter stringArrayConveter = new StringArrayConveter();

	private static Converter byteArrayConveter = new ByteArrayConveter();

	private static Converter charArrayConveter = new CharArrayConveter();

	private static Converter booleanArrayConveter = new BooleanArrayConveter();

	private static Converter shortArrayConveter = new ShortArrayConveter();

	private static Converter integerArrayConveter = new IntegerArrayConveter();

	private static Converter longArrayConveter = new LongArrayConveter();

	private static Converter floatArrayConveter = new FloatArrayConveter();

	private static Converter doubleArrayConveter = new DoubleArrayConveter();

	private static HashMap converts = new HashMap();

	static
	{
		converts.put(java.sql.Date.class, dateConveter);
		converts.put(java.util.Date.class, dateConveter);
		converts.put(Time.class, timeConveter);
		converts.put(Timestamp.class, timestampConveter);
		converts.put(String.class, stringConveter);
		converts.put(Boolean.class, booleanConveter);

		converts.put(BigDecimal.class, bigDecimalConveter);
		converts.put(BigInteger.class, bigIntegerConveter);

		converts.put(Long.class, longConveter);
		converts.put(Integer.class, integerConveter);
		converts.put(Short.class, shortConveter);
		converts.put(Byte.class, byteConveter);
		converts.put(Double.class, doubleConveter);
		converts.put(Float.class, floatConveter);
		converts.put(Character.class, charConveter);

		converts.put(long.class, longConveter);
		converts.put(int.class, integerConveter);
		converts.put(short.class, shortConveter);
		converts.put(byte.class, byteConveter);
		converts.put(double.class, doubleConveter);
		converts.put(float.class, floatConveter);
		converts.put(char.class, charConveter);
		converts.put(boolean.class, booleanConveter);

		converts.put(Clob.class, clobConveter);
		converts.put(Blob.class, blobConveter);

		converts.put(String[].class, stringArrayConveter);
		converts.put(byte[].class, byteArrayConveter);
		converts.put(char[].class, charArrayConveter);

		converts.put(boolean[].class, booleanArrayConveter);
		converts.put(short[].class, shortArrayConveter);
		converts.put(int[].class, integerArrayConveter);
		converts.put(long[].class, longArrayConveter);
		converts.put(float[].class, floatArrayConveter);
		converts.put(double[].class, doubleArrayConveter);
	}

	private BeanUtils()
	{
	}

	public static Object convert(Object value, Class clazz)
	{
		if (clazz == null || value == null)
		{
			return value;
		}

		if (value.getClass() == clazz)
		{
			return value;
		}

		Converter conveter = (Converter) converts.get(clazz);
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
			if (name.length() > 2 && Character.isUpperCase(name.charAt(1)))
			{
				char chars[] = name.toCharArray();
				chars[0] = Character.toUpperCase(chars[0]);
				name = new String(chars);
			}

			PropertyDescriptor property = PropertyUtils.getPropertyDescriptor(bean, name);
			if (property != null)
			{
				Class clazz = property.getPropertyType();

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
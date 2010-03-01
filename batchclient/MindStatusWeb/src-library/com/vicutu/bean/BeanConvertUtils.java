package com.vicutu.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.commons.beanutils.converters.CharacterConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

import com.vicutu.commons.lang.ClassLoaderUtils;

public class BeanConvertUtils {
	
	private BeanConvertUtils()
	{
	}
	
	public static void deregister(){
		ConvertUtils.deregister();
	}
	
	public static void registNullableConverters(){
		ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
		ConvertUtils.register(new SqlDateConverter(null), java.util.Date.class);
		ConvertUtils.register(new SqlTimeConverter(null), Time.class);
		ConvertUtils.register(new SqlTimestampConverter(null), Timestamp.class);

		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		ConvertUtils.register(new BigIntegerConverter(null), BigInteger.class);

		ConvertUtils.register(new BooleanConverter(null), Boolean.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new ByteConverter(null), Byte.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new CharacterConverter(null), Character.class);
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

}

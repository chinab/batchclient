package com.vicutu.commons.convert;

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

import com.vicutu.commons.convert.converter.BigDecimalConverter;
import com.vicutu.commons.convert.converter.BigIntegerConverter;
import com.vicutu.commons.convert.converter.BlobConveter;
import com.vicutu.commons.convert.converter.BooleanArrayConveter;
import com.vicutu.commons.convert.converter.BooleanConveter;
import com.vicutu.commons.convert.converter.ByteArrayConveter;
import com.vicutu.commons.convert.converter.ByteConverter;
import com.vicutu.commons.convert.converter.CharArrayConveter;
import com.vicutu.commons.convert.converter.CharConverter;
import com.vicutu.commons.convert.converter.ClobConveter;
import com.vicutu.commons.convert.converter.DateConveter;
import com.vicutu.commons.convert.converter.DoubleArrayConveter;
import com.vicutu.commons.convert.converter.DoubleConverter;
import com.vicutu.commons.convert.converter.FloatArrayConveter;
import com.vicutu.commons.convert.converter.FloatConverter;
import com.vicutu.commons.convert.converter.IntegerArrayConveter;
import com.vicutu.commons.convert.converter.IntegerConverter;
import com.vicutu.commons.convert.converter.LongArrayConveter;
import com.vicutu.commons.convert.converter.LongConverter;
import com.vicutu.commons.convert.converter.ShortArrayConveter;
import com.vicutu.commons.convert.converter.ShortConverter;
import com.vicutu.commons.convert.converter.StringArrayConveter;
import com.vicutu.commons.convert.converter.StringConveter;
import com.vicutu.commons.convert.converter.TimeConveter;
import com.vicutu.commons.convert.converter.TimestampConverter;
import com.vicutu.commons.lang.ClassLoaderUtils;

public final class ConvertUtils {
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

	private static HashMap<Class<?>,Converter> converts = new HashMap<Class<?>,Converter>();

	static {
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

	private ConvertUtils() {
	}

	public static Object convert(Object value, Class<?> clazz) {
		if (clazz == null || value == null) {
			return value;
		}

		if (value.getClass() == clazz) {
			return value;
		}

		Converter conveter = (Converter) converts.get(clazz);
		if (conveter == null) {
			return value;
		} else {
			return conveter.convert(clazz, value);
		}
	}

	public static Object convert(Object value, String className) throws Exception {
		if (className == null || className.equals("")) {
			return value;
		} else {
			return convert(value, ClassLoaderUtils.loadClass(className));
		}
	}

	public static Object convert(Object value, int type) {
		switch (type) {
		case Types.TIME: {
			return convert(value, Time.class);
		}
		case Types.TIMESTAMP: {
			return convert(value, Timestamp.class);
		}
		case Types.DATE: {
			return convert(value, java.util.Date.class);
		}
		case Types.CLOB: {
			return convert(value, java.sql.Clob.class);
		}
		case Types.BLOB: {
			return convert(value, java.sql.Blob.class);
		}
		case Types.BIT: {
			return convert(value, Boolean.class);
		}
		case Types.TINYINT: {
			return convert(value, Byte.class);
		}
		case Types.SMALLINT: {
			return convert(value, Short.class);
		}
		case Types.INTEGER: {
			return convert(value, Integer.class);
		}
		case Types.BIGINT: {
			return convert(value, Long.class);
		}
		case Types.DECIMAL:
		case Types.NUMERIC: {
			return convert(value, BigDecimal.class);
		}
		case Types.REAL:
		case Types.FLOAT: {
			return convert(value, Float.class);
		}
		case Types.DOUBLE: {
			return convert(value, Double.class);
		}
		default: {
			return value;
		}
		}
	}

	public static void setProperty(Object bean, String name, Object value) throws Exception {
		if (value != null) {
			if (name.length() > 2 && Character.isUpperCase(name.charAt(1))) {
				char chars[] = name.toCharArray();
				chars[0] = Character.toUpperCase(chars[0]);
				name = new String(chars);
			}

			PropertyDescriptor property = PropertyUtils.getPropertyDescriptor(bean, name);
			if (property != null) {
				Class<?> clazz = property.getPropertyType();

				value = convert(value, clazz);
				if (PropertyUtils.isWriteable(bean, name)) {
					PropertyUtils.setProperty(bean, name, value);
				}
			}
		}
	}

	public static Object getProperty(Object bean, String name) throws Exception {
		return PropertyUtils.getProperty(bean, name);
	}
}
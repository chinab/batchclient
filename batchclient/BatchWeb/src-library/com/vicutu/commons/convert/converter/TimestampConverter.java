package com.vicutu.commons.convert.converter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

@SuppressWarnings("rawtypes")
public class TimestampConverter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Timestamp) {
			return value;
		}

		if (value instanceof Date) {
			return new Timestamp(((Date) value).getTime());
		}

		if (value instanceof Time) {
			return new Timestamp(((Time) value).getTime());
		}

		if (value instanceof Long) {
			return new Timestamp(((Long) value).longValue());
		}

		String item = value.toString();
		try {
			if (item.equals("")) {
				return null;
			} else {
				return new Timestamp(Long.parseLong(item));
			}
		} catch (Throwable e) {
			try {
				return Timestamp.valueOf(item);
			} catch (Exception ex) {
				throw new ConversionException(ex);
			}
		}
	}
}
package com.vicutu.commons.convert.converter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

@SuppressWarnings("rawtypes")
public class DateConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Date) {
			return value;
		}

		if (value instanceof Time) {
			return new Date(((Time) value).getTime());
		}

		if (value instanceof Timestamp) {
			return new Date(((Timestamp) value).getTime());
		}

		if (value instanceof Long) {
			return new Date(((Long) value).longValue());
		}

		String item = value.toString();
		try {
			if (item.equals("")) {
				return null;
			} else {
				return new Date(Long.parseLong(item));
			}
		} catch (Throwable e) {
			try {
				return Date.valueOf(item);
			} catch (Exception ex) {
				throw new ConversionException(ex);
			}
		}
	}
}
package com.vicutu.commons.convert.converter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class TimeConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Time) {
			return value;
		}

		if (value instanceof Date) {
			return new Time(((Date) value).getTime());
		}

		if (value instanceof Timestamp) {
			return new Time(((Timestamp) value).getTime());
		}

		if (value instanceof Long) {
			return new Time(((Long) value).longValue());
		}

		String item = value.toString();
		try {
			if (item.equals("")) {
				return null;
			} else {
				return new Time(Long.parseLong(item));
			}
		} catch (Throwable e) {
			try {
				return Time.valueOf(item);
			} catch (Exception ex) {
				throw new ConversionException(ex);
			}
		}
	}
}
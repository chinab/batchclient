package com.vicutu.commons.convert.converter;

public class LongConverter extends NumberConverter {
	protected Object convert(Object value) {
		if (value instanceof Long) {
			return value;
		} else if (value instanceof Number) {
			return new Long(((Number) value).longValue());
		}

		return new Long(value.toString());
	}
}
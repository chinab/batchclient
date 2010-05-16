package com.vicutu.commons.convert.converter;

public class IntegerConverter extends NumberConverter {
	protected Object convert(Object value) {
		if (value instanceof Integer) {
			return value;
		} else if (value instanceof Number) {
			return new Integer(((Number) value).intValue());
		}

		return new Integer(value.toString());
	}
}
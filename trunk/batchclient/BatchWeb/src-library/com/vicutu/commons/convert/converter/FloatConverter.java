package com.vicutu.commons.convert.converter;

public class FloatConverter extends NumberConverter {
	protected Object convert(Object value) {
		if (value instanceof Float) {
			return value;
		} else if (value instanceof Number) {
			return new Float(((Number) value).floatValue());
		}

		return new Float(value.toString());
	}
}
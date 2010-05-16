package com.vicutu.commons.convert.converter;

import java.math.BigDecimal;

public class BigDecimalConverter extends NumberConverter {
	protected Object convert(Object value) {
		if (value instanceof BigDecimal) {
			return value;
		}

		return new BigDecimal(value.toString());
	}
}
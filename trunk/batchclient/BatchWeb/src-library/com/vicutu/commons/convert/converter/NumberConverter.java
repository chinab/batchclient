package com.vicutu.commons.convert.converter;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("rawtypes")
public abstract class NumberConverter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof String && value.equals("")) {
			return null;
		}

		return convert(value);
	}

	protected abstract Object convert(Object value);
}
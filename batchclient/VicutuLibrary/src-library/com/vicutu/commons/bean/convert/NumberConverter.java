package com.vicutu.commons.bean.convert;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public abstract class NumberConverter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof String && value.equals("")) {
			return null;
		}

		return convert(value);
	}

	protected abstract Object convert(Object value);
}
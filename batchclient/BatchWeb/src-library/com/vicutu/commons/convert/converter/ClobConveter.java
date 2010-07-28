package com.vicutu.commons.convert.converter;

import java.sql.Clob;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("rawtypes")
public class ClobConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Clob) {
			return value;
		}

		if (value instanceof String) {
			return new ClobImpl((String) value);
		}

		if (value instanceof char[]) {
			return new ClobImpl((char[]) value);
		}

		if (value instanceof byte[]) {
			return new ClobImpl(new String((byte[]) value));
		}

		return new ClobImpl(value.toString());
	}
}
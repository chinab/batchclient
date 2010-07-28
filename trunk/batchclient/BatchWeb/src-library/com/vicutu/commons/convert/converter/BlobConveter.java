package com.vicutu.commons.convert.converter;

import java.sql.Blob;

import org.apache.commons.beanutils.Converter;

public class BlobConveter implements Converter {

	@SuppressWarnings("rawtypes")
	public Object convert(Class type, Object value) {
		if (value instanceof Blob) {
			return value;
		}

		if (value instanceof byte[]) {
			return new BlobImpl((byte[]) value);
		}

		if (value instanceof String) {
			return new BlobImpl((String) value);
		}

		if (value instanceof char[]) {
			return new BlobImpl(new String((char[]) value));
		}

		return new BlobImpl(value.toString());
	}
}
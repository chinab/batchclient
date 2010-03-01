package com.vicutu.commons.bean.convert;

import java.sql.Blob;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class BlobConveter implements Converter {

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
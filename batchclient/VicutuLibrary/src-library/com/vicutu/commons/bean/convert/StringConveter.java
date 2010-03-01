package com.vicutu.commons.bean.convert;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class StringConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof String) {
			return value;
		}

		if (value instanceof byte[]) {
			return new String((byte[]) value);
		}

		if (value instanceof char[]) {
			return new String((char[]) value);
		}

		if (value instanceof Clob) {
			try {
				Clob clob = (Clob) value;
				char[] buffer = new char[(int) clob.length()];
				clob.getCharacterStream().read(buffer);

				return new String(buffer);
			} catch (Exception ex) {
				throw new ConverterException(ex, value, type);
			}
		}

		if (value instanceof Blob) {
			try {
				Blob blob = (Blob) value;
				byte[] buffer = new byte[(int) blob.length()];
				blob.getBinaryStream().read(buffer);
				return new String(buffer);
			} catch (Exception ex) {
				throw new ConverterException(ex, value, type);
			}
		}

		if (value instanceof Time) {
			return String.valueOf(((Time) value).getTime());
		}

		if (value instanceof Date) {
			return String.valueOf(((Date) value).getTime());
		}

		if (value instanceof Timestamp) {
			return String.valueOf(((Timestamp) value).getTime());
		}

		return value.toString();
	}
}
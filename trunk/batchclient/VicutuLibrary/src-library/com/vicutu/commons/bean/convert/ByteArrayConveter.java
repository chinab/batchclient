package com.vicutu.commons.bean.convert;

import java.sql.Blob;
import java.sql.Clob;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class ByteArrayConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof byte[]) {
			return value;
		}

		if (value instanceof String) {
			return ((String) value).getBytes();
		}

		if (value instanceof Blob) {
			try {
				Blob blob = (Blob) value;
				byte[] buffer = new byte[(int) blob.length()];
				blob.getBinaryStream().read(buffer);
				return buffer;
			} catch (Exception ex) {
				throw new ConverterException(ex, value, type);
			}
		}

		if (value instanceof Clob) {
			try {
				Clob clob = (Clob) value;
				char[] buffer = new char[(int) clob.length()];
				clob.getCharacterStream().read(buffer);

				return new String(buffer).getBytes();
			} catch (Exception ex) {
				throw new ConverterException(ex, value, type);
			}
		}

		if (value instanceof char[]) {
			return new String((char[]) value).getBytes();
		}

		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;
			byte[] values = new byte[array.length];
			for (int i = 0; i < array.length; i++) {
				Object item = array[i];
				if (item instanceof Number) {
					values[i] = ((Number) item).byteValue();
				} else {
					values[i] = Byte.parseByte(item.toString());
				}
			}

			return values;
		}

		return value.toString().getBytes();
	}
}
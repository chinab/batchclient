package com.vicutu.commons.bean.convert;

import java.sql.Blob;
import java.sql.Clob;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class CharArrayConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof char[]) {
			return value;
		}

		if (value instanceof String) {
			return ((String) value).toCharArray();
		}

		if (value instanceof Clob) {
			try {
				Clob clob = (Clob) value;
				char[] buffer = new char[(int) clob.length()];
				clob.getCharacterStream().read(buffer);

				return buffer;
			} catch (Exception ex) {
				throw new ConverterException(ex, value, type);
			}
		}

		if (value instanceof Blob) {
			try {
				Blob blob = (Blob) value;
				byte[] buffer = new byte[(int) blob.length()];
				blob.getBinaryStream().read(buffer);
				return new String(buffer).toCharArray();
			} catch (Exception ex) {
				throw new ConverterException(ex, value, type);
			}
		}

		if (value instanceof byte[]) {
			return new String((byte[]) value).toCharArray();
		}

		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;
			char[] values = new char[array.length];
			for (int i = 0; i < array.length; i++) {
				Object item = array[i];
				if (item instanceof Character) {
					values[i] = ((Character) item).charValue();
				} else {
					values[i] = item.toString().charAt(0);
				}
			}

			return values;
		}

		return value.toString().toCharArray();
	}
}
package com.vicutu.commons.convert.converter;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("rawtypes")
public class LongArrayConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;

			long[] values = new long[array.length];
			for (int i = 0; i < array.length; i++) {
				Object item = array[i];
				if (item instanceof Number) {
					values[i] = ((Number) item).longValue();
				} else {
					values[i] = Long.parseLong(item.toString());
				}
			}

			return values;
		}

		return null;
	}
}
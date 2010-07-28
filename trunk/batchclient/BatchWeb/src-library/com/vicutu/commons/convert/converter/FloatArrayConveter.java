package com.vicutu.commons.convert.converter;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("rawtypes")
public class FloatArrayConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;

			float[] values = new float[array.length];
			for (int i = 0; i < array.length; i++) {
				Object item = array[i];
				if (item instanceof Number) {
					values[i] = ((Number) item).floatValue();
				} else {
					values[i] = Float.parseFloat(item.toString());
				}
			}

			return values;
		}

		return null;
	}
}
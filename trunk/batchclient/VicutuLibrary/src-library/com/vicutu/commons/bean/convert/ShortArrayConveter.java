package com.vicutu.commons.bean.convert;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class ShortArrayConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;

			short[] values = new short[array.length];
			for (int i = 0; i < array.length; i++) {
				Object item = array[i];
				if (item instanceof Number) {
					values[i] = ((Number) item).shortValue();
				} else {
					values[i] = Short.parseShort(item.toString());
				}
			}

			return values;
		}

		return null;
	}
}
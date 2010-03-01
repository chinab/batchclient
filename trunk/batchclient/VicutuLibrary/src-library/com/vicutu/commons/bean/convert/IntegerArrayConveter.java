package com.vicutu.commons.bean.convert;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class IntegerArrayConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;

			int[] values = new int[array.length];
			for (int i = 0; i < array.length; i++) {
				Object item = array[i];
				if (item instanceof Number) {
					values[i] = ((Number) item).intValue();
				} else {
					values[i] = Integer.parseInt(item.toString());
				}
			}

			return values;
		}

		return null;
	}
}
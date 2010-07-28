package com.vicutu.commons.convert.converter;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("rawtypes")
public class BooleanArrayConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;

			boolean[] values = new boolean[array.length];
			for (int i = 0; i < array.length; i++) {
				Object item = array[i];
				if (item instanceof Boolean) {
					values[i] = ((Boolean) item).booleanValue();
				} else if (item instanceof Number) {
					values[i] = ((Number) item).intValue() == 1;
				} else if (item instanceof String) {
					values[i] = ((String) item).equalsIgnoreCase(Boolean.TRUE.toString())
							|| ((String) item).equalsIgnoreCase("yes") || ((String) item).equalsIgnoreCase("on")
							|| ((String) item).equalsIgnoreCase("1");
				} else {
					values[i] = false;
				}
			}

			return values;
		}

		return null;
	}
}
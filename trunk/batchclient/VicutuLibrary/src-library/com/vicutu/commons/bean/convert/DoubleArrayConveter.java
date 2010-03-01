package com.vicutu.commons.bean.convert;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class DoubleArrayConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;

			double[] values = new double[array.length];
			for (int i = 0; i < array.length; i++) {
				Object item = array[i];
				if (item instanceof Number) {
					values[i] = ((Number) item).doubleValue();
				} else {
					values[i] = Double.parseDouble(item.toString());
				}
			}

			return values;
		}

		return null;
	}
}
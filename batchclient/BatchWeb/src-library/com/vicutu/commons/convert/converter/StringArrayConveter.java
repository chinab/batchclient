package com.vicutu.commons.convert.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("rawtypes")
public class StringArrayConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;

			String[] values = new String[array.length];
			for (int i = 0; i < array.length; i++) {
				Object item = array[i];

				if (item instanceof String) {
					values[i] = (String) item;
				} else {
					values[i] = item.toString();
				}
			}

			return values;
		}

		if (value instanceof String) {
			String stringValue = (String) value;

			if (stringValue.equals("")) {
				return new String[0];
			}

			List<String> list = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(stringValue, ",");
			while (st.hasMoreTokens()) {
				list.add(st.nextToken());
			}

			return (String[]) list.toArray(new String[list.size()]);
		}

		return null;
	}
}
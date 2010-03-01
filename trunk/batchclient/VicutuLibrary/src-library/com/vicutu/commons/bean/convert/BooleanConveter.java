package com.vicutu.commons.bean.convert;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class BooleanConveter implements Converter {
	public Object convert(Class type, Object value) {
		if (value instanceof Boolean) {
			return value;
		} else if (value instanceof Number) {
			return new Boolean(((Number) value).intValue() == 1);
		} else {
			String svalue = value.toString();
			if (svalue.equalsIgnoreCase("true")
					|| svalue.equalsIgnoreCase("yes")
					|| svalue.equalsIgnoreCase("on")
					|| svalue.equalsIgnoreCase("1")) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		}
	}
}
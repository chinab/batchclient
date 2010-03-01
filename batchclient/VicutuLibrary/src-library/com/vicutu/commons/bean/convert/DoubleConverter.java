package com.vicutu.commons.bean.convert;

public class DoubleConverter extends NumberConverter
{
	protected Object convert(Object value)
	{
		if (value instanceof Double)
		{
			return value;
		}
		else if (value instanceof Number)
		{
			return new Double(((Number) value).doubleValue());
		}

		return new Double(value.toString());
	}
}
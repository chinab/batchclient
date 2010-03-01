package com.vicutu.commons.bean.convert;

public class LongConverter extends NumberConverter
{
	protected Object convert(Object value)
	{
		if (value instanceof Long)
		{
			return value;
		}
		else if (value instanceof Number)
		{
			return new Long(((Number) value).longValue());
		}

		return new Long(value.toString());
	}
}
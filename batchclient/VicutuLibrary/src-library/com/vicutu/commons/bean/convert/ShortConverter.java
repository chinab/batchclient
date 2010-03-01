package com.vicutu.commons.bean.convert;

public class ShortConverter extends NumberConverter
{
	protected Object convert(Object value)
	{
		if (value instanceof Short)
		{
			return value;
		}
		else if (value instanceof Number)
		{
			return new Short(((Number) value).shortValue());
		}

		return new Short(value.toString());
	}
}
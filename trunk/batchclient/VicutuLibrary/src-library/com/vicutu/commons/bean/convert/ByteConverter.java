package com.vicutu.commons.bean.convert;

public class ByteConverter extends NumberConverter
{
	protected Object convert(Object value)
	{
		if (value instanceof Byte)
		{
			return value;
		}
		else if (value instanceof Number)
		{
			return new Byte(((Number) value).byteValue());
		}

		return new Byte(value.toString());
	}
}
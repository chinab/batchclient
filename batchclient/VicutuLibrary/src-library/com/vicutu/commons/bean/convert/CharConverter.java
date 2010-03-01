package com.vicutu.commons.bean.convert;

public class CharConverter extends NumberConverter
{
	protected Object convert(Object value)
	{
		if (value instanceof Character)
		{
			return value;
		}
		else if (value instanceof String)
		{
			return new Character(((String) value).charAt(0));
		}
		else if (value instanceof Number)
		{
			return new Character((char) ((Number) value).intValue());
		}

		return new Character(value.toString().charAt(0));
	}
}
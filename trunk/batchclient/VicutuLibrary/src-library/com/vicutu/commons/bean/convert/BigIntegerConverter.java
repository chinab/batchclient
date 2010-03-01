package com.vicutu.commons.bean.convert;

import java.math.BigInteger;

public class BigIntegerConverter extends NumberConverter
{
	protected Object convert(Object value)
	{
		if (value instanceof BigInteger)
		{
			return value;
		}

		return new BigInteger(value.toString());
	}
}
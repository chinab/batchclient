package com.vicutu.commons.bean.convert;

import java.math.BigDecimal;

public class BigDecimalConverter extends NumberConverter
{
	protected Object convert(Object value)
	{
		if (value instanceof BigDecimal)
		{
			return value;
		}

		return new BigDecimal(value.toString());
	}
}
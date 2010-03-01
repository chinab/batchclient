package com.vicutu.commons.lang;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtils
{
	public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";

	public static final String DEFAULT_FORMAT_TIME = "HH:mm:ss.SSS";

	public static final String DEFAULT_FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private DateUtils()
	{
	}

	public static String formatDate(long time)
	{
		return formatDate(time, DEFAULT_FORMAT);
	}

	public static Date parseDate(String time)
	{
		return parseDate(time, DEFAULT_FORMAT);
	}

	public static Date parseDate(String time, String format)
	{
		if (format == null)
		{
			format = DEFAULT_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		try
		{
			return sdf.parse(time);
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}

	public static String formatDate(long time, String format)
	{
		Date date = new Date(time);
		if (format == null)
		{
			format = DEFAULT_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		return sdf.format(date);
	}
}
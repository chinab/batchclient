package com.vicutu.commons.lang;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.vicutu.commons.bean.BeanUtils;

public final class StringUtils extends org.apache.commons.lang.StringUtils {
	private static final char DELIM_START = '{';

	private static final char DELIM_STOP = '}';

	private StringUtils() {
	}

	public static String formatMessage(String messagePattern, Object[] args) {
		if (args == null || args.length == 0) {
			return messagePattern;
		}

		if (messagePattern == null) {
			return null;
		}

		int i = 0;
		int len = messagePattern.length();
		int j = messagePattern.indexOf(DELIM_START);

		StringBuffer sbuf = new StringBuffer(messagePattern.length() + 50);

		for (int k = 0; k < args.length; k++) {
			char escape = 'x';

			j = messagePattern.indexOf(DELIM_START, i);

			if (j == -1 || (j + 1 == len)) {
				if (i == 0) {
					return messagePattern;
				} else {
					sbuf.append(messagePattern.substring(i, messagePattern
							.length()));
					return sbuf.toString();
				}
			} else {
				char delimStop = messagePattern.charAt(j + 1);
				if (j > 0) {
					escape = messagePattern.charAt(j - 1);
				}

				if (escape == '\\') {
					k--;
					sbuf.append(messagePattern.substring(i, j - 1));
					sbuf.append(DELIM_START);
					i = j + 1;
				} else if ((delimStop != DELIM_STOP)) {
					sbuf.append(messagePattern.substring(i, messagePattern
							.length()));
					return sbuf.toString();
				} else {
					sbuf.append(messagePattern.substring(i, j));
					sbuf.append(args[k]);
					i = j + 2;
				}
			}
		}
		sbuf.append(messagePattern.substring(i, messagePattern.length()));
		return sbuf.toString();
	}

	public static String printException(Throwable exception) {
		StringWriter sw = new StringWriter();
		PrintWriter writer = new PrintWriter(sw);

		try {
			exception.printStackTrace(writer);
			return sw.getBuffer().toString();
		} finally {
			writer.close();
		}
	}

	public static String escapeSqlLike(String value, String escape) {
		if (escape != null && value != null) {
			value = StringUtils.replace(value, "_", "/_");
			value = StringUtils.replace(value, "%", "/%");
			value = StringUtils.replace(value, "?", "_");
			value = StringUtils.replace(value, "*", "%");
		}

		return value;
	}

	public static String escapeSqlWhere(String condition, String escape) {
		if (escape != null) {
			return condition + " escape '" + escape + "'";
		} else {
			return condition;
		}
	}

	// ------------------------------------------------------------------------//
	// --type convert
	// ------------------------------------------------------------------------//

	public static String getString(Object value, String defaultValue) {
		if (value == null) {
			return defaultValue;
		} else {
			if (value instanceof String) {
				return (String) value;
			} else {
				return value.toString();
			}
		}
	}

	public static int getInteger(Object value, int defaultValue) {
		if (value == null) {
			return defaultValue;
		} else {
			if (value instanceof Number) {
				return ((Number) value).intValue();
			} else if (value instanceof String) {
				String stringValue = (String) value;
				return stringValue.equals("") ? defaultValue : Integer
						.parseInt(stringValue);
			} else {
				return defaultValue;
			}
		}
	}

	public static long getLong(Object value, long defaultValue) {
		if (value == null) {
			return defaultValue;
		} else {
			if (value instanceof Number) {
				return ((Number) value).longValue();
			} else if (value instanceof String) {
				String stringValue = (String) value;
				return stringValue.equals("") ? defaultValue : Long
						.parseLong(stringValue);
			} else {
				return defaultValue;
			}
		}
	}

	public static double getDouble(Object value, double defaultValue) {
		if (value == null) {
			return defaultValue;
		} else {
			if (value instanceof Number) {
				return ((Number) value).doubleValue();
			} else if (value instanceof String) {
				String stringValue = (String) value;
				return stringValue.equals("") ? defaultValue : Double
						.parseDouble(stringValue);
			} else {
				return defaultValue;
			}
		}
	}

	public static boolean getBoolean(Object value, boolean defaultValue) {
		if (value == null) {
			return defaultValue;
		} else {
			if (value instanceof Boolean) {
				return ((Boolean) value).booleanValue();
			} else if (value instanceof String) {
				String stringValue = (String) value;
				return stringValue.equals("") ? defaultValue : stringValue
						.equalsIgnoreCase(Boolean.TRUE.toString());
			} else if (value instanceof Number) {
				return ((Number) value).intValue() > 0;
			} else {
				return defaultValue;
			}
		}
	}

	public static boolean hasText(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return false;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}
	public static String[] getStringArray(String value, String[] defaultValue)
	{
		if (value == null || value.equals(""))
		{
			return defaultValue;
		}
		else
		{
			return (String[]) BeanUtils.convert(value, String[].class);
		}
	}
}
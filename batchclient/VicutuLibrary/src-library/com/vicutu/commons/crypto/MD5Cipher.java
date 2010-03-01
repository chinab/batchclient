package com.vicutu.commons.crypto;

import java.security.MessageDigest;

import com.vicutu.commons.exception.BaseRuntimeException;

public class MD5Cipher
{
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString(byte b[])
	{
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
		{
			resultSb.append(byteToHexString(b[i]));
		}

		return resultSb.toString();
	}

	private static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
		{
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return (new StringBuffer(String.valueOf(hexDigits[d1]))).append(hexDigits[d2]).toString();
	}

	public static String toMD5Encode(String origin)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			return byteArrayToHexString(md.digest(origin.getBytes()));
		}
		catch (Throwable ex)
		{
			throw new BaseRuntimeException(ex);
		}
	}
}
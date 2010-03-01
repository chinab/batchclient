package com.vicutu.commons.lang;

public class SystemUtils
{
	public static String getAddress()
	{
		try
		{
			return java.net.InetAddress.getLocalHost().getHostAddress();
		}
		catch (Exception ex)
		{
			return "localhost";
		}
	}
}

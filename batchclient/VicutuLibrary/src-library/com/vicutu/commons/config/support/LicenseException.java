package com.vicutu.commons.config.support;

import com.vicutu.commons.exception.BaseRuntimeException;

public class LicenseException extends BaseRuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8915497109395788700L;

	public LicenseException(String msg, Throwable ex)
	{
		super(msg, ex);
	}

	public LicenseException(String msg)
	{
		super(msg);
	}
}
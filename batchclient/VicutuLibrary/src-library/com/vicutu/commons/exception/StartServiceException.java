package com.vicutu.commons.exception;

public class StartServiceException extends BaseRuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 430689150460832132L;

	public StartServiceException(Throwable ex, String serviceName)
	{
		super(ex);
		this.addAttribute("serviceName", serviceName);
	}
}
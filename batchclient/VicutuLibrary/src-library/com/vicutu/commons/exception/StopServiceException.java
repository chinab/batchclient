package com.vicutu.commons.exception;

public class StopServiceException extends BaseRuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4808346109084734658L;

	public StopServiceException(Throwable ex, String serviceName)
	{
		super(ex);
		this.addAttribute("serviceName", serviceName);
	}
}
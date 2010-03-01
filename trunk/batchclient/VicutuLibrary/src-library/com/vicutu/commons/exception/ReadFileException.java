package com.vicutu.commons.exception;

public class ReadFileException extends BaseRuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1248964114854968214L;

	public ReadFileException(Throwable ex, String fileName)
	{
		super(ex);
		addAttribute("fileName", fileName);
	}
}
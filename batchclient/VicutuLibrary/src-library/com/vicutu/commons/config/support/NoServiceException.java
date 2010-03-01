package com.vicutu.commons.config.support;

import com.vicutu.commons.exception.NoResourceException;


public class NoServiceException extends NoResourceException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6232340524776526157L;

	public NoServiceException(String resourceName)
	{
		super(resourceName);
	}
}
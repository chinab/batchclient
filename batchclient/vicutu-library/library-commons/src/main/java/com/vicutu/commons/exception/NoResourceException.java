package com.vicutu.commons.exception;

public class NoResourceException extends BaseRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6268341986014785996L;

	public NoResourceException(String resourceName) {
		addAttribute("resourceName", resourceName);
	}

	public NoResourceException(String resourceName, String resourceOwner) {
		addAttribute("resourceName", resourceName);
		addAttribute("resourceOwner", resourceOwner);
	}
}
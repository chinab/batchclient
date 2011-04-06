package com.vicutu.commons.exception;

public class CreateObjectException extends BaseRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -262785212865460471L;

	public CreateObjectException(Throwable ex, String className) {
		super("occur error when create object " + className, ex);
		addAttribute("className", className);
	}
}
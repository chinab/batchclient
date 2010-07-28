package com.vicutu.commons.convert.converter;

import com.vicutu.commons.exception.BaseRuntimeException;

@SuppressWarnings("rawtypes")
public class ConverterException extends BaseRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4939089745541093166L;

	public ConverterException(Throwable ex, Object data, Class clazz) {
		super(ex);
		this.addAttribute("data", data == null ? "" : data.toString());
		this.addAttribute("class", clazz.getName());
	}
}
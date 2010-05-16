package com.vicutu.commons.exception;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.exception.NestableRuntimeException;
import org.dom4j.Branch;
import org.dom4j.Element;

import com.vicutu.commons.lang.IReadable;
import com.vicutu.commons.lang.StringUtils;
import com.vicutu.commons.xml.XmlUtils;

@SuppressWarnings("unchecked")
public class BaseRuntimeException extends NestableRuntimeException implements IReadable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2324732128001447536L;

	private String id;

	private String description;

	private Map attributes = new HashMap();

	public BaseRuntimeException() {
		super("");
	}

	public BaseRuntimeException(String message, Throwable ex) {
		super(message, ex);
		this.description = message;
	}

	public BaseRuntimeException(String message) {
		super(message);
		this.description = message;
	}

	public BaseRuntimeException(Throwable ex) {
		super(ex);
	}

	public BaseRuntimeException(Map attributes) {
		this.attributes = attributes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	public void buildElement(Branch parent) throws Exception {
		Element rootElement = parent.addElement("exception");
		XmlUtils.addAttribute(rootElement, "id", id);
		XmlUtils.setCDATA(rootElement, "description", description);
		XmlUtils.setCDATA(rootElement, "message", getMessage());
		XmlUtils.setCDATA(rootElement, "stack", StringUtils.printException(this));
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		Iterator iter = attributes.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			Object value = attributes.get(name);
			sb.append("[").append(name).append("]=").append(value).append("\n");
		}

		sb.append(super.toString());

		return sb.toString();
	}
}
package com.vicutu.persistence.dictionary;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class DictionaryBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5321972190613579421L;

	private String label;

	private String value;

	public DictionaryBean(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public boolean equals(Object obj) {
		if (obj instanceof DictionaryBean) {
			DictionaryBean anObject = (DictionaryBean) obj;
			if (this.getLabel().equals(anObject.getLabel()) && this.getValue().equals(anObject.getValue())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}

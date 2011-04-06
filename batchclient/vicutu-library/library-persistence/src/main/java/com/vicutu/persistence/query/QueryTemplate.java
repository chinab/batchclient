package com.vicutu.persistence.query;

import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

public interface QueryTemplate {

	String getName();

	void setName(String name);

	Object getValue(String name);

	void setValue(String name, Object value);

	void setValues(Object pojo);

	void setValues(Map<?, ?> m);

	String getClazz();

	void setClazz(String clazz);

	DetachedCriteria buildDetachedCriteria();

}
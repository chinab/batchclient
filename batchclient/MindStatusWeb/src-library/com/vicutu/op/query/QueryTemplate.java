package com.vicutu.op.query;

import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

public interface QueryTemplate {

	public abstract String getName();

	public abstract void setName(String name);

	public abstract Object getValue(int index);

	public abstract Object getValue(String name);

	public abstract void setValue(int index, Object value);

	public abstract void setValue(String name, Object value);
	
	public abstract void setValues(Object pojo);
	
	public abstract void setValues(Map<?,?> m);

	public abstract String getClazz();

	public abstract void setClazz(String clazz);
	
	public abstract DetachedCriteria buildDetachedCriteria();

}
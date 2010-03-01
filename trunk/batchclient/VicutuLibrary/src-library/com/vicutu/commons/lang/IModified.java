package com.vicutu.commons.lang;

public interface IModified
{
	long getLastModified();

	void setLastModified(long lastModified);

	boolean isModified(long lastModified);
}
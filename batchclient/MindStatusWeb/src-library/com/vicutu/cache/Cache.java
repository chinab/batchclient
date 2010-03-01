package com.vicutu.cache;

import java.util.Iterator;

public interface Cache
{

	void put(String key, Object value);

	Object get(String key);

	Object get(String key, Object[] parameters);

	void remove(String key);

	void removeAll();

	Iterator<String> keySet();
	
	void update(Object parameter);

	int size();

}
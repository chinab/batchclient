package com.vicutu.cache;

import java.util.Iterator;

public interface CacheManager
{
	Cache get(String name);

	Iterator<String> keySet();

	void remove(String name);
	
	void removeAll();
}
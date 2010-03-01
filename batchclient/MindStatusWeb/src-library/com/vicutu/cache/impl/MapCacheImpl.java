package com.vicutu.cache.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.vicutu.cache.Cache;
import com.vicutu.cache.CacheDataProvider;

public class MapCacheImpl implements Cache{
	
	private Map<String,Object> mapCache=new HashMap<String,Object>();
	
	private CacheDataProvider cacheDataProvider;
	
	public void setCacheDataProvider(CacheDataProvider cacheDataProvider) {
		this.cacheDataProvider = cacheDataProvider;
	}

	public void init(){
		if(cacheDataProvider!=null){
			cacheDataProvider.fill(this);
		}
	}

	
	public Object get(String key) {
		return mapCache.get(key);
	}

	
	public Object get(String key, Object[] parameters) {
		throw new UnsupportedOperationException();
	}

	
	public Iterator<String> keySet() {
		return mapCache.keySet().iterator();
	}

	
	public void put(String key, Object value) {
		mapCache.put(key, value);
		
	}

	
	public void remove(String key) {
		mapCache.remove(key);
		
	}

	
	public void removeAll() {
		mapCache.clear();
		
	}

	
	public int size() {
		return mapCache.size();
	}

	
	public void update(Object parameter) {
		cacheDataProvider.update(this, parameter);
	}

}

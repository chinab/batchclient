package com.vicutu.cache.impl;

import java.util.Iterator;
import java.util.Map;

import com.vicutu.cache.Cache;
import com.vicutu.cache.CacheManager;

public class MapCacheManagerImpl implements CacheManager {
	private Map<String, Cache> caches;

	public void setCaches(Map<String, Cache> caches) {
		this.caches = caches;
	}

	public Cache get(String name) {
		return caches.get(name);
	}

	public Iterator<String> keySet() {
		return caches.keySet().iterator();
	}

	public void remove(String name) {
		Cache cache = caches.get(name);
		if (cache != null) {
			caches.remove(name);
		}
	}

	public void destroy() throws Throwable {
		caches.clear();
	}

	public void removeAll() {
		caches.clear();
	}
}
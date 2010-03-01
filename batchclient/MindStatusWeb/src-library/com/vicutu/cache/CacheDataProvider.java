package com.vicutu.cache;

public interface CacheDataProvider {
	
	void fill(Cache cache);
	
	void clear(Cache cache);
	
	void update(Cache cache,Object parameter);
}

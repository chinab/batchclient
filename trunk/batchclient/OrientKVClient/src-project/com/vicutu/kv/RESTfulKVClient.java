package com.vicutu.kv;

import java.io.Serializable;
import java.net.URI;

public interface RESTfulKVClient {

	<T extends Serializable> T get(URI uri, Class<T> clazz);

	int put(URI uri, Serializable ser);

	int post(URI uri, Serializable ser);

	int delete(URI uri);
}

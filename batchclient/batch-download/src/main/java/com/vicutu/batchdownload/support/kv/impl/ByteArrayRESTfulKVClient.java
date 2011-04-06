package com.vicutu.batchdownload.support.kv.impl;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.apache.http.HttpEntity;
import org.apache.http.entity.SerializableEntity;

import com.vicutu.batchdownload.support.kv.AbstractRESTfulKVClient;
import com.vicutu.batchdownload.support.kv.RESTfulKVClient;

public class ByteArrayRESTfulKVClient extends AbstractRESTfulKVClient implements RESTfulKVClient {

	private boolean buffered;

	public void setBuffered(boolean buffered) {
		this.buffered = buffered;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T extends Serializable> T decode(HttpEntity httpEntity, Class<T> clazz) throws Exception {
		return (T) (new ObjectInputStream(new BufferedInputStream(httpEntity.getContent()))).readObject();
	}

	@Override
	protected HttpEntity encode(Serializable ser) throws Exception {
		return new SerializableEntity(ser, buffered);
	}
}

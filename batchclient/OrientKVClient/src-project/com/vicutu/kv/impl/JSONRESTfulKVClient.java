package com.vicutu.kv.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.vicutu.kv.AbstractRESTfulKVClient;
import com.vicutu.kv.RESTfulKVClient;

public class JSONRESTfulKVClient extends AbstractRESTfulKVClient implements RESTfulKVClient {

	@Override
	protected <T extends Serializable> T decode(HttpEntity httpEntity, Class<T> clazz) throws Exception {
		Gson gson = new Gson();
		return gson.fromJson(new BufferedReader(new InputStreamReader(httpEntity.getContent())), clazz);
	}

	@Override
	protected HttpEntity encode(Serializable ser) throws Exception {
		Gson gson = new Gson();
		return new StringEntity(gson.toJson(ser), "UTF-8");
	}
}

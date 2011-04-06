package com.vicutu.batchdownload.support.kv;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import com.vicutu.batchdownload.utils.HttpUtils;
import com.vicutu.commons.exception.BaseRuntimeException;

public abstract class AbstractRESTfulKVClient implements RESTfulKVClient {

	protected HttpClient httpClient;

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public <T extends Serializable> T get(URI uri, Class<T> clazz) {
		HttpResponse httpResponse = null;
		try {
			httpResponse = HttpUtils.excuteRequest(httpClient, new HttpGet(uri), new BasicHttpContext());
			if (HttpStatus.SC_OK != httpResponse.getStatusLine().getStatusCode()) {
				return null;
			}
			HttpEntity httpEntity = httpResponse.getEntity();
			return httpEntity == null ? null : decode(httpEntity, clazz);
		} catch (Exception e) {
			consumeContentQuietly(httpResponse);
			throw new BaseRuntimeException(e);
		}
	}

	@Override
	public int put(URI uri, Serializable ser) {
		HttpResponse httpResponse = null;
		try {
			HttpEntity requestHttpEntity = encode(ser);
			httpResponse = HttpUtils.executeRequestWithEntity(httpClient, new HttpPut(uri), new BasicHttpContext(),
					requestHttpEntity);
			return httpResponse.getStatusLine().getStatusCode();
		} catch (Exception e) {
			throw new BaseRuntimeException(e);
		} finally {
			consumeContentQuietly(httpResponse);
		}
	}

	@Override
	public int post(URI uri, Serializable ser) {
		HttpResponse httpResponse = null;
		try {
			HttpEntity requestHttpEntity = encode(ser);
			httpResponse = HttpUtils.executeRequestWithEntity(httpClient, new HttpPost(uri), new BasicHttpContext(),
					requestHttpEntity);
			return httpResponse.getStatusLine().getStatusCode();
		} catch (Exception e) {
			throw new BaseRuntimeException(e);
		} finally {
			consumeContentQuietly(httpResponse);
		}
	}

	@Override
	public int delete(URI uri) {
		HttpResponse httpResponse = null;
		try {
			httpResponse = HttpUtils.excuteRequest(httpClient, new HttpDelete(uri), new BasicHttpContext());
			return httpResponse.getStatusLine().getStatusCode();
		} catch (Exception e) {
			throw new BaseRuntimeException(e);
		} finally {
			consumeContentQuietly(httpResponse);
		}
	}

	private static void consumeContentQuietly(HttpResponse httpResponse) {
		if (httpResponse != null) {
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				try {
					EntityUtils.consume(httpEntity);
				} catch (IOException e1) {
				}
			}
		}
	}

	protected abstract HttpEntity encode(Serializable ser) throws Exception;

	protected abstract <T extends Serializable> T decode(HttpEntity httpEntity, Class<T> clazz) throws Exception;
}

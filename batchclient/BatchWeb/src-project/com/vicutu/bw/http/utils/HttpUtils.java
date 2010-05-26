package com.vicutu.bw.http.utils;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	private HttpUtils() {
	}

	public static String downloadHtml(HttpClient httpClient, String linkUrl) throws Exception {
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet(linkUrl);
		HttpResponse response = null;
		String htmlStr = null;
		HttpEntity entity = null;
		try {
			response = httpClient.execute(httpget, localContext);
			entity = response.getEntity();
			if (entity != null) {
				htmlStr = EntityUtils.toString(entity);
			}
		} catch (Exception e2) {
			try {
				if (entity != null) {
					entity.consumeContent();
				}
			} catch (IOException e1) {
			}
			httpget.abort();
			throw e2;
		}
		return htmlStr;
	}

	public static long download(HttpClient httpClient, String linkUrl, OutputStream outputStream) throws Exception {
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet(linkUrl);
		HttpResponse response = null;
		long byteCount = 0L;
		HttpEntity entity = null;

		try {
			response = httpClient.execute(httpget, localContext);
			entity = response.getEntity();
			if (entity != null) {
				byteCount = IOUtils.copy(entity.getContent(), outputStream);
			}
		} catch (Exception e2) {
			try {
				if (entity != null) {
					entity.consumeContent();
				}
			} catch (IOException e1) {
			}
			httpget.abort();
			throw e2;
		}
		return byteCount;
	}
	
	public static void registerHttpProxy(HttpClient httpClient,String scheme,String host,int port, String username,String password){
		HttpHost proxy = new HttpHost(host, port, scheme);
		((DefaultHttpClient)httpClient).getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}
}

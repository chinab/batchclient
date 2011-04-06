package com.vicutu.batchdownload.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	private HttpUtils() {
	}

	public static boolean executeLogin(HttpClient httpClient, HttpUriRequest request, Map<String, String> parameters,
			ResponseHandler<Boolean> handler) throws Exception {
		return executeLogin(httpClient, request, parameters, null, handler);
	}

	public static boolean executeLogin(HttpClient httpClient, HttpUriRequest request, Map<String, String> parameters,
			HttpContext httpContext, ResponseHandler<Boolean> handler) throws Exception {
		if (request instanceof HttpEntityEnclosingRequestBase && parameters != null && !parameters.isEmpty()) {
			((HttpEntityEnclosingRequestBase) request).setEntity(new UrlEncodedFormEntity(
					convertToNameValuePair(parameters), HTTP.UTF_8));
		}
		if (httpContext != null) {
			return httpClient.execute(request, handler).booleanValue();
		} else {
			return httpClient.execute(request, handler, httpContext).booleanValue();
		}
	}

	public static String downloadHtml(HttpClient httpClient, String linkUrl) throws Exception {
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet(linkUrl);
		HttpResponse response = httpClient.execute(httpget, localContext);
		return EntityUtils.toString(response.getEntity());
	}

	public static long download(HttpClient httpClient, String linkUrl, OutputStream outputStream) throws Exception {
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet(linkUrl);
		HttpResponse response = httpClient.execute(httpget, localContext);
		return IOUtils.copy(response.getEntity().getContent(), outputStream);
	}

	public static HttpResponse executePostWithParameters(HttpClient httpClient, HttpPost httpPost,
			HttpContext httpContext, Map<String, String> parameters) throws Exception {
		return executeRequestWithEntity(httpClient, httpPost, httpContext, new UrlEncodedFormEntity(
				convertToNameValuePair(parameters), HTTP.UTF_8));
	}

	public static HttpResponse executeRequestWithEntity(HttpClient httpClient, HttpEntityEnclosingRequestBase request,
			HttpContext httpContext, HttpEntity entity) throws Exception {
		request.setEntity(entity);
		return excuteRequest(httpClient, request, httpContext);
	}

	public static HttpResponse excuteRequest(HttpClient httpClient, HttpUriRequest request, HttpContext httpContext)
			throws Exception {
		HttpResponse response = null;
		try {
			if (httpContext != null) {
				response = httpClient.execute(request, httpContext);
			} else {
				response = httpClient.execute(request);
			}
		} catch (Exception e) {
			try {
				if (response != null) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						EntityUtils.consume(entity);
					}
				}
			} catch (IOException e1) {
			}
			request.abort();
			throw e;
		}
		return response;
	}

	public static void registerHttpProxy(HttpClient httpClient, String scheme, String host, int port, String username,
			String password) {
		DefaultHttpClient defaultHttpClient = (DefaultHttpClient) httpClient;
		setCredentials(defaultHttpClient, host, port, username, password);
		HttpHost proxy = new HttpHost(host, port, scheme);
		defaultHttpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}

	public static void setCredentials(HttpClient httpClient, String host, int port, String username, String password) {
		DefaultHttpClient defaultHttpClient = (DefaultHttpClient) httpClient;
		defaultHttpClient.getCredentialsProvider().setCredentials(new AuthScope(host, port),
				new UsernamePasswordCredentials(username, password));
	}

	private static List<NameValuePair> convertToNameValuePair(Map<String, String> parameters) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (parameters != null && !parameters.isEmpty()) {
			Set<Entry<String, String>> entrys = parameters.entrySet();
			for (Entry<String, String> entry : entrys) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		return nvps;
	}
}

package com.vicutu.batchdownload.http.params;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class HttpParamUtils {

	private HttpParamUtils() {
	}

	public static HttpParams getDefaultHttpParam() {
		HttpParams params = new BasicHttpParams();
		fillDefaultHttpParam(params);
		return params;
	}

	public static void fillDefaultHttpParam(HttpParams params) {
		DefaultHttpClient.setDefaultHttpParams(params);
	}
}

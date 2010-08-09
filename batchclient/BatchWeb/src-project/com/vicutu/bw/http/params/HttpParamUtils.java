package com.vicutu.bw.http.params;

import org.apache.http.HttpVersion;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.VersionInfo;

import com.vicutu.commons.lang.ClassLoaderUtils;

public class HttpParamUtils {

	private HttpParamUtils() {
	}

	public static HttpParams getDefaultHttpParam() {
		HttpParams params = new BasicHttpParams();
		fillDefaultHttpParam(params);
		return params;
	}

	public static void fillDefaultHttpParam(HttpParams params) {
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);
		HttpConnectionParams.setTcpNoDelay(params, true);
		HttpConnectionParams.setSocketBufferSize(params, 8192);

		// determine the release version from packaged version info
		final VersionInfo vi = VersionInfo.loadVersionInfo("org.apache.http.client", ClassLoaderUtils.getClassLoader());
		final String release = (vi != null) ? vi.getRelease() : VersionInfo.UNAVAILABLE;
		HttpProtocolParams.setUserAgent(params, "Apache-HttpClient/" + release + " (java 1.5)");
	}
}

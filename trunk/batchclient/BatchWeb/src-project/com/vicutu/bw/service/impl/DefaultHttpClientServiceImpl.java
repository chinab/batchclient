package com.vicutu.bw.service.impl;

import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.params.CookieSpecParamBean;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vicutu.bw.service.AccessDetailService;
import com.vicutu.bw.service.HttpClientService;
import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.commons.exception.BaseRuntimeException;

@Component("defaultHttpClientService")
public class DefaultHttpClientServiceImpl implements HttpClientService {

	protected AccessDetailService accessDetailService;

	@Autowired
	public void setAccessDetailService(AccessDetailService accessDetailService) {
		this.accessDetailService = accessDetailService;
	}

	@Override
	public HttpClient getHttpClient(String accessName, boolean threadSafe) throws Exception {

		DefaultHttpClient httpClient = null;
		if (threadSafe) {
			HttpParams params = new BasicHttpParams();
			ConnManagerParams.setMaxTotalConnections(params, 100);
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

			CookieSpecParamBean cookieParams = new CookieSpecParamBean(params);
			cookieParams.setSingleHeader(true);

			ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
			httpClient = new DefaultHttpClient(cm, params);
		} else {
			httpClient = new DefaultHttpClient();
		}

		AccessDetail accessDetail = accessDetailService.findAccessDetailByName(accessName);

		this.setupProxy(httpClient, accessDetail);

		boolean successful = this.login(httpClient, accessDetail);
		if (!successful) {
			throw new BaseRuntimeException("login failed");
		}
		return httpClient;
	}

	protected void setupProxy(DefaultHttpClient httpClient, AccessDetail accessDetail) {
		if (accessDetail.isUseProxy()) {
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(accessDetail.getProxyHost(), accessDetail.getProxyPort()),
					new UsernamePasswordCredentials(accessDetail.getProxyUsername(), accessDetail.getProxyPassword()));
			HttpHost proxyHttpHost = new HttpHost(accessDetail.getProxyHost(), accessDetail.getProxyPort());
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHttpHost);
		}
	}

	protected boolean login(DefaultHttpClient httpClient, AccessDetail accessDetail) throws Exception {
		return true;
	}
}

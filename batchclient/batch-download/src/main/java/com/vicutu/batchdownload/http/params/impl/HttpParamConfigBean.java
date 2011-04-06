package com.vicutu.batchdownload.http.params.impl;

import org.apache.http.HttpVersion;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParamBean;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParamBean;

import com.vicutu.batchdownload.http.params.HttpParamConfiguration;
import com.vicutu.batchdownload.http.params.HttpParamUtils;

public class HttpParamConfigBean implements HttpParamConfiguration {

	private final HttpParams params;

	private final HttpConnectionParamBean httpConnectionParamBean;

	private final HttpProtocolParamBean httpProtocolParamBean;

	private int maxConnectionsPerRoute = -1;

	private int maxTotalConnections = -1;

	public HttpParamConfigBean() {
		params = new BasicHttpParams();
		HttpParamUtils.fillDefaultHttpParam(params);
		httpConnectionParamBean = new HttpConnectionParamBean(params);
		httpProtocolParamBean = new HttpProtocolParamBean(params);
	}

	public void setConnectionTimeout(int connectionTimeout) {
		httpConnectionParamBean.setConnectionTimeout(connectionTimeout);
	}

	public void setLinger(int linger) {
		httpConnectionParamBean.setLinger(linger);
	}

	public void setSocketBufferSize(int socketBufferSize) {
		httpConnectionParamBean.setSocketBufferSize(socketBufferSize);
	}

	public void setSoTimeout(int soTimeout) {
		httpConnectionParamBean.setSoTimeout(soTimeout);
	}

	public void setStaleCheckingEnabled(boolean staleCheckingEnabled) {
		httpConnectionParamBean.setStaleCheckingEnabled(staleCheckingEnabled);
	}

	public void setTcpNoDelay(boolean tcpNoDelay) {
		httpConnectionParamBean.setTcpNoDelay(tcpNoDelay);
	}

	public void setContentCharset(String contentCharset) {
		httpProtocolParamBean.setContentCharset(contentCharset);
	}

	public void setHttpElementCharset(String httpElementCharset) {
		httpProtocolParamBean.setHttpElementCharset(httpElementCharset);
	}

	public void setUseExpectContinue(boolean useExpectContinue) {
		httpProtocolParamBean.setUseExpectContinue(useExpectContinue);
	}

	public void setUserAgent(String userAgent) {
		httpProtocolParamBean.setUserAgent(userAgent);
	}

	public void setVersion(String version) {
		if ("1.1".equals(version)) {
			httpProtocolParamBean.setVersion(HttpVersion.HTTP_1_1);
		} else if ("1.0".equals(version) || "1".equals(version)) {
			httpProtocolParamBean.setVersion(HttpVersion.HTTP_1_0);
		} else if ("0.9".equals(version)) {
			httpProtocolParamBean.setVersion(HttpVersion.HTTP_0_9);
		}
	}

	public void setMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
		this.maxConnectionsPerRoute = maxConnectionsPerRoute;
	}

	public void setMaxTotalConnections(int maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

	@Override
	public HttpParams getHttpParam() {
		return params;
	}

	@Override
	public int getMaxConnectionsPerRoute() {
		return maxConnectionsPerRoute;
	}

	@Override
	public int getMaxTotalConnections() {
		return maxTotalConnections;
	}
}

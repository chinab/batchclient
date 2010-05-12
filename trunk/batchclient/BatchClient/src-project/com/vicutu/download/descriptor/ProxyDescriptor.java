package com.vicutu.download.descriptor;

public class ProxyDescriptor {
	private boolean useProxy;

	private String proxyHost;

	private int porxyPort;

	private String proxyUserName;

	private String proxyPassword;

	public boolean isUseProxy() {
		return useProxy;
	}

	public void setUseProxy(boolean useProxy) {
		this.useProxy = useProxy;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public int getPorxyPort() {
		return porxyPort;
	}

	public void setPorxyPort(int porxyPort) {
		this.porxyPort = porxyPort;
	}

	public String getProxyUserName() {
		return proxyUserName;
	}

	public void setProxyUserName(String proxyUserName) {
		this.proxyUserName = proxyUserName;
	}

	public String getProxyPassword() {
		return proxyPassword;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}
}
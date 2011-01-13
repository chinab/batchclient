package com.vicutu.bw.vo;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Branch;
import org.dom4j.Element;

import com.vicutu.commons.lang.IReadable;
import com.vicutu.commons.xml.XmlUtils;

@Entity
@Table(name = "bw_access_detail")
public class AccessDetail implements Serializable, IReadable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2490926164490577847L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "availble")
	private boolean availble;

	@Column(name = "home_page")
	private String homePage;

	@Column(name = "base_url")
	private String baseUrl;

	@Column(name = "use_proxy")
	private boolean useProxy;

	@Column(name = "proxy_host")
	private String proxyHost;

	@Column(name = "proxy_port")
	private int proxyPort;

	@Column(name = "proxy_username")
	private String proxyUsername;

	@Column(name = "proxy_password")
	private String proxyPassword;

	@Column(name = "use_login")
	private boolean useLogin;

	@Column(name = "login_url")
	private String loginUrl;

	@Column(name = "login_username")
	private String loginUsername;

	@Column(name = "login_password")
	private String loginPassword;

	@Column(name = "login_refresh_url")
	private String loginRefreshUrl;

	@Column(name = "use_authorization")
	private boolean useAuthorization;

	@Column(name = "authorization_username")
	private String authorizationUsername;

	@Column(name = "authorization_password")
	private String authorizationPassword;

	@Column(name = "search_url")
	private String searchUrl;

	@Column(name = "save_path")
	private String savePath;

	@Column(name = "queue_length")
	private int queueLength;

	@Column(name = "single_http_client")
	private boolean singleHttpClient;

	@Column(name = "timeout")
	private long timeout;

	@Column(name = "interval")
	private long interval;

	@Column(name = "replace_exist")
	private boolean replaceExist;

	private boolean checkStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAvailble() {
		return availble;
	}

	public void setAvailble(boolean availble) {
		this.availble = availble;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

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

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getProxyUsername() {
		return proxyUsername;
	}

	public void setProxyUsername(String proxyUsername) {
		this.proxyUsername = proxyUsername;
	}

	public String getProxyPassword() {
		return proxyPassword;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}

	public boolean isUseLogin() {
		return useLogin;
	}

	public void setUseLogin(boolean useLogin) {
		this.useLogin = useLogin;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getLoginRefreshUrl() {
		return loginRefreshUrl;
	}

	public void setLoginRefreshUrl(String loginRefreshUrl) {
		this.loginRefreshUrl = loginRefreshUrl;
	}

	public boolean isUseAuthorization() {
		return useAuthorization;
	}

	public void setUseAuthorization(boolean useAuthorization) {
		this.useAuthorization = useAuthorization;
	}

	public String getAuthorizationUsername() {
		return authorizationUsername;
	}

	public void setAuthorizationUsername(String authorizationUsername) {
		this.authorizationUsername = authorizationUsername;
	}

	public String getAuthorizationPassword() {
		return authorizationPassword;
	}

	public void setAuthorizationPassword(String authorizationPassword) {
		this.authorizationPassword = authorizationPassword;
	}

	public String getSearchUrl() {
		return searchUrl;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getQueueLength() {
		return queueLength;
	}

	public void setQueueLength(int queueLength) {
		this.queueLength = queueLength;
	}

	public boolean isSingleHttpClient() {
		return singleHttpClient;
	}

	public void setSingleHttpClient(boolean singleHttpClient) {
		this.singleHttpClient = singleHttpClient;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public boolean isReplaceExist() {
		return replaceExist;
	}

	public void setReplaceExist(boolean replaceExist) {
		this.replaceExist = replaceExist;
	}

	public boolean isCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Override
	public String toString() {
		return XmlUtils.print(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildElement(Branch parent) throws Exception {
		Map<String, Object> properties = (Map<String, Object>) BeanUtils.describe(this);
		Element rootElement = parent.addElement("AccessDetail");
		Set<Entry<String, Object>> entries = properties.entrySet();
		for (Entry<String, Object> entry : entries) {
			XmlUtils.addElement(rootElement, entry.getKey(), entry.getValue());
		}
	}
}

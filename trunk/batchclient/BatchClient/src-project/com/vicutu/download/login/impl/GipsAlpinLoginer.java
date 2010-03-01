package com.vicutu.download.login.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.download.login.Loginer;

public class GipsAlpinLoginer implements Loginer {

	private static final Logger logger = LoggerFactory.getLogger(GipsAlpinLoginer.class);

	private String userName;

	private String password;

	private String refreshUrl;

	private String loginUrl;

	@Override
	public boolean login(HttpClient httpclient) {
		HttpPost httpost = new HttpPost(loginUrl);
		HttpGet httpget = new HttpGet(refreshUrl);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("flduser", userName));
		nvps.add(new BasicNameValuePair("fldpwd", password));

		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse response = httpclient.execute(httpost);

			List<Cookie> cookies = ((DefaultHttpClient) httpclient).getCookieStore().getCookies();
			if (cookies.isEmpty()) {
				logger.debug("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					logger.info("cookie {} : {}", i, cookies.get(i).toString());
				}
			}
			response.getEntity().consumeContent();

			response = httpclient.execute(httpget);
			logger.info(EntityUtils.toString(response.getEntity()));
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return true;
			} else {
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (ClientProtocolException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return false;

	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRefreshUrl(String refreshUrl) {
		this.refreshUrl = refreshUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
}

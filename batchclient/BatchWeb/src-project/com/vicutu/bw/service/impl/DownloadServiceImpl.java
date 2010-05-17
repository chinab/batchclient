package com.vicutu.bw.service.impl;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.vicutu.bw.service.DownloadService;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;

@Service
public class DownloadServiceImpl implements DownloadService {

	private final Logger logger = LoggerFactory.getLogger(DownloadServiceImpl.class);

	@Override
	public long download(HttpClient httpClient, String linkUrl, OutputStream outputStream,
			String authorizationUsername, String authorizationPassword) throws Exception {
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet(linkUrl);
		HttpResponse response = null;
		boolean trying = true;
		int byteCount = 0;
		HttpEntity entity = null;
		while (trying) {
			try {
				response = httpClient.execute(httpget, localContext);
				entity = response.getEntity();
				if (entity != null) {
					if (entity.getContentLength() > 0) {
						try {
							byteCount = IOUtils.copy(entity.getContent(), outputStream);
						} catch (Exception e) {
							entity.consumeContent();
							throw e;
						}
					} else {
						entity.consumeContent();
					}
				}

				int sc = response.getStatusLine().getStatusCode();

				AuthState authState = null;
				if (sc == HttpStatus.SC_UNAUTHORIZED) {
					// Target host authentication required
					authState = (AuthState) localContext.getAttribute(ClientContext.TARGET_AUTH_STATE);
				}
				if (sc == HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED) {
					// Proxy authentication required
					authState = (AuthState) localContext.getAttribute(ClientContext.PROXY_AUTH_STATE);
				}

				if (authState != null) {
					AuthScope authScope = authState.getAuthScope();

					if (authorizationUsername != null && authorizationUsername.length() > 0) {
						Credentials creds = new UsernamePasswordCredentials(authorizationUsername,
								authorizationPassword);
						((DefaultHttpClient) httpClient).getCredentialsProvider().setCredentials(authScope, creds);
						trying = true;
					} else {
						trying = false;
					}
				} else {
					trying = false;
				}
			} catch (Exception e2) {
				logger.error("occur error when get url: {}", linkUrl, e2);
				try {
					if (entity != null) {
						entity.consumeContent();
					}
				} catch (IOException e1) {
				}
				httpget.abort();
			}
		}
		return byteCount;
	}

	@Override
	public String downloadHtml(HttpClient httpClient, String linkUrl, String authorizationUsername,
			String authorizationPassword) throws Exception {
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet(linkUrl);
		boolean trying = true;
		HttpResponse response = null;
		String htmlStr = null;
		HttpEntity entity = null;
		while (trying) {
			try {
				response = httpClient.execute(httpget, localContext);
				entity = response.getEntity();
				if (entity != null) {

					if (response.getStatusLine().getStatusCode() != HttpStatus.SC_UNAUTHORIZED) {
						htmlStr = EntityUtils.toString(entity);
					} else {
						entity.consumeContent();
					}
				}

				int sc = response.getStatusLine().getStatusCode();

				AuthState authState = null;
				if (sc == HttpStatus.SC_UNAUTHORIZED) {
					authState = (AuthState) localContext.getAttribute(ClientContext.TARGET_AUTH_STATE);
				}
				if (sc == HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED) {
					authState = (AuthState) localContext.getAttribute(ClientContext.PROXY_AUTH_STATE);
				}

				if (authState != null) {
					AuthScope authScope = authState.getAuthScope();

					if (authorizationPassword != null && authorizationPassword.length() > 0) {
						Credentials creds = new UsernamePasswordCredentials(authorizationPassword,
								authorizationPassword);
						((DefaultHttpClient) httpClient).getCredentialsProvider().setCredentials(authScope, creds);
						trying = true;
					}
				} else {
					trying = false;
				}
			} catch (Exception e2) {
				try {
					if (entity != null) {
						entity.consumeContent();
					}
				} catch (IOException e1) {
				}
				httpget.abort();
			}
		}
		return htmlStr;
	}
}

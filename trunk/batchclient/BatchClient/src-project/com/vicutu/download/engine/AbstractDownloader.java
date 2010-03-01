package com.vicutu.download.engine;

import java.io.FileOutputStream;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;

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

import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.download.descriptor.AuthenticationDescriptor;
import com.vicutu.download.task.AtomicTask;

public abstract class AbstractDownloader implements Callable<Integer> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpClient httpClient;

	protected AuthenticationDescriptor authenticationDescriptor;

	protected Queue<AtomicTask> taskQueue;

	protected int successCount;

	protected Set<String> contentTypes;

	protected String name;

	public String getName() {
		return name;
	}

	protected void downloadImange(AtomicTask atomicTask) throws Exception {
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet(atomicTask.getUrl());
		HttpResponse response = null;
		FileOutputStream fos = null;
		boolean trying = true;
		boolean isSuccessful = false;
		int byteCount = 0;
		while (trying) {
			response = httpClient.execute(httpget, localContext);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				if (entity.getContentLength() > 0 && this.filtConentType(entity.getContentType().getValue())) {
					try {
						fos = FileUtils.openOutputStream(atomicTask.getSavePath());
						byteCount = IOUtils.copy(entity.getContent(), fos);
						isSuccessful = true;
					} catch (Exception e) {
						entity.consumeContent();
						throw e;
					} finally {
						IOUtils.closeQuietly(fos);
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
				String authUserName = authenticationDescriptor.getAuthUserName();
				if (authUserName != null && authUserName.length() > 0) {
					Credentials creds = new UsernamePasswordCredentials(authUserName, authenticationDescriptor
							.getAuthPassword());
					((DefaultHttpClient) httpClient).getCredentialsProvider().setCredentials(authScope, creds);
					trying = true;
				} else {
					trying = false;
				}
			} else {
				trying = false;
				if (isSuccessful) {
					successCount++;
					logger.info("success : {}\t{}", atomicTask.getUrl(), FileUtils.byteCountToDisplaySize(byteCount));
				} else {

					logger.info("fail : {}", atomicTask.getUrl());

				}
			}
		}
	}

	protected boolean filtConentType(String contentType) {
		//		if (contentTypes != null)
		//		{
		//			return contentTypes.contains(contentType);
		//		}
		//		else
		//		{
		//			return contentType.contains("image") ||;
		//		}
		return true;
	}
}
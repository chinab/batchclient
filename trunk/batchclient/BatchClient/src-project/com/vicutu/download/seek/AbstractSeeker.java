package com.vicutu.download.seek;

import java.io.IOException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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

import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.download.descriptor.AuthenticationDescriptor;
import com.vicutu.download.descriptor.TaskDescriptor;
import com.vicutu.download.http.HttpClientProvider;
import com.vicutu.download.task.AtomicTask;

public abstract class AbstractSeeker implements Seeker<Integer> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected boolean done;

	protected HttpClient httpClient;

	protected HttpClientProvider httpClientProvider;

	protected AuthenticationDescriptor authenticationDescriptor;

	protected BlockingQueue<AtomicTask> taskQueue;

	protected TaskDescriptor taskDescriptor;

	protected String category;

	protected int queueLength;

	protected boolean replaceExistFile;

	protected Set<String> contentTypes;

	public void initialize() {

		httpClient = httpClientProvider.getHttpClient();

		if (queueLength <= 0) {
			taskQueue = new LinkedBlockingQueue<AtomicTask>();
		} else {
			taskQueue = new LinkedBlockingQueue<AtomicTask>(queueLength);
		}
	}

	protected String getHtmlString(String linkUrl) {
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
					String authUserName = authenticationDescriptor.getAuthUserName();
					if (authUserName != null && authUserName.length() > 0) {
						Credentials creds = new UsernamePasswordCredentials(authUserName, authenticationDescriptor
								.getAuthPassword());
						((DefaultHttpClient) httpClient).getCredentialsProvider().setCredentials(authScope, creds);
						trying = true;
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
		return htmlStr;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setQueueLength(int queueLength) {
		this.queueLength = queueLength;
	}

	public void setAuthenticationDescriptor(AuthenticationDescriptor authenticationDescriptor) {
		this.authenticationDescriptor = authenticationDescriptor;
	}

	public void setTaskDescriptor(TaskDescriptor taskDescriptor) {
		this.taskDescriptor = taskDescriptor;
	}

	public void setHttpClientProvider(HttpClientProvider httpClientProvider) {
		this.httpClientProvider = httpClientProvider;
	}

	public boolean isDone() {
		return done;
	}

	public Queue<AtomicTask> getTaskQueue() {
		return taskQueue;
	}

	public Set<String> getContentTypes() {
		return contentTypes;
	}

	public void setContentTypes(Set<String> contentTypes) {
		this.contentTypes = contentTypes;
	}

	public void setReplaceExistFile(boolean replaceExistFile) {
		this.replaceExistFile = replaceExistFile;
	}
}
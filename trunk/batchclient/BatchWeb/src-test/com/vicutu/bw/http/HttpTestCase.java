package com.vicutu.bw.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.bw.http.utils.HttpUtils;
import com.vicutu.bw.http.utils.HtmlUtils;
import com.vicutu.commons.exception.BaseRuntimeException;
import com.vicutu.commons.test.LoggedSpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "ctx-http-test.xml" })
@RunWith(LoggedSpringJUnit4ClassRunner.class)
public class HttpTestCase extends AbstractJUnit4SpringContextTests {

	private static final String TEST_URL = "http://www.apache.org";

	private HttpClient httpClient;

	@Autowired
	@Qualifier("defaultHttpClient")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Test
	public void test_html() throws Exception {
		logger.info(HttpUtils.downloadHtml(httpClient, TEST_URL));

	}

	@Test
	public void test_images() throws Exception {
		String html = HttpUtils.downloadHtml(httpClient, TEST_URL);
		List<String> images = HtmlUtils.selectAllImage(html);
		for (String image : images) {
			logger.info(image);
		}
	}

	@Test
	public void test_hrefs() throws Exception {
		String html = HttpUtils.downloadHtml(httpClient, TEST_URL);
		List<String> hrefs = HtmlUtils.selectAllHREF(html);
		for (String href : hrefs) {
			logger.info(href);
		}
	}

	@Test
	public void test_jpgs() throws Exception {
		String html = HttpUtils.downloadHtml(httpClient, TEST_URL);
		List<String> jpgs = HtmlUtils.selectAllJPG(html);
		for (String jpg : jpgs) {
			logger.info(jpg);
		}
	}

	@Test
	public void test_abs() throws Exception {
		String html = HttpUtils.downloadHtml(httpClient, TEST_URL);
		List<String> hrefs = HtmlUtils.selectAsString(html, TEST_URL, "a[href]", "abs:href");
		for (String href : hrefs) {
			logger.info(href);
		}
	}
	
	@Test
	public void test_login()throws Exception {
		final String loginUrl = "http://www.gips-alpin.com/src/en/checkpwd.php";
		final String loginRefreshUrl = "http://www.gips-alpin.com/src/en/members/start.php?userid=9741";
		HttpPost httpost = new HttpPost(loginUrl);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("flduser", "dipengfei1982@gmail.com");
		parameters.put("fldpwd", "Vicutu1982");
		boolean loginResult = HttpUtils.executeLogin(httpClient, httpost, parameters, new ResponseHandler<Boolean>() {
			@Override
			public Boolean handleResponse(HttpResponse response) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

					try {
						String html = EntityUtils.toString(response.getEntity());
						logger.info(html);
						if (html.length() > 0) {
							// TODO
							return Boolean.TRUE;
						} else {
							return Boolean.FALSE;
						}
					} catch (Exception e) {
						return Boolean.FALSE;
					}
				} else {
					return Boolean.FALSE;
				}
			}
		});
		if (loginResult) {
			HttpGet httpget = new HttpGet(loginRefreshUrl);
			HttpResponse response = httpClient.execute(httpget);
			response.getEntity().consumeContent();
		} else {
			throw new BaseRuntimeException("log in failed");
		}
		logger.info(HttpUtils.downloadHtml(httpClient, "http://www.gips-alpin.com/src/en/listshootings.php"));
	}

	@After
	public void clean_up() {
		if (httpClient != null) {
			httpClient.getConnectionManager().shutdown();
			logger.info("http client has been shut down");
		}
	}
}
